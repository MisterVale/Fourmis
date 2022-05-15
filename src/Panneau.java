import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
 
public class Panneau extends JPanel implements Constantes
{
    private ArrayList<Fourmiliere> aListFourmiliere = new ArrayList<Fourmiliere>();
    private ArrayList<Plante> aListPlante = new ArrayList<Plante>();
    private ArrayList<Pheromone> aListPheromone = new ArrayList<Pheromone>();

    private Matrice damier = new Matrice();
    private int nbStade = NB_STADE;

    //Buffer memoire (2eme buffer)
    private Graphics buffer;
    //Image memoire correspondant au buffer
    private Image image;
    
    //Image de fond
    private Image img_fond;

    private Image img_anthill;
    
    //Images des plantes, 3 types et 3 états
    private Image[][] img_plante = new Image[3][3];
    
    public int generateRandom(int max) {
        // Creation d'une instance pour l'aleatoire
        Random randomNum = new Random();
        return 50 + randomNum.nextInt(max);
    }

    public Panneau()
    {
    // Generation des fourmillieres à une position aleatoire avec 50 ouvrieres et 20 guerrieres
    Fourmiliere bel_o_kano = new Fourmiliere(generateRandom(700),generateRandom(700),50,20,5,COLOR_FOURMILIERE[2]);
	aListFourmiliere.add(bel_o_kano);
    
	Fourmiliere bel_o_kan = new Fourmiliere(generateRandom(700),generateRandom(700),50,20,5,COLOR_FOURMILIERE[1]);
	aListFourmiliere.add(bel_o_kan);

	Fourmiliere bel_o_kuni = new Fourmiliere(generateRandom(700),generateRandom(700),50,20,5,COLOR_FOURMILIERE[0]);
	aListFourmiliere.add(bel_o_kuni);

	// Recuperation des images pour les plantes
	img_plante[0][0] = Toolkit.getDefaultToolkit().getImage("images/plante.png");
	img_plante[0][1] = Toolkit.getDefaultToolkit().getImage("images/plante_moyenne.png");
	img_plante[0][2] = Toolkit.getDefaultToolkit().getImage("images/plante_mal.png");
	img_plante[1][0] = Toolkit.getDefaultToolkit().getImage("images/fleur.png");
	img_plante[1][1] = Toolkit.getDefaultToolkit().getImage("images/fleur_moyen.png");
	img_plante[1][2] = Toolkit.getDefaultToolkit().getImage("images/fleur_mal.png");
	img_plante[2][0] = Toolkit.getDefaultToolkit().getImage("images/trefle.png");
	img_plante[2][1] = Toolkit.getDefaultToolkit().getImage("images/trefle_moyen.png");
	img_plante[2][2] = Toolkit.getDefaultToolkit().getImage("images/trefle_mal.png");

	img_fond = Toolkit.getDefaultToolkit().getImage("images/fond.jpg");

	img_anthill = Toolkit.getDefaultToolkit().getImage("images/anthill.png");
    }

    private void displayAllFourmiliere()
    {
	//On parcours le aListeur de Fourmilière
	for(int i=0; i<aListFourmiliere.size();i++)
	    {
		buffer.drawImage(img_anthill, aListFourmiliere.get(i).getX()-COTE,aListFourmiliere.get(i).getY()-COTE, 3*COTE, 3*COTE, this);

		//On parcours toutes les fourmis de la fourmiliere
		for(int j=0;j<aListFourmiliere.get(i).getNbFourmi();j++)
		    {
			buffer.setColor(aListFourmiliere.get(i).getFourmi(j).getColor());
			buffer.fillOval(aListFourmiliere.get(i).getFourmi(j).getX(),aListFourmiliere.get(i).getFourmi(j).getY(), COTE, COTE);
			buffer.setColor(Color.black);
			buffer.drawOval(aListFourmiliere.get(i).getFourmi(j).getX(),aListFourmiliere.get(i).getFourmi(j).getY(), COTE, COTE);
		    }
	    }
    }

	// Incrementation des pheromones
    public void addStade()
    {
	if(nbStade < STADE_MAX)
	    nbStade++;
	else
	    System.out.println("Stade maximum atteint");
    }

