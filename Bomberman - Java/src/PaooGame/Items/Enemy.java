package PaooGame.Items;

import PaooGame.Bombs.Bomb;
import PaooGame.Bombs.BombManager;
import PaooGame.Explosion.Explosion;
import PaooGame.Explosion.ExplosionManager;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.Random;

public class Enemy extends Character{

    private Image bufferedImage;
    private boolean dead;
    int direction;
    int[] dl = {-1,0,1,0};
    int[] dc = {0,1,0,-1};

    public Enemy(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        bufferedImage = Assets.enemy;
        dead = false;
        Random random = new Random();
        direction = random.nextInt(4);
    }

    public boolean isDead(){
        return dead;
    }

    @Override
    public void Move(){
        x += xMove;
        y += yMove;
        //bounds.x += xMove;
        //bounds.y += yMove;
    }


    @Override
    protected boolean isCollision(){
        // x + xMove, y + yMove = pozitia la care se incearca sa se ajunga
        // + width * 7/8, + height * 7/8, fac dreptunghiul de coliziune mai mic pentru a fi mai usoara manevrarea prin spatii stramte
        // Verific daca pozitia viitoare are coliziune in partea de stanga - sus
        if(refLink.GetMap().GetTile((int)(x + xMove)/32,(int)(y + yMove)/32).IsSolid())
            return true;
        // Verific daca pozitia viitoare are coliziune in partea de dreapta - jos
        if(refLink.GetMap().GetTile((int)((x+xMove + width * 6/8)/32),(int)((y+yMove+height*6/8)/32)).IsSolid())
            return true;
        // Verific daca pozitia viitoare are coliziune in partea de stanga - jos
        if(refLink.GetMap().GetTile((int)((x+xMove)/32),(int)((y+yMove+height*6/8)/32)).IsSolid())
            return true;
        // Verific daca pozitia viitoare are coliziune in partea de dreapta - sus
        if(refLink.GetMap().GetTile((int)((x+xMove + width*6/8)/32),(int)((y+yMove)/32)).IsSolid())
            return true;

        // Coliziunea cu focul
        if(Explosion.fire[(int)(x + xMove)/32][(int)(y + yMove)/32] != 0){
            dead = true;
        }

        if(Explosion.fire[(int)(x + xMove + width * 6/8)/32][(int)(y + yMove)/32] != 0){
            dead = true;
        }

        if(Explosion.fire[(int)(x + xMove + width*6/8)/32][(int)(y + yMove + height*6/8)/32] != 0){
            dead = true;
        }

        if(Explosion.fire[(int)(x + xMove)/32][(int)(y + yMove + height*6/8)/32] != 0){
            dead = true;
        }

        // Coliziune cu alti monstrii
        for(int i = 0; i < EnemiesManager.getAllEnemies().size(); i++){
            Enemy enemy = EnemiesManager.getAllEnemies().get(i);
            if(enemy != this)
            {
                Rectangle temp1 = new Rectangle(bounds);
                Rectangle temp2 = new Rectangle(enemy.bounds);


                temp1.x += x + xMove;
                temp2.x += enemy.GetX();
                temp1.y += y + yMove;
                temp2.y += enemy.GetY();


                if(temp1.intersects(temp2)){
                    return true;
                }
            }

        }

        // Coliziune cu bombe
        for(int i = 0; i < BombManager.getallBombs().size(); i++){
            Bomb bomb = BombManager.getallBombs().get(i);
            Rectangle temp1 = new Rectangle(bounds);
            Rectangle temp2 = new Rectangle(bomb.getBounds());

            temp2.x += bomb.GetX();
            temp2.y += bomb.GetY();

            temp1.x += x ;
            temp1.y += y;

            temp1.x += xMove;
            temp1.y += yMove;



            if(temp1.intersects(temp2)){
                return true;
            }

        }

        return false;
    }

    public void Update(){

        xMove = dl[direction] * speed;
        yMove = dc[direction] * speed;

        Move();

        if(isCollision() == true){
            x -= xMove;
            y -= yMove;
            //bounds.x -= xMove;
            //bounds.y -= yMove;
            Random random = new Random();
            direction = random.nextInt(4);
        }


    }

    public void Draw(Graphics g){
        g.drawImage(bufferedImage, (int)(x - refLink.GetCamera().getRect().x), (int)(y-refLink.GetCamera().getRect().y), width*6/8, height*6/8, null);
    }

}
