import java.util.ArrayList;
import java.util.Scanner;



// __________________________________________________       ___________________________________________________
// Number of Nodes: 6                               |       | Number of Nodes: 6                               |
// Number of Edges: 7                               |       | Number of Edges: 7                               |
// 0  1  5                                          |       |  0  1  2                                         |
// 1  2 -2                                          |       |  0  4  1                                         |
// 1  5 -3                                          |       |  4  5  4                                         |
// 5  3  1                                          |       |  4  2  2                                         |
// 3  2  6                                          |       |  1  2  3                                         |
// 3  4 -2                                          |       |  2  3  6                                         |
// 2  4  3                                          |       |  5  3  1                                         |
// Soure Node:  0                                   |       | Soure Node:  0                                   |
// ----------------------------------               |       | ----------------------------------               |
// [0, 1, 5], [1, 2, -2], [1, 5, -3], [5, 3, 1]     |       | [0, 1, 2], [0, 4, 1], [4, 5, 4], [4, 2, 2],      |
// [3, 2, 6], [3, 4, -2], [2, 4, 3]                 |       | [1, 2, 3], [2, 3, 6], [5, 3, 1]                  |
// ----------------------------------               |       | ----------------------------------               |
// Shortest Path to each Node from Source Node      |       | Shortest Path to each Node from Source Node      |
//   (Distance Array)  :  0  5  3  3  1  2          |       |    (Distance Array)   :  0  2  3  6  1  5        |
//  At index Position  :  0  1  2  3  4  5          |       |   At index Position   :  0  1  2  3  4  5        |
// _________________________________________________|       | _________________________________________________|
       


// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(nodes * edge)  -->> Total Degree for directed (Single dir egde)
// Space Complexity:  O(n)           -->> distArray
// ---------------------------------------------------------------------
class Bellman_Ford_Algorithm {
    public int[] Bellman_Ford(int srcNode, ArrayList<ArrayList<Integer>>  arr, int nodes) {
        int distArr[] = new int[nodes];
        for(int i=0; i<nodes; i++) {
            distArr[i] = (int)(1e9);
        }
        distArr[srcNode] = 0;

        for(int i=0; i<nodes; i++) {
            for(ArrayList<Integer> edges: arr) {
                int currNode = edges.get(0);
                int nbrNode = edges.get(1);
                int weight = edges.get(2);
                if(distArr[currNode] != 1e9  &&  distArr[currNode] + weight < distArr[nbrNode])
                    distArr[nbrNode] = distArr[currNode] + weight;
            }
        }
        // // nth relaxation to detect Negaitve Cycle
        for(ArrayList<Integer> edges: arr) {
            int currNode = edges.get(0);
            int nbrNode = edges.get(1);
            int weight = edges.get(2);
            if(distArr[currNode] != 1e9  &&  distArr[currNode] + weight < distArr[nbrNode]) {
                int negArr[] = new int[1];
                negArr[0] = -1;
                return negArr;
            }
        }
        return distArr;
    }
}
// ______________________________________________________________________________




class Graph_27_Bellman_Ford_Algorithm {
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
            int weight = scanner.nextInt();
            row.add(nodeX);
            row.add(nodeY);
            row.add(weight);
            arr.add(row);           
        }
        System.out.println("-------------------");
        System.out.print("Soure Node: ");
        int srcNode = scanner.nextInt();
        for(ArrayList<Integer> adjData: arr) {
            System.out.print("[" + adjData.get(0) + ", " + adjData.get(1) + ", " +adjData.get(2)+ "]");
        }     
        
        System.out.println("\n----------------------------------");
        Bellman_Ford_Algorithm shortestPath = new Bellman_Ford_Algorithm();
        int[] distArr = shortestPath.Bellman_Ford(srcNode, arr, nodes); 
        System.out.print("Shortest Path to each Node from Source Node (Distance Array): ");
        for(int data: distArr) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 