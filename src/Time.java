import javax.swing.*;

public class Time extends Thread implements Constantes
{
    private Panneau p_sim;
    private PanneauCfg p_cfg;

    public Time(Panneau p_s, PanneauCfg p_c)
    {
	super();
	p_sim = p_s;
	p_cfg = p_c;
    }

	// Boucle infinie decrementant le timer
    public void run() 
    {
	while(true)
	    {	
		try 
		    {
			sleep(DELAY);
			p_sim.update();
			p_cfg.update(p_sim);
		    }
		catch (InterruptedException e)
		    {return;}
	    }
    }
}
