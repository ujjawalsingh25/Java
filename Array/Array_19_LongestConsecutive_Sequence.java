import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


// _________________________________________________
// Size: 9                                          |
// Array: 102 4 100 1 101 3 2 1 1                   |
// Longest Consecutive Sequence: 4                  |
//          [ 1, 2, 3, 4 ]                          | 
// -----------------------------------------        |
// Size: 14                                         |
// Array: 1 1 1 2 2 3 4 4 100 100 100 101 101 102   |
// Longest Consecutive Sequence: 4                  |
// _________________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int Array_19_LongestConsecutiveSequence_Brute(ArrayList<Integer> arr, int n) {
        int maxLength = 0, currElement = 0, currLength = 0;
        for(int i=0; i<n ;i++) {
            currLength = 1;
            currElement = arr.get(i);
            while(arr.contains(currElement+1)) {
                currElement += 1;
                currLength++;
            }
            maxLength = Math.max(maxLength, currLength);
        }
        return maxLength;
    }
}
// _____________________________________________________________________________

 
// ___________________  BETTER  ___________________________________________________
// Time Complexity: O()  -->> loop + HashMap operations --> O(1) -> unsorted_map
// Space Complexity: O(1) 
// ---------------------------------------------------
class SolutionBetter {
    public int Array_19_LongestConsecutiveSequence_Better(ArrayList<Integer> arr, int n) {
        Collections.sort(arr);
        int lastSmallest = Integer.MIN_VALUE, currCount = 0, maxLength = 1;
        for(int i=0; i<n; i++) {
            if(lastSmallest + 1 == arr.get(i)) {
                lastSmallest = arr.get(i);
                currCount++;
            } 
            else if(lastSmallest != arr.get(i)) {
                lastSmallest = arr.get(i);
                currCount = 1;
            }
            maxLength = Math.max(maxLength, currCount);
        }
        return maxLength;
    }
}
// _____________________________________________________________________________
  
     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2*n + n)   -->> 2*n -->> n is of loop and  for && n -->> putting data to set  
// Space Complexity: O(n)
// -----------------------------------------------------------------
class SolutionOptimal {
    public int Array_19_LongestConsecutiveSequence_OPTM(ArrayList<Integer> arr, int n) {
        HashSet<Integer> st = new HashSet<>();
        int currElement = 0, maxLength = 1, length = 0; 
        for(int i=0; i<n; i++) {
            st.add(arr.get(i));
        }
        for(int setIter: st) {
            if(!st.contains(setIter - 1)) {
                currElement = setIter;
                length = 1;
                while(st.contains(currElement + 1)) {
                    currElement += 1;
                    length++;
                }
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
}
// ______________________________________________________________________________


class Array_19_LongestConsecutive_Sequence {
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
        int ansBr = solBr.Array_19_LongestConsecutiveSequence_Brute(arr, n);
        System.out.print("Longest Consecutive Sequence (Brute): " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.Array_19_LongestConsecutiveSequence_Better(arr, n);
        System.out.print("\nLongest Consecutive Sequence (Better): " + ansBetter);
        
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.Array_19_LongestConsecutiveSequence_OPTM(arr, n);        
        System.out.print("\nLongest Consecutive Sequence (OPT): " + ansOPT);
        

        scanner.close();
    } 
} 