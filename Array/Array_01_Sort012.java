import java.util.ArrayList;
import java.util.Scanner;


// ________________________________________
//                                         |
// Brute Solution  -->> Sorting()          |
// Time Complexity : O(n*logn)             |
// ________________________________________|


// ___________________  BETTER  _________________________________________________
// Time Complexity: O(2n)
// -------------------------------------------------
class SolutionBetter {
    public void sortArray012_Brute(ArrayList<Integer> arr) {
        int n = arr.size();
        int countZero = 0, countOne = 0, countTwo = 0;
        for(int i = 0; i< n; i++){
            if(arr.get(i) == 0)     countZero++;
            if(arr.get(i) == 1)     countOne++;
            if(arr.get(i) == 2)     countTwo++;
        }
        for(int i=0;i<countZero;i++){
            arr.set(i, 0);
        }
        for(int i=countZero; i<countOne + countZero; i++){
            arr.set(i, 1);
        }
        for(int i=countTwo + countOne; i<n; i++){
            arr.set(i, 2);
        }
    }
}
// ______________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// ------------------  DUTCH NATIONAL FLAG ALGORITHM  ----------------------
// Time Complexity: O(2n)
// -------------------------------------------------
class SolutionOptimal {
    public void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public void sortArray012_Optimal(ArrayList<Integer> arr) {
        int low = 0, mid = 0, high = arr.size()-1;
        while(mid <= high){
            if(arr.get(mid) == 0) {
                swap(arr, low, mid);          // swap mid and low Since mid also need to have low value for further sort
                low++;
                mid++;
            } else if(arr.get(mid) == 2) {
                swap(arr, mid, high);       // swap mid and high Since mid also need to have high value for further sort
                high--;
                // mid++;  // Not to increment mid -->> after swapping with high, the value at mid might still be 0, 1, or 2
            } else  mid++;
        }
    }
}
// ______________________________________________________________________________

class Array_01_Sort012
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

        // SolutionBetter sol = new SolutionBetter();
        // sol.sortArray012_Better(arr);

        SolutionOptimal solOPT = new SolutionOptimal();
        solOPT.sortArray012_Optimal(arr);

        System.out.print("Sorted Array012: ");
        for(int i=0;i<n;i++){
            System.out.print(arr.get(i) + " ");
        }
        scanner.close();        
    }
}