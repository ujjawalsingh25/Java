import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


// _________________________________________
// Size: 10                                 |
// Array: 2 5 9 6 9 3 8 9 7 1               |
// Dublicate: 9                             |
// ------------------------------------     |
// Size: 5                                  |
// Array: 2 3 1 3 4                         |
// Dublicate: 3                             |
// Slow: 2  , Fast: 2                       |
// Slow: 1  , Fast: 3                       |
// Slow: 3  , Fast: 3                       |
// Slow2: 3  , Fast2: 2                     |
// Slow2: 3  , Fast2: 1                     |
// ------------------------------------     |
// Size: 5                                  |
// Array: 1 2 3 4 4                         |
// Dublicate: 4                             |
// Slow: 1  , Fast: 1                       |
// Slow: 2  , Fast: 3                       |
// Slow: 3  , Fast: 4                       |
// Slow: 4  , Fast: 4                       |
// Slow2: 4  , Fast2: 1                     |
// Slow2: 4  , Fast2: 2                     |
// Slow2: 4  , Fast2: 3                     |
// _________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n log*n) + O(n)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int findDublicate_Brute(ArrayList<Integer> arrMain, int n) {
        ArrayList<Integer> arr = new ArrayList<>(arrMain);      // Creates a copy so that main function "arr" won't change
        //                                  in C++ we pass referrence as "&arr" OR if "arr" then makes copy itself

        // In Java, all objects, including ArrayList<Integer>, are passed by reference, 
        // but primitives (like int, double, etc.) are passed by value.
        //  However, Java does not have C++-style references (&) to control whether an argument
        //  is passed by reference or by value explicitly. 
        Collections.sort(arr);
        for(int i=0; i<n-1; i++) {
            if(arr.get(i) == arr.get(i+1))  return arr.get(i);
        }
        return -1;
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(n)
// Space Complexity: O(n)
// ---------------------------------------------------
class SolutionBetter {
    public int findDublicate_Better(ArrayList<Integer> arr, int n) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for(int i=0; i<n; i++) {
            // if(mpp[arr.get(i)] > 1) return arr.get(i);
            // mpp[arr.get(i)] = +1;
            if (mpp.containsKey(arr.get(i)))  return arr.get(i);
            mpp.put(arr.get(i), 1);
        }
        return -1;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    public int findDublicate_OPTM(ArrayList<Integer> arr) {
        int slow = arr.get(0);
        int fast = arr.get(0);
        
        do {
            System.out.println("Slow: " + slow + "  , Fast: " + fast);
            slow = arr.get(slow);
            fast = arr.get(arr.get(fast));
        } while(slow != fast);
        System.out.println("Slow: " + slow + "  , Fast: " + fast);
        
        fast = arr.get(0);
        while(slow != fast) {
            System.out.println("Slow2: " + slow + "  , Fast2: " + fast);
            slow = arr.get(slow);
            fast = arr.get(fast);
        }
        return slow;
    }
}
// ______________________________________________________________________________


class Array_10_FindDublicate_inArrayOf_Nplus1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        
        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0; i<n; i++) {
            arr.add(scanner.nextInt());
        }

        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.findDublicate_Brute(arr, n);
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.findDublicate_Better(arr, n);
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.findDublicate_OPTM(arr);

        System.out.println("Dublicate in 'N+1' Array: " + ansBr);
        System.out.println("Dublicate in 'N+1' Array: " + ansBetter);
        System.out.println("Dublicate in 'N+1' Array: " + ansOPT);

        scanner.close();
    }
}