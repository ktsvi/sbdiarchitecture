package urifia.gaml.architecture.sbdi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
// Une classe pour stocker une arête de graphe
class Edge
{
    int src, dest;
 
    Edge(int src, int dest)
    {
        this.src = src;
        this.dest = dest;
    }
}
 
// Une classe pour représenter un objet graphique
class Graph
{
    // Une liste de listes pour représenter une liste de contiguïté
    List<List<Integer>> adjList = new ArrayList<>();
 
    // Constructeur pour construire un graphe
    public Graph(List<Edge> edges)
    {
        // trouve le sommet numéroté maximum
        int n = 0;
        for (Edge e: edges) {
            n = Integer.max(n, Integer.max(e.src, e.dest));
        }
 
        // alloue de la mémoire pour la liste de contiguïté
        for (int i = 0; i <= n; i++) {
            adjList.add(i, new ArrayList<>());
        }
 
        // ajoute des arêtes au graphe orienté
        for (Edge current: edges)
        {
            // alloue un nouveau nœud dans la liste de contiguïté de src à dest
            adjList.get(current.src).add(current.dest);
 
            // décommentez sous la ligne pour un graphe non orienté
 
            // alloue un nouveau nœud dans la liste de contiguïté de dest à src
            // adjList.get(current.dest).add(current.src);
        }
    }
 
    // Fonction pour imprimer la représentation de la liste d'adjacence d'un graphe
    public static void printGraph(Graph graph)
    {
        int src = 0;
        int n = graph.adjList.size();
 
        while (src < n)
        {
            // affiche le sommet courant et tous ses sommets voisins
            for (int dest: graph.adjList.get(src)) {
                System.out.print("(" + src + " ——> " + dest + ")\t");
            }
            System.out.println();
            src++;
        }
    }

 
  /*  public static void main (String[] args)
    {
        // Entrée : liste des arêtes dans un digraphe (selon le schéma ci-dessus)
        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2),
                new Edge(2, 0), new Edge(2, 1), new Edge(3, 2),
                new Edge(4, 5), new Edge(5, 4));
 
        // construit un graphe à partir de la liste d'arêtes donnée
        Graph graph = new Graph(edges);
 
        // affiche la représentation de la liste d'adjacence du graphe
        Graph.printGraph(graph);
    }*/

}
