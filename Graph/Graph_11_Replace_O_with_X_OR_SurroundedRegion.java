import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Size(n): 3
// Size(m): 3
//   Array 
//  0  0  0
//  0  1  0
//  1  0  1
// ----------------------------
// DistanceArray of Nearest Cell having 1
//  2  1  2
//  1  0  1
//  0  1  0

class Pair {
    int row, col, distance;
    Pair(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}


// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O(n*m) + O(4* n*m)  -> approx O(n*m)  -->> Adding 1 to visitedArr + n*m is for every node && check 4 directions 
// Space Complexity:  O(n*m) + O(n*m)  -->> visitedArray + distanceArr   
// ---------------------------------------------------------------------
class SolutionOptimal {
    public int[][] DistanceArray_NearestCell_HavingONE_OPTM(int[][] arr, int n, int m) {

        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition

        Queue<Pair> quu = new LinkedList<>();
        boolean[][] visitedArr = new boolean[n][m];
        int[][] distanceArr = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 1) {
                    quu.add(new Pair(i, j, 0));
                    visitedArr[i][j] = true;
                }
                else  visitedArr[i][j] = false;
            }
        }

        int deltaRow[] = {-1, 0, +1, 0};
        int deltaCol[] = {0, +1, 0, -1};
        while(!quu.isEmpty()) {
            int row = quu.peek().row;
            int col = quu.peek().col;
            int distance = quu.peek().distance;
            quu.remove();
            distanceArr[row][col] = distance;
            for(int i=0; i<deltaRow.length; i++) {
                int nbrRow = row + deltaRow[i];
                int nbrCol = col + deltaCol[i];
                if(nbrRow >= 0 && nbrRow < n && nbrCol >= 0 && nbrCol < m && 
                visitedArr[nbrRow][nbrCol] == false ) {
                    quu.add(new Pair(nbrRow, nbrCol, distance + 1));
                    visitedArr[nbrRow][nbrCol] = true;
                }
            } 
        }
        return distanceArr;        
    }
}
// ______________________________________________________________________________



class Graph_11_Replace_O_with_X_OR_SurroundedRegion {
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
                arr[i][j] = scanner.nextLine().charAt(0);    
            }
        }
        System.out.println("-----------------------------------");
        
        SolutionOptimal solOPT = new SolutionOptimal();
        System.out.println("DistanceArray of Nearest Cell having 1");
        char[][] distanceArray = solOPT.DistanceArray_NearestCell_HavingONE_OPTM(arr, n, m);    
        for(int[] iterRow: distanceArray) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    } 
} 