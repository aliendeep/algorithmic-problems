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

// Bruteforce
public class Solution {
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