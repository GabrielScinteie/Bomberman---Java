package PaooGame.Items;
import PaooGame.Explosion.Explosion;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class EnemiesManager {
    private static List<Enemy> allEnemies = new ArrayList<Enemy>();

    public static List<Enemy> getAllEnemies(){return allEnemies;}
    public static void initEnemies(RefLinks refLinks){
        allEnemies.add(new Enemy(refLinks, 15*32,3 * 32));
        allEnemies.add(new Enemy(refLinks, 6*32,6 * 32));
        allEnemies.add(new Enemy(refLinks, 7*32,27 * 32));
        allEnemies.add(new Enemy(refLinks, 7*32,19 * 32));
    }

    public static void Update(){
       for(int i = 0; i < allEnemies.size(); i++)
       {
           allEnemies.get(i).Update();
           if(allEnemies.get(i).isDead() == true)
               allEnemies.remove(i);
       }


    }

    public static void Draw(Graphics g){
        for(int i = 0; i < allEnemies.size(); i++)
            allEnemies.get(i).Draw(g);
    }

}
