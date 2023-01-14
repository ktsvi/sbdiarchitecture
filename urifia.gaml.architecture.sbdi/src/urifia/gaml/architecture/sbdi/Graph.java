package urifia.gaml.architecture.sbdi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
// Une classe pour stocker une ar�te de graphe
class Edge
{
    int src, dest;
 
    Edge(int src, int dest)
    {
        this.src = src;
        this.dest = dest;
    }
}
 
// Une classe pour repr�senter un objet graphique
class Graph
{
    // Une liste de listes pour repr�senter une liste de contigu�t�
    List<List<Integer>> adjList = new ArrayList<>();
 
    // Constructeur pour construire un graphe
    public Graph(List<Edge> edges)
    {
        // trouve le sommet num�rot� maximum
        int n = 0;
        for (Edge e: edges) {
            n = Integer.max(n, Integer.max(e.src, e.dest));
        }
 
        // alloue de la m�moire pour la liste de contigu�t�
        for (int i = 0; i <= n; i++) {
            adjList.add(i, new ArrayList<>());
        }
 
        // ajoute des ar�tes au graphe orient�
        for (Edge current: edges)
        {
            // alloue un nouveau n�ud dans la liste de contigu�t� de src � dest
            adjList.get(current.src).add(current.dest);
 
            // d�commentez sous la ligne pour un graphe non orient�
 
            // alloue un nouveau n�ud dans la liste de contigu�t� de dest � src
            // adjList.get(current.dest).add(current.src);
        }
    }
 
    // Fonction pour imprimer la repr�sentation de la liste d'adjacence d'un graphe
    public static void printGraph(Graph graph)
    {
        int src = 0;
        int n = graph.adjList.size();
 
        while (src < n)
        {
            // affiche le sommet courant et tous ses sommets voisins
            for (int dest: graph.adjList.get(src)) {
                System.out.print("(" + src + " ��> " + dest + ")\t");
            }
            System.out.println();
            src++;
        }
    }

 
  /*  public static void main (String[] args)
    {
        // Entr�e : liste des ar�tes dans un digraphe (selon le sch�ma ci-dessus)
        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2),
                new Edge(2, 0), new Edge(2, 1), new Edge(3, 2),
                new Edge(4, 5), new Edge(5, 4));
 
        // construit un graphe � partir de la liste d'ar�tes donn�e
        Graph graph = new Graph(edges);
 
        // affiche la repr�sentation de la liste d'adjacence du graphe
        Graph.printGraph(graph);
    }*/

}
