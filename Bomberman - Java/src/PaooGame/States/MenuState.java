package PaooGame.States;

import PaooGame.Game;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{

    Font titleFont;
    Font buttonFont;
    public Rectangle playButton;
    public Rectangle settingsButton;
    public Rectangle quitButton;
    public Rectangle aboutButton;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);

        time = System.currentTimeMillis();
        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        titleFont = new Font("arial", Font.BOLD,80);
        buttonFont = new Font("arial",Font.BOLD, 40);
        playButton = new Rectangle(GameWidth/10*4, GameHeight/8*3,GameWidth/5, GameHeight/12);
        settingsButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 4,GameWidth/5, GameHeight/12);
        aboutButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 5,GameWidth/5, GameHeight/12);
        quitButton = new Rectangle(GameWidth/10*4, GameHeight/8 * 6,GameWidth/5, GameHeight/12);


    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        //System.out.println(refLink.GetGame().GetMouseManager().isClicked());
       if(System.currentTimeMillis() - time > 400)
       {
           if(refLink.GetGame().GetMouseManager().isClicked())
           {
               int mx =  refLink.GetGame().GetMouseManager().getMouseX();
               int my =  refLink.GetGame().GetMouseManager().getMouseY();
               Game game = refLink.GetGame();
               Point p = new Point(mx,my);
               //System.out.println(p.x + " " + p.y);

               if(State.GetState().getClass().getSimpleName().equals("MenuState")){

                   if(playButton.contains(p)){
                       State.SetState(game.getChooseLevelState());
                       game.getGameWnd().GetWndFrame().requestFocusInWindow();
                   }

                   if(settingsButton.contains(p)){
                       State.SetState(game.getSettingsState());
                   }

                   if(aboutButton.contains(p)){
                       State.SetState(game.getAboutState());
                   }

                   if(quitButton.contains(p)){
                       System.exit(-1);
                   }
               }

               if(State.GetState().getClass().getSimpleName().equals("SettingsState")){

               }

               if(State.GetState().getClass().getSimpleName().equals("PlayState")){
                   System.out.println("Sunt in joc dar apas pe mouse");
               }
           }
       }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

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
        g.setColor(Color.WHITE);
        g.drawString("BomberBoy", refLink.GetGame().GetWidth()/4 + 30,refLink.GetGame().GetHeight()/4 + 30);

        g2d.setColor(Color.WHITE);
        g2d.draw(playButton);
        g2d.draw(settingsButton);
        g2d.draw(quitButton);
        g2d.draw(aboutButton);
        //g2d.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
        //g2d.fillRect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
        //g2d.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);


        g.setFont(buttonFont);
        g.setColor(Color.WHITE);
        g.drawString("Play",playButton.x + playButton.width/10*3,playButton.y + playButton.height/10*8);
        g.drawString("Settings",settingsButton.x + settingsButton.width/10,settingsButton.y + settingsButton.height/10*8);
        g.drawString("About",aboutButton.x + aboutButton.width/10*2,aboutButton.y + aboutButton.height/10*8);
        g.drawString("Quit",quitButton.x + quitButton.width/10*3,quitButton.y + quitButton.height/10*8);


    }
}
