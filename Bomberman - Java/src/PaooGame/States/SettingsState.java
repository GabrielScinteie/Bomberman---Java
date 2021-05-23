package PaooGame.States;

import PaooGame.Bombs.BombManager;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Items.EnemiesManager;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{

    String soundOn = "ON";

    Font buttonFont;
    Font buttonFont2;
    Font titleFont;

    public Rectangle backButton;
    public Rectangle soundButton;

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);

        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        titleFont = new Font("arial", Font.BOLD,80);
        buttonFont = new Font("arial",Font.BOLD,35);
        buttonFont2 = new Font("arial",Font.BOLD,40);
        soundButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 4,GameWidth/5, GameHeight/12);
        backButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 6,GameWidth/5, GameHeight/12);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        if(System.currentTimeMillis() - time > 200)
        {
            if(refLink.GetGame().GetMouseManager().isClicked())
            {
                int mx =  refLink.GetGame().GetMouseManager().getMouseX();
                int my =  refLink.GetGame().GetMouseManager().getMouseY();
                Game game = refLink.GetGame();
                Point p = new Point(mx,my);
                //System.out.println(p.x + " " + p.y);

                if(backButton.contains(p)){
                    State.SetState(game.getMenuState());
                    game.getGameWnd().GetWndFrame().requestFocusInWindow();
                }

                if(soundButton.contains(p)){
                    if(soundOn == "ON")
                    {
                        soundOn = "OFF";
                        refLink.GetGame().getAudio().stopMusic();
                    }

                    else
                    {
                        soundOn = "ON";
                        refLink.GetGame().getAudio().startMusic();
                    }

                }
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

        BufferedImage backgroundImage = Assets.grass;
        Image background = backgroundImage.getScaledInstance(refLink.GetGame().GetWidth(),refLink.GetGame().GetHeight(),Image.SCALE_DEFAULT);
        g.drawImage(background,0,0,null);

        Image bomberboy = Assets.heroRight.getScaledInstance(refLink.GetGame().GetWidth()/4,refLink.GetGame().GetHeight()/4,Image.SCALE_DEFAULT);
        g.drawImage(bomberboy,refLink.GetGame().GetWidth()/10*8 - 60, refLink.GetGame().GetHeight()/10*4 + 10,null);

        Image enemy = Assets.enemy.getScaledInstance(refLink.GetGame().GetWidth()/5,refLink.GetGame().GetHeight()/5,Image.SCALE_DEFAULT);
        g.drawImage(enemy,refLink.GetGame().GetWidth()/16, refLink.GetGame().GetHeight()/10*4 - 20,null);
        g.drawImage(enemy,refLink.GetGame().GetWidth()/6, refLink.GetGame().GetHeight()/10*6,null);

        Image bomb = Assets.bomb[1].getScaledInstance(refLink.GetGame().GetWidth()/6,refLink.GetGame().GetHeight()/6,Image.SCALE_DEFAULT);
        g.drawImage(bomb,refLink.GetGame().GetWidth()/10*6 + 10, refLink.GetGame().GetHeight()/10*5,null);

        Image destructableWall = Assets.destructableWall.getScaledInstance(refLink.GetGame().GetWidth()/8,refLink.GetGame().GetHeight()/8,Image.SCALE_DEFAULT);

        for(int i = 0; i < 8; i++){
            g.drawImage(destructableWall,refLink.GetGame().GetWidth()/8*i, refLink.GetGame().GetHeight()/8*7,null);
        }
        for(int i = 0; i < 8; i++){
            g.drawImage(destructableWall,refLink.GetGame().GetWidth()/8*i, 0,null);
        }

        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("BomberBoy", refLink.GetGame().GetWidth()/4 + 30,refLink.GetGame().GetHeight()/4 + 30);

        g.setFont(buttonFont2);
        g.setColor(Color.WHITE);

        g.drawString("Back",backButton.x + backButton.width/4, backButton.y + backButton.height/10*8);

        g.setFont(buttonFont);
        g.drawString("Sound:" + soundOn,soundButton.x + 8, soundButton.y + soundButton.height/10*8);

        g2d.draw(soundButton);
        g2d.draw(backButton);
    }
}
