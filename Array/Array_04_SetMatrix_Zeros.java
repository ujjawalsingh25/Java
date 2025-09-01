import java.util.Scanner;

// _____________________________________
// Size (n): 4                          |
// Size (m): 4                          |
// Matrix:  1   1   1   1               |
//          1   0   0   1               |
//          1   1   0   1               |
//          1   1   1   1               |
// ----------------------------------   |
//                 Columns              |
//                  ↓   ↓               |  
//    Rows      1   0   0   1           |  
//  →  →  →     0   0   0   0           |  
//  →  →  →     0   0   0   0           |  
//              1   0   0   1           |  
// _____________________________________|

// ___________________  BRUTE  ______________________________________________
// Time Complexity: O((n*m)*(n+m) + (n*m)) --> O((n^3) + (n*m))  -->> Loop * (makeRow + makeCol) + Loop
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    void makeCol_minusOne(int arr[][], int row, int n) {
        for(int i=0; i<n; i++) {     // rows are mean to be fixed we only change columns '↓'
            if(arr[i][row] != 0)  arr[i][row] = -1;
        }
    }
    void makeRow_minusOne(int arr[][], int col, int m) {
        for(int j=0; j<m; j++) {     // rows are mean to be fixed we only change columns '→'
            if(arr[col][j] != 0)  arr[col][j] = -1;
        }
    }

    public int[][] SetMatrix_Zeros_Brute(int arr[][], int n, int m) {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) {
                    makeCol_minusOne(arr, i, n);
                    makeRow_minusOne(arr, j, m);
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                if(arr[i][j] == -1)   arr[i][j] = 0;
            }
        }
        return arr;
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(2* n * m)
// Space Complexity: O(n) + O(m)  -->> for arrays using for hashing
// ---------------------------------------------------
class SolutionBetter {
    public int[][] SetMatrix_Zeros_Better(int arr[][], int n, int m) {
        int rowMark[] = new int[n];
        int colMark[] = new int[m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) {
                    rowMark[i] = 1;     
                    colMark[j] = 1; 
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(rowMark[j] == 1 || colMark[i] == 1)   arr[i][j] = 0;
            }
        }        
        return arr;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2* n * m)
// Space Complexity: 1 variable -->> col0 
// -----------------------------------------------------------------
class SolutionOptimal {
    public int[][] SetMatrix_Zeros_OPTM(int[][] arr, int n, int m)  {
        int col0 = 1;
        for(int i=0; i<n ;i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) {
                    arr[i][0] = 0;
                    if(j != 0)  arr[0][j] = 0;
                    else col0 = 0;
                }
            }
        }
        for(int i=1; i<n ;i++) {
            for(int j=1; j<m; j++) {
                if(arr[i][j] != 0) {        // element is not 0 already
                    if(arr[i][0] == 0 || arr[0][j] == 0)   arr[i][j] = 0;
                }
            }
        }
        if(arr[0][0] == 0) {        // FIRST SOLVE FOR ROWS       
            for(int j=0; j<m; j++) {
                arr[0][j] = 0;
            }
        }
        if(col0 == 0) {                 // SECONDLY SOLVE FOR COLUMNS
            for(int i=0; i<n; i++) {
                arr[i][0] = 0;
            }
        }
        return arr;   
    }
}
// ______________________________________________________________________________


class Array_04_SetMatrix_Zeros
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

        // SolutionBrute solBr = new SolutionBrute();
        // int[][] ansBr = solBr.SetMatrix_Zeros_Brute(arr, n, m);
        // for(int[] iterRow: ansBr) {
        //     for(int element: iterRow) {
        //         System.out.print(element + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("-----------------------------------");

        // SolutionBetter solBetter = new SolutionBetter();
        // int[][] ansBetter = solBetter.SetMatrix_Zeros_Better(arr, n, m);
        // for(int[] iterRow : ansBetter) {
        //     for(int element : iterRow) {
        //         System.out.print(element + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("-----------------------------------");

        SolutionOptimal solOPT = new SolutionOptimal();
        int[][] ansOPTM = solOPT.SetMatrix_Zeros_OPTM(arr, n, m);
        for(int[] iterRow : ansOPTM) {
            for(int element : iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}