package PaooGame.Items;
import PaooGame.Explosion.Explosion;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class EnemiesManager {
    private static int lvl;
    private static List<Enemy> allEnemies = new ArrayList<Enemy>();
    private static Integer numberOfEnemies = 0;
    public static List<Enemy> getAllEnemies(){return allEnemies;}


    public static void initEnemies(RefLinks refLinks){
        if(lvl == 1) {

            allEnemies.add(new Enemy(refLinks, 15 * 32, 3 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 6 * 32, 6 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 7 * 32, 27 * 32));
            numberOfEnemies++;



            allEnemies.add(new Enemy(refLinks, 2 * 32, 17 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 10 * 32, 17 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 19 * 32, 14 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 24 * 32, 15 * 32));
            numberOfEnemies++;


            allEnemies.add(new Enemy(refLinks, 19 * 32, 22 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 4 * 32, 23 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 17 * 32, 9 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 12 * 32, 5 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 32 * 32, 14 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 26 * 32, 21 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 19 * 32, 27 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 14 * 32, 10 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 2 * 32, 22 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 22 * 32, 25 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 14 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 26 * 32, 16 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 39 * 32, 4 * 32));
            numberOfEnemies++;


            for(int i = 0; i < allEnemies.size(); i++){
                allEnemies.get(i).setSpeed((float)0.75);
            }


        }
        else
        {
            allEnemies.add(new Enemy(refLinks, 9 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 6 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 30 * 32, 21 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 47 * 32, 11 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 11 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 15 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 19 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 23 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 57 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 49 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 49 * 32, 19 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 49 * 32, 15 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 49 * 32, 7 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 49 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 2 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 12 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 24 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 29 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 3 * 32, 8 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 3 * 32, 11 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 13 * 32, 11 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 19 * 32, 11 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 23 * 32, 11 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 20 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 15 * 32, 27 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 37 * 32, 2 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 37 * 32, 11 * 32));
            numberOfEnemies++;
            allEnemies.add(new Enemy(refLinks, 19 * 32, 27 * 32));
            numberOfEnemies++;

            allEnemies.add(new Enemy(refLinks, 19 * 32, 22 * 32));
            numberOfEnemies++;

            for(int i = 0; i < allEnemies.size(); i++){
                allEnemies.get(i).setSpeed((float)1.5);
            }




        }
    }

    public static void Update(){
       for(int i = 0; i < allEnemies.size(); i++)
       {
           allEnemies.get(i).Update();
           if(allEnemies.get(i).isDead())
           {
                allEnemies.remove(i);
                i--;
                numberOfEnemies--;
           }

       }


    }

    public static void clean(){
        while(allEnemies.size() != 0){
            allEnemies.remove(0);
        }
        numberOfEnemies = 0;
    }

    public static void Draw(Graphics g){
        for(int i = 0; i < allEnemies.size(); i++)
            allEnemies.get(i).Draw(g);
        g.drawImage(Assets.enemy.getScaledInstance(32,32,Image.SCALE_DEFAULT), 2* 32,0,null);
        g.setFont(new Font("arial",Font.BOLD,40));
        g.setColor(Color.black);
        g.drawString(numberOfEnemies.toString(),3*32,32);
    }

    public static void setLevel(int x){lvl = x;}
    public static int getLevel(){return lvl;}
}
