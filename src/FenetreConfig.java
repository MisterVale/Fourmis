import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class FenetreConfig extends JFrame implements Constantes,ActionListener
{
    // Déclaration des bouttons
    private PanneauCfg panel = new PanneauCfg();
    private JButton[] JStade = {new JButton("Augmenter nuage alerte"), new JButton("Diminuer nuage alerte")};
    private JButton[] JAdd1 = {new JButton("Ajouter une ouvriere rouge"), new JButton("Ajouter une guerriere rouge"), new JButton("Ajouter une commandante rouge")};
    private JButton[] JAdd2 = {new JButton("Ajouter une ouvriere bleue"), new JButton("Ajouter une guerriere bleue"), new JButton("Ajouter une commandante bleue")};
    private JButton[] JAdd3 = {new JButton("Ajouter une ouvriere verte"), new JButton("Ajouter une guerriere verte"), new JButton("Ajouter une commandante verte")};
    private JButton JBombe = new JButton("Bombe");
    // Boutton à image : déclaration differente (pas de string)
    private JButton[] JDecor = {new JButton(),new JButton()};  
    // Label pour afficher la taille du nuage de phéromones
    private JLabel tailleNuage= new JLabel("");
    // Icone de la fenetre de configuration
    private Image icon;
    // Ecritures utilisees: une normale et une plus grande
    private Font font;
    private Font fontG;

    // Constructeur : initialisations
    public FenetreConfig()
    {
	// On cree une fentetre
	super("Parametres");
	// On charge une icone et on l applique
	icon = Toolkit.getDefaultToolkit().getImage("images/iconCfg.png");
	setIconImage(icon);
	// taille de la fenetre
	setBounds(20+LONG+8,20,LONG_CFG,HAUT+28);
	// Permet de fermer la fenetre correctement
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	try
	    {
		// Lit les polices d ecriture
		File fis = new File("fonts/NeutonReg-2010.9.28-1.51.ttf");
		
		font = Font.createFont(Font.TRUETYPE_FONT, fis);
		font = font.deriveFont((float)12.0);
		fontG = Font.createFont(Font.TRUETYPE_FONT, fis);
		fontG = font.deriveFont((float)16.0);
	
	    }
	catch (Exception e) 
	    {
		System.out.println("Impossible de charger la police");
	    }
	

	panel.setLayout(null); 
	// taille et position des bouttons
	// ---------- bouttons de l'ajout de fourmis bleues
	JAdd1[0].setBounds((LONG_CFG/2)-100,130,200,25);
	JAdd1[1].setBounds((LONG_CFG/2)-100,160,200,25);
	JAdd1[2].setBounds((LONG_CFG/2)-100,190,200,25);
	// ---------- bouttons de l'ajout de fourmis vertes
	JAdd2[0].setBounds((LONG_CFG/2)-100,310,200,25);
	JAdd2[1].setBounds((LONG_CFG/2)-100,340,200,25);
	JAdd2[2].setBounds((LONG_CFG/2)-100,370,200,25);
	// ---------- bouttons de l'ajout de fourmis rouges
	JAdd3[0].setBounds((LONG_CFG/2)-100,490,200,25);
	JAdd3[1].setBounds((LONG_CFG/2)-100,520,200,25);
	JAdd3[2].setBounds((LONG_CFG/2)-100,550,200,25);
	// ---------- boutton de destruction
	JBombe.setBounds((LONG_CFG/2)-60,750,120,25);
	// ---------- bouttons de changement de decor
	JDecor[0].setBounds(40,690,75,35);
	JDecor[1].setBounds(150,690,75,35);
	// ---------- bouttons d'agrandissement / diminution du nuage de phéromones
	JStade[0].setBounds((LONG_CFG/2)-90,590,180,25);
	JStade[1].setBounds((LONG_CFG/2)-90,620,180,25);
	// -------------------- Label ou est indique la taille du nuage de pheromones 
	tailleNuage.setBounds((LONG_CFG/2),645,40,40);



	// Image pour les bouttons pour changer de decor
	JDecor[0].setIcon(new ImageIcon("images/fond.jpg"));
	JDecor[1].setIcon(new ImageIcon("images/fond2.jpg"));

	tailleNuage.setFont(fontG);
	tailleNuage.setText(""+NB_STADE);
	JBombe.setFont(font);
	// on associe un evenement au boutton
	JBombe.addActionListener(this);

	// on ajoute le boutton au panneau
	panel.add(JBombe);
	panel.add(tailleNuage);

	for(int i=0; i < 3; i++)
	    {
		// on change la police du boutton
		JAdd1[i].setFont(font);
		JAdd2[i].setFont(font);
		JAdd3[i].setFont(font);
		
		// on ajoute le boutton au panneau
		panel.add(JAdd1[i]);
		panel.add(JAdd2[i]);
		panel.add(JAdd3[i]);
		
		// on associe un evenement a chaque boutton
		JAdd1[i].addActionListener(this);
		JAdd2[i].addActionListener(this);
		JAdd3[i].addActionListener(this);
	    }

	for(int i=0; i < 2; i++)
	    {
	    JDecor[i].setFont(font);
	    panel.add(JDecor[i]);
	    JDecor[i].addActionListener(this);
	    }

	for(int i=0; i < 2; i++)
	    {
		JStade[i].setFont(font);
		panel.add(JStade[i]);
		JStade[i].addActionListener(this);
	    }

	setContentPane(panel);
	setVisible(true);
	setResizable(false);

    }

    public PanneauCfg getPanneau()
    {
	return panel;
    }

    public void actionPerformed(ActionEvent e)
    {

	// Selon où l'utilisateur clique, on récupère la source
	// et on lance la fonction correspondante
	Object source = e.getSource();
	if (source == JStade[0])
	    {
		panel.addStade();
		tailleNuage.setText(""+panel.getStade());
	    }
	else if(source == JStade[1])
	    {
		panel.dimStade();
		tailleNuage.setText(""+panel.getStade());
	    }
	else if (source == JAdd1[0])
	    panel.addFourmiOuv(0);
	else if(source == JAdd1[1])
	    panel.addFourmiGue(0);
    else if(source == JAdd1[2])
	    panel.addFourmiCmdt(0);
	else if (source == JAdd2[0])
	    panel.addFourmiOuv(1);
	else if(source == JAdd2[1])
	    panel.addFourmiGue(1);
    else if(source == JAdd2[2])
	    panel.addFourmiCmdt(1);
	else if (source == JAdd3[0])
	    panel.addFourmiOuv(2);
	else if(source == JAdd3[1])
	    panel.addFourmiGue(2);
    else if(source == JAdd3[2])
	    panel.addFourmiCmdt(2);
	else if(source == JBombe)
	    panel.killAll();
	else if(source == JDecor[0])
	    panel.changeDecor(0);
	else if(source == JDecor[1])
	    panel.changeDecor(1);
    }
}
