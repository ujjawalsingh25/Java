import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// _________________________________________________________________________________
// Size: 3                                                                          |
// Array: 1 2 3                                                                     | 
// Permutations: { [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1] }       |
// _________________________________________________________________________________|

// ___________________  BRUTE  _________________________________________________
// Time Complexity: O(n^3)
// Space Complexity: O(1)
// -------------------------------------------------------------------------
class SolutionBrute {
    public void nextPermutation_Brute(ArrayList<Integer> arr) {
        
    }
}
// ______________________________________________________________________________


// _________________________________________________________________________________
// Size: 3                                                                          |
// Array: 1 2 3                                                                     | 
// Permutations: { [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1] }       |
// Next Permutation after [1,2,3] --> [ 1, 3, 2 ]                                   |
//                                                                                  |
// Size: 7                                                                          |
// Array: 2 1 5 4 3 0 0                                                             |
// Next Permutation --> [ 2 3 0 0 1 4 5 ]                                           | 
// _________________________________________________________________________________|

// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(3n)
// Space Complexity: O(n) {if array modification consider}  
//                  else:  O(1) {Sice no extra space used}
// -----------------------------------------------------------------
class Solution {
    public void swap(ArrayList<Integer> arr, int x, int y){
        int temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }

    public ArrayList<Integer> nextPermutation(ArrayList<Integer> arr) {
        int n = arr.size(), peakIndex = -1;
        for(int i=n-2; i>=0;i--){
            if(arr.get(i) < arr.get(i+1)){
                peakIndex = i;
                break;
            }
        }
        if(peakIndex == -1) {
            Collections.reverse(arr);
            return arr;
        }
        for(int i = n-1; i >= peakIndex && peakIndex != -1; i--){
            if(arr.get(i) > arr.get(peakIndex)) {
                swap(arr, i, peakIndex);
                break;
            }
        } 
        Collections.reverse(arr.subList(peakIndex + 1, n) );
        return arr;
    }
}
// ______________________________________________________________________________


class Array_03_NextPermutation
{
    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size: ");
        int n = scanner.nextInt();
        System.out.print("Array: ");
        for(int i=0;i<n;i++) {
            arr.add(scanner.nextInt());
        }

        Solution sol = new Solution();
        ArrayList<Integer> nextPer = sol.nextPermutation(arr);
        System.out.print("Next Permutation: ");
        for(int i=0;i<n;i++){
            System.out.print(nextPer.get(i) + " ");
        }

        scanner.close();
    }
}