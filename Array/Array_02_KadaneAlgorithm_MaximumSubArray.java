import java.util.ArrayList;
import java.util.Scanner;

// _____________________________________________________________________
// Size: 9                                                              |
// Array: -2 1 -3 4 -1 2 1 -5 4                                         |
// Output: 6                                                            |
// Explanation: The subarray [4,-1,2,1] has the largest sum 6.          |
// _____________________________________________________________________|

// ___________________  BRUTE  _________________________________________________
// Time Complexity: O(n^3)
// Space Complexity: O(1)
// -------------------------------------------------------------------------
class SolutionBrute {
    public int maximumSubArray_Brute(ArrayList<Integer> arr) {
        int n = arr.size();
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum = 0;
                for(int k=i;k<=j;k++){              // j alst element should be included
                    sum += arr.get(k);
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }
        return maxSum;
    }
}
// ______________________________________________________________________________


// ___________________  BETTER  _________________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionBetter {
    public int maximumSubArray_Better(ArrayList<Integer> arr) {
        int n = arr.size();
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum += arr.get(j);
                maxSum = Math.max(sum ,maxSum);
            }
        }
        return maxSum;
    }
}
// ______________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// ------------------  Kandane's ALGORITHM  ----------------------
// Time Complexity: O(n)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    public int maximumSubArray_Optimal(ArrayList<Integer> arr) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        // int high = 0;
        // while(high < arr.size()){
        //     sum += arr.get(high);
        //     maxSum = Math.max(maxSum, sum);
        //     if(sum < 0)   sum = 0;
        //     high++;
        // }
        // return maxSum;
        for(int i=0;i<arr.size();i++){
            sum += arr.get(i);
            if(sum > maxSum)   maxSum = sum;
            if(sum < 0)     sum = 0;
        }
        return maxSum;
    }
}
// ______________________________________________________________________________


// ___________________  Printing SubArray  ________________________________________________
// -----------------------------------------------------------------
// Time Complexity: O(n) + O(SubArray-Size) // for printing
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionPrintingArray {
    public void printMaximumSubArray(ArrayList<Integer> arr) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        int arrStart = -1, arrEnd = -1, startIdx = 0;
        for(int i=0;i<arr.size();i++){
            if(sum == 0)    startIdx = i;
            sum += arr.get(i);
            if(sum > maxSum) {
                maxSum = sum;
                arrStart = startIdx;
                arrEnd = i;
            }
            if(sum < 0)     sum = 0;
        }
        for(int i=arrStart; i<=arrEnd; i++) {
            System.out.print(arr.get(i) + " ");
        }System.err.println();
    }
}
// ______________________________________________________________________________

class Array_02_KadaneAlgorithm_MaximumSubArray
{
    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0;i<n;i++){
            arr.add(scanner.nextInt());
        } 

        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.maximumSubArray_Brute(arr);
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.maximumSubArray_Better(arr);
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.maximumSubArray_Optimal(arr);
        
        SolutionPrintingArray print = new SolutionPrintingArray();
        System.out.print("SubArray with Largest Sum: ");
        print.printMaximumSubArray(arr); 

        System.out.println("Maximum SubArray with Largest Sum (Brute): " + ansBr);
        System.out.println("Maximum SubArray with Largest Sum (Better): " + ansBetter);
        System.out.println("Maximum SubArray with Largest Sum (Optimal): " + ansOPT);
        
        scanner.close();
    }
}