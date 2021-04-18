package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.Random;

public class Enemy extends Character{

    private Image bufferedImage;

    public Enemy(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        bufferedImage = Assets.enemy;

        Random random = new Random();
        if(random.nextInt(10) % 2 == 0){
            xMove = speed;
        }
        else
        {
            yMove = speed;
        }


    }

    @Override
    public void Move(){
        x += xMove;
        y += yMove;
    }

    public void Update(){
        Random random = new Random();
        int x = random.nextInt(4);
        System.out.println(x);
        if(isCollision() == true)
        {
            switch(x){
                case 0:
                    xMove = speed;
                    break;
                case 1:
                    xMove = -speed;
                    break;
                case 2:
                    yMove = -speed;
                    break;
                case 3:
                    yMove = speed;
                    break;
                default:
                    xMove = 0;
                    yMove = 0;
            }
        }
        Move();
    }

    public void Draw(Graphics g){
        g.drawImage(bufferedImage, (int)(x - refLink.GetCamera().getRect().x), (int)(y-refLink.GetCamera().getRect().y), width*6/8, height*6/8, null);

    }

}
