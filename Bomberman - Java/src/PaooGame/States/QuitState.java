package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;

public class QuitState extends State {
    /*! \fn public QuitState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

     */
    public QuitState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        //refLink.GetGame().setRunState(false);
        //refLink.GetGame().StopGame();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {

    }
}
