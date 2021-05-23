package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

public class Door{
    private static Door door = null;
    private Image bufferedImage;
    private float x;
    private float y;
    private int width;
    private int height;
    private Rectangle bounds;
    private RefLinks refLink;

    private Door(){
        bufferedImage = Assets.door;
    }

    public static Door getInstance(){
        if(door == null){
            door = new Door();
        }

        return door;
    }

    public static void Init(RefLinks refLink, float x, float y, int width, int height){
        door.x = x;
        door.y = y;
        door.height = height;
        door.width = width;
        door.refLink = refLink;
        door.bounds = new Rectangle(0,0,width,height);
    }

    public float getX(){return x;}
    public float getY(){return y;}
    public Rectangle getBounds(){return bounds;}

    public void Draw(Graphics g){
        g.drawImage(bufferedImage,(int)(x - refLink.GetCamera().getRect().x),(int)(y - refLink.GetCamera().getRect().y),width,height,null);
    }
}
