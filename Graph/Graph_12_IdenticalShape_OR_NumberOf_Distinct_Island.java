import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner; 

// Size(n): 4
// Size(m): 5
//   Array 
//  1  1  0  1  1
//  1  0  0  0  0
//  0  0  0  1  1
//  1  1  0  1  0
// ----------------------------
// Number of Distinct Island || Islands At Boundaries: 2   
// (0,0) , (0,1) , (1,0)
// (3,0) , (3,1)

class Pair {
    int row, col;
    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}


// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O(n*m) + O(4* n*m)  -> approx O(n*m)  -->> Adding 1 to visitedArr + n*m is for every node && check 4 directions 
// Space Complexity:  O(n*m) + O(n*m)  -->> visitedArray + distanceArr   
// ---------------------------------------------------------------------
class SolutionOptimal {
    private String toString(int row, int col) {
        return Integer.toString(row) + " " + Integer.toString(col);
    }

    public void dfs(int row, int col, int rowBase, int colBase, ArrayList<String> pointsPair,
    int[][] arr, boolean[][] visitedArr, int n, int m) {

        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition
        // TODO  --->>   Need Complition

        
        visitedArr[row][col] = true;
        pointsPair.add(toString(row - rowBase, col - colBase));
        int deltaRow[] = {-1, 0, +1, 0};
        int deltaCol[] = {0, +1, 0, -1};
        for(int i=0; i<deltaRow.length; i++) {
            int nbrRow = row + deltaRow[i];
            int nbrCol = col + deltaCol[i];
            if(nbrRow >= 0 && nbrRow < n && nbrCol >= 0 && nbrCol < m && 
            arr[nbrRow][nbrCol] == 1 && visitedArr[nbrRow][nbrCol] == false) {
                dfs(nbrRow, nbrCol, rowBase, colBase, pointsPair, arr, visitedArr, n, m);
            }
        } 
        
    }

    public int NumberOf_DistinctIsland_OR_Island_At_Boundaries_OPTM(int[][] arr, int n, int m) {
        HashSet<ArrayList<String>> st = new HashSet<>();
        boolean[][] visitedArr = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visitedArr[i][j] && arr[i][j] == 1) {
                    ArrayList<String> pointsPair = new ArrayList<>();
                    dfs(j, j, i, j, pointsPair, arr, visitedArr, n, m);
                    st.add(pointsPair);
                }
            }
        }
        return st.size();        
    }
}
// ______________________________________________________________________________



class Graph_12_IdenticalShape_OR_NumberOf_Distinct_Island {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size(n): ");
        int n = scanner.nextInt();
        System.out.print("Size(m): ");
        int m = scanner.nextInt();
        
        int[][] arr = new int[n][m];
        System.out.println("Array ");
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                arr[i][j] = scanner.nextInt();    
            }
        }
        System.out.println("-----------------------------------");
        
        SolutionOptimal solOPT = new SolutionOptimal();
        int distinctIsland = solOPT.NumberOf_DistinctIsland_OR_Island_At_Boundaries_OPTM(arr, n, m);    
        System.out.println("Number of Distinct Island || Islands At Boundaries: " + distinctIsland);
        
        scanner.close();
    } 
} 