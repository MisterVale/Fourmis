# Ant War
Simulation de guerre de fourmis entre trois fourmilières (Projet Java 2A IMERIR)

## Sujet

Trois fourmillières de factions différentes placées aléatoirement sur la carte vont aller chercher des ressources pour gagner des points. 

50 fourmis ouvrières auront pour tâche de suivre les ordres des 5 fourmis "commandants" qui recevront eux mêmes des ordres de la part de la reine disposée dans la fourmillière. Pour les défendre, 20 fourmis guerrières assureront la sécurité des ouvrières pendant leur récolte. Chacune des fourmis est gérée par un Thread.

A chaque récolte une fourmi posera des phéromones afin d'attirer les fourmis alliées pour leur indiquer le point de nourriture. Les fourmis guerrières éliminant des fourmis adverses relacheront elles aussi des phéromones pour prévenir les ouvrières alliées qu'une menace était présente à cette case. La taille des phéromones peut être augmentée ou diminiée grâce à l'interface juxtaposé à la partie en cours. La taille peut varier entre 3 et 10 (Taille de base : 4)

L'ajout de fourmis ouvrières, guerrières et commandantes peut se faire via l'interface juxtaposé à la carte, tout comme le temps restant et les points en temps réél des différentes équipes. Deux images de carte sont disponibles mais cela n'interfère pas le placement des fourmillières ni des ressources à récolter.

Un bouton "Bombe" permet de tuer toutes les fourmis présentes dans le jeu et ainsi geler les scores avant la fin du décompte.

La visualisation de la partie est réalisée sous JavaFx.

Temps d'une partie : 2 minutes

Taille de la carte : 80x80 

Type de victoire : Plus grand nombre de points

Une fois le décompte arrivé à 0, la partie se gèle, laissant toutes les fourmis sur la carte et annonçant les scores des différentes équipes ainsi que le vainqueur de la partie dans la console (Boucle while infinie). 

## Requirements

Dernière version de Java installée (JDK18)

```bash
$ sudo apt-get install openjdk-8-jre
```

## Mise en place

Compilation : 
```bash
javac Case.java Constante.java Fenetre.java FenetreConfig.java Fourmi.java 
FourmiGuerriere.java Fourmilliere.java FourmiOuvriere.java Matrice.java Panneau.java
PanneauCfg.java Pheromone.java PheromoneAlerte.java PheromoneNourriture.java Placement.java
Plante.java Start.java Time.java
```

Lancement de l'application : 
```bash
java Start
```
