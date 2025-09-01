import java.util.ArrayList;
import java.util.Scanner;


// ________________________
// Number of Nodes: 5      |________________________
// Number of Edges: 6      |                        |
// Edge1: 1                |    0 0 0 0 0           |
// Edge2: 2                |    0 0 0 0 1           |
// Edge1: 1                |    0 0 0 0 0           |
// Edge2: 3                |    0 0 0 0 0           |
// Edge1: 3                |    0 1 0 0 0           |
// Edge2: 4                |________________________|
// Edge1: 2                |    1 -> 4              |
// Edge2: 4                |    2 -> 5 5 5 5 5      |
// Edge1: 2                |    3 ->                |
// Edge2: 5                |    4 -> 1              |
// Edge1: 4                |    5 -> 2 2 2 2 2      |
// Edge2: 5                |________________________|
// ________________________|
// ________________________|
// Number of Nodes: 5      |
// Number of Edges: 6      |
// Edge: 1                 |________________________
// Neighbour: 2            |                        |
// Weight: 4               |   0  4  6  0  0        |                                                            
// Edge: 1                 |   4  0  0  1  9        |                                    
// Neighbour: 3            |   6  0  0  7  0        |                                    
// Weight: 6               |   0  1  7  0  8        |                                    
// Edge: 3                 |   0  9  0  8  0        |                                    
// Neighbour: 4            |________________________|_______________________________ 
// Weight: 7               |                                                        |
// Edge: 2                 |   1 -> 2(weight: 4) , 3(weight: 6)                     |
// Neighbour: 4            |   2 -> 1(weight: 4) , 4(weight: 1) , 5(weight: 9)      |
// Weight: 1               |   3 -> 1(weight: 6) , 4(weight: 7)                     |
// Edge: 2                 |   4 -> 3(weight: 7) , 2(weight: 1) , 5(weight: 8)      |
// Neighbour: 5            |   5 -> 2(weight: 9) , 4(weight: 8)                     |
// Weight: 9               |________________________________________________________|
// Edge: 4                 |
// Neighbour: 5            |
// Weight: 8               |
// ________________________|



// ___________________  BRUTE  ___________________________________________
// Time Complexity: O()       
// Space Complexity:  O(n^2)   
// ----------------------------------------------------
class GraphBrute_adjacencyMatrix {
    int [][] graph_adjMatrix;
    int nodes;

    GraphBrute_adjacencyMatrix(int nodes) {
        this.nodes = nodes;
        graph_adjMatrix = new int[nodes+1][nodes+1];
    }

    void addEdges(int edge1, int edge2) {
        graph_adjMatrix[edge1][edge2] = 1;
        graph_adjMatrix[edge2][edge1] = 1;
    }
    void removeEdges(int edge1, int edge2) {
        graph_adjMatrix[edge1][edge2] = 0;
        graph_adjMatrix[edge2][edge1] = 0;
    }
    void printGraph() {
        for(int i=0; i<nodes; i++) {
            for(int j=0; j<nodes; j++) {
                System.out.print(graph_adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// ----------------------------------------------------------
// ----------------------------------------------------------
class WeightedGraphBrute_adjacencyMatrix {
    int [][] graph_adjMatrix;
    int nodes;

    WeightedGraphBrute_adjacencyMatrix(int nodes) {
        this.nodes = nodes;
        graph_adjMatrix = new int[nodes+1][nodes+1];
    }

    void addEdges(int edge1, int edge2, int weight) {
        graph_adjMatrix[edge1][edge2] = weight;
        graph_adjMatrix[edge2][edge1] = weight;
    }
    void removeEdges(int edge1, int edge2) {
        graph_adjMatrix[edge1][edge2] = 0;
        graph_adjMatrix[edge2][edge1] = 0;
    }
    void printGraph() {
        for(int i=1; i<=nodes; i++) {
            for(int j=1; j<=nodes; j++) {
                System.out.print(graph_adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// _____________________________________________________________________________

  
     
// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O()       
// Space Complexity:  Around --> O(2 * edges)       
// ---------------------------------------------------------------------
class GraphOptimal_ArrayList {
    ArrayList<ArrayList<Integer>> graph_ArrayList;
    int nodes, edges;

    GraphOptimal_ArrayList(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
        graph_ArrayList = new ArrayList<>();
        for (int i=0; i<=nodes; i++) {
            graph_ArrayList.add(new ArrayList<>());
        }
    }

    void addEdges(int edge1, int edge2) {
        graph_ArrayList.get(edge1).add(edge2);
        graph_ArrayList.get(edge2).add(edge1);
    }
    void addEdges_DirectedGraph(int edge1, int edge2) {
        graph_ArrayList.get(edge1).add(edge2);
    }
    void printGraph() {
        for(int i=1; i<=nodes; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : graph_ArrayList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

// ----------------------------------------------------------
// ----------------------------------------------------------
class EdgesWeight_Pair {
    int to, weight;

    EdgesWeight_Pair(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class WeightedGraphOptimal_ArrayList {
    ArrayList<ArrayList<EdgesWeight_Pair>> graph_ArrayList;
    int nodes, edges;

    WeightedGraphOptimal_ArrayList(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
        graph_ArrayList = new ArrayList<>();
        for (int i=0; i<=nodes; i++) {
            graph_ArrayList.add(new ArrayList<>());
        }
    }

    void addEdges(int edge, int neighbour, int weight) {
        graph_ArrayList.get(edge).add(new EdgesWeight_Pair(neighbour, weight));
        graph_ArrayList.get(neighbour).add(new EdgesWeight_Pair(edge, weight));
    }
    void addEdges_DirectedGraph(int edge, int neighbour, int weight) {
        graph_ArrayList.get(edge).add(new EdgesWeight_Pair(neighbour, weight));
    }
    void printGraph() {
        for (int i = 1; i <= nodes; i++) {
            System.out.print(i + " -> ");
            for (EdgesWeight_Pair edge : graph_ArrayList.get(i)) {
                System.out.print(edge.to + "( weight: " + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}
// ______________________________________________________________________________





class Graph_01_Basics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();

        GraphBrute_adjacencyMatrix graphBrute = new GraphBrute_adjacencyMatrix(nodes);
        GraphOptimal_ArrayList graphOptimal = new GraphOptimal_ArrayList(nodes, edges);
        for(int i=0; i<edges; i++) {
            System.out.print("Edge: ");
            int edge = scanner.nextInt();
            System.out.print("Neighbour: ");
            int neighbour = scanner.nextInt();

            graphBrute.addEdges(edge, neighbour);
            graphOptimal.addEdges(edge, neighbour);
        }
        System.out.println("-------------------");
        graphBrute.printGraph();
        System.out.println("-------------------");
        graphOptimal.printGraph();
        System.out.println("-------------------");

        WeightedGraphBrute_adjacencyMatrix weightedGraph_Brute = new WeightedGraphBrute_adjacencyMatrix(nodes);
        WeightedGraphOptimal_ArrayList weightedGraph_Optimal = new WeightedGraphOptimal_ArrayList(nodes, edges);
        for(int i=0; i<edges; i++) {
            System.out.print("Edge: ");
            int edge = scanner.nextInt();
            System.out.print("Neighbour: ");
            int neighbour = scanner.nextInt();
            System.out.print("Weight: ");
            int weight = scanner.nextInt();

            weightedGraph_Brute.addEdges(edge, neighbour, weight);
            weightedGraph_Optimal.addEdges(edge, neighbour, weight);
        }
        System.out.println("-------------------");
        weightedGraph_Brute.printGraph();
        weightedGraph_Optimal.printGraph();
        
        scanner.close();
    } 
} 