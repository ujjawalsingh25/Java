import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//____________________________________________________       _______________________________________________
// Size of String Array: 6                            |      | Size of String Array: 5                      |
// Number of Char | Nodes: 5                          |      | Number of Char | Nodes: 4                    |
// String Array                                       |      | String Array                                 |
// [ "baa" , "abcd" , "abca" , "cab" , "cad" , "e" ]  |      | [ "baa" , "abcd" , "abca" , "cab" , "cad" ]  |
// Order of Alien Dictionary: 'b' 'd' 'a' 'c' 'e'     |      | Order of Alien Dictionary: 'b' 'd' 'a' 'c'   |
//____________________________________________________|      |______________________________________________|

              

// ___________________  With Adjacency ArrayList  _____________________________
// Time Complexity: O(nodes + edge + total number char in words)
// Space Complexity:  O(n + e) + O(n)  approx O(n + e)    -->> AdjacencyArray + String Ans
// ---------------------------------------------------------------------
class Alien_Dictionary {
    public ArrayList<Integer> topoSort_DFS(ArrayList<ArrayList<Integer>> adjArr, int nodes) {
        Queue<Integer> quu = new LinkedList<Integer>();
        ArrayList<Integer> ansArr = new ArrayList<>();
        int indegree[] = new int[nodes+1];
        for(int i=0; i<nodes; i++) {
            for(int dirNbrNode: adjArr.get(i)) {
                indegree[dirNbrNode]++;       // count "Incoming Nodes" to currentNode || Indegree
            }
        }
        for(int i=0; i<nodes; i++) {
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


    public String dictionary_Order_OfAlienChar(ArrayList<String> arr, int arrSize, int nodes) {
        ArrayList<ArrayList<Integer>> adjArr = new ArrayList<>();
        for(int i=0; i<nodes; i++) {
            adjArr.add(new ArrayList<>());
        }
        
        for(int i=0; i<arrSize-1; i++) {
            String str1 = arr.get(i);
            String str2 = arr.get(i + 1);
            int minLength = Math.min(str1.length(), str2.length());
            for(int j=0; j<minLength; j++) {
                if(str1.charAt(j) != str2.charAt(j)) {
                    adjArr.get(str1.charAt(j) - 'a').add(str2.charAt(j) - 'a');
                    break;   // -->> Convert the string to equivalent values like a—>0 , b—>1 , ... z—>25  
                }
            }
        }

        String ans = "";
        ArrayList<Integer> topoSortedArr = topoSort_DFS(adjArr, nodes);
        for(int data: topoSortedArr) {
            ans = ans + (char)(data + (int)('a'));   // -->> convert back the Sorted “Int” to “CHAR”
        }
        return ans;
    }
}
// ______________________________________________________________________________




class Graph_20_Alien_Dictionary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<String>();
        
        System.out.print("Size of String Array: ");
        int size = scanner.nextInt();                
        System.out.print("Number of Char | Nodes: ");
        int nodes = scanner.nextInt();                
        System.out.println("String Array: ");
        scanner.nextLine();                     //  consume newline
        for(int i=0; i<size; i++) {
            String str = scanner.nextLine();
            arr.add(str);                     
        }
        System.out.println("-------------------");


        Alien_Dictionary dictionaryOrder_inAlien = new Alien_Dictionary();
        String orderOfChar = dictionaryOrder_inAlien.dictionary_Order_OfAlienChar(arr, size, nodes);
        System.out.print("\nOrder of Alien Dictionary: ");
        for(char data: orderOfChar.toCharArray()) {
            System.out.print(data + " ");
        }

        scanner.close();
    } 
} 