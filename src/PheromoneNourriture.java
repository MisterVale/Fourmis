import java.awt.*;

class PheromoneNourriture extends Pheromone
{
    public PheromoneNourriture(int x, int y, Fourmiliere f, Color c)
    {
	super(x,y,f);
	this.c = c;
    }
} 