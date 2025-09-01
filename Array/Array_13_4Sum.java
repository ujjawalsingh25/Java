import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


// _____________________________________
// Size: 6                              |
// Array: 1 0 -1 0 -2 2                 |
// Target: 0                            |
//   (-2, 0, 0, 2)  ,  (-1, 0, 0, 1)    |
//       (-2, -1, 1, 2)                 |
// ----------------------------------   |
// Size: 14                             |
// Array: 1 1 1 2 2 2 3 3 3 4 4 4 5 5   |
// Target: 8                            |
//   (1, 1, 1, 5)  ,  (1, 1, 2, 4)      |
//   (1, 2, 2, 3)  ,  (1, 1, 3, 3)      |
// _____________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^4 * log(No. of Unique Quads) )
// Space Complexity: 2 * O(No. of Unique Quads)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<ArrayList<Integer>> FourSum_Brute(ArrayList<Integer> arr, int n, int target) {
        HashSet<ArrayList<Integer>> st = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++) {
                for(int k=j+1; k<n; k++) {
                    for(int l=k+1; l<n; l++) {
                        ArrayList<Integer> tempArr = new ArrayList<>();
                        if((arr.get(i) + arr.get(j) + arr.get(k) + arr.get(l)) == target){ 
                            tempArr.add(arr.get(i));
                            tempArr.add(arr.get(j));
                            tempArr.add(arr.get(k));
                            tempArr.add(arr.get(l));
                            Collections.sort(tempArr);
                            st.add(tempArr);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(st);  // Convert Set to ArrayList 
    }      // since Set -->> ArrayList<Integer>; so ans -->> ArrayList<ArrayList<Integer>>
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(n^3 * log(No. of Unique Quads) )
// Space Complexity: O(n) + 2 * O(No. of Unique Quads)  -->> O(n) -> HashSet; 2* -->> int-Set and Ans-Array
// ---------------------------------------------------
class SolutionBetter {
    public ArrayList<ArrayList<Integer>> FourSum_Better(ArrayList<Integer> arr, int n, int target) {
        HashSet<ArrayList<Integer>> st = new HashSet<>();           // Set to store arr
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++) {
                HashSet<Integer> store = new HashSet<>();        // Set to store int as "Third No. Options" 
                for(int k=j+1; k<n; k++) {
                    int forth = target -(arr.get(i) + arr.get(j) + arr.get(k));
                    if(store.contains(forth)) {
                        ArrayList<Integer> tempArr = new ArrayList<>();
                        tempArr.add(arr.get(i));
                        tempArr.add(arr.get(j));
                        tempArr.add(arr.get(k));
                        tempArr.add(forth);
                        Collections.sort(tempArr);
                        st.add(tempArr);
                    }
                    else store.add(arr.get(k));
                }
            }
        }
        return new ArrayList<>(st);  // Convert Set to ArrayList 
    }      // since Set -->> ArrayList<Integer>; so ans -->> ArrayList<ArrayList<Integer>>
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n * log*n) + O(n^3)     -->> Sort + Loop
// Space Complexity: O(No. of Unique Quads)
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<ArrayList<Integer>> FourSum_OPTM(ArrayList<Integer> arr, int n, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Collections.sort(arr);
        for(int i=0; i<n; i++) {
            if(i > 0 && arr.get(i) == arr.get(i - 1))   continue;
            for(int j=i+1; j<n; j++) {
                if(j != (i+1) && arr.get(j) == arr.get(j - 1))   continue;
                int k = j+1, l = n-1;
                while(k < l) {
                    int sum = arr.get(i) + arr.get(j) + arr.get(k) + arr.get(l);
                    if(sum == target) {
                        ArrayList<Integer> tempArr = new ArrayList<>();
                        tempArr.add(arr.get(i));
                        tempArr.add(arr.get(j));
                        tempArr.add(arr.get(k));
                        tempArr.add(arr.get(l));
                        ans.add(tempArr);
                        k++; l--;
                        while(k < l && arr.get(k) == arr.get(k-1))  k++;    // if on increasing iteration
                        while(k < l && arr.get(l) == arr.get(l+1))  l--;    // we get same element then continue...
                    }
                    else if(sum < target)  k++;
                    else l--;
                }
            }
        }
        return ans;
    }
}
// ______________________________________________________________________________


class Array_13_4Sum {
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
        ArrayList<ArrayList<Integer>> ansBr = solBr.FourSum_Brute(arr, n, target);
        for(ArrayList<Integer> iterRow: ansBr) {
            for(int element: iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
        
        SolutionBetter solBetter = new SolutionBetter();
        ArrayList<ArrayList<Integer>> ansBetter = solBetter.FourSum_Better(arr, n, target);
        for(ArrayList<Integer> iterRow : ansBetter) {
            for(int element : iterRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
            
        SolutionOptimal solOPT = new SolutionOptimal();
        ArrayList<ArrayList<Integer>> ansOPT = solOPT.FourSum_OPTM(arr, n, target);
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