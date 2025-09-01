import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


// _____________________           // "a" --> "b"  -->> "b" should complete before "a"   
// Number of Nodes: 4   |                                ________________________                           
// Number of Edges: 4   |     // nodes -->> totalNode    | Number of Nodes: 4   |                               
// Edge: 1              |     // edges -->> totalEdge    | Number of Edges: 3   |_____________                               
// Neighbour: 2         |_____________                   | Edge: 1              |            |                               
// Edge: 4              |            |                   | Neighbour: 4         |   1 -> 4   |           
// Neighbour: 3         |   1 -> 2   |                   | Edge: 2              |   2 -> 1   |               
// Edge: 2              |   4 -> 3   |                   | Neighbour: 1         |   3 -> 2   |               
// Neighbour: 4         |   2 -> 4   |                   | Edge: 3              |____________|               
// Edge: 4              |   4 -> 1   |                   | Neighbour: 2         |              
// Neighbour: 1         |____________|                   |______________________|_____________         
// _____________________|__________                      | Completed Course Order:  4 1 2 3   |   
// Completed Course Order:  { }    |                     |____________________________________| 
// ________________________________|       
       
               
               

class Pair {
    int first, second;
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(n + edge)  -->> Total Degree for directed (Single dir egde)
// Space Complexity:  O(n) + O(n)       -->> Indegree Array + Queue
// ---------------------------------------------------------------------
class Course_Schedule {
    public ArrayList<Integer> topoSort_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        Queue<Integer> quu = new LinkedList<Integer>();
        ArrayList<Integer> ansArr = new ArrayList<>();
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
        
        while(!quu.isEmpty()) {
            int currentNode = quu.peek();
            quu.remove();
            ansArr.add(currentNode);
            for(int dirNbrNode: adjArr.get(currentNode)) {
                indegree[dirNbrNode]--;
                if(indegree[dirNbrNode] == 0)  quu.add(dirNbrNode);
            }
        }
        return ansArr;
    }


    public boolean Course_Schedule1(ArrayList<Pair> arr, int nodes) {
        ArrayList<ArrayList<Integer>> adjArr = new ArrayList<>();
        int edges = arr.size();
        for(int i=0; i<=nodes; i++) {
            adjArr.add(new ArrayList<>());
        }
        for(int i=0; i<edges; i++) {
            Pair pr = arr.get(i);
            adjArr.get(pr.first).add(pr.second);
        }

        ArrayList<Integer> topoSortedArr = topoSort_DFS(adjArr, nodes);
        if(topoSortedArr.size() == nodes)  return true;
        return false;
    }

    public ArrayList<Integer> Course_Schedule2(ArrayList<Pair> arr, int nodes) {
        ArrayList<ArrayList<Integer>> adjArr = new ArrayList<>();
        ArrayList<Integer> emptyArr = new ArrayList<>();
        int edges = arr.size();
        for(int i=0; i<=nodes; i++) {
            adjArr.add(new ArrayList<>());
        }
        for(int i=0; i<edges; i++) {
            Pair pr = arr.get(i);
            adjArr.get(pr.second).add(pr.first);
        }

        ArrayList<Integer> topoSortedArr = topoSort_DFS(adjArr, nodes);
        if(topoSortedArr.size() == nodes)  return topoSortedArr;
        return emptyArr;
    }
}
// ______________________________________________________________________________




class Graph_19_Course_Schedule_1_and_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pair> arr = new ArrayList<Pair>();
        
        System.out.print("Number of Nodes: ");
        int nodes = scanner.nextInt();
        System.out.print("Number of Edges | Pairs: ");
        int edges = scanner.nextInt();
        
        System.out.println("Prerequisites: ");
        for(int i=0; i<edges; i++) {
            System.out.print("Edge: ");
            int edge = scanner.nextInt();
            System.out.print("Neighbour: ");
            int neighbour = scanner.nextInt();
            
            Pair pr = new Pair(edge, neighbour); 
            arr.add(pr);                     
        }
        System.out.println("-------------------");


        Course_Schedule courseCompleteOrder = new Course_Schedule();
        
        boolean canComplete = courseCompleteOrder.Course_Schedule1(arr, nodes);
        System.out.print("Can Course Complete: ");
        if(canComplete)  System.out.print(canComplete);
        else   System.out.print(canComplete);

        ArrayList<Integer> completedOrder = courseCompleteOrder.Course_Schedule2(arr, nodes); 
        System.out.print("\nCompleted Course Order: ");
        for(int data: completedOrder) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 