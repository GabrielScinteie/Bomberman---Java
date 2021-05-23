package PaooGame.States;

import PaooGame.Bombs.BombManager;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Items.EnemiesManager;
import PaooGame.RefLinks;
import PaooGame.db.DatabaseOperations;
import PaooGame.db.PlayerInfo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Struct;
import java.util.ArrayList;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class HighscoreState extends State
{

    private static ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
    private static ArrayList<PlayerInfo> players2 = new ArrayList<PlayerInfo>();

    Font buttonFont;
    Font buttonFont2;
    Font titleFont;

    public Rectangle backButton;
    public Rectangle soundButton;

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public HighscoreState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);

        DatabaseOperations.getTabelOrdered("HighscoreLvl1","Time");
        DatabaseOperations.getTabelOrdered("HighscoreLvl2","Time");
        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        titleFont = new Font("arial", Font.BOLD,80);
        buttonFont = new Font("arial",Font.BOLD,35);
        buttonFont2 = new Font("arial",Font.BOLD,40);
        soundButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 4,GameWidth/5, GameHeight/12);
        backButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 7,GameWidth/5, GameHeight/16);
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

        /*Image bomberboy = Assets.heroRight.getScaledInstance(refLink.GetGame().GetWidth()/4,refLink.GetGame().GetHeight()/4,Image.SCALE_DEFAULT);
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
        */

        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("BomberBoy", refLink.GetGame().GetWidth()/4 + 30,refLink.GetGame().GetHeight()/8);

        g.setFont(buttonFont2);
        g.setColor(Color.WHITE);

        g.drawString("Back",backButton.x + backButton.width/4, backButton.y + backButton.height/10*8);
        g2d.draw(backButton);

        g.drawString("Highscore Level 1",refLink.GetGame().GetWidth()/12,refLink.GetGame().GetHeight()/4 + 30);
        g.drawString("Highscore Level 2",refLink.GetGame().GetWidth()/2 + 100,refLink.GetGame().GetHeight()/4 + 30);
        g.setFont(buttonFont);
        int startingY = refLink.GetGame().GetHeight()/4 + 30;
        int startingX = refLink.GetGame().GetWidth()/10;
        for(int i = 0; i < players.size() && i < 6; i++){
            g.drawString(players.get(i).toString() + "s",startingX, startingY + 50 * (i + 1));
        }

        startingX = refLink.GetGame().GetWidth()/2 + 130;
        for(int i = 0; i < players2.size() && i < 6; i++){
            g.drawString(players2.get(i).toString() + "s",startingX, startingY + 50 * (i + 1));
        }
    }

    public static ArrayList<PlayerInfo> getPlayers(){return players;}
    public static void clearPlayers(){
        players = new ArrayList<PlayerInfo>();
    }
    public static void addPlayer(PlayerInfo x){players.add(x);}

    public static ArrayList<PlayerInfo> getPlayers2(){return players2;}
    public static void clearPlayers2(){
        players2 = new ArrayList<PlayerInfo>();
    }
    public static void addPlayer2(PlayerInfo x){players2.add(x);}
}