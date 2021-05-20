package PaooGame.States;

import PaooGame.Bombs.BombManager;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Items.EnemiesManager;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class ChooseLevelState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class ChooseLevelState extends State
{

    Font buttonFont;
    Font titleFont;
    public Rectangle firstLvlButton;
    public Rectangle secondLvlButton;
    public Rectangle backButton;
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ChooseLevelState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);

        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        titleFont = new Font("arial", Font.BOLD,80);
        buttonFont = new Font("arial",Font.BOLD,40);

        firstLvlButton = new Rectangle(GameWidth/10*4, GameHeight/8*3,GameWidth/5, GameHeight/12);
        secondLvlButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 4,GameWidth/5, GameHeight/12);
        backButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 5,GameWidth/5, GameHeight/12);
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

                if (firstLvlButton.contains(p)) {
                    // Curat nivelul de elementele ce nu voi disparea odata cu referinta la PlayState
                    EnemiesManager.clean();
                    BombManager.clean();
                    ExplosionManager.clean();

                    PlayState playState = new PlayState(refLink,1);
                    State.SetState(playState);
                    game.setPlayState(playState);
                    game.getGameWnd().GetWndFrame().requestFocusInWindow();

                }

                if(secondLvlButton.contains(p)){

                    // Curat nivelul de elementele ce nu voi disparea odata cu referinta la PlayState
                    EnemiesManager.clean();
                    BombManager.clean();
                    ExplosionManager.clean();

                    PlayState playState = new PlayState(refLink,2);
                    State.SetState(playState);
                    game.setPlayState(playState);
                    game.getGameWnd().GetWndFrame().requestFocusInWindow();
                }

                if(backButton.contains(p)){
                    State.SetState(game.getMenuState());
                    game.getGameWnd().GetWndFrame().requestFocusInWindow();
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

        g.setFont(buttonFont);
        g.setColor(Color.WHITE);
        g.drawString("Lvl 1",firstLvlButton.x + firstLvlButton.width/10*3,firstLvlButton.y + firstLvlButton.height/10*8);
        g.drawString("Lvl 2",secondLvlButton.x + secondLvlButton.width/10*3,secondLvlButton.y + secondLvlButton.height/10*8);
        g.drawString("Back",backButton.x + backButton.width/4, backButton.y + backButton.height/10*8);

        g2d.draw(firstLvlButton);
        g2d.draw(secondLvlButton);
        g2d.draw(backButton);
    }
}

