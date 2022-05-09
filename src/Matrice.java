import java.util.*;
import java.awt.*;

class Matrice implements Constantes
{
    private Case a[][] = new Case[LONG][HAUT];

    public Matrice() {
		for(int i=0; i<LONG; i++){
			for(int j=0; j<HAUT; j++)
			{ 
				a[i][j] = new Case(i,j);
			}
		}
		System.out.println("Longueur : " + LONG + " Hauteur : " + HAUT);
		System.out.println("Taille de la map : " + LONG / 10 + " x : " + HAUT / 10);
    }

    public void loadPlante(Placement pl)
    {
	if(pl.obj instanceof Plante)
	    {
		if(pl.live)
		    a[pl.new_x/10][pl.new_y/10].addPlante((Plante)pl.obj);
		else
		    a[pl.new_x/10][pl.new_y/10].deletePlante((Plante)pl.obj);
	    }
	else
	    System.out.println("Error : load plante echec");
    }

    private void regarderAutour(FourmiOuvriere f, Placement pl)
    {
	// On stocke un temps temporaire
	// pour que la fourmi parte dans le bon sens quand elle tombe sur une phéromone
	int tempsPlusFaible=DUREE_VIE_PHERO;
	
	if(a[(pl.new_x/10)-1][pl.new_y/10].getPheroNourri(f) != null && (pl.new_x/10)- 1 >= 0)
	    {
		if(tempsPlusFaible > a[(pl.new_x/10)-1][pl.new_y/10].getPheroNourri(f).getTemps())
		    {
			tempsPlusFaible = a[(pl.new_x/10)-1][pl.new_y/10].getPheroNourri(f).getTemps();
			f.setDirection(FourmiOuvriere.Direction.GAUCHE);
		    }
	    }
	if(a[(pl.new_x/10)+1][pl.new_y/10].getPheroNourri(f) != null && (pl.new_x/10)+1 < LONG/10 )
	    {
		if(tempsPlusFaible > a[(pl.new_x/10)+1][pl.new_y/10].getPheroNourri(f).getTemps() )
		    {
			tempsPlusFaible = a[(pl.new_x/10)+1][pl.new_y/10].getPheroNourri(f).getTemps();
			f.setDirection(FourmiOuvriere.Direction.DROITE);
		    }	
	    }
	if(a[pl.new_x/10][(pl.new_y/10)+1].getPheroNourri(f) != null && (pl.new_y/10)+1 < HAUT/10 )
	    {
		if(tempsPlusFaible > a[(pl.new_x/10)][(pl.new_y/10)+1].getPheroNourri(f).getTemps())
		    {
			tempsPlusFaible = a[pl.new_x/10][(pl.new_y/10)+1].getPheroNourri(f).getTemps();
			f.setDirection(FourmiOuvriere.Direction.BAS);
		    }			
	    }
	if(a[pl.new_x/10][(pl.new_y/10)-1].getPheroNourri(f) != null && (pl.new_y/10)-1 >=0)
	    {
		if(tempsPlusFaible > a[(pl.new_x/10)][(pl.new_y/10)-1].getPheroNourri(f).getTemps())
		    {
			tempsPlusFaible = a[pl.new_x/10][(pl.new_y/10)-1].getPheroNourri(f).getTemps();
			f.setDirection(FourmiOuvriere.Direction.HAUT);
		    }
	    }
	

	//---------------------------//

	if(a[(pl.new_x/10)-1][pl.new_y/10].getPlante() != null && (pl.new_x/10)- 1 >= 0)
	    f.setDirection(FourmiOuvriere.Direction.GAUCHE);
	else if(a[(pl.new_x/10)+1][pl.new_y/10].getPlante() != null && (pl.new_x/10)+1 < LONG/10 )
	    f.setDirection(FourmiOuvriere.Direction.DROITE);
	else if(a[pl.new_x/10][(pl.new_y/10)+1].getPlante() != null && (pl.new_y/10)+1 < HAUT/10 )
	    f.setDirection(FourmiOuvriere.Direction.BAS);
	else if(a[pl.new_x/10][(pl.new_y/10)-1].getPlante() != null && (pl.new_y/10)-1 >=0)
	    f.setDirection(FourmiOuvriere.Direction.HAUT);
			
	if(tempsPlusFaible == DUREE_VIE_PHERO)
	    f.setMode(FourmiOuvriere.Mode.RECHERCHE);	
    }

