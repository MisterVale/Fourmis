import java.util.*;

class Case
{

    // Coordonnees en x et y de la case
    private int x;
    private int y;
    // ArrayList d'objet ou l'on stocke tout ce qui se trouve dans la case
    private ArrayList<Object> aListObj = new ArrayList<Object>();

    // Constructeur avec parametres
    public Case(int x, int y)
    {
	this.x = x;
	this.y = y;
    }

    // Ajouter une fourmi (ouvriere, guerriere ou commandante) a l'arrayList
    public void addFourmi(Fourmi f)
    {
	aListObj.add(f);
    }

    // Ajouter une pheromone (alerte ou nourriture) a l'arrayList
    public void addPheromone(Pheromone p)
    {
	aListObj.add(p);
    }

    // Ajouter une plante a l'arrayList
    public void addPlante(Plante p)
    {
	aListObj.add(p);
    }

    // Supprimer une plante de l'arrayList
    public void deletePlante(Plante p)
    {
	aListObj.remove(p);
    }

    // Supprimer une pheromone de l'arrayList
    public void deletePheromone(Pheromone p)
    {
	aListObj.remove(p);
    }

    // Supprimer une fourmi de l'arrayList
    public void deleteFourmi(Fourmi f)
    {
	aListObj.remove(f);
    }

    public boolean containPheroAlerte(Pheromone p)
    {
	// Fonction permettant de vérifier si une case contient déjà
	// une pheromone alerte : permet donc de ne pas en recréer par dessus

	// Cette fonction n'est generee que lorsque l'on cree un nuage de pheromones alertes
	for(int k=0; k < aListObj.size() ; k++)
	    {
		if(aListObj.get(k) instanceof PheromoneAlerte)
		    {
			if(((PheromoneAlerte)aListObj.get(k)).getMainPhero() == ((PheromoneAlerte)p).getMainPhero())
			    return true;
		    }
	    }
	return false;
    }
    

    public Plante getPlante()
    {
	// Fonction permettant de retourner une plante s'il en existe une
	for(int k=0; k < aListObj.size() ; k++)
	    {
		if(aListObj.get(k) instanceof Plante)
		    return((Plante)aListObj.get(k));
	    }
	// s il n y en a pas, on retourne null
	return null;
    }

    public int containFourmiOuvr(FourmiGuerriere f)
    {
	// Quand une fourmi guerriere et ouvriere sont sur une meme case
	// on va recuperer l'id de la fourmi ouvriere pour ensuite la supprimer
	int id;
	for(int k=0; k< aListObj.size(); k++)
	    {
		if(aListObj.get(k) instanceof FourmiOuvriere)
		    // on verifie que la fourmi n'appartient pas au meme clan que la fourmi guerriere passee en parametre
		    if(f.getFourmiliere() != ((FourmiOuvriere)aListObj.get(k)).getFourmiliere())
			{
			    id = ((FourmiOuvriere)aListObj.get(k)).get_Id();
			    this.deleteFourmi(((FourmiOuvriere)aListObj.get(k))); 
			    // on retourne l'id de la fourmi detruite
			    return id;
			}
	    }
	// retourne -1 quand il n y a pas de fourmi ouvriere dans la case
	return -1;
    }

    public int containFourmiGuer(FourmiGuerriere f)
    {
	//Quand 2 fourmis guerrieres sont sur une meme case
	int id;
	for(int k=0; k< aListObj.size(); k++)
	    {
		if(aListObj.get(k) instanceof FourmiGuerriere)
		    if(f.getFourmiliere() != ((FourmiGuerriere)aListObj.get(k)).getFourmiliere())
			{
			    id = ((FourmiGuerriere)aListObj.get(k)).get_Id();
			    // on tire un nb entre 0 et 1 pour savoir qui va mourir
			    int nb_tire = (int) (Math.random() * 2);

			    if( nb_tire == 0)
				{
				    // on supprime la fourmi guerriere du vecteur 
				    id = ((FourmiGuerriere)aListObj.get(k)).get_Id();
				    this.deleteFourmi(((FourmiGuerriere)aListObj.get(k))); 
				}
			    else
				{
				    // on supprime la fourmi passee en parametre si on tire 1
				    id = f.get_Id();
				    this.deleteFourmi(f); 
				    
				}
			    // on retourne l'id de la fourmi qui vient de mourir
			    return id;
			    
			}
	    }
	// s il n y a pas deux fourmis guerriere dans la case on retourne -1
	return -1;
    }


    public PheromoneNourriture getPheroNourri(FourmiOuvriere f)
    {
	// on parcours l'arrayList pour trouver une pheromone nourriture
	// on a passe la fourmi en parametre pour verifier que la pheromone provient d'une fourmi de sa fourmiliere
	for(int k=0; k < aListObj.size() ; k++)
	    {
		if(aListObj.get(k) instanceof PheromoneNourriture)
		    if(((PheromoneNourriture)aListObj.get(k)).getFourmiliere() == f.getFourmiliere())
			return ((PheromoneNourriture)aListObj.get(k));
	    }
	// on retourne null si la pheromone appartient au clan adverse ou s'il n y a pas de pheromone sur cette case
	return null;
    }

    public PheromoneAlerte getPheroAlerte(FourmiOuvriere f)
    {
	// on parcourt l'arrayList pour trouver une pheromone alerte
	// on a passe la fourmi en parametre pour verifier que la pheromone provient d une fourmi de sa fourmiliere
	for(int k=0; k < aListObj.size() ; k++)
	    {
		if(aListObj.get(k) instanceof PheromoneAlerte)
		    if(((PheromoneAlerte)aListObj.get(k)).getFourmiliere() == f.getFourmiliere())
			return ((PheromoneAlerte)aListObj.get(k));
	    }
	// on retourne null si la pheromone appartient au clan adverse ou s il n y a pas de pheromone sur cette case
	return null;
    }

}
