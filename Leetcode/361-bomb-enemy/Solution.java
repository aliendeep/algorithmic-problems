/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
public class Solution {
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