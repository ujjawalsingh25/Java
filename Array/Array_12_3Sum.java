import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


// _____________________________________
// Size: 6                              |
// Array: -1 0 1 2 -1 -4                |
// Target: 0                            |
//   (-1, -1, 2)  ,  (-1, 0, 1)         |
// ----------------------------------   |
// Size: 14                             |
// Array: 1 1 1 2 2 2 3 3 3 4 4 4 5 5   |
// Target: 8                            |
//     (1, 2, 5)  ,  (1, 3, 4)          |
//     (2, 2, 4)  ,  (2, 3, 3)          |
// _____________________________________|




// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^3 * log(No. of Unique Triplet) )
// Space Complexity: 2 * O(No. of Unique Triplet)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<ArrayList<Integer>> ThreeSum_Brute(ArrayList<Integer> arr, int n, int target) {
        HashSet<ArrayList<Integer>> st = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++) {
                for(int k=j+1; k<n; k++) {
                    ArrayList<Integer> tempArr = new ArrayList<>();
                    if((arr.get(i) + arr.get(j) + arr.get(k)) == target){ 
                        tempArr.add(arr.get(i));
                        tempArr.add(arr.get(j));
                        tempArr.add(arr.get(k));
                        Collections.sort(tempArr);
                        st.add(tempArr);
                    }
                }
            }
        }
        return new ArrayList<>(st);  // Convert Set to ArrayList 
    }      // since Set -->> ArrayList<Integer>; so ans -->> ArrayList<ArrayList<Integer>>
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(2*n)
// Space Complexity: O(n)  -> HashMap
// ---------------------------------------------------
class SolutionBetter {
    public ArrayList<ArrayList<Integer>> ThreeSum_Better(ArrayList<Integer> arr, int n, int target) {
        HashSet<ArrayList<Integer>> st = new HashSet<>();           // Set to store arr
        for(int i=0; i<n; i++){
            HashSet<Integer> store = new HashSet<>();        // Set to store int as "Third No. Options" 
            for(int j=i+1; j<n; j++) {
                int third = target - (arr.get(i) + arr.get(j));
                if(store.contains(third)) {
                    ArrayList<Integer> tempArr = new ArrayList<>();
                    tempArr.add(arr.get(i));
                    tempArr.add(arr.get(j));
                    tempArr.add(third);
                    Collections.sort(tempArr);
                    st.add(tempArr);
                }
                else store.add(arr.get(j));
            }
        }
        return new ArrayList<>(st);  // Convert Set to ArrayList 
    }      // since Set -->> ArrayList<Integer>; so ans -->> ArrayList<ArrayList<Integer>>
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n * log*n) + O(n^2)     -->> Sort + Loop
// Space Complexity: O(No. of Unique Triplet)
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<ArrayList<Integer>> ThreeSum_OPTM(ArrayList<Integer> arr, int n, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Collections.sort(arr);
        for(int i=0; i<n; i++) {
            if(i>0 && arr.get(i) == arr.get(i -1))   continue;
            int j = i+1, k = n-1;
            while(j < k) {
                int sum = arr.get(i) + arr.get(j) + arr.get(k);
                if(sum == target) {
                    ArrayList<Integer> tempArr = new ArrayList<>();
                    tempArr.add(arr.get(i));
                    tempArr.add(arr.get(j));
                    tempArr.add(arr.get(k));
                    ans.add(tempArr);
                    j++; k--;
                    while(j < k && arr.get(j) == arr.get(j-1))  j++;    // if on increasing iteration
                    while(j < k && arr.get(k) == arr.get(k+1))  k--;    // we get same element then incr iterator
                }
                else if(sum < target)  j++;
                else k--;
            }
        }
        return ans;
    }
}
// ______________________________________________________________________________


class Array_12_3Sum {
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
        System.out.println("-----------------------------------");

        SolutionBrute solBr = new SolutionBrute();
        ArrayList<ArrayList<Integer>> ansBr = solBr.ThreeSum_Brute(arr, n, target);
        for(ArrayList<Integer> iterRow: ansBr) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
        
        SolutionBetter solBetter = new SolutionBetter();
        ArrayList<ArrayList<Integer>> ansBetter = solBetter.ThreeSum_Better(arr, n, target);
        for(ArrayList<Integer> iterRow : ansBetter) {
            for(int element : iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
            
        SolutionOptimal solOPT = new SolutionOptimal();
        ArrayList<ArrayList<Integer>> ansOPT = solOPT.ThreeSum_OPTM(arr, n, target);
        for(ArrayList<Integer> iterRow : ansOPT) {
            for(int element : iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");

        scanner.close();
    } 
}