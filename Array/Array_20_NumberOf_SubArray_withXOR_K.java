import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// _______________________________________________
// Size: 5                                        |
// Array: 4 2 2 6 4                               |
// Target: 6                                      |
// Number of SubArray with XOR equals k: 4        |
//    (4, 2) , (2, 2, 6) , (6) , (4, 2, 2, 6 4)   |
// ----------------------------------             |
// Size: 5                                        |
// Array: 5 6 7 8 9                               |
// Target: 5                                      |
// Number of SubArray with XOR equals k: 2        |
//      (5)  ,  (5, 6, 7, 8, 9)                   |
// _______________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^3)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int NumberOf_SubArray_withXOR_K_Brute(ArrayList<Integer> arr, int n, int target) {
        int count = 0;
        for(int i=0; i<n ;i++) {
            for(int j=i; j<n; j++) {
                int xor = 0;
                for(int k=i; k<=j; k++) {
                    xor ^= arr.get(k);
                }
                if(xor == target) count++;
            }
        }
        return count;
    }
}
// _____________________________________________________________________________

  
// ___________________  BETTER  __________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBetter {
    public int NumberOf_SubArray_withXOR_K_Better(ArrayList<Integer> arr, int n, int target) {
        int count = 0;
        for(int i=0; i<n ;i++) {
            int xor = 0;
            for(int j=i; j<n; j++) {
                xor ^= arr.get(j);
                if(xor == target) count++;
            }
        }
        return count;
    }
}
// _____________________________________________________________________________
  
     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n * (log*n || O(1))  -->> loop + HashMap operations --> O(1) -> unsorted_map
// Space Complexity: O(n) 
// -----------------------------------------------------------------
class SolutionOptimal {
    public int NumberOf_SubArray_withXOR_K_OPTM(ArrayList<Integer> arr, int n, int target) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int totalXOR = 0, count = 0;
        mpp.put(0, 1);      // Initialize {0: 1} -->> where a subarray itself equals the target
        for(int i=0; i<n; i++) {
            totalXOR ^= arr.get(i);     // Calculate cumulative XOR from index 0 to i
            int prefixXOR = totalXOR ^ target;

            count += mpp.getOrDefault(prefixXOR, 0);   // If prefixXOR exists in the map,
            //                                                                  add its frequency to count 
            mpp.put(totalXOR, mpp.getOrDefault(totalXOR, 0) + 1);  ///update the frequency 
            //                                                                      of the current totalXOR
        }
        return count; 
    }
}
// ______________________________________________________________________________


class Array_20_NumberOf_SubArray_withXOR_K {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0; i<n; i++) {
            arr.add(scanner.nextInt());
        }
        System.out.print("Target: ");
        int target = scanner.nextInt();
        
        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.NumberOf_SubArray_withXOR_K_Brute(arr, n, target);
        System.out.print("Longest SubArray with Sum equals k (Brute): " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.NumberOf_SubArray_withXOR_K_Better(arr, n, target);
        System.out.print("\nLongest SubArray with Sum equals k (Better): " + ansBetter);
        
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.NumberOf_SubArray_withXOR_K_OPTM(arr, n, target);        
        System.out.print("\nLongest SubArray with Sum equals k (OPT): " + ansOPT);
        

        scanner.close();
    } 
} 