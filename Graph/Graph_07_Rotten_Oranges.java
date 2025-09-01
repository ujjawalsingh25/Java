import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Size(n): 3
// Size(m): 3
// Array 
//  0  1  2
//  0  1  1
//  2  1  1
// -----------------------------------
// Time for Rotten Oranges: 2
// Rotten Oranges
//  0  2  2
//  0  2  2
//  2  2  2

class Pair {
    int first, second, time;
    Pair(int first, int second, int time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }
}


// ___________________  OPTIMAL  __________________________________________
// Time Complexity: O(n*m) + O(4* n*m)  -> approx O(n*m)  -->> Adding first rotten + n*m is for every node && check 4 directions 
// Space Complexity:  O(n*m)       -->> visitedArray    
// ---------------------------------------------------------------------
class SolutionOptimal {
    public int Rotten_Oranges_OPTM(int[][] arr, int n, int m) {
        Queue<Pair> quu = new LinkedList<>();
        int[][] visitedArr = new int[n][m];
        int countFresh = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 1)   countFresh++;
                else if(arr[i][j] == 2) {
                    quu.add(new Pair(i, j, 0));
                    visitedArr[i][j] = 2;
                }
                else  visitedArr[i][j] = 0;
            }
        }

        int maxTime = 0, makeRottenCount = 0;
        int deltaRow[] = {-1, 0, +1, 0};
        int deltaCol[] = {0, +1, 0, -1};
        while(!quu.isEmpty()) {
            int row = quu.peek().first;
            int col = quu.peek().second;
            int time = quu.peek().time;
            maxTime = Math.max(maxTime, time);
            quu.remove();
            for(int i=0; i<deltaRow.length; i++) {
                int nbrRow = row + deltaRow[i];
                int nbrCol = col + deltaCol[i];
                if(nbrRow >= 0 && nbrRow < n && nbrCol >= 0 && nbrCol < m && 
                visitedArr[nbrRow][nbrCol] == 0 && arr[nbrRow][nbrCol] == 1) {
                    quu.add(new Pair(nbrRow, nbrCol, time+1));
                    visitedArr[nbrRow][nbrCol] = 2;
                    makeRottenCount++;
                }
            } 
        }
        if(makeRottenCount != countFresh)  return -1;
        return maxTime;        
    }
}
// ______________________________________________________________________________



class Graph_07_Rotten_Oranges {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Size(n): ");
        int n = scanner.nextInt();
        System.out.print("Size(m): ");
        int m = scanner.nextInt();
        
        int[][] arr = new int[n][m];
        System.out.println("Array ");
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) {
                arr[i][j] = scanner.nextInt();    
            }
        }
        System.out.println("-----------------------------------");
        
        SolutionOptimal solOPT = new SolutionOptimal();
        int rottenTime = solOPT.Rotten_Oranges_OPTM(arr, n, m);    
        System.out.println("Time for Rotten Oranges: " + rottenTime);
        
        scanner.close();
    } 
} 