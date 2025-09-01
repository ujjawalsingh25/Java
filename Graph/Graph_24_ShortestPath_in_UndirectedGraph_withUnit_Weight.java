import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


// _____________________________________________       _______________________________________________
// Number of Nodes: 9                           |       | Number of Nodes: 8                           |
// Number of Edges: 10                          |       | Number of Edges: 10                          |
// 0 1                                          |       | 1 2                                          |
// 0 3                                          |       | 1 0                                          |
// 3 4                                          |       | 0 3                                          |
// 4 5                                          |       | 3 4                                          |
// 5 6                                          |       | 3 7                                          |
// 1 2                                          |       | 4 5                                          |
// 2 6                                          |       | 4 6                                          |
// 6 7                                          |       | 4 7                                          |
// 7 8                                          |       | 5 6                                          |
// 6 8                                          |       | 7 6                                          |
// ----------------------------------           |       | ----------------------------------           |
// Soure Node:  0                               |       | Soure Node:  0                               |
// [0, 1], [0, 3], [3, 4], [4, 5], [5, 6]       |       | [1, 2], [1, 0], [0, 3], [3, 4], [3, 7]       |
// [1, 2], [2, 6], [6, 7], [7, 8], [6, 8]       |       | [4, 5], [4, 6], [4, 7], [5, 6], [7, 6]       |
// ----------------------------------           |       | ----------------------------------           |
// Shortest Path to each Node from Source Node  |       | Shortest Path to each Node from Source Node  |
//   (Distance Array)  :  0 1 2 1 2 3 3 4 4     |       |    (Distance Array)   :  0 1 2 1 2 3 3 2     |
//  At index Position  :  0 1 2 3 4 5 6 7 8     |       |   At index Position   :  0 1 2 3 4 5 6 7     |
// _____________________________________________|       | _____________________________________________|




// ___________________  By Creating Adjacency ArrayList  from Array  ________________
// Time Complexity: O(n + edge)  +  O(n + edge)   -->> Topo  + shortestPath
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Queue 
// ---------------------------------------------------------------------
class ShortestPath_in_Undirected_Graph_withUnit_Weight {
    public int[] ShortestPath_in_UndirectedGraph(int srcNode, ArrayList<ArrayList<Integer>>  arr, int nodes, int edges) {
        ArrayList<ArrayList<Integer>> adjArr = new ArrayList<>();
        for(int i=0; i<nodes; i++) {
            adjArr.add(new ArrayList<>());
        }
        for(int i=0; i<edges; i++) {
            int u = arr.get(i).get(0);
            int v = arr.get(i).get(1);
            adjArr.get(u).add(v);
            adjArr.get(v).add(u);
        }

        Queue<Integer> quu = new LinkedList<>();
        int distArr[] = new int[nodes];
        for(int i=0; i<nodes; i++) {
            distArr[i] = (int)(1e9);
        }
        distArr[srcNode] = 0;
        quu.add(srcNode);
        while(!quu.isEmpty()) {
            int currNode = quu.peek();
            quu.remove();
            for(int nbrNode: adjArr.get(currNode)) {
                if(distArr[currNode] + 1 < distArr[nbrNode]) {
                    distArr[nbrNode] = distArr[currNode] + 1;
                    quu.add(nbrNode);
                }
            }
        }
        for(int i=0; i<nodes; i++) {
            if(distArr[i] == 1e9)   distArr[i] = -1;
        }
        
        return distArr;
    }
}
// ______________________________________________________________________________




class Graph_24_ShortestPath_in_UndirectedGraph_withUnit_Weight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges: ");
        int edges = scanner.nextInt();
        
        for(int i=0; i<edges; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            int nodeX = scanner.nextInt();
            int nodeY = scanner.nextInt();
            row.add(nodeX);
            row.add(nodeY);
            arr.add(row);           
        }
        System.out.println("-------------------");
        System.out.print("Soure Node: ");
        int srcNode = scanner.nextInt();
        for(ArrayList<Integer> adjData: arr) {
            System.out.print("[" + adjData.get(0) + ", " + adjData.get(1) + "]");
        }    
        
        System.out.println("\n----------------------------------");
        ShortestPath_in_Undirected_Graph_withUnit_Weight shortestPath = new ShortestPath_in_Undirected_Graph_withUnit_Weight();
        int[] distArr = shortestPath.ShortestPath_in_UndirectedGraph(srcNode, arr, nodes, edges); 
        System.out.print("Shortest Path to each Node from Source Node (Distance Array): ");
        for(int data: distArr) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 