import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// ______________________________
// Size: 6                       |
// Array: 4 3 6 2 1 1            |
// Missing: 5                    |
// Repeating: 1                  |
// ----------------------------- |
// Size: 5                       |
// Array: 1 2 3 3 5              |
// Missing: 4                    |
// Repeating: 3                  |
// ______________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<Integer> Missing_and_Repeating_Brute(ArrayList<Integer> arr, int n) {
        int missing = -1, repeating = -1;
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i=1; i<=n; i++) {
            int count = 0;
            for(int j=0; j<n; j++) {
                if(arr.get(j) == i) count++;    
            }
            if(count >= 2)  repeating = i;
            else if(count == 0)  missing = i;
            if(missing != -1 && repeating != -1)  break;
        }
        ans.add(missing);
        ans.add(repeating);
        return ans; 
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(2n)
// Space Complexity: O(n)  -> HashMap
// ---------------------------------------------------
class SolutionBetter {
    public ArrayList<Integer> Missing_and_Repeating_Better(ArrayList<Integer> arr, int n) {
        int missing = -1, repeating = -1;
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> mpp = new HashMap<>();

        for(int i=0; i<n; i++) {
            mpp.put(arr.get(i), mpp.getOrDefault(arr.get(i), 0) + 1);
            // The method getOrDefault(i, defaultValue: 0) is used to safely retrieve the count 
            // of the number i from the HashMap mpp. If i is not found in mpp, it returns 0 
            // instead of null, preventing a NullPointerException.

            // The method "PUT" -->> Associates the specified value with the specified key in this map. 
            //If the map previously contained a mapping for the key, the old value is replaced with "+1 to prev".
        }

        for(int i=1; i<=n; i++) {
            // if(mpp.get(i) >= 2)    repeating = i;     // leads to NullPointerException   
            // else if(mpp.get(i) == 0)  missing = i;    // leads to NullPointerException   
            int count = mpp.getOrDefault(i, 0);
            if (count == 2) repeating = i;
            else if (count == 0) missing = i;
        }
        ans.add(missing);
        ans.add(repeating);
        return ans;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<Integer> Missing_and_Repeating__OPTM(ArrayList<Integer> arr, int n) {
        int mis = 0, rep = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        
        int sumArr = 0, sumSqArr = 0;
        int sumInt = (n*(n+1))/2;
        int sumSqInt = (n*(n+1)*(2*n+1))/6;
        for(int i=0;i<n;i++) {
            sumArr += arr.get(i);
            sumSqArr += arr.get(i) * arr.get(i);
        }

        int eq1 = sumArr - sumInt;
        int eq2 = (sumSqArr - sumSqInt) / eq1;

        rep = (eq2 + eq1)/2;
        mis = eq2 - rep;

        ans.add(mis);
        ans.add(rep);
        return ans;
    }
}
// ______________________________________________________________________________


// ___________________ OPTIMISED using XOR  ________________________________________________
// Time Complexity: O()
// Space Complexity: O()
// -----------------------------------------------------------------
class Solution_Most_Optimal {
    // public ArrayList<Integer> Missing_and_Repeating__mostOPTM(ArrayList<Integer> arr, int n) {

    // }
}
// ______________________________________________________________________________


class Array_11_Missing_and_RepeatingNumber {
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
        ArrayList<Integer> ansBr = solBr.Missing_and_Repeating_Brute(arr, n);
            System.out.println("Missing Brute: " + ansBr.get(0));
            System.out.println("Repeating Brute: " + ansBr.get(1));
            
        SolutionBetter solBetter = new SolutionBetter();
        ArrayList<Integer> ansBetter = solBetter.Missing_and_Repeating_Better(arr, n);
            System.out.println("Missing Better: " + ansBetter.get(0));
            System.out.println("Repeating Better: " + ansBetter.get(1));
            
        SolutionOptimal solOPT = new SolutionOptimal();
        ArrayList<Integer> ansOPT = solOPT.Missing_and_Repeating__OPTM(arr, n);
            System.out.println("Missing OPTM: " + ansOPT.get(0));
            System.out.println("Repeating OPTM: " + ansOPT.get(1));
            
        // Solution_Most_Optimal solOPT_XOR = new Solution_Most_Optimal();
        // ArrayList<Integer> ansOPT_XOR = solOPT_XOR.Missing_and_Repeating__mostOPTM(arr, n);
        //     System.out.println("Missing mostOPTM: " + ansOPT_XOR.get(0));
        //     System.out.println("Repeating mostOPTM: " + ansOPT_XOR.get(1));

        scanner.close();
    } 
}