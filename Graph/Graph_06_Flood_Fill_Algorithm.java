import java.util.Scanner;

// Size(n): 3
// Size(m): 3
// Array 
// 1  1  1
// 2  2  0
// 2  2  2
// -----------------------------------
// Initial Row: 2
// Initial Column: 2
// New Color: 3
// 1  1  1
// 3  3  0
// 3  3  3


// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O(4* n*m)    -->>  n*m is for every node && check 4 directions for each node 
// Space Complexity:    O(n*m) + O(n*m)       -->> ansArray + Reccursion Stack Space    
// ---------------------------------------------------------------------
class SolutionOptimal {
    void DFS_Traversal_toFillColor(int row, int col, int[] deltaRow, int[] deltaCol, 
    int newColor, int initialColor, int[][] ans, int[][] arr, int n, int m) {
        ans[row][col] = newColor;
        for(int i=0; i<deltaRow.length; i++) {
            int nbrRow = row + deltaRow[i];
            int nbrCol = col + deltaCol[i];
            if(nbrRow >= 0 && nbrRow < n && nbrCol >= 0 && nbrCol < m && 
            arr[nbrRow][nbrCol] == initialColor && ans[nbrRow][nbrCol] != newColor) {
                DFS_Traversal_toFillColor(nbrRow, nbrCol, deltaRow, deltaCol, newColor, initialColor, ans, arr, n, m);
            }
        }
    }

    public int[][] FloodFill_Algorithm_OPTM(int row, int col, int newColor, int[][] arr, int n, int m) {
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = arr[i][j];
            }
        }
        int initialColor = arr[row][col];
        int deltaRow[] = {-1, 0, +1, 0};
        int deltaCol[] = {0, +1, 0, -1};
        DFS_Traversal_toFillColor(row, col, deltaRow, deltaCol, newColor, initialColor, ans, arr, n, m);

        return ans;        
    }
}
// ______________________________________________________________________________



class Graph_06_Flood_Fill_Algorithm {
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
        System.out.print("Initial Row: ");
        int initialRow = scanner.nextInt();
        System.out.print("Initial Column: ");
        int initialCol = scanner.nextInt();
        System.out.print("New Color: ");
        int newColor = scanner.nextInt();
        
        
        SolutionOptimal solOPT = new SolutionOptimal();
        int[][] floodFilled_Array = solOPT.FloodFill_Algorithm_OPTM(initialRow, initialCol, newColor, arr, n, m);    
        for(int[] iterRow: floodFilled_Array) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    } 
} 