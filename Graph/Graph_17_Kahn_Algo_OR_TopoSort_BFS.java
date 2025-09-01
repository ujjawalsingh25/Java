import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


// _____________________                                  
// Number of Nodes: 6   |                                ________________________                           
// Number of Edges: 6   |     // nodes -->> totalNode    | Number of Nodes: 6   |                               
// Edge: 5              |     // edges -->> totalEdge    | Number of Edges: 5   |                               
// Neighbour: 0         |_____________                   | Edge: 0              |_____________                               
// Edge: 5              |            |                   | Neighbour: 1         |            |           
// Neighbour: 2         |   0 -> { } |                   | Edge: 1              |   0 -> 1   |               
// Edge: 2              |   1 -> { } |                   | Neighbour: 2         |   1 -> 2   |               
// Neighbour: 3         |   2 -> 3   |                   | Edge: 2              |   2 -> 3   |               
// Edge: 3              |   3 -> 1   |                   | Neighbour: 3         |   3 -> 5 4 |           
// Neighbour: 1         |   4 -> 1 0 |                   | Edge: 3              |   4 -> { } |           
// Edge: 4              |   5 -> 0 2 |                   | Neighbour: 4         |   5 -> { } |              
// Neighbour: 1         |____________|                   | Edge: 3              |____________|           
// Edge: 4              |                                | Neighbour: 5         |              
// Neighbour: 0         |                                |______________________|____________________       
// _____________________|___________________             | Topological Sorted Nodes: 0 1 2 3 4 5     | 
// Topological Sorted Nodes: 4 5 0 2 3 1    |            |                     OR  : 0 1 2 3 5 4     |                                                        
//                     OR  : 4 5 2 3 1 0    |            |    (Multiple Solution Possible)           |     
//   (Multiple Solution Possible)           |            |___________________________________________|  
// _________________________________________|                   
       
               




// ___________________  Graph  ________________________________________________
class Graph_adjArrayList {
    ArrayList<ArrayList<Integer>> graph_ArrayList;
    int nodes, edges;

    Graph_adjArrayList(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
        graph_ArrayList = new ArrayList<>();
        for (int i=0; i<=nodes; i++) {                  // create empty arrayList of 
            graph_ArrayList.add(new ArrayList<>());     // "node size" to add edge and neighbor later
        }
    }

    void addEdges(int edge1, int edge2) {
        graph_ArrayList.get(edge1).add(edge2);
        // graph_ArrayList.get(edge2).add(edge1);     // NOT REQUIRED FOR DIRECTED GRAPH (ONLY ONE CONNECTION)
    }
    void printGraph() {
        for(int i=0; i<nodes; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : graph_ArrayList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
// ___________________________________________________________________



// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(n + edge)  -->> Total Degree for directed (Single dir egde)
// Space Complexity:  O(n) + O(n)       -->> Indegree Array + Queue
// ---------------------------------------------------------------------
class KahnAlgo_TopoSort_BFS {
    public ArrayList<Integer> topoSort_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        Queue<Integer> quu = new LinkedList<Integer>();
        ArrayList<Integer> ansArr = new ArrayList<>();
        int indegree[] = new int[nodes+1];

        for(int i=0; i<nodes; i++) {
            for(int dirNbrNode: adjArr.get(i)) {
                indegree[dirNbrNode]++;       // count "Incoming Nodes" to currentNode || Indegree
            }
        }

        for(int i=0; i<nodes; i++) {
            if(indegree[i] == 0) {
                quu.add(i);     // Add nodes to Queue having "Incoming Nodes Count 0" || Indegree = 0
            }
        }
        
        while(!quu.isEmpty()) {
            int currentNode = quu.peek();
            quu.remove();
            ansArr.add(currentNode);
            for(int dirNbrNode: adjArr.get(currentNode)) {
                indegree[dirNbrNode]--;
                if(indegree[dirNbrNode] == 0)  quu.add(dirNbrNode);
            }
        }
        return ansArr;
    }
}
// ______________________________________________________________________________




class Graph_17_Kahn_Algo_OR_TopoSort_BFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();

        Graph_adjArrayList graph = new Graph_adjArrayList(nodes, edges);
        for(int i=0; i<edges; i++) {
            System.out.print("Edge: ");
            int edge = scanner.nextInt();
            System.out.print("Neighbour: ");
            int neighbour = scanner.nextInt();
            
            graph.addEdges(edge, neighbour);
        }
        System.out.println("-------------------");
        graph.printGraph();

        KahnAlgo_TopoSort_BFS topoSort = new KahnAlgo_TopoSort_BFS();
        ArrayList<Integer> sortedArray = topoSort.topoSort_DFS(graph.graph_ArrayList, nodes); 
        System.out.print("Topological Sorted Nodes (Kahn's Algorithm): ");
        for(int data: sortedArray) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 