import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame implements Constantes
{
    private Panneau panel = new Panneau();
    private Image icon;
    
    public Fenetre()
    {
	
	super("Foumis Simulator");
	icon = Toolkit.getDefaultToolkit().getImage("images/iconSim.png");
	setIconImage(icon);

	setBounds(20,20,LONG+8,HAUT+28);//8 et 28 pour les bordures pour obtenir la bonne taille de 800x600

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel.readFile();
	setContentPane(panel);
	setVisible(true);
	
	//	setResizable(false);


    }

    public Panneau getPanneau()
    {
	return panel;
    }
}
