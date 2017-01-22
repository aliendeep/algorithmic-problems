/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (
the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point 
until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
import java.util.*;

// Time Complexity: O(mn)

public class Solution {
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int row;
    int col;
    
    int getKillCount(int dir, char[][] grid, int r, int c, int[][][] dp){
        if(r < 0 || r >= row || c < 0 || c >= col)
            return 0;
        if(grid[r][c] == 'W')   return 0;
        if(dp[dir][r][c]!= -1)  return dp[dir][r][c];
        
        // either enemy or 0
        int _r  = r + move[dir][0];
        int _c  = c + move[dir][1];
        dp[dir][r][c] = (grid[r][c] == 'E' ? 1 : 0) + getKillCount(dir, grid, _r, _c, dp);
        return dp[dir][r][c];
    }

    public int maxKilledEnemies(char[][] grid) {
        row = grid.length;
        if(row == 0)        return 0;
        col = grid[0].length;
        int maxKill = 0;

        int[][][] dp = new int[4][row][col];
        for(int[][] x : dp){
            for(int[] y : x)
                Arrays.fill(y, -1);
        }

        for(int r=0; r<row; ++r){
            for(int c=0; c<col; ++c){
                if(grid[r][c] == '0'){
                    int sum = 0;
                    for(int dir=0; dir<4; ++dir){
                        sum += getKillCount(dir, grid, r, c, dp);
                    }
                    maxKill = Math.max(maxKill, sum);
                }
            }            
        }
        return maxKill;
    }
}

class Solution2 {
    // O(mn) Solution
    // DP
    class Cell{
        // Number of enemies to its left including itself
        public int left;
        // Number of enemies to its right including itself
        public int right;
        // Number of enemies to its up including itself        
        public int up;
        // Number of enemies to its down including itself        
        public int down;
    }
    
    public int maxKilledEnemies(char[][] grid) {
        int r = grid.length;
        if(r == 0)          return 0;
        int c = grid[0].length;
        
        Cell[][] cell = new Cell[r][c];
        // left & up
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                cell[i][j] = new Cell();
                if(grid[i][j] == 'W')
                    continue;
                
                cell[i][j].up = (grid[i][j] == 'E' ? 1 : 0);     
                cell[i][j].left = (grid[i][j] == 'E' ? 1 : 0); 
                if(i > 0){
                    cell[i][j].up += cell[i-1][j].up;
                }
                if(j > 0){
                    cell[i][j].left += cell[i][j-1].left; 
                }
            }
        }
        
        int maxKill = 0;        
        for(int i=r-1; i>=0; --i){
            for(int j=c-1; j>=0; --j){
                if(grid[i][j] == 'W')
                    continue;
                cell[i][j].down = (grid[i][j] == 'E' ? 1 : 0); 
                cell[i][j].right = (grid[i][j] == 'E' ? 1 : 0); 
                
                if(i < r-1)                
                    cell[i][j].down += cell[i+1][j].down; 
                if(j < c-1)
                    cell[i][j].right += cell[i][j+1].right; 
                
                // if it's empty spot
                if(grid[i][j] == '0')
                    maxKill = Math.max(maxKill, cell[i][j].up + cell[i][j].left + cell[i][j].down + cell[i][j].right);
            }
        }
        return maxKill;
    }
}

// Bruteforce
class Solution3 {
    // ["0E00","E0WE","0E00"]
    public int calculateVal(char[][] grid, int start, int end) {
        int r = grid.length;
        int c = grid[0].length;
        int cnt = 0;
        int i = start - 1;
        while(i >= 0 && grid[i][end] != 'W'){
            if(grid[i][end] == 'E')
                cnt++;
            i--;
        }
        i = start + 1;
        while(i < r && grid[i][end] != 'W'){
            if(grid[i][end] == 'E')
                cnt++;
            i++;
        }
        int j = end - 1;
        while(j >= 0 && grid[start][j] != 'W'){
            if(grid[start][j] == 'E')
                cnt++;
            j--;
        }
        j = end + 1;
        while(j < c && grid[start][j] != 'W'){
            if(grid[start][j] == 'E')
                cnt++;
            j++;;
        }
        return cnt;
    }

    public int maxKilledEnemies(char[][] grid) {
        int r = grid.length;
        if(r == 0)  
            return 0;
        int c = grid[0].length;
        
        int result = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == '0'){
                    result = Math.max(result, calculateVal(grid, i, j));
                }
            }
        }           
        return result;
    }
}
