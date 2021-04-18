package PaooGame;

import java.awt.geom.Rectangle2D;

public class Camera {
    private Rectangle2D.Double camera; /*!< Referinta catre camera ce cuprinde dreptunghiul ce se vede la un moment de timp.*/
    private RefLinks refLink; /*!< Referinta catre shortcut. */

    public Camera(RefLinks refLink, Rectangle2D.Double camera){
        this.camera = camera;
        this.refLink = refLink;
    }

    public Rectangle2D.Double getRect(){ return camera;}

    public void Update(){
        camera.x = refLink.GetHero().GetX() - refLink.GetGame().GetWidth()/2;
        camera.y = refLink.GetHero().GetY() - refLink.GetGame().GetHeight()/2;
        if(camera.x < 0){
            camera.x = 0;
        }

        if(camera.y < 0){
            camera.y = 0;
        }

        if(camera.x > refLink.GetMap().getWidth() * 32 - camera.width){
            camera.x = refLink.GetMap().getWidth()* 32 - camera.width;
        }

        if(camera.y > refLink.GetMap().getHeight()*32 - camera.height){
            camera.y = refLink.GetMap().getHeight()*32 - camera.height;
        }
    }
}
