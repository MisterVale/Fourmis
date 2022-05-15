import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

public class PanneauCfg extends JPanel implements Constantes
{
    //temps[0] contient les millisecondes
    //temps[1] contient les secondes
    //temps[2] contient les minutes
    //temps[3] contient les heures
    private int[] temps = {0,0,2,0};

    private int[] nbOuv = {0,0,0};
    private int[] nbGue = {0,0,0};
    private int[] nbCmdt = {0,0,0};
    private int[] res = {0,0,0};

    private Panneau simul;

    //Buffer memoire (2eme buffer)
    private Graphics buffer;
    //Image memoire correspondant au buffer
    private Image image;
    
    //Image de fond
    private Image img_fond;

    private Font font;
    private Font fontTime;

    // Constructeur 
    public PanneauCfg()
    {
	img_fond = Toolkit.getDefaultToolkit().getImage("images/fondpara.png");

	try
	    {
		// chargement de la police d ecriture
		File fis = new File("fonts/NeutonReg-2010.9.28-1.51.ttf");
		
		font = Font.createFont(Font.TRUETYPE_FONT, fis);
		font = font.deriveFont((float)14.0);
		fontTime = Font.createFont(Font.TRUETYPE_FONT, fis);
		fontTime = font.deriveFont((float)20.0);
	    }
	catch (Exception e) 
	    {
		System.out.println("Impossible de charger la police");
	    }
    }

    public void updateTime()
    {
	// Mise a jour du temps
	//temps[0] += DELAY;

	if (temps[3]==0 && temps[2]==0 && temps[1]==0){
		temps[0]=0;
		temps[1]=0;
		temps[2]=0;
		temps[3]=0;
		System.out.println("Fin de partie");
		System.out.println("Points de la fourmilière rouge : " + res[0]);
		System.out.println("Points de la fourmilière bleue : " + res[1]);
		System.out.println("Points de la fourmilière verte : " + res[2]);
		if (res[0] > res[1] && res[0] > res[2]){
			System.out.println("Victoire de la fourmilière rouge !");
		}
		if (res[1] > res[0] && res[1] > res[2]){
			System.out.println("Victoire de la fourmilière bleue !");
		}
		if (res[2] > res[0] && res[2] > res[1]){
			System.out.println("Victoire de la fourmilière verte !");
		}
		while (true) {
			// Boucle infinie pour stopper le timer a la fin de la partie
		}
	}
	if(temps[1]!=0 && temps[2]==0 && temps[3]!=0){
		temps[3]--;
		temps[2]=59;
    }
	if(temps[1]==0 && temps[2]!=0){
		temps[2]--;
		temps[1]=59;
    }
    if(temps[1]==-1 && temps[2]==0){
		temps[2]=0;
		temps[1]=59;
    }
	if(temps[0]==1000)
	    {
		temps[1]--;
		temps[0]=0;
	    }
	 else {
	 	temps[0] += DELAY;
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
	// mise a jour du temps
	updateTime();
	// on choisi sa police, sa couleur et sa position
	buffer.setColor(Color.black);
	buffer.setFont(fontTime);
	buffer.drawString(""+temps[3]+":"+temps[2]+":"+temps[1],LONG_CFG/2-20, 20);

	//1ere fourmiliere
	buffer.setFont(font);
	buffer.drawString("Nombre de Fourmis ouvriere : " + nbOuv[0],10, 60);
	buffer.drawString("Nombre de Fourmis guerriere : " + nbGue[0],10, 80);
	buffer.drawString("Nombre de Fourmis commandant : " + nbCmdt[0],10, 100);
	buffer.drawString("Reserves : " + res[0],10, 120);

	//2eme fourmiliere
	buffer.drawString("Nombre de Fourmis ouvriere : " + nbOuv[1],10, 240);
	buffer.drawString("Nombre de Fourmis guerriere : " + nbGue[1],10, 260);
	buffer.drawString("Nombre de Fourmis commandant : " + nbCmdt[1],10, 280);
	buffer.drawString("Reserves : " + res[1],10, 300);
	
	//3eme fourmiliere
	buffer.drawString("Nombre de Fourmis ouvriere : " + nbOuv[2],10, 420);
	buffer.drawString("Nombre de Fourmis guerriere : " + nbGue[2],10, 440);
	buffer.drawString("Nombre de Fourmis commandant : " + nbCmdt[2],10, 460);
	buffer.drawString("Reserves : " + res[2],10, 480);

	// Rectangle rouge
	buffer.setColor(Color.red);
	buffer.drawRect(2,40,LONG_CFG-8, 175); 
	// Rectangle bleu
	buffer.setColor(Color.blue);
	buffer.drawRect(2,220,LONG_CFG-8, 175); 
	// Rectangle vert
	buffer.setColor(Color.green);
	buffer.drawRect(2,400,LONG_CFG-8, 175); 

	g.drawImage(image,0,0,this);
    }

    // ajouter un stade
    public void addStade()
    {
	simul.addStade();
    }

    public int getStade()
    {
	return simul.getStade();
    }

    public void dimStade()
    {
	simul.dimStade();
    }

    public void addFourmiOuv(int f)
    {
	simul.addFourmiOuv(f);
    }

    public void addFourmiGue(int f)
    {
	simul.addFourmiGue(f);
    }

    public void addFourmiCmdt(int f)
    {
	simul.addFourmiCmdt(f);
    }
    
    public void update(Panneau p)
    {
	// Mise a jour des informations relatives aux fourmilieres
	// dans le panneau de configuration
	simul = p;
	p.info(nbOuv, nbGue, nbCmdt, res);
	repaint();
    }   

    public void killAll()
    {
	simul.killAll();
    }

    public void changeDecor(int n)
    {
	simul.changeDecor(n);
    }

  
}
