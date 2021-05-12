package PaooGame.Explosion;

import PaooGame.Bombs.Bomb;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExplosionManager {
    private static List<Explosion> allExplosion = new ArrayList<Explosion>();
    private static RefLinks refLink;

    public static void setRefLink(RefLinks RefLink){
        refLink = RefLink;
    }

    public static List<Explosion> getallExplosions(){return allExplosion;}

    public static void Update(){
        for(int i = 0; i < allExplosion.size(); i++){
            double time = allExplosion.get(i).getTime();
            if(time < Bomb.getLifetime()){
                allExplosion.get(i).Update();
            }
            else {
                allExplosion.get(i).clean();
                allExplosion.remove(i);
            }
        }
    }

    public static void Draw(Graphics g){
        for(int i = 0; i < Explosion.fireWidth; i++)
        {
            for(int j = 0; j < Explosion.fireHeight; j++)
            {
                switch(Explosion.fire[i][j]){
                    case 9:
                        g.drawImage(Explosion.animation[0][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 1:
                        g.drawImage(Explosion.animation[1][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 2:
                        g.drawImage(Explosion.animation[2][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 3:
                        g.drawImage(Explosion.animation[3][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 4:
                        g.drawImage(Explosion.animation[4][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 5:
                        g.drawImage(Explosion.animation[5][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 6:
                        g.drawImage(Explosion.animation[6][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 7:
                        g.drawImage(Explosion.animation[7][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    case 8:
                        g.drawImage(Explosion.animation[8][0], (int)(i * 32 - refLink.GetCamera().getRect().x), (int)(j * 32 - refLink.GetCamera().getRect().y), Explosion.DEFAULT_EXPLOSION_WIDTH, Explosion.DEFAULT_EXPLOSION_HEIGHT, null);
                        break;
                    default:
                        break;

                }
            }
        }
    }
}
