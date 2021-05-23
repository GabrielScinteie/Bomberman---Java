package PaooGame.Graphics;

import java.awt.*;
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
    public static BufferedImage heroDead;
    public static BufferedImage grass;
    public static BufferedImage wall;
    public static BufferedImage door;
    public static BufferedImage enemy;
    public static BufferedImage enemy2;
    public static BufferedImage destructableWall;
    public static BufferedImage []bomb = new BufferedImage[3];
    public static Image heart;
    public static BufferedImage [][]fire = new BufferedImage[9][4]; // linia 0 <-> centru
                                                                    // linia 1 <-> sus
                                                                    // linia 2 <-> dreapta
                                                                    // linia 3 <-> jos
                                                                    // linia 4 <-> stanga

    public static BufferedImage clock;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/BombermanSpriteSheet.png"));
        SpriteSheet heartSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/heart.png"));
        SpriteSheet clockSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/clock.png"));

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
        enemy2 = sheet.crop(3,18); // enemy looking left
        door = sheet.crop(11,3);
        heroDead = sheet.crop(2,2);
        bomb[0] = sheet.crop(2,3); // animatia 1 a exploziei
        bomb[1] = sheet.crop(1,3); // animatia 2 a exploziei
        bomb[2] = sheet.crop(0,3); // animatia 3 a exploziei
        fire[0][0] = sheet.crop(2,6);
        fire[1][0] = sheet.crop(2,5);
        fire[2][0] = sheet.crop(2,4);
        fire[3][0] = sheet.crop(3,6);
        fire[4][0] = sheet.crop(4,6);
        fire[5][0] = sheet.crop(2,7);
        fire[6][0] = sheet.crop(2,8);
        fire[7][0] = sheet.crop(1,6);
        fire[8][0] = sheet.crop(0,6);
        //heart = heartSheet.getSubImage(0,0,255,255).getScaledInstance(32,32, Image.SCALE_DEFAULT);
        heart = heartSheet.getSubImage(0,0,673,601);
        clock = clockSheet.getSubImage(0,0,268,512);

    }
}
