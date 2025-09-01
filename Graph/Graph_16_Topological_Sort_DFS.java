import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


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
// _____________________|___________________             | Topological Sorted Nodes: 0 1 2 3 5 4     | 
// Topological Sorted Nodes: 5 4 2 3 1 0    |            |___________________________________________|                                                        
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
// Time Complexity: O(n + edge)   -->> (Total Degrees)    // NOT "2*E" as directed graph so one side edge
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Stack 
// ---------------------------------------------------------------------
class TopologicalSort_DFS {
    public void findTopo_Order_dfs(int currentNode, Stack<Integer> stk, boolean[] visitedArr, ArrayList<ArrayList<Integer>> adjArr) {
        visitedArr[currentNode] = true;
        for(int neighborNode: adjArr.get(currentNode)) {
            if(!visitedArr[neighborNode]) {
                findTopo_Order_dfs(neighborNode, stk, visitedArr, adjArr);
            }
        }
        stk.push(currentNode);
    }

    public ArrayList<Integer> topoSort_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        Stack<Integer> stk = new Stack<Integer>();
        ArrayList<Integer> ansArr = new ArrayList<>();
        boolean visitedArr[] = new boolean[nodes+1];
        for(int i=0; i<nodes; i++) {
            if(!visitedArr[i]) {
                findTopo_Order_dfs(i, stk, visitedArr, adjArr);
            }
        }
        
        while(!stk.empty()) {
            ansArr.add(stk.peek());
            stk.pop();
        }
        return ansArr;
    }
}
// ______________________________________________________________________________




class Graph_16_Topological_Sort_DFS {
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

        TopologicalSort_DFS topoSort = new TopologicalSort_DFS();
        ArrayList<Integer> sortedArray = topoSort.topoSort_DFS(graph.graph_ArrayList, nodes); 
        System.out.print("Topological Sorted Nodes: ");
        for(int data: sortedArray) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 