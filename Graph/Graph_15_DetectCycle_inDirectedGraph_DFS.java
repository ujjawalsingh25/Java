import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// ______________________                                                                                                            
//  Number of Nodes: 10  |                                                                                                        
//  Number of Edges: 11  |         nodes -->> totalNode             _______________________         
//  Edge: 1              |         edges -->> totalEdge             | Number of Nodes: 9   |                     
//  Neighbour: 2         |__________________                        | Number of Edges: 8   |                                      
//  Edge: 2              |                 |                        | Edge: 1              |                                      
//  Neighbour: 3         |   1 -> 2        |                        | Neighbour: 2         |____________                   
//  Edge: 3              |   2 -> 3        |                        | Edge: 2              |            |                  
//  Neighbour: 4         |   3 -> 4 7      |                        | Neighbour: 3         |   1 -> 2   |             
//  Edge: 4              |   4 -> 5        |                        | Edge: 3              |   2 -> 3   |                      
//  Neighbour: 5         |   5 -> 6        |                        | Neighbour: 4         |   3 -> 4   |                   
//  Edge: 5              |   6 -> { }      |                        | Edge: 4              |   4 -> 5   |                    
//  Neighbour: 6         |   7 -> 5        |                        | Neighbour: 5         |   5 -> 6   |                      
//  Edge: 3              |   8 -> 9 2      |                        | Edge: 5              |   6 -> 7   |                  
//  Neighbour: 7         |   9 -> 10       |                        | Neighbour: 6         |   7 -> 8   |                   
//  Edge: 7              |  10 -> 8        |                        | Edge: 6              |   8 -> 9   |                        
//  Neighbour: 5         |_________________|                        | Neighbour: 7         |   9 -> { } |           
//  Edge: 8              |                                          | Edge: 6              |____________|                         
//  Neighbour: 2         |                                          | Neighbour: 8         |                              
//  Edge: 8              |                                          | Edge: 8              |                                   
//  Neighbour: 9         |                                          | Neighbour: 9         |                                  
//  Edge: 9              |                                          |______________________|_________________                      
//  Neighbour: 10        |                                          | Is Cycle Detected :  False             |                 
//  Edge: 10             |                                          |________________________________________|                
//  Neighbour: 8         |                                                                                                 
// ______________________|__________________               
//  Is Cycle Detected :  True               |              
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
// Time Complexity: O(n + edge) + O(n)   -->> (Loop + Total Degrees)  // Single "E" as directed
// Space Complexity:  O(n) + O(n)  -->> VisitedArr + pathVisArr
// Space Complexity(Optimised) :  O(n)   -->> VisitedArr  
//                 -->> Not use "PathVisArr" if for pathVisArr increase visArr to 2 from "1" 
// ---------------------------------------------------------------------
class DetectCycle_inDirectedGraph_DFS {
    public boolean detectCycle_DFS(int currentNode, boolean[] visitedArr, boolean[] pathVisitedArr, ArrayList<ArrayList<Integer>> adjArr) {
        visitedArr[currentNode] = true;
        pathVisitedArr[currentNode] = true;
        for(int neighborNode: adjArr.get(currentNode)) {
            if(!visitedArr[neighborNode]) {
                if(detectCycle_DFS(neighborNode, visitedArr, pathVisitedArr, adjArr) == true)  return true;
            }
            else if(pathVisitedArr[neighborNode] == true) return true;
        }
        pathVisitedArr[currentNode] = false;      // **If no further neighboring nodes —>> Rollback
        return false;
    }

    public boolean isCycle_inDirectedGraph_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        boolean visitedArr[] = new boolean[nodes+1];
        boolean pathVisitedArr[] = new boolean[nodes+1];
        Arrays.fill(visitedArr, false);         // initialize the array with all false
        Arrays.fill(pathVisitedArr, false);         // initialize the array with all false

        for(int i=1; i<=nodes; i++) {
            if(!visitedArr[i]) {
                if(detectCycle_DFS(i, visitedArr, pathVisitedArr, adjArr) == true) return true;
            }
        }
        return false;
    }
}
// ______________________________________________________________________________




class Graph_15_DetectCycle_inDirectedGraph_DFS {
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

        DetectCycle_inDirectedGraph_DFS detectCycle_inDirectedGraph = new DetectCycle_inDirectedGraph_DFS();
        boolean detectCycle_DFS = detectCycle_inDirectedGraph.isCycle_inDirectedGraph_DFS(graph.graph_ArrayList, nodes); 
        if(detectCycle_DFS)   System.out.println("True");
        else     System.out.println("False");

        scanner.close();
    } 
} 