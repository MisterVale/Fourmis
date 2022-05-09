import java.awt.*;

public class FourmiGuerriere extends Fourmi 
{

    // Constructeur avec parametres
    public FourmiGuerriere(int x, int y, Fourmiliere f, Color c)
    {
	// cree une fourmi grace au constructeur de la classe mere
	super(x,y,f);

	// Les fourmis guerrieres sont + foncees que les ouvrieres
	if(c.getRed() == 0 )
	    this.c= new Color(c.getRed()+100,c.getGreen(),c.getBlue(),c.getAlpha());
	else if(c.getBlue() == 0)
	    this.c= new Color(c.getRed(),c.getGreen(),c.getBlue()+40,c.getAlpha());
    }

    public void action() throws Placement
    {
	// la seule action de la fourmi guerriere est de se deplacer aleatoirement
	super.nextStep();
    }
}