	// Decrementation des pheromones
    public void dimStade()
    {
	if(nbStade > NB_STADE-1)
	    nbStade--;
	else
	    System.out.println("Stade minimum atteint");
    }
    
    // Lors de l'appui sur le bouton Bombe, tue toutes les fourmis et change le fond de la carte
    public void killAll()
    {
	for(int i=0; i<aListFourmiliere.size();i++)
	    {
		for(int j=0; j<aListFourmiliere.get(i).getNbFourmi();j++)
		    {
			try
			    {
				aListFourmiliere.get(i).killFourmi(j);
			    }
			catch(Placement e)
			    {
				damier.killFourmi(e);
			    }
		    }
		aListFourmiliere.get(i).killAll();
	    }

	for(int i=0; i<aListPlante.size();i++)
	    aListPlante.get(i).setTaille(5);

	img_fond = Toolkit.getDefaultToolkit().getImage("images/mort.jpg");

    }

	// Ajout des plantes sur le plateau de jeu
    private void displayAllPlante()
    {
	//On parcours le aListeur de plante
	for(int i=0; i<aListPlante.size();i++)
	    {
		Plante a = aListPlante.get(i);
		switch(a.getType())
		    {
		    case 0:
			if(a.getTaille() >= 20)
			    buffer.drawImage(img_plante[0][0], a.getX(),a.getY(), COTE, COTE, this);
			else if(a.getTaille() >= 10)
			    buffer.drawImage(img_plante[0][1], a.getX(),a.getY(), COTE, COTE, this);
			else
			    buffer.drawImage(img_plante[0][2], a.getX(),a.getY(), COTE, COTE, this);
			break;
		    case 1:
			if(a.getTaille() >= 20)
			    buffer.drawImage(img_plante[1][0], a.getX(),a.getY(), COTE, COTE, this);
			else if(a.getTaille() >= 10)
			    buffer.drawImage(img_plante[1][1], a.getX(),a.getY(), COTE, COTE, this);
			else
			    buffer.drawImage(img_plante[1][2], a.getX(),a.getY(), COTE, COTE, this);
			break;
		    case 2:
			if(a.getTaille() >= 20)
			    buffer.drawImage(img_plante[2][0], a.getX(),a.getY(), COTE, COTE, this);
			else if(a.getTaille() >= 10)
			    buffer.drawImage(img_plante[2][1], a.getX(),a.getY(), COTE, COTE, this);
			else
			    buffer.drawImage(img_plante[2][2], a.getX(),a.getY(), COTE, COTE, this);
			break;
		    }
	    }
    }

    private void displayAllPheromone()
    {
	//On parcours le aListeur de pheromone
	for(int i=0; i<aListPheromone.size();i++)
	    {
		buffer.setColor(aListPheromone.get(i).getColor());
		buffer.fillRect(aListPheromone.get(i).getX(),aListPheromone.get(i).getY(), COTE, COTE);
	    }
    }

    public void paintComponent(Graphics g)
    {
	if(buffer == null)
	    {
		image = createImage(this.getWidth(),this.getHeight());
		buffer = image.getGraphics();
	    }

	buffer.drawImage(img_fond, 0, 0, this.getWidth(), this.getHeight(), this);

	displayAllPheromone();
	displayAllPlante();
	displayAllFourmiliere();

	g.drawImage(image,0,0,this);
    }

	// Ajout des fourmis et des reserves
    public void info(int[] nbOuv, int[] nbGue, int[] nbCmdt, int[] res)
    {
	for(int i=0; i<aListFourmiliere.size();i++)
	    {
		nbOuv[i] = aListFourmiliere.get(i).getNbOuv();
		nbGue[i] = aListFourmiliere.get(i).getNbGue();
		nbCmdt[i] = aListFourmiliere.get(i).getNbCmdt();
		res[i] = aListFourmiliere.get(i).getReserves();	
	    }
    }

	// Ajout des fourmis ouvrieres
    public void addFourmiOuv(int i)
    {
	if(i>= 0 && i < aListFourmiliere.size())
	    aListFourmiliere.get(i).addFourmiOuv();
	else
	    System.out.println("Error : fourmiliere non reconnue");
    }

