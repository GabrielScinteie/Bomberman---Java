package PaooGame.Input;

import PaooGame.Game;
import PaooGame.RefLinks;
import PaooGame.States.MenuState;
import PaooGame.States.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean clicked;
    private int mouseX,mouseY;
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/


    public boolean isClicked(){
        return clicked;
    }

    public int getMouseX(){ return mouseX;}
    public int getMouseY(){return mouseY;}

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        //System.out.println("Mouse apasat");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("released");
        clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exited");
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        //System.out.println(mouseX + " " + mouseY);
    }
}
