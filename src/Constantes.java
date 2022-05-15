import java.awt.*;

interface Constantes
{
    // Longueur de la fenetre
    public static final int LONG = 800;
    public static final int LONG_CFG = 270;
    // Largeur de la fenetre
    public static final int HAUT = 800;
    // temps en ms entre deux images
    public static final int DELAY = 50;
    // Longueur et largeur de chaque element (plante, pheromone ...)
    public static final int COTE = 10;


    // nombre de stade de la progression des pheromones alertes
    // plus on l'augmente, plus le nuage de pheromone sera grand
    public static final int NB_STADE = 4;
    public static final int STADE_MAX = 10;


    // taille maximum de la plante
    public static final int TAILLE_PLANTE = 50;
    // Duree de vie d une pheromone
    public static final int DUREE_VIE_PHERO = 50;
    // Couleurs possibles pour les fourmilieres
    public static final Color[] COLOR_FOURMILIERE = {new Color(1,255,0,250), new Color(0,1,255,250), new Color(255,0,1,250)};
}
