package PaooGame.States;

import PaooGame.Camera;
import PaooGame.Items.EnemiesManager;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

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

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = new Hero(refLink,64, 64);
        refLink.SetHero(hero);
        Rectangle2D.Double cameraRect = new Rectangle2D.Double(0,0,refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight());
        camera = new Camera(refLink, cameraRect);
        refLink.SetCamera(camera);
        EnemiesManager.initEnemies(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();
        camera.Update();
        EnemiesManager.Update();
        hero.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);
        EnemiesManager.Draw(g);
    }
}
