package PaooGame.Subscribers;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;

import java.awt.*;

public class LifeUI implements Subscriber{
    Integer lifesLeft;

    public LifeUI(){
        super();
        lifesLeft = 3;
    }

    public void update(Hero hero){
        lifesLeft = hero.getLifesLeft();
    }

    public void Draw(Graphics g){
        g.drawImage(Assets.heart.getScaledInstance(32,32, Image.SCALE_DEFAULT), 3,0,null);
        g.setFont(new Font("arial",Font.BOLD,40));
        g.setColor(Color.BLACK);
        g.drawString(lifesLeft.toString(),35,32);
    }
}
