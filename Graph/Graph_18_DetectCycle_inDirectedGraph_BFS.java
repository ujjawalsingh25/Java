import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
//  Edge: 8              |                                          | Edge: 7              |____________|                         
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
// Time Complexity: O(n + edge)  -->> Total Degree for directed (Single dir egde)
// Space Complexity:  O(n) + O(n)       -->> Indegree Array + Queue
// ---------------------------------------------------------------------
class KahnAlgo_TopoSort_BFS {
    public int topoSort_Size_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        Queue<Integer> quu = new LinkedList<Integer>();
        int indegree[] = new int[nodes+1];

        for(int i=1; i<=nodes; i++) {
            for(int dirNbrNode: adjArr.get(i)) {
                indegree[dirNbrNode]++;       // count "Incoming Nodes" to currentNode || Indegree
            }
        }

        for(int i=1; i<=nodes; i++) {
            if(indegree[i] == 0) {
                quu.add(i);     // Add nodes to Queue having "Incoming Nodes Count 0" || Indegree = 0
            }
        }
        
        int count = 0;          // rather returning array return count of array size
        while(!quu.isEmpty()) {
            int currentNode = quu.peek();
            quu.remove();
            count++;

            for(int dirNbrNode: adjArr.get(currentNode)) {
                indegree[dirNbrNode]--;
                if(indegree[dirNbrNode] == 0)  quu.add(dirNbrNode);
            }
        }
        return count;
    }

    public boolean isCycle_inDirectedGraph_BFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        int topoSize = topoSort_Size_DFS(adjArr, nodes);
        if(topoSize == nodes)  return false;
        return true;
    }
}
// ______________________________________________________________________________




class Graph_18_DetectCycle_inDirectedGraph_BFS {
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
        boolean detectCycle_BFS = topoSort.isCycle_inDirectedGraph_BFS(graph.graph_ArrayList, nodes); 
        System.out.print("Is Cyclic Graph (Kahn's Algorithm): ");
        if(detectCycle_BFS)   System.out.println("True");
        else     System.out.println("False");

        scanner.close();
    } 
} 