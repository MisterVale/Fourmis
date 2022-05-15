import java.awt.*;

abstract class Fourmi extends Thread implements Constantes
{

    // nombre de fourmis créées
    private static int nbFourmis = 0; 
    // fourmiliere a laquelle elle appartient
    protected Fourmiliere f;
    // Les coordonnées de la fourmi
    protected int x,y;
    // son identifiant
    protected int id;
    // sa couleur (relative a sa fourmiliere)
    protected Color c;
 
    protected Placement pl = new Placement();

    // Constructeur avec paramètres
    protected Fourmi ( int x, int y, Fourmiliere f)
    {
	this.f = f;
	this.x = x;
	this.y = y;
	pl.obj = this;
	pl.live = true;

	// on ajoute une fourmi au nombre total quand on en crée une
	nbFourmis++;
	// l'identifiant de la fourmi correspond au nombre de fourmis qui existent a ce moment la.On est ainsi sur qu il est unique.
	id = nbFourmis;
    }

    protected void nextStep() throws Placement
    {	 	

	// Cette fonction permet a la fourmi de se mouvoir, de bouger
	// Elle est lancee quand celle ci est en mode RECHERCHE
	// Sa direction est aleatoire: on choisi donc entre 4 nombres 
	// chacun correspondant a une direction differente.
	int choix = (int) (Math.random() * 4);

	pl.old_x = x;
	pl.old_y = y;

	switch(choix)
	    {
	    case 0:
		// la fourmi part a droite
		if(x+COTE < LONG-20)
		    x+=10;
		break;
	    case 1:
		// la fourmi part a gauche
		if(x-10 >= 20)
		    x-=10;
		break;
	    case 2:
		// la fourmi part en bas
		if(y+COTE < HAUT-20)
		    y+=10;
		break;
	    case 3:
		// la fourmi part en haut
		if(y-10 >= 20)
		    y-=10;
		break;
	    }

	pl.new_x = x;
	pl.new_y = y;
	
	throw pl;
    }

    // methode abstraite parce que les fourmis ouvrieres et guerrieres
    // n'ont pas la meme facon d agir
    abstract protected void action() throws Placement;

    // accesseur
    protected int getX()
    {
	return x;
    }

    // accesseur
    protected int getY()
    {
	return y;
    }

    // accesseur
    protected Color getColor()
    {
	return c;
    }

    // accesseur
    protected Fourmiliere getFourmiliere()
    {
	return f;
    }

    // accesseur
    protected int get_Id()
    {
	return id;
    }

    protected void kill() throws Placement
    {
	pl.old_x = x;
	pl.old_y = y;
	pl.live = false;

	throw pl;
    }

}
