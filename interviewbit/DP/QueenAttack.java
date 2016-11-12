import java.util.*;

public class QueenAttack {
    /*
        https://www.interviewbit.com/problems/queen-attack/    
        We define f(i, j, k) as a number of queen attacks on the cell (i, j) 
        from direction k. Eight directions can be given numbers 0 to 7.
        Now, to see how many attacks are there on a cell (i, j), we go to its neighbour 
        in direction k (say n_i, n_j). If the cell (n_i, n_j) has a queen, then there is just 1 attack. 
        Else, number of attacks is f(n_i, n_j, k).

        Can you formulate base cases?

        We just have to take the sum of f(i, j, k) for all k=0 to 7 to find the answer for the position 
        (i, j).
        The total number of states is O(N*M*8) and the transition is O(1), 
        so total complexity is O(N*M).    
    */   
    int move[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int cnt = 0;
    public int compute(int k, int i, int j, int[][] cell, int[][][] dp){
        int n = cell.length;
        int m = cell[0].length;

        if(dp[k][i][j] != -1)
            return dp[k][i][j];

        cnt = 0;
        int x = i + move[k][0];
        int y = j + move[k][1];
        if(x >= 0 && x < n && y >= 0 && y < m){
            if(cell[x][y] == 1){
                cnt += 1;
            }
            else{
                cnt += compute(k, x, y, cell, dp);
            }
        }
        dp[k][i][j] = cnt;        
        return cnt;
    }

    public ArrayList<ArrayList<Integer>> queenAttack(ArrayList<String> A) {
        int n = A.size();
        int m = A.get(0).length();
        int[][] cell = new int[n][m];

        // init row
        for(int i=0; i<n; i++) {
            String s = A.get(i);
            for(int j=0; j<m; ++j){
                if(s.charAt(j) == '1'){
                    cell[i][j] = 1;
                }
            }
        }
        
        int[][][] dp = new int[8][n][m];
        for(int k=0; k<8; ++k){
            for(int i=0; i<n; ++i){
                for(int j=0; j<m; ++j){
                    dp[k][i][j] = -1;
                }
            }
        }       

        int[][] result = new int[n][m];
        for(int k=0; k<8; ++k){
            for(int i=0; i<n; ++i){
                for(int j=0; j<m; ++j){
                    result[i][j] += compute(k, i, j, cell, dp);
                }
            }        
        }

        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        for(int i=0; i<n; ++i)
            r.add(new ArrayList<>());
            
        for(int i=0; i<n; ++i){
            for(int j=0; j<m; ++j){
                r.get(i).add(result[i][j]);
            }
        }
        return r;
    }

    public static void print(ArrayList<ArrayList<Integer>> r){
        for(ArrayList<Integer> t : r){
            for(int p : t)
                System.out.print(p + " ");
            System.out.println();
        }
    }
    // Your Input: 5 1101001 1001000 1100101 1100011 1000011 
    // Expected output is [2 5 4 5 5 3 3 ] [5 6 6 4 4 5 3 ] [4 7 5 5 5 5 4 ] [4 6 3 5 5 6 5 ] [3 4 4 4 5 5 3 ]

    public static void main(String[] args){
        //String[] a = {"010", "100", "001"};
        String[] a = {"1101001", "1001000", "1100101", "1100011", "1000011"};
        int n = a.length;
        int m = a[0].length();

        ArrayList<String> r = new ArrayList<>();       
        for(int i=0; i<n; ++i)
            r.add(a[i]);
        QueenAttack ob = new QueenAttack();
        ArrayList<ArrayList<Integer>> result  = ob.queenAttack(r);
        print(result);
    }
}
