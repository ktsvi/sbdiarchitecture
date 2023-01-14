package urifia.gaml.architecture.sbdi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
// Une classe pour stocker une ar�te de graphe
/*class Edgetkg
{
    int src, dest, weight;
 
    Edgetkg(int src, int dest, int weight)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}
 
// Une classe pour stocker les n�uds de la liste d'adjacence
class Node
{
    int value, weight;
 
    Node(int value, int weight)
    {
        this.value = value;
        this.weight = weight;
    }
 
    @Override
    public String toString() {
        return this.value + " (" + this.weight + ")";
    }
}
 
// Une classe pour repr�senter un objet graphique
class Topographknowledge2
{
    // Une liste de listes pour repr�senter une liste de contigu�t�
    List<List<Node>> adjList = new ArrayList<>();
 
    // Constructor to build a topological knowledge graph
    public Topographknowledge2(List<Edgetkg> edges)
    {
        // trouve le sommet num�rot� maximum
        int n = 0;
        for (Edgetkg e: edges) {
            n = Integer.max(n, Integer.max(e.src, e.dest));
        }
 
        // alloue de la m�moire pour la liste de contigu�t�
        for (int i = 0; i <= n; i++) {
            adjList.add(i, new ArrayList<>());
        }
 
        // ajoute des ar�tes au graphe orient�
        for (Edgetkg e: edges)
        {
            // alloue un nouveau n�ud dans la liste de contigu�t� de src � dest
            adjList.get(e.src).add(new Node(e.dest, e.weight));
 
            // d�commentez sous la ligne pour un graphe non orient�
 
            // alloue un nouveau n�ud dans la liste de contigu�t� de dest � src
            // adjList.get(e.dest).add(new Node(e.src, e.weight));
        }
    }
 
    // Fonction pour imprimer la repr�sentation de la liste d'adjacence d'un graphe
    public static void printGraph(Topographknowledge2 graph)
    {
        int src = 0;
        int n = graph.adjList.size();
 
        while (src < n)
        {
            // affiche le sommet courant et tous ses sommets voisins
            for (Node edge: graph.adjList.get(src)) {
                System.out.printf("%d ��> %s\t", src, edge);
            }
 
            System.out.println();
            src++;
        }
    }

    public static void main (String[] args)
    {
        // Entr�e : liste des ar�tes dans un digraphe pond�r� (selon le sch�ma ci-dessus)
        // tuple `(x, y, w)` repr�sente une ar�te de `x` � `y` ayant le poids `w`
        List<Edgetkg> edges = Arrays.asList(
                new Edgetkg(0, 1, 6), new Edgetkg(1, 2, 7), new Edgetkg(2, 0, 5),
                new Edgetkg(2, 1, 4), new Edgetkg(3, 2, 10), new Edgetkg(4, 5, 1),
                new Edgetkg(5, 4, 3));
 
        // construit un graphe � partir de la liste d'ar�tes donn�e
        Topographknowledge2 graph = new Topographknowledge2(edges);
 
        // affiche la repr�sentation de la liste d'adjacence du graphe
        Topographknowledge2.printGraph(graph);
    }
}
*/
