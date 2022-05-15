import java.awt.*;

public class FourmiCommandant extends Fourmi 
{

    // Constructeur avec parametres
    public FourmiCommandant(int x, int y, Fourmiliere f, Color c)
    {
	// cree une fourmi grace au constructeur de la classe mere
	super(x,y,f);

	// Les fourmis commandant sont plus foncées que les guerrieres
	if(c.getRed() == 0 )
	    this.c= new Color(c.getRed(),c.getGreen(),c.getBlue()-100,c.getAlpha());
	else if(c.getBlue() == 0)
	    this.c= new Color(c.getRed(),c.getGreen()-100,c.getBlue(),c.getAlpha());
    else if(c.getGreen() == 0)
	    this.c= new Color(c.getRed()-100,c.getGreen(),c.getBlue(),c.getAlpha());
    }

    public void action() throws Placement
    {
	// la seule action de la fourmi commandant est de se deplacer aleatoirement
	super.nextStep();
    }
}
