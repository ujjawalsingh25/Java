import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// _________________________________________
// Size: 9                                  |
// Array: 1 2 3 1 1 1 1 3 3                 |
// Target: 3                                |
// Longest SubArray with Sum equals k: 4    |
//     -->>  [ 1 1 1 1 ]                    |
// ----------------------------------       |
// Size: 6                                  |
// Array: 1 0 -4 3 1 0                      |
// Target: 0                                |
// Longest SubArray with Sum equals k: 5    |
//     -->>  [ 0 -4 3 1 0 ]                 |
// _________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int LongestSubArray_withSumK_Brute(ArrayList<Integer> arr, int n, int target) {
        int maxLength = 0;
        for(int i=0; i<n ;i++) {
            int sum = 0;
            for(int j=i; j<n; j++) {
                sum += arr.get(j);
                if(sum == target) maxLength = Math.max(maxLength, j-i+1);
            }
        }
        return maxLength;
    }
}
// _____________________________________________________________________________



// ___________________  BETTER (Both positive and Negative) __________________________
// Time Complexity: O(n * (log*n || O(1))  -->> loop + HashMap operations --> O(1) -> unsorted_map
// Space Complexity: O(n) 
// ---------------------------------------------------
class SolutionBetter {
    public int LongestSubArray_withSumK_Better(ArrayList<Integer> arr, int n, int target) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int sum = 0, length = 0, maxLength = 0;
        for(int i=0; i<n; i++) {
            sum += arr.get(i);
            if(sum == target) {         // If the sum itself equals the target, update maxLength
                maxLength = Math.max(maxLength, i+1);
            }
            int prefixSum = sum - target;
            if(mpp.containsKey(prefixSum)) {    // Check if prefixSum(sum - target) exists
                length = i - mpp.get(prefixSum);
                maxLength = Math.max(maxLength, length);
            }
            if(!mpp.containsKey(sum)) {  
                mpp.put(sum, i);
            }
        }
        return maxLength;
    }
}
// _____________________________________________________________________________
  
     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2*n)   -->> Second loop don't runs for all iterations -->> O(n) in worst case  
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    public int LongestSubArray_withSumK_OPTM(ArrayList<Integer> arr, int n, int target) {
        int left = 0, right = 0;
        int sum = arr.get(0), maxLength = 0;
        while(right < n) {
            while(left <= right && sum > target) {
                sum -= arr.get(left);
                left++;
            }
            if(sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
            if(right < n)  sum += arr.get(right);
        }
        return maxLength;
    }
}
// ______________________________________________________________________________


class Array_18_LongestSubArray_withSumK {
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
        int ansBr = solBr.LongestSubArray_withSumK_Brute(arr, n, target);
        System.out.print("Longest SubArray with Sum equals k (Brute): " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.LongestSubArray_withSumK_Better(arr, n, target);
        System.out.print("\nLongest SubArray with Sum equals k (Better): " + ansBetter);
        
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.LongestSubArray_withSumK_OPTM(arr, n, target);        
        System.out.print("\nLongest SubArray with Sum equals k (OPT): " + ansOPT);
        

        scanner.close();
    } 
} 