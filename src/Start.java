
class Start
{
      public static void main (String[] args)
    {	
	Fenetre fourmiz = new Fenetre();
	FenetreConfig cfg = new FenetreConfig();
	Time timer = new Time(fourmiz.getPanneau(),cfg.getPanneau());
	timer.run();
    }
}

