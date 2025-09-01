import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// _________________________________________________________________
// Size: 8                                                          |
// SubIntervals: 1 3 2 4 2 6 8 9 8 10 2 4 15 18 16 17               |
// ----------------------------                                     |
// Ranges are:                                                      |
// 1 6                                                              |
// 8 10                                                             |
// 15 18                                                            |
// _________________________________________________________________|


// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n * log n) + O(2n)
// Space Complexity: O(n)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<ArrayList<Integer>> mergerOverlappingInterval_Brute(ArrayList<ArrayList<Integer>> arr) {
        int n = arr.size();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        // Sort the intervals by the start time
        Collections.sort(arr, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                return a.get(0) - b.get(0);  // Sort by the first element (start of the interval)
            }
        });
        
        for(int i=0;i<n;i++){
            int start = arr.get(i).get(0);
            int end = arr.get(i).get(1);
            if(!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)){
                continue;
            }
            for(int j=i+1;j<n;j++){
                if(arr.get(j).get(0) <= end){
                    end = Math.max(end, arr.get(j).get(1));
                }
                else break;
            }
            ArrayList<Integer> newInterval = new ArrayList<>();     // need to make evertime on each iteration
            newInterval.add(start);
            newInterval.add(end);
            ans.add(newInterval);
        }
        return ans;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n*log n) + O(n)
// Space Complexity: O(n)
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<ArrayList<Integer>> mergerOverlappingInterval_OPTM(ArrayList<ArrayList<Integer>> arr) {
        int n = arr.size();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        Collections.sort(arr, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                return a.get(0) - b.get(0);  // Sort by the first element (start of the interval)
            }
        });

        for(int i=0;i<n;i++){
            if(ans.isEmpty() || arr.get(i).get(0) > ans.get(ans.size() - 1).get(1)) {
                ans.add(arr.get(i));
            }
            else {
                // ans.back()[1] = max(ans..back()[1], arr[i][1]);
                int lastEnd = ans.get(ans.size() - 1).get(1);
                int currentEnd = arr.get(i).get(1);
                ans.get(ans.size() - 1).set(1, Math.max(lastEnd, currentEnd)); 
            }
        }
        return ans;
    }
}
// ______________________________________________________________________________


class Array_08_MergeOverlappingSubintervals
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("SubIntervals: ");
        for(int i=0;i<n;i++) {
            ArrayList<Integer> subInterval = new ArrayList<>();
            for(int j=0;j<2;j++){
                subInterval.add(scanner.nextInt());
            }
            arr.add(subInterval);
        }
        System.out.println("----------------------------");

        SolutionBrute subInterval = new SolutionBrute();
        ArrayList<ArrayList<Integer>> mergedSubInterval = subInterval.mergerOverlappingInterval_Brute(arr);
        for(ArrayList<Integer> intervalIter : mergedSubInterval){
            for(int intervals: intervalIter){
                System.out.print(intervals + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");

        SolutionOptimal subIntervalOPTM = new SolutionOptimal();
        ArrayList<ArrayList<Integer>> mergedSubIntervalOPTM = subIntervalOPTM.mergerOverlappingInterval_OPTM(arr);
        for(ArrayList<Integer> intervalIter : mergedSubIntervalOPTM){
            for(int intervals: intervalIter){
                System.out.print(intervals + " ");
            }
            System.out.println();
        }


        scanner.close();
    }
}
