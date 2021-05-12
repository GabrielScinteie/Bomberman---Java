package PaooGame.Bombs;

import PaooGame.Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Items.Hero;
import PaooGame.RefLinks;

public class Bomb {
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private BufferedImage []animation;        /*!< Referinta catre imaginea curenta a bombei.*/
    private BufferedImage image;
    private double startTime;
    private boolean playerInside; /* playerInside = true -> player-ul a plantat bomba, dar nu a iesit din raza ei*/
    private static final double lifetime = 3;
    public static final int DEFAULT_BOMB_WIDTH  = 32;   /*!< Latimea implicita a imaginii bombei.*/
    public static final int DEFAULT_BOMB_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii bombei.*/

    public Bomb(RefLinks refLink, float x, float y)
    {
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = DEFAULT_BOMB_WIDTH;     /*!< Retine latimea imaginii.*/
        this.height = DEFAULT_BOMB_HEIGHT;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/
        this.animation = Assets.bomb; /*Retine sprite-ul de inceput*/
        this.image = this.animation[0];
        startTime = System.nanoTime();
        playerInside = true;
        ///Creaza dreptunghi de coliziune pentru modul normal, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        bounds = new Rectangle(0, 0, width, height);
    }

    public void Update(){
        long nanosInSecond = 1000000000;
        if((System.nanoTime() - startTime) / nanosInSecond  < 0.5)
            image = animation[0];
        if((System.nanoTime() - startTime) / nanosInSecond  >= 0.5 && (System.nanoTime() - startTime) / nanosInSecond < 1)
            image = animation[1];
        if((System.nanoTime() - startTime) / nanosInSecond  >= 1 && (System.nanoTime() - startTime) / nanosInSecond < 1.5)
            image = animation[0];
        if((System.nanoTime() - startTime) / nanosInSecond  >= 1.5 && (System.nanoTime() - startTime) / nanosInSecond < 2)
            image = animation[2];
        if((System.nanoTime() - startTime) / nanosInSecond  >= 2 && (System.nanoTime() - startTime) / nanosInSecond < 2.5)
            image = animation[0];
        if((System.nanoTime() - startTime) / nanosInSecond  >= 2.5 && (System.nanoTime() - startTime) / nanosInSecond < 3)
            image = animation[2];
        //if((System.nanoTime() - startTime) / nanosInSecond  >= 3 && (System.nanoTime() - startTime) / nanosInSecond < 3.5)
            //image = animation[2];
        // Odata ce player-ul a iesit din raza bombei, el nu se va mai putea intoarce <-> playerInside devine false
        if(playerInside == true){
            Hero hero = refLink.GetHero();
            Rectangle temp1 = new Rectangle(hero.getBounds());
            Rectangle temp2 = new Rectangle(bounds);
            temp1.x += hero.GetX();
            temp1.y += hero.GetY();
            temp2.x += x;
            temp2.y += y;

            if(temp1.intersects(temp2) == false){
                playerInside = false;
            }
        }
    }

    // Returns the time elapsed from the creation of the bomb
    public double getTime(){return (System.nanoTime() - startTime) / 1000000000;}
    public static double getLifetime(){return lifetime;}
    public boolean isPlayerInside(){return playerInside;}
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)(x - refLink.GetCamera().getRect().x), (int)(y-refLink.GetCamera().getRect().y), width*6/8, height*6/8, null);

    }

    public Rectangle getBounds(){ return bounds;}

    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX()
    {
        return x;
    }

    /*! \fn public float GetY()
        brief Returneaza coordonata pe axa Y.
     */
    public float GetY()
    {
        return y;
    }

    /*! \fn public float GetWidth()
        \brief Returneaza latimea entitatii.
     */
    public int GetWidth()
    {
        return width;
    }

    /*! \fn public float GetHeight()
        \brief Returneaza inaltimea entitatii.
     */
    public int GetHeight()
    {
        return height;
    }

    /*! \fn public float SetX()
        \brief Seteaza coordonata pe axa X.
     */
    public void SetX(float x)
    {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
     */
    public void SetY(float y)
    {
        this.y = y;
    }

    /*! \fn public float SetWidth()
        \brief Seteaza latimea imaginii entitatii.
     */
    public void SetWidth(int width)
    {
        this.width = width;
    }

    /*! \fn public float SetHeight()
        \brief Seteaza inaltimea imaginii entitatii.
     */
    public void SetHeight(int height)
    {
        this.height = height;
    }
}
