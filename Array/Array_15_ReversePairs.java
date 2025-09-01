import java.util.ArrayList;
import java.util.Scanner;


// __________________________________
// Size: 5                          |
// Array: 1 3 2 7 1                 |
// Number of Reverse Pairs: 3       |
//       (3,1) , (7,1)              |
// Explanation:                     |
//    (3,1) -->>  3 > 2*1           |
//    (7,1) -->>  7 > 2*1           |
// -----------------------------    |
// Size: 5                          |
// Array: 2 4 3 5 1                 |
// Number of Inversion: 3           |
//    (4,1) , (3,1) , (5,1)         |
// Explanation:                     |
//    (4,1) -->>  4 > 2*1           |
//    (3,1) -->>  3 > 2*1           |
//    (5,1) -->>  5 > 2*1           |
// _________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int Number_of_ReversePairs_Brute(ArrayList<Integer> arr, int n) {
        int count = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1; j<n;j++) {
                if(arr.get(i) > 2*arr.get(j)) count++;
            }
        }
        return count;
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2n* log*n)
// Space Complexity: O(n)  -->> Only if we are not copying array and altering the array
// -----------------------------------------------------------------
class SolutionOptimal {
    void mergeArray(ArrayList<Integer> arr, int low, int mid, int high) {
        ArrayList<Integer> tempArr = new ArrayList<>();
        int left = low, right = mid+1;
        while(left <= mid && right <= high) {
            if(arr.get(left) <= arr.get(right)) {         
                tempArr.add(arr.get(left));
                left++;
            } else {
                tempArr.add(arr.get(right));
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
    }
    int countPairs_beforMerge(ArrayList<Integer> arr, int low, int mid, int high) {
        int count = 0, right = mid+1;                 // added function in mergeSort to count reverse-pairs befor merging array
        for(int i=low; i<=mid; i++) {
            while(right <= high && arr.get(i) > 2*arr.get(right))    right++;
            count += (right - (mid+1) );
        }  
        return count;
    }
    int mergeSort_and_CountReversePairs(ArrayList<Integer> arr, int low, int high) {
        int count = 0;                                                     // modified mergeSort
        if(low >= high) return count;
        int mid = low + (high-low)/2;
        count += mergeSort_and_CountReversePairs(arr, low, mid);          // modified mergeSort
        count += mergeSort_and_CountReversePairs(arr, mid+1, high);      // modified mergeSort
        count += countPairs_beforMerge(arr, low, mid, high);            
        mergeArray(arr, low, mid, high);            
        return count;                                                  // modified mergeSort
    }

    public int Number_of_ReversePairs_OPTM(ArrayList<Integer> arr, int n) {
        return mergeSort_and_CountReversePairs(arr, 0, n-1);
    }
}
// ______________________________________________________________________________


class Array_15_ReversePairs {
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
        int ansBr = solBr.Number_of_ReversePairs_Brute(arr, n);
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.Number_of_ReversePairs_OPTM(arr, n);
        
        System.out.println("Number of Reverse Pairs Brute: " + ansBr);
        System.out.println("Number of Reverse Pairs OPTM: " + ansOPT);

        scanner.close();
    } 
}