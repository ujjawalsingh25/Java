import java.util.Scanner;


// _____________________________________
// String: a b c a a b c d b a          |
// Longest Consecutive Sequence: 4      |
//          [ a  b  c  d ]              | 
// ----------------------------------   |
// String: a b c a a b c d b a          |
// Longest Consecutive Sequence: 4      |
//          [ a  b  c  d ]              |
// _____________________________________|



// ___________________  BRUTE  ______________________________________________
// Time Complexity: O(n^2)
// Space Complexity: O(1)
// ---------------------------------------------------
class SolutionBrute {
    public int Longest_SubString_withoutRepeating_Char_Brute(String str) {
        int maxLength = 1, currLength = 0;
        
        return maxLength;
    }
}
// _____________________________________________________________________________

 
// ___________________  BETTER  ___________________________________________________
// Time Complexity: O()  -->> loop + HashMap operations --> O(1) -> unsorted_map
// Space Complexity: O(1) 
// ---------------------------------------------------
class SolutionBetter {
    public int Longest_SubString_withoutRepeating_Char_Better(String str) {
        int maxLength = 0;
        
        return maxLength;
    }
}
// _____________________________________________________________________________
  
     
// ___________________  OPTIMAL  ________________________________________________
// Time Complexity: O(2*n + n)   -->> 2*n -->> n is of loop and  for && n -->> putting data to set  
// Space Complexity: O(n)
// -----------------------------------------------------------------
class SolutionOptimal {
    public int Longest_SubString_withoutRepeating_Char_OPTM(String str) {
        int maxLength = 0; 

        return maxLength;
    }
}
// ______________________________________________________________________________


class Array_22_Longest_SubString_withoutRepeating_Char {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("String: ");
        String str = scanner.nextLine();
        
        
        SolutionBrute solBr = new SolutionBrute();
        int ansBr = solBr.Longest_SubString_withoutRepeating_Char_Brute(str);
        System.out.print("Longest Consecutive Sequence (Brute): " + ansBr);
        
        SolutionBetter solBetter = new SolutionBetter();
        int ansBetter = solBetter.Longest_SubString_withoutRepeating_Char_Better(str);
        System.out.print("\nLongest Consecutive Sequence (Better): " + ansBetter);
        
            
        SolutionOptimal solOPT = new SolutionOptimal();
        int ansOPT = solOPT.Longest_SubString_withoutRepeating_Char_OPTM(str);        
        System.out.print("\nLongest Consecutive Sequence (OPT): " + ansOPT);
        

        scanner.close();
    } 
} 