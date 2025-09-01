import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// _____________________                                ________________________                    
// Number of Nodes: 9   |                               | Number of Nodes: 9   |                       
// Number of Edges: 7   |     // nodes -->> totalNode   | Number of Edges: 6   |      
// Edge: 1              |     // edges -->> totalEdge   | Edge: 1              |        
// Neighbour: 3         |________________               | Neighbour: 3         |________________       
// Edge: 1              |               |               | Edge: 1              |               |     
// Neighbour: 2         |   1 -> 2 3    |               | Neighbour: 2         |   1 -> 2 3    |     
// Edge: 2              |   2 -> 1 4    |               | Edge: 2              |   2 -> 1 4    |   
// Neighbour: 4         |   3 -> 1      |               | Neighbour: 4         |   3 -> 1      |     
// Edge: 5              |   4 -> 2      |               | Edge: 5              |   4 -> 2      |  
// Neighbour: 6         |   5 -> 6      |               | Neighbour: 6         |   5 -> 6      |     
// Edge: 7              |   6 -> 5      |               | Edge: 7              |   6 -> 5      |              
// Neighbour: 8         |   7 -> 8 9    |               | Neighbour: 8         |   7 -> 8 9    |       
// Edge: 7              |   8 -> 7 9    |               | Edge: 7              |   8 -> 7      |       
// Neighbour: 9         |   9 -> 7 8    |               | Neighbour: 9         |   9 -> 7      |       
// Edge: 8              |_______________|               |______________________|_______________|________         
// Neighbour: 9         |                               | Cycle Detected in Connected Component: False  |             
// _____________________|_________________________      |_______________________________________________|  
// Cycle Detected in Connected Component:  True   |       
// _______________________________________________|        



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
        graph_ArrayList.get(edge2).add(edge1);
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
// ___________________________________________________________________



// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(n + 2 * edge) + O(n)   -->> (Loop + Total Degrees) + Loop to DETECT CYCLE IN CONNECTED COMPONENT
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Recursion Stack Space
// ---------------------------------------------------------------------
class DetectCycle_inUndirectedGraph_DFS {
    public boolean detectCycle_DFS(int currentNode, int parentNode, ArrayList<ArrayList<Integer>> adjArr, boolean[] visitedArr) {
        visitedArr[currentNode] = true;
        for(int neighborNode: adjArr.get(currentNode)) {
            if(!visitedArr[neighborNode]) {
                if(detectCycle_DFS(neighborNode, currentNode, adjArr, visitedArr) == true)  return true;
            }
            else if(neighborNode != parentNode && visitedArr[neighborNode]) return true;
        }
        return false;
    }

    public boolean isCycle_inUndirectedGraph_ConnectedComponent_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        boolean visitedArr[] = new boolean[nodes+1];
        Arrays.fill(visitedArr, false);         // initialize the array with all false
        for(int i=1; i<=nodes; i++) {
            if(!visitedArr[i]) {
                if(detectCycle_DFS(i, -1, adjArr, visitedArr)) return true;
            }
        }
        return false;
    }
}
// ______________________________________________________________________________




class Graph_09_DetectCycle_inUndirectedGraph_DFS {
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

        DetectCycle_inUndirectedGraph_DFS detectCycle_inUndirectedGraph = new DetectCycle_inUndirectedGraph_DFS();
        boolean detectCycle_DFS = detectCycle_inUndirectedGraph.isCycle_inUndirectedGraph_ConnectedComponent_DFS(graph.graph_ArrayList, nodes); 
        if(detectCycle_DFS)   System.out.println("True");
        else     System.out.println("False");

        scanner.close();
    } 
} 