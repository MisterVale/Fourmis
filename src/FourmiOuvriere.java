import java.awt.*;

public class FourmiOuvriere extends Fourmi 
{
    private int charge; // Nourriture portée
    private static int chargeMax; // Nourriture maximum portable
    // Differents modes possibles dans lesquels peut se trouver la fourmi
    static public enum Mode{RECHERCHE, MANGER, RENTRE, PHEROMONE};
    private Mode mode;
    // Directions possibles
    public static enum Direction{ HAUT, BAS, DROITE, GAUCHE};
    private Direction direction;


    // Constructeur avec parametres 
    public FourmiOuvriere(int x, int y, Fourmiliere f, Color c)
    {
	super(x,y,f);
	charge = 0;
	chargeMax = 5;
	pl.phe = null;
	// mode par defaut
	mode = Mode.RECHERCHE;

	// on personnalise la couleur des fourmis ouvrieres pour les distinguer
	if(c.getRed() == 0 )
	    this.c= new Color(c.getRed()+150,c.getGreen()+150,c.getBlue(),c.getAlpha());
	else if(c.getBlue() == 0)
	    this.c= new Color(c.getRed()+150,c.getGreen(),c.getBlue()+150,c.getAlpha());
	else if(c.getGreen() == 0)
	    this.c= new Color(c.getRed(),c.getGreen()+150,c.getBlue()+150,c.getAlpha());
    }


    private void eat() throws Placement
    { 
	// Fonction permettant a la fourmi de gerer tous les evenements relatifs au moment ou elle est sur une plante
       
	if(charge == chargeMax)
	    {
		// Si elle ne peut plus rien porter, elle rentre dans sa fourmiliere
		mode = Mode.RENTRE;
		// elle laisse une pheromone nourriture sur sa route
		pl.phe = new PheromoneNourriture(x,y,f,c);
	    }
	else
	    // si sa charge est incomplete, elle continue a manger et augmente sa charge.
	    charge ++;
	
	throw pl;
    }

    public void setMode(Mode m)
    {
	mode = m;
    }

    public Mode getMode()
    {
	return mode;
    }

    public void setDirection(Direction d)
    {
	direction = d;
    }

    public boolean chargeComplete()
    {
	return(charge == chargeMax);
    }

    public void suivrePheroNourri() throws Placement
    { 

	// Permet a la fourmi de se deplacer sur la carte dans quatre directions 
	// (HAUT, BAS, DROITE, GAUCHE) quand elle recherche de la nourriture
	switch(direction)
	    {
	    case HAUT:
		if(pl.old_x == x && pl.old_y == y-10)
		    {
			mode=Mode.RECHERCHE;
			super.nextStep();	
		    }
		else
		    {
			pl.old_x = x;
			pl.old_y = y;
			y-=10;
		    }
		break;
	    case BAS:
		if(pl.old_x == x && pl.old_y == y+10)
		    {
			mode=Mode.RECHERCHE;
			super.nextStep();	
		    }
		else
		    {
			pl.old_x = x;
			pl.old_y = y;
			y+=10;
		    }
		break;
	    case DROITE:
		if(pl.old_x == x+10 && pl.old_y == y)
		    {
			mode=Mode.RECHERCHE;
			super.nextStep();	
		    }
		else
		    {
			pl.old_x = x;
			pl.old_y = y;
			x+=10;
		    }
		break;
	    case GAUCHE:
		if(pl.old_x == x-10 && pl.old_y == y)
		    {
			mode=Mode.RECHERCHE;
			super.nextStep();	
		    }
		else
		    {
			pl.old_x = x;
			pl.old_y = y;
			x-=10;
		    }
		break;
	    }
		
	pl.new_x = x;
	pl.new_y = y;
	
	throw pl;
    }
    
    private void fuir() throws Placement
    {
	// Bouge la fourmi en fonction de la direction dans laquelle elle se trouve
	pl.old_x = x;
	pl.old_y = y;

	switch(direction)
	    {
	    case HAUT:
		y-=10;
		break;
	    case BAS:
		y+=10;
		break;
	    case GAUCHE:
		x-=10;
		break;
	    case DROITE:
		x+=10;
		break;
	    }
	pl.new_x = x;
	pl.new_y = y;
	
	throw pl;

    }
    private void getHome() throws Placement
    {
	// Quand la fourmi est en mode RENTRE
	// elle revient dans sa fourmiliere
	if(x != f.getX() || y != f.getY())
	    {
		pl.old_x = x;
		pl.old_y = y;

		// si elle se trouve juste au dessus ou au dessous de la fourmiliere, elle part en haut ou en bas (en direction de sa fourmiliere)
		if(x == f.getX())
		    {
			if(y > f.getY())
			    y-=10;
			else if(y < f.getY())
			    y+=10;	
		    }
		// de meme, si elle se trouve a gauche ou a droite de sa fourmiliere elle part a gauche ou a droite
		else if(y == f.getY())
		    {
			if(x > f.getX())
			    x-=10;  
			else if(x < f.getX())
			    x+=10;
		    }
		else
		    {
			// si elle se trouve autre part,selon le nombre aleatoire tire, elle part soit : 
			int choix = (int) (Math.random() * 2);
			if(choix == 1)
			    {
				// en haut ou en bas (en direction de la fourmiliere)
				if(y > f.getY())
				    y-=10;
				else if(y < f.getY())
				    y+=10;
			    }
			else
			    {
				// a gauche ou a droite (en direction de la fourmiliere)
				if(x > f.getX())
				    x-=10;  
				else if(x < f.getX())
				    x+=10;
			    }
		    }
	
		pl.new_x = x;
		pl.new_y = y;
		// elle laisse une pheromone nourriture sur la route
		pl.phe = new PheromoneNourriture(x,y,f,c);

		throw pl;
	    }
	else
	    {
		// on entre ici si la fourmi est sur sa fourmiliere
		pl.phe = null;
		// on vide la charge de la fourmi pour l'ajouter aux reserves de sa fourmiliere
		f.addReserves(charge);
		charge = 0;
		// ensuite la fourmi repart a la recherche de plantes
		mode = Mode.RECHERCHE;
	    }
    }

    

    public void action() throws Placement
    {
	// Selon le mode, on lance la fonction qui correspond
	switch(mode)
	    {
	    case RECHERCHE :
		super.nextStep();	
		break;
	    case MANGER :
		eat();
		break;
	    case RENTRE :
		getHome();
		break;
	    case PHEROMONE:
		suivrePheroNourri();
		break;
	    }
    }
}
