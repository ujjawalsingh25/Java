import java.util.ArrayList;
import java.util.Scanner;


// _____________________
// Number of Nodes: 8   |____________________________________
// Number of Edges: 5   |             |                      |
// Edge: 1              |   1 -> 2    |    0 1 0 0 0 0 0 0   |
// Neighbour: 2         |   2 -> 1 3  |    1 0 1 0 0 0 0 0   |      
// Edge: 2              |   3 -> 2    |    0 1 0 0 0 0 0 0   |    
// Neighbour: 3         |   4 -> 5    |    0 0 0 0 1 0 0 0   |    
// Edge: 4              |   5 -> 4 6  |    0 0 0 1 0 1 0 0   |  
// Neighbour: 5         |   6 -> 5    |    0 0 0 0 1 0 0 0   |    
// Edge: 5              |   7 -> 8    |    0 0 0 0 0 0 0 1   |
// Neighbour: 6         |   8 -> 7    |    0 0 0 0 0 0 1 0   |
// Edge: 7              |_____________|______________________|              
// Neighbour: 8         |       
// _____________________|____
// Number of Provinces: 3    |
// __________________________| 



// ___________________  Graph  ________________________________________________
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
    
class Graph_adjacencyMatrix {
    ArrayList<ArrayList<Integer>> graph_adjMatrix;
    int nodes;

    Graph_adjacencyMatrix(int nodes) {
        this.nodes = nodes;
        graph_adjMatrix = new ArrayList<>();

        for (int i=0    ; i<=nodes; i++) {           // Initialize the matrix with 0s
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= nodes; j++) {
                row.add(0);
            }
            graph_adjMatrix.add(row);
        }
    }

    void addEdges(int edge1, int edge2) {
        graph_adjMatrix.get(edge1).set(edge2, 1);
        graph_adjMatrix.get(edge2).set(edge1, 1);
    }
    void printGraph() {
        for(int i=1; i<=nodes; i++) {
            for(int j=1; j<=nodes; j++) {
                System.out.print(graph_adjMatrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
// ______________________________________________________________________________



     
// ___________________  With Adjacency Matrix  _____________________________
// Time Complexity: O(n) + O(2* Edges)    -->> Loop + Total Degrees
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Recursion Stack Space
// ---------------------------------------------------------------------
class Number_of_Provinces_inMatrix {
    void dfs_recursion(int node, boolean visited[], ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;
        for(int neighbour: adjList.get(node)) {
            if(!visited[neighbour])  dfs_recursion(neighbour, visited, adjList);
        }
    }

    public int numOfProvinces_inMatrix(ArrayList<ArrayList<Integer>> adjMatrix, int nodes) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<=nodes; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        //  Change the AdjacencyMatrix to AdjacencyArrayList  -->> 0 0 0 to {2 -> [1, 3] }
        //                                                         0 1 0    {3 -> [4, 2] }       
        for(int i=1; i<=nodes; i++) {
            for(int j=1; j<=nodes; j++) {
                if(adjMatrix.get(i).get(j) == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        boolean visited[] = new boolean[nodes + 1];
        int count = 0;
        for(int i=1; i<=nodes; i++) {       // since node 0 is not present
            if(!visited[i]) {
                count++;
                dfs_recursion(i, visited, adjList);
            }
        }
        return count;
    }
}
// ______________________________________________________________________________



// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(n) + O(2* Edges)    -->> Loop + Total Degrees
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Recursion Stack Space
// ---------------------------------------------------------------------
class Number_of_Provinces_inArrayList {
    void dfs_recursion(int node, boolean visited[], ArrayList<ArrayList<Integer>> adjArr) {
        visited[node] = true;
        for(int neighbour: adjArr.get(node)) {
            if(!visited[neighbour])  dfs_recursion(neighbour, visited, adjArr);
        }
    }

    public int numOfProvinces_inArrayList(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        boolean visited[] = new boolean[nodes + 1];
        int count = 0;
        for(int i=1; i<=nodes; i++) {       // since node 0 is not present
            if(!visited[i]) {
                count++;
                dfs_recursion(i, visited, adjArr);
            }
        }
        return count;
    }
}
// ______________________________________________________________________________




class Graph_04_Number_of_Provinces {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();

        Graph_adjArrayList graph = new Graph_adjArrayList(nodes, edges);
        Graph_adjacencyMatrix graphMatrix = new Graph_adjacencyMatrix(nodes);
        for(int i=0; i<edges; i++) {
            System.out.print("Edge: ");
            int edge = scanner.nextInt();
            System.out.print("Neighbour: ");
            int neighbour = scanner.nextInt();
            
            graph.addEdges(edge, neighbour);
            graphMatrix.addEdges(edge, neighbour);
        }
        System.out.println("-------------------");
        graph.printGraph();
        graphMatrix.printGraph();
        System.out.println("-------------------");

        Number_of_Provinces_inMatrix totalProvince_inMatrix = new Number_of_Provinces_inMatrix();
        int provincesCount_inMatrix = totalProvince_inMatrix.numOfProvinces_inMatrix(graphMatrix.graph_adjMatrix, nodes); 
        System.out.println("Number of Provinces Matrix: " + provincesCount_inMatrix);

        Number_of_Provinces_inArrayList totalProvince_inArrayList = new Number_of_Provinces_inArrayList();
        int provincesCount_inArrayList = totalProvince_inArrayList.numOfProvinces_inArrayList(graph.graph_ArrayList, nodes); 
        System.out.println("Number of Provinces ArrayList: " + provincesCount_inArrayList);

        scanner.close();
    } 
} 