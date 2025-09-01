import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// _______________________________________________       ________________________________________________
// Number of Nodes: 6                             |     |  Number of Nodes: 6                            |
// Number of Edges: 8                             |     |  Number of Edges: 7                            |
// 0 1 4                                          |     |  0 1 2                                         |
// 0 2 4                                          |     |  0 4 1                                         |
// 1 2 2                                          |     |  4 5 4                                         |
// 2 3 3                                          |     |  4 2 2                                         |
// 2 4 1                                          |     |  1 2 3                                         |
// 2 5 6                                          |     |  2 3 6                                         |
// 3 5 2                                          |     |  5 3 1                                         |
// 4 5 3                                          |     |  Soure Node: 0                                 |
// -------------------                            |     |  -------------------                           |
//0 -> {1, 4} {2, 4}                              |     |  0 -> {1, 2} {4, 1}                            |
// 1 -> {2, 2}                                    |     |  1 -> {2, 3}                                   |
// 2 -> {3, 3} {4, 1} {5, 6}                      |     |  2 -> {3, 6}                                   |
// 3 -> {5, 2}                                    |     |  3 ->                                          |
// 4 -> {5, 3}                                    |     |  4 -> {5, 4} {2, 2}                            |
// 5 ->                                           |     |  5 -> {3, 1}                                   |
// ----------------------------------             |     |  --------------------------------              |
// Shortest Path to each Node from                |     |  Shortest Path to each Node from               |
//    Source Node (Distance Array): 0 4 4 7 5 8   |     |     Source Node (Distance Array): 0 2 3 6 1 5  |
// _______________________________________________|     |________________________________________________|



// ___________________  Graph  ________________________________________________
class Graph_adjArrayList {
    ArrayList<ArrayList<ArrayList<Integer>>>  graph_ArrayList;
    int nodes, edges;

    Graph_adjArrayList(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
        graph_ArrayList = new ArrayList<>();
        for (int i=0; i<=nodes; i++) {                  // create empty arrayList of 
            graph_ArrayList.add(new ArrayList<>());     // "node size" to add edge and neighbor later
        }
    }

    void addEdges(int edge1, int edge2, int weight) {
        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(edge2);   // neighbor
        edge.add(weight);  // weight
        graph_ArrayList.get(edge1).add(edge);
    }
    void printGraph() {
        for(int i=0; i<nodes; i++) {
            System.out.print(i + " -> ");
            for (ArrayList<Integer> neighbor : graph_ArrayList.get(i)) {
                System.out.print("{" + neighbor.get(0) + ", " + neighbor.get(1) + "} ");
            }
            System.out.println();
        }
    }
}
// ___________________________________________________________________

class Pair {
    int distance, node;
    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

// ___________________  With Directed Weighted Graph's ArrayList  _______________________
// Time Complexity: O(edge * log nodes)  
// Space Complexity:  O(nodes) +  O(edges)   -->>  distArray + priorityQueue
// ---------------------------------------------------------------------
class Dijktras_Algorithm_usingSET {
    public int[] Dijktras_Algorithm(int srcNode, ArrayList<ArrayList<ArrayList<Integer>>>  arr, int nodes) {
        // HashSet || TreeSet -->> Not possible in Java 
        // HashSet || TreeSet -->> Not possible in Java 
        // HashSet || TreeSet -->> Not possible in Java 
    }
}
// ______________________________________________________________________________



class Graph_26_Dijktras_Algorithm_usingSET {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();

        Graph_adjArrayList graph = new Graph_adjArrayList(nodes, edges);
        for(int i=0; i<edges; i++) {
            int edge = scanner.nextInt();
            int neighbour = scanner.nextInt();
            int weight = scanner.nextInt();
            
            graph.addEdges(edge, neighbour, weight);
        }
        System.out.print("Soure Node: ");
        int srcNode = scanner.nextInt();
        System.out.println("-------------------");
        graph.printGraph();

        System.out.println("--------------------------------");
        Dijktras_Algorithm_usingSET shortestPath = new Dijktras_Algorithm_usingSET();
        int[] distArr = shortestPath.Dijktras_Algorithm(srcNode, graph.graph_ArrayList, nodes); 
        System.out.print("Shortest Path to each Node from Source Node (Distance Array): ");
        for(int data: distArr) {
            System.out.print(data + " ");
        }

        
        scanner.close();
    } 
} 