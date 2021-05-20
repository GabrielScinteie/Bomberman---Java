package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YouLostState extends State{

    private long time;
    Font buttonFont;
    Font titleFont;
    public Rectangle menuButton;

    /*! \fn public YouLostState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public YouLostState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        time = System.currentTimeMillis();

        int GameWidth = refLink.GetGame().GetWidth();
        int GameHeight = refLink.GetGame().GetHeight();

        buttonFont = new Font("arial",Font.BOLD,40);
        titleFont = new Font("arial",Font.BOLD,90);
        menuButton = new Rectangle(GameWidth/10*4 - GameWidth/50, GameHeight/2,GameWidth/4, GameHeight/10);

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
        BufferedImage backgroundImage = Assets.grass;
        Image background = backgroundImage.getScaledInstance(refLink.GetGame().GetWidth(),refLink.GetGame().GetHeight(),Image.SCALE_DEFAULT);
        g.drawImage(background,0,0,null);

        Graphics2D g2d = (Graphics2D)g;


        g.setFont(buttonFont);
        g.setColor(Color.WHITE);
        g2d.draw(menuButton);
        g.drawString("Main Menu", menuButton.x + menuButton.width / 12, menuButton.y + menuButton.height/4*3);

        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        g.drawString("Game Over!", refLink.GetWidth()/4, refLink.GetHeight()/3);

    }
}
