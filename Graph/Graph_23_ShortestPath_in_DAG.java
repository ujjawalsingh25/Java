import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


// __________________________________________________       ___________________________________________________
// Number of Nodes: 7                               |       | Number of Nodes: 7                               |
// Number of Edges: 8                               |       | Number of Edges: 8                               |
// 0 1 2                                            |       | 0 5 3                                            |
// 1 3 1                                            |       | 0 4 2                                            |
// 2 3 3                                            |       | 4 5 1                                            |
// 4 0 3                                            |       | 4 2 1                                            |
// 4 2 1                                            |       | 4 6 3                                            |
// 5 4 1                                            |       | 6 1 2                                            |
// 6 4 2                                            |       | 1 3 1                                            |
// 6 5 3                                            |       | 2 3 3                                            |
// ----------------------------------               |       | ----------------------------------               |
// Soure Node:  6                                   |       | Soure Node:  0                                   |
// [0, 1, 2], [1, 3, 1], [2, 3, 3], [4, 0, 3]       |       | [0, 5, 3], [0, 4, 2], [4, 5, 1], [4, 2, 1]       |
// [4, 2, 1], [5, 4, 1], [6, 4, 2], [6, 5, 3]       |       | [4, 6, 3], [6, 1, 2], [1, 3, 1], [2, 3, 3]       |
// ----------------------------------               |       | ----------------------------------               |
// Shortest Path to each Node from Source Node      |       | Shortest Path to each Node from Source Node      |
//   (Distance Array)  :  5  7  3  6  2  3  0       |       |    (Distance Array)   :  0  7  3  5  2  3  5     |
//  At index Position  :  0  1  2  3  4  5  6       |       |   At index Position   :  0  1  2  3  4  5  6     |
// _________________________________________________|       | _________________________________________________|


class Pair {
    int nbr, wt;
    Pair(int nbr, int wt) {
        this.nbr = nbr;
        this.wt = wt;
    }
}


// ___________________  By Creating Adjacency ArrayList  from Array  ________________
// Time Complexity: O(n + edge)  +  O(n + edge)   -->> Topo  + shortestPath
// Space Complexity:  O(n) + O(n)       -->> VisitedArr + Stack 
// ---------------------------------------------------------------------
class ShortestPath_in_Directed_Acyclic_Graph {
    public void topoSort_DFS(int currNode, Stack<Integer> stk, boolean[] visitedArr, ArrayList<ArrayList<Pair>> adjArr) {
        visitedArr[currNode] = true;
        for(int i=0; i<adjArr.get(currNode).size(); i++) {        //  in case of  4 --> {0,3} , {2,1}   it be like
            int nbrNode = adjArr.get(currNode).get(i).nbr;    // nbrNode = adjArr.4.[0,3].0
            if(!visitedArr[nbrNode]) {
                topoSort_DFS(nbrNode, stk, visitedArr, adjArr);
            }
        }
        stk.add(currNode);
    }


    public int[] ShortestPath_in_DAG(int srcNode, ArrayList<ArrayList<Integer>>  arr, int nodes, int edges) {
        ArrayList<ArrayList<Pair>> adjArr = new ArrayList<>();
        for(int i=0; i<nodes; i++) {
            ArrayList<Pair> temp = new ArrayList<Pair>();
            adjArr.add(temp);
        }
        for(int i=0; i<edges; i++) {
            int currNode = arr.get(i).get(0);
            int nbrNode = arr.get(i).get(1);
            int weight = arr.get(i).get(2);
            adjArr.get(currNode).add(new Pair(nbrNode, weight));
        }

        boolean visitedArr[] = new boolean[nodes+1];
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i<nodes; i++) {
            if(!visitedArr[i])    topoSort_DFS(i, stk, visitedArr, adjArr);
        }

        int distArr[] = new int[nodes];
        for(int i=0; i<nodes; i++) {
            distArr[i] = (int)(1e9);
        }
        distArr[srcNode] = 0;
        while(!stk.isEmpty()) {
            int currNode = stk.peek();
            stk.pop();
            for(int i=0; i<adjArr.get(currNode).size(); i++) {
                int nbrNode = adjArr.get(currNode).get(i).nbr;
                int weight = adjArr.get(currNode).get(i).wt;
                if(distArr[currNode] + weight < distArr[nbrNode]) {
                    distArr[nbrNode] = distArr[currNode] + weight;
                }
            }
        }
        
        return distArr;
    }
}
// ______________________________________________________________________________




class Graph_23_ShortestPath_in_DAG {
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
        ShortestPath_in_Directed_Acyclic_Graph shortestPath = new ShortestPath_in_Directed_Acyclic_Graph();
        int[] distArr = shortestPath.ShortestPath_in_DAG(srcNode, arr, nodes, edges); 
        System.out.print("Shortest Path to each Node from Source Node (Distance Array): ");
        for(int data: distArr) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 