	// Ajout des fourmis guerrieres
    public void addFourmiGue(int i)
    {
	if(i>= 0 && i < aListFourmiliere.size())
	    aListFourmiliere.get(i).addFourmiGue();
	else
	    System.out.println("Error : fourmiliere non reconnue");
    }

	// Ajout des fourmis commandantes
    public void addFourmiCmdt(int i)
    {
	if(i>= 0 && i < aListFourmiliere.size())
	    aListFourmiliere.get(i).addFourmiCmdt();
	else
	    System.out.println("Error : fourmiliere non reconnue");
    }
    
	// Recuperation du niveau de pheromones
    public int getStade()
    {
	return nbStade;
    }

	// Images du fond de la carte
    public void changeDecor(int n)
    {
	if(n == 0)
	    img_fond = Toolkit.getDefaultToolkit().getImage("images/fond.jpg");
	else if(n == 1)
	    img_fond = Toolkit.getDefaultToolkit().getImage("images/fond2.jpg");
    }

    public void update()
    {
	//On bouge les fourmi
	for(int i=0; i<aListFourmiliere.size();i++)
	    {
		for(int j=0; j<aListFourmiliere.get(i).getNbFourmi();j++)
		    {
			try
			    {
				aListFourmiliere.get(i).actionFourmi(j);
			    }
			catch(Placement e)
			    {
				damier.update(e,aListFourmiliere,aListPheromone);

				if(e.phe != null)
				    aListPheromone.add(e.phe);
			    }
		    }
	    }

	//On met à jour les plantes
	for(int i=0; i<aListPlante.size();i++)
	    {
		try
		    {
			aListPlante.get(i).grandir();
			//On teste pour lancer une exeption si elle est morte et mettre ainsi à jour le damier
			aListPlante.get(i).testLife();
		    }
		catch(Placement e)
		    {
			damier.loadPlante(e);
			//Si on arrive ça veut dire que la plante est forcément morte donc on l'enlève du aListeur
			aListPlante.remove(i);
		    }
	    }

	//On met à jour les pheromones
	for(int i=0; i<aListPheromone.size();i++)
	    {
		try
		    {
			if(aListPheromone.get(i) instanceof PheromoneNourriture)
			    aListPheromone.get(i).dissiper();
			else if(aListPheromone.get(i) instanceof PheromoneAlerte)
			    {
				int st = ((PheromoneAlerte)aListPheromone.get(i)).getStade();
				if(st > 0 && st < nbStade && aListPheromone.get(i).getTemps() != DUREE_VIE_PHERO) 
				    {
					damier.updatePheroAlerte(aListPheromone.get(i),aListPheromone);
					((PheromoneAlerte)aListPheromone.get(i)).propager();
				    }
				aListPheromone.get(i).dissiper();
			    }

			aListPheromone.get(i).testLife();
		    }
		catch(Placement e)
		    {
			damier.updatePhero(e);
			//Si on arrive ici la pheromone est forcement morte
			aListPheromone.remove(i);
		    }
	    }
	repaint();
    }   

public void readFile()
    {
	String nameFic = "data/Plante.data";
		
	//lecture du fichier texte	
	try
	    {
		InputStream ips=new FileInputStream(nameFic); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		int tmp_x=0, tmp_y=0;
		int cpt;
	
		while ((ligne=br.readLine())!=null)
		    {
			cpt=0;
			while (cpt < 80)
			    {
				if(Integer.parseInt(ligne.substring(cpt,cpt+1)) == 1)
				    {
					Plante p = new Plante(tmp_x,tmp_y,TAILLE_PLANTE);
					aListPlante.add(p);
					
					try
					    {
						p.placement();	
					    }
					catch(Placement e)
					    {
						damier.loadPlante(e);
					    }
				    }
				cpt++;
				tmp_x += COTE;
			    }
			tmp_y += COTE;
			tmp_x = 0;
		    }
		br.close(); 
	    }		
	catch (Exception e)
	    {
		System.out.println(e.toString());
	    }
    }       
}
