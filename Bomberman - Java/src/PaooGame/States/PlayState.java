package PaooGame.States;

import PaooGame.Bombs.BombManager;
import PaooGame.Camera;
import PaooGame.Exceptions.WrongMapNumberException;
import PaooGame.Explosion.Explosion;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Door;
import PaooGame.Items.EnemiesManager;
import PaooGame.Items.Hero;
import PaooGame.Maps.MapsCreator;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Subscribers.LifeUI;
import PaooGame.db.DatabaseOperations;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Door door;  /*<Referinta catre obiectul de tip usa*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private Camera camera; /*!< Referinta catre camera ce cuprinde dreptunghiul ce se vede la un moment de timp.*/
    private int level; /*!<Referinta catre numarul nivelului.*/
    private LifeUI lifeUI; /*!<Referinta catre UI care arata vietile.*/
    private long time;  /*Referinta catre o variabila ce masoara timpul.*/

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink, int lvl) {
        /// Apel al constructorului clasei de baza
        super(refLink);

        /// Setez timpul de start
        time = System.currentTimeMillis();

        /// Selectez nivelul
        level = lvl;

        // Creez harta
        MapsCreator creator = new MapsCreator(refLink);
        try {
            map = creator.getMap(level);
        } catch (WrongMapNumberException e) {
            // Daca numarul hartii este dat gresit voi folosi implicit harta numarul 1
            //System.out.println(e.getMessage());
            map = creator.getDefaultMap();
            level = 1;
        }

        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        /// Initializez harta in care voi stoca tile-uri ce sunt in flacari
        Explosion.initFireMap(refLink);

        /// Setez level-ul in EnemiesManager pentru ca fiecare nivel are inamicii la propriile coordonate
        EnemiesManager.setLevel(level);

        /// Initializez inamicii
        EnemiesManager.initEnemies(refLink);

        if (level == 1) {
            door = Door.getInstance();
            Door.Init(refLink,57 * 32, 27 * 32,32,32);

        }
        else
        {
            door = Door.getInstance();
            Door.Init(refLink,57 * 32, 27 * 32,32,32);
        }

        /// In functie de nivelul ales eroul incepe dintr-o anumita pozitie
        if (level == 1) {
            hero = Hero.getInstance(refLink, 64, 64);
            hero.SetSpeed(2);
            hero.setStartingPosition(64, 64);
        } else {
            hero = Hero.getInstance(refLink, 3 * 32, 27 * 32);
            hero.SetSpeed(2);
            hero.setStartingPosition(3 * 32, 27 * 32);
        }

        /// Referinta catre erou este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetHero(hero);

        /// Creez o instanta a clasei ce se ocupa de afisarea numarului de vieti ramase ale eroului
        lifeUI = new LifeUI();

        /// Adaug variabila ce se ocupa de afisarea numarului de vieti in lista de subscriberi ai eroului
        hero.addSubscriber(lifeUI);

        /// Initializez marimea camerei ce va urmari player-ul
        Rectangle2D.Double cameraRect = new Rectangle2D.Double(0, 0, refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight());
        camera = new Camera(refLink, cameraRect);

        /// Referinta catre camera este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetCamera(camera);

        /// Setez obiectele de tip shortcut din clasele BombManager si ExplosionManager pentru a putea folosi shortcut-urile
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

        if (Hero.getInstance(refLink,0,0).isLevelFinished() == true) {
            if (EnemiesManager.getLevel() == 1) {
                State.SetState(refLink.GetGame().getLevelCompletedState());
                refLink.GetGame().getGameWnd().GetWndFrame().requestFocusInWindow();
                int timeSpent = (int) ((System.currentTimeMillis() - time) / 1000);
                DatabaseOperations.insertRecordInHighScoreTabels("HighscoreLvl1", refLink.GetGame().getPlayerName(), timeSpent);
                DatabaseOperations.getTabelOrdered("HighscoreLvl1", "Time");
            } else {
                State.SetState(refLink.GetGame().getEndState());
                refLink.GetGame().getGameWnd().GetWndFrame().requestFocusInWindow();
                int timeSpent = (int) ((System.currentTimeMillis() - time) / 1000);
                DatabaseOperations.insertRecordInHighScoreTabels("HighscoreLvl2", refLink.GetGame().getPlayerName(), timeSpent);
                DatabaseOperations.getTabelOrdered("HighscoreLvl2", "Time");
            }

        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        map.Draw(g);
        BombManager.Draw(g);
        ExplosionManager.Draw(g);
        hero.Draw(g);
        EnemiesManager.Draw(g);
        lifeUI.Draw(g);
        door.Draw(g);

        Font timeFont = new Font("arial", Font.BOLD, 40);
        long timeSpent = (System.currentTimeMillis() - time) / 1000;
        //g.drawString("Time:" + timeSpent, refLink.GetGame().GetWidth()/2,32);
        g.drawString("" + timeSpent, 5 * 32 + 16, 32);
        g.drawImage(Assets.clock.getScaledInstance(32, 60, Image.SCALE_DEFAULT), 4 * 32 + 16, -16, null);
    }
}
