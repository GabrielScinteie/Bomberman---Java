package PaooGame.Items;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Bombs.*;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private double timeSinceLastBomb;     /*Durata de la ultima bomba pusa*/
    private double bombCooldown;
    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        image = Assets.heroStop;
        timeSinceLastBomb = System.nanoTime();
        bombCooldown = 2;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 32;
        normalBounds.height = 32;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    @Override
    protected boolean isCollision(){
        // x + xMove, y + yMove = pozitia la care se incearca sa se ajunga
        // + width * 6/8, + height * 6/8, fac dreptunghiul de coliziune mai mic pentru a fi mai usoara manevrarea prin spatii stramte
        // Verific daca pozitia viitoare are coliziune in partea de stanga - sus
        if(refLink.GetMap().GetTile((int)(x + xMove)/32,(int)(y + yMove)/32).IsSolid() == true)
            return true;

        // Verific daca pozitia viitoare are coliziune in partea de dreapta - jos
        if(refLink.GetMap().GetTile((int)((x+xMove + width * 6/8)/32),(int)((y+yMove+height*6/8)/32)).IsSolid() == true)
            return true;

        // Verific daca pozitia viitoare are coliziune in partea de stanga - jos
        if(refLink.GetMap().GetTile((int)((x+xMove)/32),(int)((y+yMove+height*6/8)/32)).IsSolid() == true)
            return true;

        // Verific daca pozitia viitoare are coliziune in partea de dreapta - sus
        if(refLink.GetMap().GetTile((int)((x+xMove + width*6/8)/32),(int)((y+yMove)/32)).IsSolid() == true)
            return true;

        // verific interactiunile cu monstrii
        for(int i = 0; i < EnemiesManager.getAllEnemies().size(); i++){
            Enemy enemy = EnemiesManager.getAllEnemies().get(i);
            Rectangle temp1 = new Rectangle(bounds);
            Rectangle temp2 = new Rectangle(enemy.bounds);
            temp1.x += x;
            temp1.y += y;
            temp2.x += enemy.GetX();
            temp2.y += enemy.GetY();

            if(temp1.intersects(temp2)){
                x = y = 65;
            }
        }

        // verific interactiunile cu bombele
        for(int i = 0; i < BombManager.getallBombs().size(); i++){
            Bomb bomb = BombManager.getallBombs().get(i);
            Rectangle temp1 = new Rectangle(bounds);
            Rectangle temp2 = new Rectangle(bomb.getBounds());
            temp1.x += x ;
            temp1.y += y;
            temp2.x += bomb.GetX();
            temp2.y += bomb.GetY();

            temp1.x += xMove;
            temp1.y += yMove;
            if(temp1.intersects(temp2) && !bomb.isPlayerInside()){
                return true;
            }

        }

        return false;
    }



    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        Move();
            ///Actualizeaza imaginea
        image = Assets.heroStop;
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().up == true)
        {
        image = Assets.heroUp;
        }
        if(refLink.GetKeyManager().down == true)
        {
            image = Assets.heroDown;
        }
        if(refLink.GetKeyManager().right == true) {
            image = Assets.heroRight;
        }
        if(refLink.GetKeyManager().space == true) {
            image = Assets.heroStop;
        }
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */


    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
        else
            ///Verificare apasare tasta "jos"
            if(refLink.GetKeyManager().down)
            {
                yMove = speed;
            }
            else
                ///Verificare apasare tasta "left"
                if(refLink.GetKeyManager().left)
                {
                    xMove = -speed;
                }
                else
                    ///Verificare apasare tasta "dreapta"
                    if(refLink.GetKeyManager().right)
                    {
                        xMove = speed;
                    }

        if(refLink.GetKeyManager().space)
        {
            // mecanismul de cooldown
            long SecondToNano = 1000000000;
            if((System.nanoTime() - timeSinceLastBomb) / SecondToNano > bombCooldown){
                int xInt = (int)x + width/2;
                int yInt = (int)y + height/2;

                // +4 pentru a centra mai bine bomba
                BombManager.getallBombs().add(new Bomb(refLink,(int)xInt/32*32 ,(int)yInt/32*32));
                System.out.println("Am adaugat bomba");
                timeSinceLastBomb = System.nanoTime();
            }

        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)(x - refLink.GetCamera().getRect().x), (int)(y-refLink.GetCamera().getRect().y), width*6/8, height*6/8, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
    }
}
