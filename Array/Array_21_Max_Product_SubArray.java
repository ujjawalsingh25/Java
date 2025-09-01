import java.util.ArrayList;
import java.util.Scanner;


// ____________________________________
// Size: 4                             |
// Array: 2 3 -2 4                     |
// Maximum Product SubArray : 2        |
//   -->>  (2 * 3)                     |
// ----------------------------------  |
// Size: 8                             |
// Array: 3 2 -1 4 -6 3 -2 6           |
// Maximum Product SubArray : 864      | 
//   -->>  (4 * -6 * 3 * -2 * 6)       |
// ____________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^3)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int Maximum_Product_SubArray_Brute(ArrayList<Integer> arr, int n) {
        int maxProduct = Integer.MIN_VALUE;
        for(int i=0; i<n ;i++) {
            for(int j=i; j<n; j++) {
                int product = 1;
                for(int k=i; k<=j; k++) {
                    product *= arr.get(k);
                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }
        return maxProduct;
    }
}
// _____________________________________________________________________________

  
// ___________________  BETTER  __________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBetter {
    public int Maximum_Product_SubArray_Better(ArrayList<Integer> arr, int n) {
        int maxProduct = Integer.MIN_VALUE;
        for(int i=0; i<n ;i++) {
            int product = 1;
            for(int j=i; j<n; j++) {
                product *= arr.get(j);
                maxProduct = Math.max(maxProduct, product); 
            }
        }
        return maxProduct;
    }
}
// _____________________________________________________________________________
  
     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n)
// Space Complexity: O(1) 
// -----------------------------------------------------------------
class SolutionOptimal {
    public int Maximum_Product_SubArray_OPTM(ArrayList<Integer> arr, int n) {
        int prefixProduct = 1, suffixProduct = 1, maxProduct = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            if(prefixProduct == 0)  prefixProduct = 1;
            if(suffixProduct == 0)  suffixProduct = 1;

            prefixProduct *= arr.get(i);
            suffixProduct *= arr.get(n-i-1);
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }        
        return maxProduct; 
    }
}
// ______________________________________________________________________________


class Array_21_Max_Product_SubArray {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0; i<n; i++) {
            arr.add(scanner.nextInt());
        }
        
        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.Maximum_Product_SubArray_Brute(arr, n);
        System.out.print("Longest SubArray with Sum equals k (Brute): " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.Maximum_Product_SubArray_Better(arr, n);
        System.out.print("\nLongest SubArray with Sum equals k (Better): " + ansBetter);
        
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.Maximum_Product_SubArray_OPTM(arr, n);        
        System.out.print("\nLongest SubArray with Sum equals k (OPT): " + ansOPT);

        scanner.close();
    } 
} 