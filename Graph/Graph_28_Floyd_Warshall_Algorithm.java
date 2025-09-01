import java.util.Scanner;


// __________________________________________________       ___________________________________________________
// Number of Nodes: 6                               |       | Number of Nodes: 6                               |
// Number of Edges: 7                               |       | Number of Edges: 7                               |
// 0  1  6                                          |       | 0  1  5                                          |
// 0  2  2                                          |       | 1  2 -2                                          |
// 0  3  4                                          |       | 1  5 -3                                          |
// 2  1  3                                          |       | 3  2  6                                          |
// 3  1  1                                          |       | 5  3  1                                          |
// 2  4  1                                          |       | 2  4  3                                          |
// 4  1  1                                          |       | 2  4  3                                          |
// Soure Node:  0                                   |       | Soure Node:  0                                   |
// ----------------------------------               |       | ----------------------------------               |
// [0, 1, 6], [0, 2, 2], [0, 3, 4], [2, 1, 3],      |       | [0, 1, 5], [1, 2, -2], [1, 5, -3], [5, 3, 1]     |
// [3, 1, 1], [2, 4, 1], [4, 1, 1]                  |       | [3, 2, 6], [3, 4, -2], [2, 4, 3]                 |
// ----------------------------------               |       | ----------------------------------               |
// Shortest Path to each Node from Every Node       |       | Shortest Path to each Node from Every Node       |
//   (Distance Array)  :                            |       |   (Distance Array)  :                            |
//    0  4  2  4  3  ∞                              |       |     0   5   3   3   1   2                        |
//    ∞  0  ∞  ∞  ∞  ∞                              |       |     ∞   0  -2  -2  -4  -3                        |
//    ∞  2  0  ∞  1  ∞                              |       |     ∞   ∞   0   ∞   3   ∞                        |
//    ∞  1  ∞  0  ∞  ∞                              |       |     ∞   ∞   6   0  -2   ∞                        |
//    ∞  1  ∞  ∞  0  ∞                              |       |     ∞   ∞   ∞   ∞   0   ∞                        |
//    ∞  ∞  ∞  ∞  ∞  0                              |       |     ∞   ∞   7   1   ∞   0                        |
// _________________________________________________|       | _________________________________________________|
       

// ___________________  Graph  ________________________________________________
class Graph_adjacencyMatrix {
    int[][] graph_adjMatrix;
    int nodes;

    Graph_adjacencyMatrix(int nodes) {
        this.nodes = nodes;
        graph_adjMatrix = new int[nodes][nodes];   // Initialize matrix with 0

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (i == j) graph_adjMatrix[i][j] = 0;
                else graph_adjMatrix[i][j] = -1;        // -1 means no edge
            }
        }
    }

    void addEdges(int edge1, int edge2, int weight) {
        graph_adjMatrix[edge1][edge2] = weight;      // directed edge
    }
    void printGraph() {
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                System.out.print(graph_adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// ______________________________________________________________________________


// ___________________  With Adjacency Matrix  _____________________________
// Time Complexity: O(n^3)  -->> Total Degree for directed (Single dir egde)
// Space Complexity: O(1) || O(n^2)  -->> we are using Array itself but if consider its space is consume  
// ---------------------------------------------------------------------
class Floyd_Warshall_Algorithm {
    public int[][] Floyd_Warshall(int[][] arr) {
        int n = arr.length;
        // Replace -1 with INF and ensure diagonal is 0
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j] == -1)   arr[i][j] = (int)(1e9);
                if(i == j)   arr[i][j] = 0;
            }
        }

        for(int viaNode=0; viaNode<n; viaNode++) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if (arr[i][viaNode] != (int)1e9 && arr[viaNode][j] != (int)1e9) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][viaNode] + arr[viaNode][j]);
                    }
                }   
            }
        }
        // Convert INF back to -1
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j] == (int)(1e9))   arr[i][j] = -1;
            }
        }
        return arr;
    }
}
// ______________________________________________________________________________




class Graph_28_Floyd_Warshall_Algorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();

        Graph_adjacencyMatrix graphMatrix = new Graph_adjacencyMatrix(nodes);
        for (int i = 0; i < edges; i++) {
            int vertex = scanner.nextInt();
            int neighbor = scanner.nextInt();
            int weight = scanner.nextInt();
            graphMatrix.addEdges(vertex, neighbor, weight);
        }
        System.out.println("Array : ");
        graphMatrix.printGraph();
        
        System.out.println("\n----------------------------------");
        Floyd_Warshall_Algorithm shortestPath = new Floyd_Warshall_Algorithm();
        int[][] distArr = shortestPath.Floyd_Warshall(graphMatrix.graph_adjMatrix); 
        System.out.println("Shortest Path to each Node from Source Node (Distance Array): ");
        for (int[] row : distArr) {
            for (int val : row) {
                if (val == -1) System.out.print("∞ ");
                else System.out.print(val + " ");
            }
            System.out.println();
        }

        scanner.close();
    } 
} 