import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// ______________________                                                                                                            
//  Number of Nodes: 12  |                                                                                                        
//  Number of Edges: 14  |         nodes -->> totalNode    _______________________         
//  Edge: 0              |         edges -->> totalEdge    | Number of Nodes: 7   |                     
//  Neighbour: 1         |______________                   | Number of Edges: 8   |                                      
//  Edge: 1              |              |                  | Edge: 0              |____________                          
//  Neighbour: 2         |   0 -> 1     |                  | Neighbour: 1         |            |                  
//  Edge: 2              |   1 -> 2     |                  | Edge: 0              |   0 -> 1 2 |                  
//  Neighbour: 3         |   2 -> 3 4   |                  | Neighbour: 2         |   1 -> 3 2 |             
//  Edge: 2              |   3 -> 4 5   |                  | Edge: 1              |   2 -> 5   |                      
//  Neighbour: 4         |   4 -> 6     |                  | Neighbour: 3         |   3 -> 0   |                   
//  Edge: 3              |   5 -> 6     |                  | Edge: 3              |   4 -> 5   |                    
//  Neighbour: 4         |   6 -> 7     |                  | Neighbour: 0         |   5 -> { } |                      
//  Edge: 3              |   7 -> { }   |                  | Edge: 1              |   6 -> { } |                  
//  Neighbour: 5         |   8 -> 1 9   |                  | Neighbour: 2         |   7 -> 1   |                   
//  Edge: 4              |   9 -> 10    |                  | Edge: 7              |____________|                        
//  Neighbour: 6         |  10 -> 8     |                  | Neighbour: 1         |                        
//  Edge: 5              |  11 -> 9     |                  | Edge: 2              |                                      
//  Neighbour: 6         |______________|                  | Neighbour: 5         |                              
//  Edge: 6              |                                 | Edge: 4              |                                   
//  Neighbour: 7         |                                 | Neighbour: 5         |                                  
//  Edge: 8              |                                 |______________________|___________________                      
//  Neighbour: 1         |                                 | Eventual Safe Nodes using DFS:  2 4 5 6  |                 
//  Edge: 8              |                                 |__________________________________________|                
//  Neighbour: 9         |                                                                                                 
//  Edge: 9              |               
//  Neighbour: 10        |               
//  Edge: 10             |               
//  Neighbour: 8         |               
//  Edge: 11             |               
//  Neighbour: 9         |                          
// ______________________|___________________________               
// Eventual Safe Nodes using DFS: 0 1 2 3 4 5 6 7    |              
// __________________________________________________|              

               

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
// Time Complexity: O(n + edge) + O(n)   -->> (Loop + Total Degrees)  // Single "E" as directed
// Space Complexity:  O(n) + O(n) + O(n)  -->> VisitedArr + pathVisArr + checkSafe
// Space Complexity(Optimised) :  O(n)   -->> VisitedArr    -->> Not use "PathVisArr & checkSafe" 
//             if for pathVisArr a counter to increase visArr to 2 from "1"  && to 3 for checkSafe
// ---------------------------------------------------------------------
class Eventual_SafeStates_DFS {
    public boolean detectCycle_DFS(int currentNode, boolean[] checkSafe, boolean[] pathVisitedArr, 
    boolean[] visitedArr, ArrayList<ArrayList<Integer>> adjArr) {
        visitedArr[currentNode] = true;
        pathVisitedArr[currentNode] = true;
        checkSafe[currentNode] = false;
        for(int neighborNode: adjArr.get(currentNode)) {
            if(!visitedArr[neighborNode]) {
                if(detectCycle_DFS(neighborNode, checkSafe, pathVisitedArr, visitedArr, adjArr) == true)  
                    return true;
            }
            else if(pathVisitedArr[neighborNode] == true) return true;
        }
        checkSafe[currentNode] = true;
        pathVisitedArr[currentNode] = false;      // **If no further neighboring nodes —>> Rollback
        return false;
    }

    public ArrayList<Integer> eventual_SafeNodes(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        boolean visitedArr[] = new boolean[nodes+1];
        boolean pathVisitedArr[] = new boolean[nodes+1];
        boolean checkSafe[] = new boolean[nodes+1];
        Arrays.fill(visitedArr, false);         // initialize the array with all false
        Arrays.fill(pathVisitedArr, false);         // initialize the array with all false
        Arrays.fill(checkSafe, false);         // initialize the array with all false

        for(int i=0; i<nodes; i++) {
            if(!visitedArr[i]) {
                detectCycle_DFS(i, checkSafe, pathVisitedArr, visitedArr, adjArr);
            }
        }

        ArrayList<Integer> safeNodes = new ArrayList<>();
        for(int i=0; i<nodes; i++) {
            if(checkSafe[i] == true)  safeNodes.add(i);
        }
        // Collections.sort(safeNodes);
        return safeNodes;
    }
}
// ______________________________________________________________________________




class Graph_21_Eventual_SafeStates_DFS {
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

        Eventual_SafeStates_DFS safeNodes = new Eventual_SafeStates_DFS();
        ArrayList<Integer> ansArr = safeNodes.eventual_SafeNodes(graph.graph_ArrayList, nodes); 
        System.out.print("Eventual Safe Nodes using DFS: ");
        for(int data: ansArr) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 