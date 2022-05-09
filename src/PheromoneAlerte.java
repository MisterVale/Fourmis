import java.awt.*;

class PheromoneAlerte extends Pheromone implements Constantes
{
    private int stade;
    private PheromoneAlerte p; //Phero principale

    public PheromoneAlerte(int x, int y,Fourmiliere f, Color c,int s)
    {
	super(x,y,f);
	this.c = c;
	stade = s;
    }

    public void setMainPhero(PheromoneAlerte p)
    {
	this.p = p;
    }

    public PheromoneAlerte getMainPhero()
    {
	return p;
    }

    public int getStade()
    {
	return stade;
    }

    public void propager()
    {
	stade = -1;
    }
}
