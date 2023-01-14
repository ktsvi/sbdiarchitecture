package urifia.gaml.architecture.sbdi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
// Une classe pour stocker une arête du topokgraphe
class Edgetkg
{   
	Geometry src, dest;
	SpatialRelationship weight;
 
    Edgetkg(Geometry src, Geometry dest, SpatialRelationship weight)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}
 
// Une classe pour stocker les nœuds de la liste d'adjacence
class Nodetkg
{
	Geometry value; 
	SpatialRelationship weight;
 
    Nodetkg(Geometry value, SpatialRelationship weight)
    {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.value.getId_geometry()+"-"+this.value.getName_geometry() + " (" + this.weight.sr_name + ")";
    }
}
 
// Une classe pour représenter la carte mentale de l'agent (objet graphique)
class Topographknowledge
{
    // Une liste de listes pour représenter une liste de contiguïté
    List<List<Nodetkg>> adjList = new ArrayList<>();
 
    // Constructor to build a topological knowledge graph
    public Topographknowledge(List<Edgetkg> edges)
    {
        // trouve le sommet numéroté maximum
        int n = 0; 
        for (Edgetkg e: edges) {
            n = Integer.max(n, Integer.max(e.src.getId_geometry() , e.dest.getId_geometry()));
        }
 
        // alloue de la mémoire pour la liste de contiguïté
        for (int i = 0; i <= n; i++) {
            adjList.add(i, new ArrayList<>());
        }
 
        // ajoute des arêtes au graphe orienté
        for (Edgetkg e: edges)
        {
            // alloue un nouveau nœud dans la liste de contiguïté de src à dest
            adjList.get(e.src.getId_geometry()).add(new Nodetkg(e.dest, e.weight));
 
            // décommentez sous la ligne pour un graphe non orienté
 
            // alloue un nouveau nœud dans la liste de contiguïté de dest à src
            // adjList.get(e.dest).add(new Nodetkg(e.src, e.weight));
        }
    }
 
    // Fonction pour imprimer la représentation de la liste d'adjacence d'un graphe
    public static void printGraph(Topographknowledge graph)
    {
        int src = 0;
        int n = graph.adjList.size();
       // System.out.printf("Taille de la carte mentale de l'agent : %d",n);
        while (src < n)
        {
            // affiche le sommet courant et tous ses sommets voisins
            for (Nodetkg edge: graph.adjList.get(src)) {
                System.out.printf("%d ——> %s\t\n", src , edge);
            }

          //  System.out.println();
            src++;
        }
    }
/*
    public static void main (String[] args)
    {
    	Geometry geom1 = new Geometry(1,"Road1", "Line");
    	Geometry geom2 = new Geometry(2,"Subdivision", "Polygon");
    	Geometry geom3 = new Geometry(3,"Building", "Polygon");
    	
    	int[][] DC= {{0,0},{0,0}}; 
    	int[][] EC= {{1,0},{0,0}};
    	int[][] PO= {{1,1},{1,1}};
    	SpatialRelationship weight =new SpatialRelationship("DC", DC);
    	SpatialRelationship weight2 =new SpatialRelationship("EC", EC);
    	SpatialRelationship weight3 =new SpatialRelationship("PO", PO);

        // Entrée : liste des arêtes dans un digraphe pondéré (selon le schéma ci-dessus)
        // tuple `(x, y, w)` représente une arête de `x` à `y` ayant le poids `w`
        List<Edgetkg> edges = Arrays.asList(
                new Edgetkg(geom1, geom2, weight), new Edgetkg(geom1, geom3, weight2), new Edgetkg(geom3, geom2, weight3));
 
        // construit un graphe à partir de la liste d'arêtes donnée
        Topographknowledge graph = new Topographknowledge(edges);
 
        // affiche la représentation de la liste d'adjacence du graphe
        Topographknowledge.printGraph(graph);
    } */
}

