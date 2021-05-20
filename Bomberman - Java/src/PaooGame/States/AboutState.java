package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{

    Font buttonFont;
    Font titleFont;
    Font writingFont;
    public Rectangle backButton;
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);

        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        titleFont = new Font("arial", Font.BOLD,80);
        buttonFont = new Font("arial",Font.BOLD,40);
        writingFont = new Font("arial",Font.BOLD,20);
        backButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 6,GameWidth/5, GameHeight/12);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
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
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
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
        g.drawString("Back",backButton.x + backButton.width/4, backButton.y + backButton.height/10*8);

        g.setFont(writingFont);
        g.setColor(Color.WHITE);
        g.drawString("BomberBoy is a very enthusiastic young",
                refLink.GetGame().GetWidth()/3 - 30, refLink.GetGame().GetHeight()/3 + 40);

        g.drawString("gamer who got trapped in his brand new",
                refLink.GetGame().GetWidth()/3 - 30, refLink.GetGame().GetHeight()/3 + 60);

        g.drawString("game due to an unexpected malfunction.",
                refLink.GetGame().GetWidth()/3 - 30, refLink.GetGame().GetHeight()/3 + 80);
        g.drawString("Help him get out by defeating all",
                refLink.GetGame().GetWidth()/3 , refLink.GetGame().GetHeight()/3 + 100);
        g.drawString("the monsters he encounters!",
                refLink.GetGame().GetWidth()/3 + 20, refLink.GetGame().GetHeight()/3 + 120);
        g.drawString("W - up",
                refLink.GetGame().GetWidth()/2 - 40, refLink.GetGame().GetHeight()/2 + 40);
        g.drawString("S - down",
                refLink.GetGame().GetWidth()/2 - 48, refLink.GetGame().GetHeight()/2 + 60);
        g.drawString("A - left",
                refLink.GetGame().GetWidth()/2 - 40, refLink.GetGame().GetHeight()/2 + 80);
        g.drawString("D - right",
                refLink.GetGame().GetWidth()/2 - 40, refLink.GetGame().GetHeight()/2 + 100);
        g.drawString("ESC - escape",
                refLink.GetGame().GetWidth()/2 - 60, refLink.GetGame().GetHeight()/2 + 140);
        g.drawString("SPACE - bomb",
                refLink.GetGame().GetWidth()/2 - 70, refLink.GetGame().GetHeight()/2 + 120);



        g2d.draw(backButton);
    }
}
