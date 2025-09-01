import java.util.ArrayList;
import java.util.Scanner;


// __________________________________
// Size: 6                          |
// Array: 4 3 6 2 1 1               |
// -----------------------------    |
// Size: 5                          |
// Array: 5 3 2 4 1                 |
// Number of Inversion: 8           |
//  (5,3) , (5,2) , (5,4) , (5,1)   |
//  (3,2) , (3,1) , (2,1) , (4,1)   |
// _________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int Number_of_Inversions_Brute(ArrayList<Integer> arr, int n) {
        int count = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1; j<n;j++) {
                if(arr.get(i) > arr.get(j)) count++;
            }
        }
        return count;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(n* log*n)
// Space Complexity: O(n)  -->> Only if we are not copying array and altering the array
// -----------------------------------------------------------------
class SolutionOptimal {
    int mergeArray_CountPairs(ArrayList<Integer> arr, int low, int mid, int high) {
        ArrayList<Integer> tempArr = new ArrayList<>();
        int count = 0, left = low, right = mid+1;
        while(left <= mid && right <= high) {
            if(arr.get(left) <= arr.get(right)) {
                tempArr.add(arr.get(left));
                left++;
            } else {
                tempArr.add(arr.get(right));
                count += (mid + 1 - left);                  // modified mergeSort
                right++;
            }
        }
        while(left <= mid) {
            tempArr.add(arr.get(left));
            left++;
        }
        while(right <= high) {
            tempArr.add(arr.get(right));
            right++;
        }
        for(int i=low; i<=high; i++){
            arr.set(i, tempArr.get(i-low));
        }
        return count;
    }
    int mergeSort_and_CountInversion(ArrayList<Integer> arr, int low, int high) {
        int countPairs = 0;                                 // modified mergeSort
        if(low >= high) return countPairs;
        int mid = low + (high-low)/2;
        countPairs += mergeSort_and_CountInversion(arr, low, mid);          // modified mergeSort
        countPairs += mergeSort_and_CountInversion(arr, mid+1, high);      // modified mergeSort
        countPairs += mergeArray_CountPairs(arr, low, mid, high);         // modified mergeSort
        return countPairs;                                               // modified mergeSort
    }

    public int Number_of_Inversions_OPTM(ArrayList<Integer> arr, int n) {
        return mergeSort_and_CountInversion(arr, 0, n-1);
    }
}
// ______________________________________________________________________________


class Array_14_Number_of_Inversions {
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
        int ansBr = solBr.Number_of_Inversions_Brute(arr, n);
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.Number_of_Inversions_OPTM(arr, n);
        
        System.out.println("Number of Inversions Brute: " + ansBr);
        System.out.println("Number of Inversions OPTM: " + ansOPT);

        scanner.close();
    } 
}