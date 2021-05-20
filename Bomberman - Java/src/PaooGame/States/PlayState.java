package PaooGame.States;

import PaooGame.Bombs.BombManager;
import PaooGame.Camera;
import PaooGame.Exceptions.WrongMapNumberException;
import PaooGame.Explosion.Explosion;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Items.EnemiesManager;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map1;
import PaooGame.Maps.MapsCreator;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Subscribers.LifeUI;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Graphics.Assets.enemy;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private Camera camera; /*!< Referinta catre camera ce cuprinde dreptunghiul ce se vede la un moment de timp.*/
    private int level; /*!<Referinta catre numarul nivelului.*/
    private LifeUI lifeUI; /*!<Referinta catre UI care arata vietile.*/

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink, int lvl)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului

        level = lvl;
        MapsCreator creator = new MapsCreator(refLink);
        try{
            map = creator.getMap(level);
        }catch(WrongMapNumberException e){
            // Daca numarul hartii este dat gresit voi folosi implicit harta numarul 1
            System.out.println(e.getMessage());
            map = creator.getDefaultMap();
            level = 1;
        }

            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
        Explosion.initFireMap(refLink);
            ///Construieste eroul

        EnemiesManager.setLevel(level);
        System.out.println("Constructor PlayState");
        EnemiesManager.initEnemies(refLink);

        if(EnemiesManager.getLevel() == 1)
        {
            hero = Hero.getInstance(refLink,64, 64);
            hero.SetSpeed(2);
            hero.setStartingPosition(64,64);
        }
        else
        {
            hero = Hero.getInstance(refLink,3 * 32,27* 32);
            hero.SetSpeed(2);
            hero.setStartingPosition(3 * 32, 27 * 32);
        }

        refLink.SetHero(hero);

        lifeUI = new LifeUI();
        hero.addSubscriber(lifeUI);

        Rectangle2D.Double cameraRect = new Rectangle2D.Double(0,0,refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight());
        camera = new Camera(refLink, cameraRect);
        refLink.SetCamera(camera);

        BombManager.setRefLink(refLink);
        ExplosionManager.setRefLink(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() {

        map.Update();
        camera.Update();
        EnemiesManager.Update();
        BombManager.Update();
        ExplosionManager.Update();
        hero.Update();

        if (EnemiesManager.getAllEnemies().size() == 0) {
            State.SetState(refLink.GetGame().getLevelCompletedState());
            refLink.GetGame().getGameWnd().GetWndFrame().requestFocusInWindow();
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {

        map.Draw(g);
        BombManager.Draw(g);
        ExplosionManager.Draw(g);
        hero.Draw(g);
        EnemiesManager.Draw(g);
        lifeUI.Draw(g);

    }
}
