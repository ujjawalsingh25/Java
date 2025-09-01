import java.util.ArrayList;
import java.util.Scanner;

// _____________________________________
// Size (n): 3                          |
// Size (m): 3                          |
// Matrix:  1  2  3                     |
//          4  5  6                     |
//          7  8  9                     |
// Spiral Traversal :                   |
//    1 2 3 6 9 8 7 4 5                 |
// --------------------------------     |
// Size (n): 4                          |
// Size (m): 4                          |
// Matrix:  1  2  3  4                  |
//          5  6  7  8                  |
//          9  4  2  6                  |
// Spiral Traversal :                   |
//    1 2 3 4 8 6 2 4 9 5 6 7           |
// _____________________________________|


// ___________________  OPTIMAL && Only Solution _________________________________________
// Time Complexity: O(n*m)
// Space Complexity: O(n*m)     // size of 1D array 
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<Integer> SpiralTraversal_ofMatrix(int[][] arr, int n, int m)  {
        ArrayList<Integer> ans = new ArrayList<>();
        int top = 0, bottom = n-1, left = 0, right = m-1;
        while(left <= right && top <= bottom) {
            for(int i=left; i<=right; i++)  ans.add(arr[top][i]);
            top++;
            for(int i=top; i<=bottom; i++)  ans.add(arr[i][right]);
            right--;
            if(top <= bottom) {       // if 1D horizontal array so bottom can't be back and OutofBound
                for(int i=right; i>=left; i--)  ans.add(arr[bottom][i]);
                bottom--;
            }
            if(left <= right) {     // if 1D vertical array so left can't be back and OutofBound
                for(int i=bottom; i>=top; i--)  ans.add(arr[i][left]);
                left++;
            }
        }
        return ans;
    }
}
// ______________________________________________________________________________


class Array_05_SpiralTraversal_ofMatrix
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Size (n): ");
        int n = scanner.nextInt();
        System.out.print("Size (m): ");
        int m = scanner.nextInt();

        int arr[][] = new int[n][m];

        System.out.println("Matrix: ");
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] =  scanner.nextInt();
            }
        }
        System.out.println("-----------------------------------");

        SolutionOptimal spiralTraversal = new SolutionOptimal();
        ArrayList<Integer> spiral = spiralTraversal.SpiralTraversal_ofMatrix(arr, n, m);
        for(int i=0; i<n*m; i++) {
            System.out.print(spiral.get(i) + " ");
        }

        scanner.close();
    }
}