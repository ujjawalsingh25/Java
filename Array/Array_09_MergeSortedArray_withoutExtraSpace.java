import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// _________________________________________
// Size(n1): 4                              |
// Size(n2): 5                              |
// Array 1: 1 3 5 7                         |
// Array 2: 0 2 6 8 9                       |
// ----------------------------             |
// Array 1(Sorted): 0 1 2 3                 |
// Array 2(Sorted): 5 6 7 8 9               |
// _________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n + m) + O(n + m)  
// Space Complexity: O(n + m)
// ---------------------------------------------------
class SolutionBrute {
    public void mergeSortedArray(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m) {
        ArrayList<Integer> arrStore = new ArrayList<>();
        int left = 0, right = 0, index = 0;
        while(left < n && right < m) {
            if(arr1.get(left) <= arr2.get(right)) {
                arrStore.add(arr1.get(left)); 
                left++; index++;
            } else {
                arrStore.add(arr2.get(right));
                right++; index++;
            }
        }
        while(left < n) {
            arrStore.add(arr1.get(left++)); 
        }
        while(right < m) {
            arrStore.add(arr2.get(right++)); 
        }

        for(int i=0; i<n+m; i++){
            if(i<n) arr1.set(i, arrStore.get(i));
            else  arr2.set(i-n, arrStore.get(i));
        }
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(min(n,m)) + O(n*log n) + O(m*log m)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBetter {
    public void swap(ArrayList<Integer> arr1, int left, ArrayList<Integer> arr2, int right) {
        int temp = arr1.get(left);
        arr1.set(left, arr2.get(right));
        arr2.set(right, temp);
    }

    public void mergeSortedArray(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m) {
        int left = n-1, right = 0;
        while(left >= 0 && right < m){
            if(arr1.get(left) > arr2.get(right)) {
                swap(arr1, left, arr2, right);  // swap(arr1[left], arr2[right]);
                left--; 
                right++;
            }
            else  break;
        }
        Collections.sort(arr1);
        Collections.sort(arr2);
    }
}
// _____________________________________________________________________________


// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(log2(n+m)) * O(n+m)
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    void swapIfGreater(ArrayList<Integer> arr1, int ind1, ArrayList<Integer> arr2, int ind2) {
        SolutionBetter swapFromBetter = new SolutionBetter();
        if(arr1.get(ind1) > arr2.get(ind2)) {
            swapFromBetter.swap(arr1, ind1, arr2, ind2);    // swap(arr1[ind1], arr2[ind2]);
        }
    }

    public void mergeSortedArray_gapMethod(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m) {
        int len = (n + m);
        int gap = (len/2) + (len%2);
        while(gap > 0) {
            int left = 0;
            int right = left + gap;
            while(right < len) {
                // if one element in arr1 and one in arr2
                if(left < n && right >= n) {
                    swapIfGreater(arr1, left, arr2, right-n);
                }
                // if both elements are in arr2
                else if(left >= n) {
                    swapIfGreater(arr2, left-n, arr2, right-n);
                }
                // if both elements are in arr1
                else {
                    swapIfGreater(arr1, left, arr2, right);
                }
                left++;   right++;
            }
            if(gap == 1)   break;
            gap = (gap / 2) + (gap % 2);
        }
    }
}
// ______________________________________________________________________________


class Array_09_MergeSortedArray_withoutExtraSpace 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        System.out.print("Size(n1): ");
        int n1 = scanner.nextInt();
        System.out.print("Size(n2): ");
        int n2 = scanner.nextInt();
        System.out.print("Array 1: ");
        for(int i=0;i<n1;i++){
            arr1.add(scanner.nextInt());
        }
        System.out.print("Array 2: ");
        for(int i=0;i<n2;i++){
            arr2.add(scanner.nextInt());
        }
        System.out.println("----------------------------");


        SolutionBrute mergeBrute = new SolutionBrute();
        mergeBrute.mergeSortedArray(arr1, arr2, n1, n2);
        System.out.print("Array 1(Sorted Brute): ");
        for(int i=0;i<n1;i++) {
            System.out.print(arr1.get(i) + " ");
        }
        System.out.print("\nArray 2(Sorted Brute): ");
        for(int i=0;i<n2;i++) {
            System.out.print(arr2.get(i) + " ");
        }
        System.out.println("\n----------------------------");
        
        SolutionBetter mergeBetter = new SolutionBetter();
        mergeBetter.mergeSortedArray(arr1, arr2, n1, n2);
        System.out.print("Array 1(Sorted Brute): ");
        for(int i=0;i<n1;i++) {
            System.out.print(arr1.get(i) + " ");
        }
        System.out.print("\nArray 2(Sorted Brute): ");
        for(int i=0;i<n2;i++) {
            System.out.print(arr2.get(i) + " ");
        }
        System.out.println("\n----------------------------");
        
        SolutionOptimal merge_gapMethod = new SolutionOptimal();
        merge_gapMethod.mergeSortedArray_gapMethod(arr1, arr2, n1, n2);
        System.out.print("Array 1(Sorted Brute): ");
        for(int i=0;i<n1;i++) {
            System.out.print(arr1.get(i) + " ");
        }
        System.out.print("\nArray 2(Sorted Brute): ");
        for(int i=0;i<n2;i++) {
            System.out.print(arr2.get(i) + " ");
        }
        System.out.println("\n----------------------------");

        scanner.close();
    }
}