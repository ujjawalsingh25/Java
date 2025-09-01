import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// __________________________________________
// Size: 7                                  |
// Array: 2 2 3 3 1 2 2                     |
// Majority Element > n/2 times: 2          |
// ----------------------------------       |
// Size: 16                                 |                  
// Array: 7 7 5 7 5 1 5 7 5 5 7 7 5 5 5 5   |
// Majority Element > n/2 times: 5          |
// _________________________________________|


// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int MajorityElement_Brute(ArrayList<Integer> arr, int n) {
        for(int i=0; i<n; i++) {
            int count = 0;
            for(int j=0; j<n; j++) {
                if(arr.get(i) == arr.get(j))  count++;
            }
            if(count > n/2)  return arr.get(i);
        }
        return -1;
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(n* log*n) + O(n)  -->> hashLoop + add freq to hash
// Space Complexity: O(n) 
// ---------------------------------------------------
class SolutionBetter {
    public int MajorityElement_Better(ArrayList<Integer> arr, int n) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for(int i=0; i<n; i++) {
            mpp.put(arr.get(i), mpp.getOrDefault(arr.get(i), 0) + 1);  
        }
        for(int keyIter : mpp.keySet()) {
            // System.out.print("key or arr[i] " +  " -->> "  +  "Value or freq");
            // System.out.print("\n" + keyIter   +  " -->> "  +  mpp.get(keyIter));
            if(mpp.get(keyIter) > n/2)  return keyIter;
        }System.out.println();
        return -1;
    }
}
// _____________________________________________________________________________

                 
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n) + O(n)  -->> Finding Majority Element + Verifying it    
// Space Complexity: O(1)      (if it is fixed to exist a Majority element no need to verify O(n) )
// -----------------------------------------------------------------
class SolutionOptimal {
    public int MajorityElement_OPTM(ArrayList<Integer> arr, int n) {
        int count = 0, element = 0, verifyCount = 0;
        for(int i=0; i<n; i++) {
            if(count == 0) {
                element = arr.get(i);
                count++;
            }
            else if(arr.get(i) == element)  count++;
            else count--;
        }
        if(count > 0) {
            for(int i=0; i<n; i++) {
                if(arr.get(i) == element)   verifyCount++;
            }
        }
        if(verifyCount > n/2)   return element;
        return -1;
    }
}
// ______________________________________________________________________________


class Array_16_MajorityElement_nBy2_TimesGreater {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0; i<n; i++) {
            arr.add(scanner.nextInt());
        }
        System.out.println("-----------------------------------");

        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.MajorityElement_Brute(arr, n);
        System.out.println("Majority Element > n/2 times Brute: " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.MajorityElement_Better(arr, n);
        System.out.println("Majority Element > n/2 times Better: " + ansBetter);
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.MajorityElement_OPTM(arr, n);        
        System.out.println("Majority Element > n/2 times OPT: " + ansOPT);

        scanner.close();
    } 
} 