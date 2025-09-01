import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 0 1 1 0 0 1 1 0 0 0 1 0 0 0 0 0 1 1 0 1
// _____________________________________________________________________________________
//           Unsolved Sudoko                 |            Solved Sudoko                 |
//      5   3  |_|  6   7   8  9 |_|  2      |     5   3  |4|  6   7   8  9 |1|  2      |
//      6   7   2   1   9   5  3  4   8      |     6   7   2   1   9   5  3  4   8      | 
//     |_|  9   8   3   4   2  5  6   7      |    |1|  9   8   3   4   2  5  6   7      |
//      8   5   9   7   6   1  4  2   3      |     8   5   9   7   6   1  4  2   3      |
//      4   2   6   8   5   3  7  9   1      |     4   2   6   8   5   3  7  9   1      |
//      7   1   3   9  |_|  4  8  5   6      |     7   1   3   9  |2|  4  8  5   6      |
//      9   6  |_|  5   3   7  2  8   4      |     9   6  |1|  5   3   7  2  8   4      | 
//      2   8   7   4   1   9  6  3   5      |     2   8   7   4   1   9  6  3   5      |
//      3   4   5   2   8   6  1  7   9      |     3   4   5   2   8   6  1  7   9      |
// __________________________________________|__________________________________________|

class Pair {
    int first, second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }   
}

// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O(9*n^2)    -->>  n^2 is for every node && check 9 directions for each node 
// Space Complexity:    O(n^2) + O(n^2)       -->> VisitedArr + Queue (if all connected n^2)    
// ---------------------------------------------------------------------
class SolutionOptimal {
    void BFS_Traversal_toCount(int row, int col, boolean[][] visited, char[][] arr, int n, int m) {
        Queue<Pair> quu = new LinkedList<Pair>();
        visited[row][col] = true;
        quu.add(new Pair(row, col));
        while(!quu.isEmpty()) {
            int visRow = quu.peek().first;
            int visCol = quu.peek().second;
            quu.remove();

            for(int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                for(int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                    int nbrRow = visRow + deltaRow;
                    int nbrCol = visCol + deltaCol;
                    if(nbrRow >= 0 && nbrRow < n && nbrCol >= 0 && nbrCol < m && arr[nbrRow][nbrCol] == '1' && visited[nbrRow][nbrCol] == false) {
                        visited[nbrRow][nbrCol] = true;
                        quu.add(new Pair(nbrRow, nbrCol));
                    }
                }
            }
        }


    }

    public int NumberOf_ConnectedComponents_OPTM(char[][] arr, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for(int row=0; row<n; row++){
            for(int col=0; col<m; col++) {
                if(visited[row][col] == false && arr[row][col] == '1') {
                    count++;
                    BFS_Traversal_toCount(row, col, visited, arr, n, m);
                }
            }
        }
        return count;        
    }
}
// ______________________________________________________________________________



class Graph_05_Number_of_ConnectedComponents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size(n): ");
        int n = scanner.nextInt();
        System.out.print("Size(m): ");
        int m = scanner.nextInt();
        
        char[][] arr = new char[n][m];
        System.out.println("Array ");
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                arr[i][j] = scanner.next().charAt(0);    
                //  Reads the next token (i.e., word) && Skips leading spaces
                // input '5' -->> String -> '5' so char taken = str[0] = 5 and store 
            }
        }
        System.out.println("-----------------------------------");
        
        
        SolutionOptimal solOPT = new SolutionOptimal();
        int noOf_ConnectedComponents = solOPT.NumberOf_ConnectedComponents_OPTM(arr, n, m);        
        System.out.println("Number of Connected Components: " + noOf_ConnectedComponents);
        
        scanner.close();
    } 
} 