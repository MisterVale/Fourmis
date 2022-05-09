import java.awt.*;
import java.util.*;

class Fourmiliere
{
    // Coordonnees de la fourmiliere
    private int x;
    private int y;
    // Reserves de la fourmiliere
    private int reserves;
    // Couleur de la fourmiliere et donc des fourmis qui la compose
    private Color c;
    // ArrayList contenant toutes les fourmis
    private ArrayList<Fourmi> allFourmi = new ArrayList<Fourmi>();

    // Constructeur avec parametres
    public Fourmiliere(int x, int y, int nbFourmiOuv, int nbFourmiGue, Color c)
    {
	this.x = x;
	this.y = y;
	this.c = c;
	// Initialement, les reserves sont vides, elles se remplissent quand les fourmis ramenent des plantes dans la fourmiliere
	reserves = 0;

	for(int i=0; i<nbFourmiOuv;i++)
	    {
		FourmiOuvriere fo = new FourmiOuvriere(x,y,this,c);
		allFourmi.add(fo);
	    }
	for(int i=0; i<nbFourmiGue;i++)
	    {
		FourmiGuerriere fg = new FourmiGuerriere(x,y,this,c);
		allFourmi.add(fg);
	    }
    }

    public int getReserves ()
    { 
	return reserves;
    }

    public void addReserves ( int apport )
    { 
	// L'apport n est pas toujours le meme 
	// si une fourmi mange une portion de 3 sur une plante et que celle ci meurt, la fourmi rentre a la fourmiliere.
	// On ajoute ce que la fourmi a rapporte aux reserves de la fourmiliere
	reserves += apport ;
    }

    public void actionFourmi(int indice) throws Placement
    {
	allFourmi.get(indice).action();
    }

    //Compte le nombre de fourmis ouvriere dans le vecteur
    public int getNbOuv()
    {
	int cpt = 0;
	for(int i=0; i<allFourmi.size();i++)
	    {
		if(allFourmi.get(i) instanceof FourmiOuvriere)
		    cpt++;
	    }
	return cpt;
    }

    public int getNbGue()
    {
	int cpt = 0;
	for(int i=0; i<allFourmi.size();i++)
	    {
		if(allFourmi.get(i) instanceof FourmiGuerriere)
		    cpt++;
	    }
	return cpt;
    }

    public int getNbFourmi()
    {
	return allFourmi.size();
    }

    public int getX()
    {
	return x;
    }

    public int getY()
    {
	return y;
    }

    public Fourmi getFourmi(int indice)
    {
	// on retourne la fourmi se trouvant a l'indice i de l arrayList
	if(indice >= 0 && indice <= allFourmi.size())
	    return allFourmi.get(indice);
	else
	    // on retourne null si elle n existe pas
	    return null;
    }

    public Fourmi getFourmiID(int id)
    {
	// on retourne la fourmi dont l'identifiant est celui passé en parametre
	for(int i=0;i<allFourmi.size();i++)
	    if(allFourmi.get(i).getId() == id)
		return allFourmi.get(i);
	// on retourne null si elle n existe pas
	return null;
    }

    public void removeFourmi(int id)
    {
	// on supprime de l'arrayList la fourmi dont l'identifiant est celui passé en parametre
	for(int i=0;i<allFourmi.size();i++)
	    if(allFourmi.get(i).getId() == id)
		allFourmi.remove(i);
    }

    public void addFourmiOuv()
    {
	//on cree une nouvelle fourmi ouvriere on l'ajoute a larrayList
	FourmiOuvriere fo = new FourmiOuvriere(x,y,this,c);
	allFourmi.add(fo);
    }

    public void addFourmiGue()
    {
	//on cree une nouvelle fourmi guerriere on l'ajoute a larrayList
	FourmiGuerriere fg = new FourmiGuerriere(x,y,this,c);
	allFourmi.add(fg);
    }

    public void killFourmi(int i) throws Placement
    {
	if(i>=0 && i < allFourmi.size())
	    allFourmi.get(i).kill();
    }

    public void killAll()
    {
	allFourmi.clear();
    }
}