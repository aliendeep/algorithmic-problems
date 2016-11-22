/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
*/

// Time: O(rc)
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length;
        if( r == 0)
            return 0;

        int c = grid[0].length;
        int perimeter = 0;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(grid[i][j] == 1){
                    int cnt = 4;
                    if(i > 0 && grid[i-1][j] == 1)      cnt--;
                    if(i+1 < r && grid[i+1][j] == 1)    cnt--;
                    if(j > 0 && grid[i][j-1] == 1)      cnt--;
                    if(j+1 < c && grid[i][j+1] == 1)    cnt--;
                    perimeter += cnt;
                }
            }
        }
        return perimeter;
    }
}

public class Solution2 {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length;
        if( r == 0)
            return 0;

        int c = grid[0].length;
        int perimeter = 0;
        int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(grid[i][j] == 1){
                    for(int k=0; k<4; ++k){
                        int x = i + moves[k][0];
                        int y = j + moves[k][1];
                        if(x < 0 || x >= r || y < 0 || y >= c || grid[x][y] == 0)  
                            perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }
}