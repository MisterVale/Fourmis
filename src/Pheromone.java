import java.awt.*;

abstract class Pheromone implements Constantes
{
    protected int temps;
    protected int x, y;
    protected Fourmiliere f;
    protected Placement pl = new Placement();
    protected Color c;

    protected Pheromone(int x, int y, Fourmiliere f)
    {
	this.x = x;
	this.y = y;
	this.f = f;
	pl.obj = this;
	pl.new_x = x;
	pl.new_y = y;
	pl.live = true;
	temps = DUREE_VIE_PHERO;
    }

    protected void testLife() throws Placement
    {
	if(temps == 0)
	    {
		pl.live = false;
		throw pl;
	    }
    }

    protected int getX()
    {
	return x;
    }

    protected int getTemps()
    {
	return temps;
    }

    protected int getY()
    {
	return y;
    }

    protected Fourmiliere getFourmiliere()
    {
	return f;
    }


    protected void dissiper()
    {
	if(temps > 0)
	    {
		temps--;
	    }

	//Test au cas ou pour eviter une erreur
	if(c.getAlpha()-5 >= 0)
	    c = new Color(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()-5);
    }

    protected Color getColor()
    {
	return c;
    }

}