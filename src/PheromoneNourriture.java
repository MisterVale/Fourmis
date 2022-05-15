import java.awt.*;

class PheromoneNourriture extends Pheromone
{
	// Cree des pheromones lors de la recuperation de ressources avant de les dessiner sur le jeu 
    public PheromoneNourriture(int x, int y, Fourmiliere f, Color c)
    {
	super(x,y,f);
	this.c = c;
    }
} 
