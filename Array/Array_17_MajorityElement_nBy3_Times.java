import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// _________________________________________
// Size: 8                                  |
// Array: 1 1 1 1 3 2 2 2                   |
// Majority Element > n/2 times: [1, 2]     |
// ----------------------------------       |
// Size: 14                                 |
// Array: 1 1 1 1 5 1 7 7 7 5 7 7 6 3       |
// Majority Element > n/2 times: [1, 7]     |
// _________________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public ArrayList<Integer> MajorityElement_Brute(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(ans.contains(arr.get(i)))  continue; 
            int count = 0;
            for(int j=0; j<n; j++) {
                if(arr.get(i) == arr.get(j))  count++;
            }
            if(count > n/3)  ans.add(arr.get(i)); 
            if(ans.size() == n/3) break;
        }        
        return ans;
    }
}
// _____________________________________________________________________________


// ___________________  BETTER  ______________________________________________
// Time Complexity: O(n * (log*n || O(1))  -->> loop + HashMap operations --> O(1) -> unsorted_map
// Space Complexity: O(n) 
// ---------------------------------------------------
class SolutionBetter {
    public ArrayList<Integer> MajorityElement_Better(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for(int i=0; i<n; i++) {
            mpp.put(arr.get(i), mpp.getOrDefault(arr.get(i), 0) + 1);
            if (mpp.get(arr.get(i)) > n/3 && !ans.contains(arr.get(i)))  ans.add(arr.get(i));
        }
        return ans;
    }
}
// _____________________________________________________________________________

     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2*n)   -->> finding element + verifying  
// Space Complexity: O(1)
// -----------------------------------------------------------------
class SolutionOptimal {
    public ArrayList<Integer> MajorityElement_OPTM(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        int count1 = 0, count2 = 0; 
        int element1 = 0, element2 = 0; 
        int verifyCount1 = 0, verifyCount2 = 0; 
        for(int i=0; i<n; i++) {
                if(count1 == 0 && element2 != arr.get(i)) {
                    element1 = arr.get(i);
                    count1++;
                } 
                else if(count2 == 0 && element1 != arr.get(i)) {
                    element2 = arr.get(i);
                    count2++;
                }
            else if(arr.get(i) == element1)  count1++;
            else if(arr.get(i) == element2)  count2++;
            else {
                count1--;
                count2--;
            }
        }
        if(count1 > 0 && count2 > 0) {
            for(int i=0; i<n; i++) {
                if(arr.get(i) == element1)   verifyCount1++;
                if(arr.get(i) == element2)   verifyCount2++;
            }
        }
        if(verifyCount1 > n/3)   ans.add(element1);
        if(verifyCount2 > n/3)   ans.add(element2);
        return ans;
    }
}
// ______________________________________________________________________________


class Array_17_MajorityElement_nBy3_Times {
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
        ArrayList<Integer> ansBr = solBr.MajorityElement_Brute(arr, n);
        System.out.print("Majority Element > n/2 times Brute: ");
        for(int arrIter: ansBr) {
            System.out.print(arrIter + " ");
        }
        
        SolutionBetter solBetter = new SolutionBetter();
        ArrayList<Integer> ansBetter = solBetter.MajorityElement_Better(arr, n);
        System.out.print("\nMajority Element > n/2 times Better: ");
        for(int arrIter: ansBetter) {
            System.out.print(arrIter + " ");
        }
            
        SolutionOptimal solOPT = new SolutionOptimal();
        ArrayList<Integer> ansOPT = solOPT.MajorityElement_OPTM(arr, n);        
        System.out.print("\nMajority Element > n/2 times OPT: ");
        for(int arrIter: ansOPT) {
            System.out.print(arrIter + " ");
        }

        scanner.close();
    } 
} 