import java.util.ArrayList;
import java.util.Scanner;

// ________________________________________________
// 1   2   3   4                                   |
// 5   6   7   8                                   |
// 9   10  11  12                                  |
// 13  14  15  16                                  |
// ----------------------------------              |
//    ↓  ↓  ↓                                      |
//   Transpose                                     |
//    ↓  ↓  ↓                                      |  
// 1   5   9   13  |   →  →  →    13   9   5  1    |  
// 2   6   10  14  |   Reverse    14  10   6  2    |  
// 3   7   11  15  |   →  →  →    15  11   7  3    |  
// 4   8   12  16  |              16  12   8  4    |  
// ________________________________________________|

// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(n^2)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<ArrayList<Integer>> rotateMatrix_Brute(ArrayList<ArrayList<Integer>> arr) {
        int n = arr.size();
        int m = arr.get(0).size();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                ans.get(i).add(0);          // Initialize the matrix with 0 to store ans
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                ans.get(j).set(n-1-i, arr.get(i).get(j));
            }
        }
        return ans;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n/2 * n/2) + O(n * n/2)   (Transpose + Reverse)
// Space Complexity: O(1) 
// -----------------------------------------------------------------
class SolutionOptimal {
    public void swap(ArrayList<Integer> arr, int x, int y){
        int temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }

    public void reverseRows(ArrayList<ArrayList<Integer>> arr) {
        for(ArrayList<Integer> row : arr){
            int start = 0;
            int end = row.size()-1;
            while (start < end) {
                swap(row, start, end);
                start++;
                end--;
            }
        }
    }

    public void transpose(ArrayList<ArrayList<Integer>> arr) {
        int n = arr.size();
        int m = arr.get(0).size();
        // for(int i=0;i<n;i++){
            // for(int j=0;j<n;j++){
            //     if(i == j)
            //         ans[i][j] = arr[i][j];
            //     else
            //         ans[i][j] = arr[j][i];     
            // }
        // }
            // **** OR **** in same array we save space.
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<m;j++){
                // swap(arr[i][j], arr[j][i]);
                int temp = arr.get(i).get(j);
                arr.get(i).set(j, arr.get(j).get(i));
                arr.get(j).set(i, temp);
            }
        }
    }

    public void rotateMatrix(ArrayList<ArrayList<Integer>> arr) {
        transpose(arr);
        reverseRows(arr);
    }


}
// ______________________________________________________________________________


class Array_07_RotateMatrix__Reverse_and_Transpose
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

        System.out.print("Size (n): ");
        int n = scanner.nextInt();
        System.out.print("Size (m): ");
        int m = scanner.nextInt();
        System.out.println("Matrix ");
        for(int i=0;i<n;i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0;j<m;j++) {
                row.add(scanner.nextInt());
            }
            arr.add(row);
        }
        System.out.println("-----------------------------------");

        SolutionBrute matrix = new SolutionBrute();
        ArrayList<ArrayList<Integer>> rotated = matrix.rotateMatrix_Brute(arr);
        for(ArrayList<Integer> iterRow: rotated) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");

        SolutionOptimal matrixOptimal = new SolutionOptimal();
        matrixOptimal.rotateMatrix(arr);
        for(ArrayList<Integer> iterRow: arr) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }


        scanner.close();
    }
}