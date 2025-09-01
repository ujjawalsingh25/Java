import java.util.ArrayList;
import java.util.Scanner;


class Graph_adjArrayList {
    ArrayList<ArrayList<Integer>> graph_ArrayList;
    int nodes, edges;

    Graph_adjArrayList(int nodes, int edges) {
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

     
// ___________________  Depth First Search  __________________________________________
// Time Complexity: O(n! * n)       -->> generating n! times for n loops
// Space Complexity:  O(1)       
// ---------------------------------------------------------------------
class DFS_ofGraph {
    public ArrayList<Integer> graph_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        ArrayList<Integer> ans = new ArrayList<>();
        return ans;
    }
}
// ______________________________________________________________________________




class Graph_03_DFS {
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
        System.out.print("Starting Node: ");
        // int startingNode = scanner.nextInt();

        // ArrayList<Integer> dfs = new ArrayList<>();
        // DFS_ofGraph get_DFS = new DFS_ofGraph();
        // dfs = d.graph_DFS(adj, nodes);

        scanner.close();
    } 
} 