import java.util.ArrayList;
import java.util.Scanner;


// ______________________                                                                                                            
//  Number of Nodes: 10  |                                                                                                        
//  Number of Edges: 10  |       // nodes -->> totalNode            _______________________         
//  Edge: 1              |      // edges -->> totalEdge             | Number of Nodes: 8   |                     
//  Neighbour: 2         |__________________                        | Number of Edges: 8   |                                      
//  Edge: 2              |                 |                        | Edge: 1              |                                      
//  Neighbour: 3         |   1 -> 2        |                        | Neighbour: 2         |________________                   
//  Edge: 3              |   2 -> 1 3      |                        | Edge: 2              |               |                  
//  Neighbour: 4         |   3 -> 2 4 9    |                        | Neighbour: 3         |   1 -> 2      |             
//  Edge: 3              |   4 -> 3 5      |                        | Edge: 3              |   2 -> 1 3 6  |                      
//  Neighbour: 9         |   5 -> 4 6      |                        | Neighbour: 4         |   3 -> 2 4    |                   
//  Edge: 4              |   6 -> 5 7 10   |                        | Edge: 4              |   4 -> 3 5 7  |                    
//  Neighbour: 5         |   7 -> 6 8      |                        | Neighbour: 5         |   5 -> 4 6    |                      
//  Edge: 5              |   8 -> 7        |                        | Edge: 4              |   6 -> 5 2    |                  
//  Neighbour: 6         |   9 -> 3 10     |                        | Neighbour: 7         |   7 -> 4 8    |                   
//  Edge: 6              |  10 -> 6 9      |                        | Edge: 7              |   8 -> 7      |                        
//  Neighbour: 7         |_________________|                        | Neighbour: 8         |_______________|           
//  Edge: 6              |                                          | Edge: 5              |                                         
//  Neighbour: 10        |                                          | Neighbour: 6         |                              
//  Edge: 7              |                                          | Edge: 6              |                                   
//  Neighbour: 8         |                                          | Neighbour: 2         |                                  
//  Edge: 9              |                                          |______________________|_________________                      
//  Neighbour: 10        |                                          | Is Graph Bipartite:  False             |                 
// _______________________|_________________                        |________________________________________|                
//  Is Graph Bipartite:  True               |                                                                              
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
// Space Complexity:  O(n) + O(n)       -->> colorArr + Recursion Stack Space
// ---------------------------------------------------------------------
class Detect_Bipartite_Graph_DFS {
    public boolean check_Alternate_Color_DFS(int node, int color, int[] colorArr, ArrayList<ArrayList<Integer>> adjArr) {
        colorArr[node] = color;

        for(int neighborNode: adjArr.get(node)) {
            if(colorArr[neighborNode] == -1) {
                if(check_Alternate_Color_DFS(neighborNode, 1 - color, colorArr, adjArr) == false)  return false;
            }   
            else if(colorArr[neighborNode] == color)  return false;
        } 
        return true;
    }

    public boolean isBipartite_Graph_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        int colorArr[] = new int[nodes+1];
        for(int i=0; i<nodes; i++)  colorArr[i] = -1;    // initialize the array with all -1
        for(int i=1; i<=nodes; i++) {
            if(colorArr[i] == -1) {
                if(check_Alternate_Color_DFS(i, 0, colorArr, adjArr) == false)  return false;
            }
        }
        return true;
    }
}
// ______________________________________________________________________________




class Graph_14_Bipartite_Graph_DFS {
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

        Detect_Bipartite_Graph_DFS detect_Bipartite_Graph = new Detect_Bipartite_Graph_DFS();
        boolean isBipartite_DFS = detect_Bipartite_Graph.isBipartite_Graph_DFS(graph.graph_ArrayList, nodes); 
        if(isBipartite_DFS)   System.out.println("True");
        else     System.out.println("False");

        scanner.close();
    } 
} 