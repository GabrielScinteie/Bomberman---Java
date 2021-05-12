package PaooGame.Explosion;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {
    private int x;
    private int y;
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    public static int lifeTime = 1; /* Timpul de viata a exploziei*/
    private double startTime;
    private int partOfAnimation; /* partOfAnimation = 0 <-> the first animation of explosion
                                    partOfAnimation = 1 <-> the second part of explosion
                                    */

    public static BufferedImage[][]animation = Assets.fire; /*Retine sprite-ul de inceput*/;
    public static int [][]fire;       /* fire[i][j] = 1 <-> este foc pe tile-ul [i][j]*/
    public static int fireHeight, fireWidth;

    public static final int DEFAULT_EXPLOSION_WIDTH = 32;
    public static final int DEFAULT_EXPLOSION_HEIGHT = 32;


    public static void initFireMap(RefLinks refLink){
        fireWidth = refLink.GetMap().getWidth();
        fireHeight = refLink.GetMap().getHeight();
        fire = new int[refLink.GetMap().getWidth()][refLink.GetMap().getHeight()];
        for(int i = 0; i < fireWidth; i++)
            for(int j = 0; j < fireHeight; j++)
                fire[i][j] = 0;
    }

    // coordonatele din centrul exploziei
    public Explosion(RefLinks refLink, int x, int y)
    {

        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = DEFAULT_EXPLOSION_WIDTH;     /*!< Retine latimea imaginii.*/
        this.height = DEFAULT_EXPLOSION_HEIGHT;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/
        partOfAnimation = 0;
        startTime = System.nanoTime();
        ///Creaza dreptunghi de coliziune pentru modul normal, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        bounds = new Rectangle(0, 0, width, height);

        fire[x/32][y/32] = 9;
        Tile up1    = refLink.GetMap().GetTile(x/32, (y - DEFAULT_EXPLOSION_HEIGHT)/32);
        Tile up2    = refLink.GetMap().GetTile(x/32, (y - 2 * DEFAULT_EXPLOSION_HEIGHT)/32);
        Tile right1 = refLink.GetMap().GetTile((x + DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile right2 = refLink.GetMap().GetTile((x + 2 * DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile down1  = refLink.GetMap().GetTile(x/32, (y + DEFAULT_EXPLOSION_WIDTH)/32);
        Tile down2  = refLink.GetMap().GetTile(x/32, (y + 2 * DEFAULT_EXPLOSION_WIDTH)/32);
        Tile left1  = refLink.GetMap().GetTile((x - DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile left2  = refLink.GetMap().GetTile((x - 2 * DEFAULT_EXPLOSION_WIDTH)/32, y/32);

        if(up1.GetId() == 2 || up1.GetId() == 0){
            fire[x/32][(y - DEFAULT_EXPLOSION_HEIGHT)/32] = 1;
            refLink.GetMap().SetTile(x/32,(y - DEFAULT_EXPLOSION_HEIGHT)/32,0);

            if(up2.GetId() == 2 || up2.GetId() == 0){
                fire[x/32][(y - 2 * DEFAULT_EXPLOSION_HEIGHT)/32] = 2;
                refLink.GetMap().SetTile(x/32,(y - 2 * DEFAULT_EXPLOSION_HEIGHT)/32,0);
            }
        }

        if(right1.GetId() == 2 || right1.GetId() == 0){
            fire[(x + 32)/32][y/32] = 3;
            refLink.GetMap().SetTile((x + DEFAULT_EXPLOSION_WIDTH)/32, y/32,0);

            if(right2.GetId() == 2 || right2.GetId() == 0){
                fire[(x + 2*32)/32][y/32] = 4;
                refLink.GetMap().SetTile((x + 2 * DEFAULT_EXPLOSION_WIDTH)/32, y/32,0);
            }
        }

        if(down1.GetId() == 2 || down1.GetId() == 0){
            fire[x/32][(y + 32)/32] = 5;
            refLink.GetMap().SetTile(x/32,(y + DEFAULT_EXPLOSION_HEIGHT)/32,0);

            if(down2.GetId() == 2 || down2.GetId() == 0){
                fire[x/32][(y + 2*32)/32] = 6;
                refLink.GetMap().SetTile(x/32,(y + 2*  DEFAULT_EXPLOSION_HEIGHT)/32,0);
            }
        }

        if(left1.GetId() == 2 || left1.GetId() == 0){
            fire[(x-32)/32][y/32] = 7;
            refLink.GetMap().SetTile((x - DEFAULT_EXPLOSION_WIDTH)/32, y/32,0);
            if(left2.GetId() == 2 || left2.GetId() == 0){
                fire[(x - 2 * 32)/32][y/32] = 8;
                refLink.GetMap().SetTile((x - 2* DEFAULT_EXPLOSION_WIDTH)/32, y/32,0);
            }
        }

    }

    public double getTime(){return (System.nanoTime() - startTime) / 1000000000;}
    public int getPartOfAnimation(){return getPartOfAnimation();}

    public void Update(){


    }

    public void Draw(Graphics g)
    {

    }

    public void clean(){
        Tile up1    = refLink.GetMap().GetTile(x/32, (y - DEFAULT_EXPLOSION_HEIGHT)/32);
        Tile up2    = refLink.GetMap().GetTile(x/32, (y - 2 * DEFAULT_EXPLOSION_HEIGHT)/32);
        Tile right1 = refLink.GetMap().GetTile((x + DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile right2 = refLink.GetMap().GetTile((x + 2 * DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile down1  = refLink.GetMap().GetTile(x/32, (y + DEFAULT_EXPLOSION_WIDTH)/32);
        Tile down2  = refLink.GetMap().GetTile(x/32, (y + 2 * DEFAULT_EXPLOSION_WIDTH)/32);
        Tile left1  = refLink.GetMap().GetTile((x - DEFAULT_EXPLOSION_WIDTH)/32, y/32);
        Tile left2  = refLink.GetMap().GetTile((x - 2 * DEFAULT_EXPLOSION_WIDTH)/32, y/32);

        fire[x/32][y/32] = 0;

        if(up1.GetId() == 2 || up1.GetId() == 0){
            fire[x/32][(y - DEFAULT_EXPLOSION_HEIGHT)/32] = 0;

            if(up2.GetId() == 2 || up2.GetId() == 0){
                fire[x/32][(y - 2 * DEFAULT_EXPLOSION_HEIGHT)/32] = 0;

            }
        }

        if(right1.GetId() == 2 || right1.GetId() == 0){
            fire[(x + 32)/32][y/32] = 0;

            if(right2.GetId() == 2 || right2.GetId() == 0){
                fire[(x + 2*32)/32][y/32] = 0;

            }
        }

        if(down1.GetId() == 2 || down1.GetId() == 0){
            fire[x/32][(y + 32)/32] = 0;

            if(down2.GetId() == 2 || down2.GetId() == 0){
                fire[x/32][(y + 2*32)/32] = 0;

            }
        }

        if(left1.GetId() == 2 || left1.GetId() == 0){
            fire[(x-32)/32][y/32] = 0;

            if(left2.GetId() == 2 || left2.GetId() == 0){
                fire[(x - 2 * 32)/32][y/32] = 0;

            }
        }
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
    public void SetX(int x)
    {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
     */
    public void SetY(int y)
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
