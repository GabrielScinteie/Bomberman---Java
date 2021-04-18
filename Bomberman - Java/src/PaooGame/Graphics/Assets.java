package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage heroStop;
    public static BufferedImage heroUp;
    public static BufferedImage heroDown;
    public static BufferedImage grass;
    public static BufferedImage wall;
    public static BufferedImage enemy;
    public static BufferedImage destructableWall;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/BombermanSpriteSheet.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 4);
        wall = sheet.crop(3,3);
        heroStop = sheet.crop(0,2);
        heroLeft = sheet.crop(0, 0);
        heroRight = sheet.crop(0, 1);
        heroUp = sheet.crop(4,1);
        heroDown = sheet.crop(3,0);
        destructableWall = sheet.crop(4,3);
        enemy = sheet.crop(0,18);
    }
}