    public void fuir(FourmiOuvriere f, PheromoneAlerte p)
    {
	int f_x = f.getX();
	int f_y = f.getY();
	int p_x = p.getX();
	int p_y = p.getY();

	if(f_x == p_x)
	    {
		if(f_y > p_y && f_y+COTE+10 <= HAUT)
		    f.setDirection(FourmiOuvriere.Direction.BAS);
		else if(f_y < p_y && f_y-10 >= 0)
		    f.setDirection(FourmiOuvriere.Direction.HAUT);
		else if(f_y == p_y && f_y+COTE+10 <= HAUT)
		    //Ou elle veut
		    f.setDirection(FourmiOuvriere.Direction.HAUT);
	    }
	else if(f_x < p_x && f_x-10 >= 0)
	    f.setDirection(FourmiOuvriere.Direction.GAUCHE);
	else if(f_x > p_x && f_x+COTE+10 <= LONG)
	    f.setDirection(FourmiOuvriere.Direction.DROITE);
    }

    public void killFourmi(Placement pl)
    {
	if(pl.obj instanceof Fourmi)
	    {
		if(!pl.live)
		    a[pl.old_x/10][pl.old_y/10].deleteFourmi((Fourmi)pl.obj);
	    }
    }

    public void update(Placement pl,ArrayList<Fourmiliere> aListF,ArrayList<Pheromone> aListP)
    {
	if(pl.obj instanceof Fourmi)
	    {
		a[pl.old_x/10][pl.old_y/10].deleteFourmi((Fourmi)pl.obj);
		a[pl.new_x/10][pl.new_y/10].addFourmi((Fourmi)pl.obj);

		Case case_actuelle = a[pl.new_x/10][pl.new_y/10];

		if(pl.obj instanceof FourmiOuvriere)
		    {
			if(((FourmiOuvriere)pl.obj).getMode() == FourmiOuvriere.Mode.RECHERCHE)
			    {
				//Si la plante p est diff de null => la case contient une plante p
				Plante p = (Plante)case_actuelle.getPlante();

				PheromoneAlerte ph_tmp = case_actuelle.getPheroAlerte((FourmiOuvriere)pl.obj);
				if(p != null)
				    {
					((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.MANGER);
					p.diminue();
				    }
				else if(case_actuelle.getPheroNourri((FourmiOuvriere)pl.obj) != null)
				    {
					((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.PHEROMONE);
					regarderAutour(((FourmiOuvriere)pl.obj),pl);
				    }
				//Si c'est different de null => la case contient une phero alerte de la meme colonie que la fourmi ouvriere
				else if(ph_tmp != null)
				    {
					// on veut changer la direction de la fourmi pour qu'elle aille dans le sens contraire du centre de la phero alerte
					PheromoneAlerte centre_ph_tmp = ph_tmp.getMainPhero();
					((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.FUIR);
					fuir(((FourmiOuvriere)pl.obj), centre_ph_tmp);

				    }
			    }
			else if(((FourmiOuvriere)pl.obj).getMode() == FourmiOuvriere.Mode.RENTRE)
			    {
				if(pl.phe != null)
				    case_actuelle.addPheromone(pl.phe);
				else
				    System.out.println("Error : aucune pheromone disponible pour la fourmi");
			    }
			else if(((FourmiOuvriere)pl.obj).getMode() == FourmiOuvriere.Mode.MANGER)
			    {
				Plante p = case_actuelle.getPlante();
				if(p != null)
				    p.diminue();
				else
				    ((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.RENTRE);
			    }
			else if(((FourmiOuvriere)pl.obj).getMode() == FourmiOuvriere.Mode.PHEROMONE)
			    {
				Plante p = case_actuelle.getPlante();
				if(p != null)
				    {
					((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.MANGER);
					p.diminue();
				    }
				if(case_actuelle.getPheroNourri((FourmiOuvriere)pl.obj) == null)
				    ((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.RECHERCHE);
				else
				    regarderAutour(((FourmiOuvriere)pl.obj),pl);
			    }
			else if(((FourmiOuvriere)pl.obj).getMode() == FourmiOuvriere.Mode.FUIR)
			    {
				PheromoneAlerte ph = case_actuelle.getPheroAlerte(((FourmiOuvriere)pl.obj));
				if(ph == null)
				    ((FourmiOuvriere)pl.obj).setMode(FourmiOuvriere.Mode.RECHERCHE);
				else
				    {
					PheromoneAlerte centre_ph_tmp = ph.getMainPhero();
					fuir(((FourmiOuvriere)pl.obj), centre_ph_tmp);
				    }
			    }
		    }
		else if(pl.obj instanceof FourmiGuerriere)
		    {
			//Si c'est different de -1 cela veut dire qu'il faut enlever la fourmi d'identifiant id retoune par la fonction suivante
			int id = case_actuelle.containFourmiOuvr(((FourmiGuerriere)pl.obj));

			if(id != -1)
			    {
				for(int k=0;k<aListF.size();k++)
				    {
					Fourmi f = aListF.get(k).getFourmiID(id);
					if(f != null)
					    {
						//Si c'est une fourmi ouvriere qui meurt alors on cree une phero alerte
						PheromoneAlerte p = new PheromoneAlerte(pl.new_x,pl.new_y,f.getFourmiliere(),f.getColor(),1);
						p.setMainPhero(p);
						//stade=1, c'est le centre de propagation des phero alerte
						case_actuelle.addPheromone(p);
						aListP.add(p);
					    }
					aListF.get(k).removeFourmi(id);
				    } 
			    }
			else
			    {
				id = case_actuelle.containFourmiGuer(((FourmiGuerriere)pl.obj));
				if(id != -1) 
				    for(int k=0;k<aListF.size();k++)
					aListF.get(k).removeFourmi(id);
			    }
		    }
	    }
    }

    public void updatePhero(Placement pl)
    {
	if(pl.obj instanceof PheromoneNourriture)
	    {
		if(!pl.live)
		    a[pl.new_x/10][pl.new_y/10].deletePheromone(((PheromoneNourriture)pl.obj));
		else
		    System.out.println("Error : on ne doit jamais arriver la");
	    }
    }

    public void updatePheroAlerte(Pheromone p,ArrayList<Pheromone> aListP)
    {
	int x = p.getX();
	int y = p.getY();
	Fourmiliere f = p.getFourmiliere();
	Color c = p.getColor();
	int stade = ((PheromoneAlerte)p).getStade();
	PheromoneAlerte main = ((PheromoneAlerte)p).getMainPhero();
	PheromoneAlerte phe;

	if((x/10)+1 < LONG && !a[(x/10)+1][y/10].containPheroAlerte(p))
	    {
		phe = new PheromoneAlerte(x+10,y,f,c,stade+1);
		phe.setMainPhero(main);
		a[(x/10)+1][y/10].addPheromone(phe);
		aListP.add(phe);
	    }
	if((x/10)-1 >= 0 && !a[(x/10)-1][y/10].containPheroAlerte(p))
	    {
		phe = new PheromoneAlerte(x-10,y,f,c,stade+1);
		phe.setMainPhero(main);
		a[(x/10)-1][y/10].addPheromone(phe);
		aListP.add(phe);
	    }
	if((y/10)+1 < HAUT && !a[x/10][(y/10)+1].containPheroAlerte(p))
	    {
		phe = new PheromoneAlerte(x,y+10,f,c,stade+1);
		phe.setMainPhero(main);
		a[x/10][(y/10)+1].addPheromone(phe);
		aListP.add(phe);
	    }
	if((y/10)-1 >= 0 && !a[x/10][(y/10)-1].containPheroAlerte(p))
	    {
		phe = new PheromoneAlerte(x,y-10,f,c,stade+1);
		phe.setMainPhero(main);
		a[x/10][(y/10)-1].addPheromone(phe);
		aListP.add(phe);
	    }
    }

}
