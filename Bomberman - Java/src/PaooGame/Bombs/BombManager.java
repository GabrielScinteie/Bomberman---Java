package PaooGame.Bombs;

import PaooGame.Explosion.Explosion;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Items.Enemy;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BombManager {
    private static List<Bomb> allBombs = new ArrayList<Bomb>();
    private static RefLinks refLink;

    public static void setRefLink(RefLinks RefLink){
        refLink = RefLink;
    }
    public static List<Bomb> getallBombs(){return allBombs;}

    public static void Update(){
        for(int i = 0; i < allBombs.size(); i++){
            double time = allBombs.get(i).getTime();
            if(time < Bomb.getLifetime()){
                allBombs.get(i).Update();
            }
            else
            {
                ExplosionManager.getallExplosions().add( new Explosion(refLink,(int)(allBombs.get(i).GetX()), (int)(allBombs.get(i).GetY())));
                System.out.println(allBombs.get(i).GetX() + " " + (allBombs.get(i).GetY()));

                allBombs.remove(i);
            }
        }

    }

    public static void Draw(Graphics g){
        for(int i = 0; i < allBombs.size(); i++)
            allBombs.get(i).Draw(g);
    }
}
