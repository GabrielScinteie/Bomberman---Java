package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EndState extends State{
    private long time;
    Font buttonFont;
    Font titleFont;
    Font writingFont;

    public Rectangle menuButton;

    /*! \fn public YouLostState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public EndState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        time = System.currentTimeMillis();

        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        writingFont = new Font("arial",Font.BOLD,30);
        buttonFont = new Font("arial",Font.BOLD,40);
        titleFont = new Font("arial",Font.BOLD,80);
        menuButton = new Rectangle(GameWidth/10*4 - GameWidth/50, GameHeight/8*6,GameWidth/4, GameHeight/12);

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        if(System.currentTimeMillis() - time > 100){
            if(refLink.GetGame().GetMouseManager().isClicked()){
                int mx =  refLink.GetGame().GetMouseManager().getMouseX();
                int my =  refLink.GetGame().GetMouseManager().getMouseY();
                Game game = refLink.GetGame();
                Point p = new Point(mx,my);
                if(menuButton.contains(p)){
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

        Image bomberboy = Assets.heroRight.getScaledInstance(refLink.GetGame().GetWidth()/6,refLink.GetGame().GetHeight()/6,Image.SCALE_DEFAULT);
        g.drawImage(bomberboy,refLink.GetGame().GetWidth()/2 - 200, refLink.GetGame().GetHeight()/2 + 30,null);

        Image door = Assets.door.getScaledInstance(refLink.GetGame().GetWidth()/6,refLink.GetGame().GetHeight()/6,Image.SCALE_DEFAULT);
        g.drawImage(door,refLink.GetGame().GetWidth()/2, refLink.GetGame().GetHeight()/2 + 30,null);

        /*Image bomberboy = Assets.heroRight.getScaledInstance(refLink.GetGame().GetWidth()/4,refLink.GetGame().GetHeight()/4,Image.SCALE_DEFAULT);
        g.drawImage(bomberboy,refLink.GetGame().GetWidth()/10*8 - 60, refLink.GetGame().GetHeight()/10*4 + 10,null);

        Image enemy = Assets.enemy.getScaledInstance(refLink.GetGame().GetWidth()/5,refLink.GetGame().GetHeight()/5,Image.SCALE_DEFAULT);
        g.drawImage(enemy,refLink.GetGame().GetWidth()/16, refLink.GetGame().GetHeight()/10*4 - 20,null);
        g.drawImage(enemy,refLink.GetGame().GetWidth()/6, refLink.GetGame().GetHeight()/10*6,null);

        Image bomb = Assets.bomb[1].getScaledInstance(refLink.GetGame().GetWidth()/6,refLink.GetGame().GetHeight()/6,Image.SCALE_DEFAULT);
        g.drawImage(bomb,refLink.GetGame().GetWidth()/10*6 + 10, refLink.GetGame().GetHeight()/10*5,null);
        */

        Image destructableWall = Assets.destructableWall.getScaledInstance(refLink.GetGame().GetWidth()/8,refLink.GetGame().GetHeight()/8,Image.SCALE_DEFAULT);

        for(int i = 0; i < 8; i++){
            g.drawImage(destructableWall,refLink.GetGame().GetWidth()/8*i, refLink.GetGame().GetHeight()/8*7,null);
        }
        for(int i = 0; i < 8; i++){
            g.drawImage(destructableWall,refLink.GetGame().GetWidth()/8*i, 0,null);
        }



        g.setFont(buttonFont);
        g.setColor(Color.WHITE);
        g2d.draw(menuButton);
        g.drawString("Main Menu", menuButton.x + menuButton.width / 12, menuButton.y + menuButton.height/4*3);

        g.setFont(titleFont);
        g.setColor(Color.YELLOW);
        g.drawString("Congratulations!", refLink.GetWidth()/5 - 30, refLink.GetHeight()/4+30);

        g.setFont(writingFont);
        g.setColor(Color.red);
        g.drawString("You have helped BomberBoy escape the evil game!", refLink.GetWidth()/8, refLink.GetHeight()/3 + 60);
        g.drawString("Go check the Highscores and try to be the best!",refLink.GetWidth()/8 + 30, refLink.GetHeight()/3 + 100);
    }
}
