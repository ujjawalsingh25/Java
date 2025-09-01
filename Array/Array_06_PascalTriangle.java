import java.util.ArrayList;
import java.util.Scanner;

// _____________________________________________
// Row: 5                                       |
// Column: 2                                    |
// 1                                            |
// 1 1                                          |
// 1 2 1                                        |
// 1 3 3 1                                      |
// ----------------------------                 |
// Element at (5, 2): 4                         |
// ----------------------------                 |
// 1 4 6 4 1                                    |
// 1 4 6 4 1                                    |
// ----------------------------                 |
// _____________________________________________|

// ___________________  BRUTE  _________________________________________________
// Time Complexity:  
// Space Complexity: 
// -------------------------------------------------------------------------
class SolutionBrute {
    public void printPascalTriangle_Brute(int rows) {
        int[][] C = new int[rows][rows];
        for (int n = 0; n < rows; n++) {
            for (int r = 0; r <= n; r++) {
                if (r == 0 || r == n) {
                    C[n][r] = 1;
                } else {
                    C[n][r] = C[n - 1][r - 1] + C[n - 1][r];
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int k = 1; k < (rows - i); k++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// ______________________________________________________________________________


// _____________  Find Element of Pascal Triangle at given position (row, column)  ____________________
// Time Complexity: O(r)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class findElement_ofPascalTriangle
{    
    long find_nCr(int n, int r) {
        long result = 1;
            for(int i=0;i<r;i++){
                result = result * (n-i);
                result = result / (i+1);
            }
        return result;
    }

    class SolutionOptimal {
        public long find_nthRow_rthCol_Element(int n, int r) {        // find n-1 C r-1
            return find_nCr(n-1, r-1);
        }
    }
}
// ______________________________________________________________________________



// _____________   Find all Element of Pascal Triangle for given row(n)  ________________
class find_nthRow_allElements_ofPascalTriangle
{
    long find_nCr(int n, int r) {
        long result = 1;
            for(int i=0;i<r;i++){
                result = result * (n-i);
                result = result / (i+1);
            }
        return result;
    }

    // ___________________  BETTER  ______________________
    // Time Complexity: O(n * r)
    // Space Complexity: O(1)
    // ----------------------------------
    class SolutionBetter {
        public void find_nthRow_allElement_Better(int n) {
            for(int i=1; i<=n; i++){                                  // Loop should be 1 to n *****
                System.out.print(find_nCr(n-1, i-1) + " ");
            }
            System.out.println();
        }
    }
    // ________________________________________________________
    
    // ___________________  OPTIMAL  _________________
    // Time Complexity: 
    // Space Complexity: 
    // ----------------------------------
    class SolutionOptimal {
        public void find_nthRow_allElement_OPTM(int n) {
            int ans = 1;
            System.out.print(ans);
            for(int i=1; i<n; i++) {
                ans = ans * (n-1);
                ans = ans / i;
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }
    // ____________________________________________
}
// ______________________________________________________________________________




// ___________________  Printing Pascal Triangle  ________________________________
// -----------------------------------------------------------------
// Time Complexity: O(n) + O(SubArray-Size) // for printing
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionPrintingArray {
    ArrayList<Long> generateRow(int row) {
        long ans = 1;
        ArrayList<Long> ansRow = new ArrayList<>();
        ansRow.add(ans);
        for(int col=1; col<row; col++) {
            ans = ans * (row - col);
            ans = ans / (col);
            ansRow.add(ans);
        }
        return ansRow;
    }

    public ArrayList<ArrayList<Long>> print_PascalTriangle(int n) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<>();
        for(int i=1;i<n;i++){
            triangle.add(generateRow(i));
        }
        return triangle;
    }
}
// ______________________________________________________________________________

class Array_06_PascalTriangle
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Row: ");
        int n = scanner.nextInt();
        System.out.print("Column: ");
        int r = scanner.nextInt();


        SolutionBrute triangleBrute = new SolutionBrute();
        triangleBrute.printPascalTriangle_Brute(n-1);
        System.out.println("----------------------------");
        
        findElement_ofPascalTriangle solution = new findElement_ofPascalTriangle();
        findElement_ofPascalTriangle.SolutionOptimal findElement = solution.new SolutionOptimal();
        long element = findElement.find_nthRow_rthCol_Element(n, r);        // find n-1 C r-1
        System.out.println("Element at (" + n + ", " + r + "): " + element);
        System.out.println("----------------------------");
        
        find_nthRow_allElements_ofPascalTriangle solutionAllElement = new find_nthRow_allElements_ofPascalTriangle();
        find_nthRow_allElements_ofPascalTriangle.SolutionBetter allRowELement_Better = solutionAllElement.new SolutionBetter(); 
        find_nthRow_allElements_ofPascalTriangle solutionAllElement_OPTM = new find_nthRow_allElements_ofPascalTriangle();
        find_nthRow_allElements_ofPascalTriangle.SolutionBetter allRowELement_OPTM = solutionAllElement_OPTM.new SolutionBetter(); 
        allRowELement_Better.find_nthRow_allElement_Better(n);
        allRowELement_OPTM.find_nthRow_allElement_Better(n);
        System.out.println("----------------------------");

        SolutionPrintingArray trianglePrint = new SolutionPrintingArray();
        ArrayList<ArrayList<Long>> triangle = trianglePrint.print_PascalTriangle(n);
        for(ArrayList<Long> iterRow: triangle){
            for(long data: iterRow) {
                System.out.print(data + " ");
            }
            System.out.println();
        }


        scanner.close();
    }
}