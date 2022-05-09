import java.awt.*;
import java.util.*;

class Plante implements Constantes
{

    private int x, y;
    private int taille;
    private static int taille_max = 50;
    private int temps_evo;
    private Placement pl = new Placement();
    private int type;
   
    public int generateRandom(int min, int max) {
        // create instance of Random class
        Random randomNum = new Random();
        return min + randomNum.nextInt(max);
    }
   
    public Plante(int x, int y, int taille)
    {
	this.x = generateRandom(50, 750);
	this.y = generateRandom(50, 750);
	//this.x = x;
	//this.y = y;
	this.taille = taille;
	type = (int) (Math.random() * 3);

	pl.obj = this;
	pl.new_x = this.x;
	pl.new_y = this.y;
	pl.live = true;
	temps_evo = 0;
    }

    public int getType()
    {
	return type;
    }

    public void placement() throws Placement
    {
	throw pl;
    }

    public void testLife() throws Placement
    {
	if(taille <= 0)
	    {
		pl.live = false;
		throw pl;
	    }
    }

    public void grandir()
    { 
	temps_evo ++;
	if(temps_evo == 20 && taille<taille_max)
	    {
		taille ++;		
		temps_evo = 0;
	    }
    }

    public void diminue()
    { 
	taille --;
    } 

    public int getX()
    {
	return x;
    }

    public int getY()
    {
	return y;
    }

    public int getTaille()
    {
	return taille;
    }

    public void setTaille(int t)
    {
	taille = t;
    }
}
