/*
Consider a matrix with  rows and  columns, where each cell contains either a  or a  and any cell containing a  is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally; in other words, cell  is connected to cells , , , , , , , and , provided that the location exists in the matrix for that .

If one or more filled cells are also connected, they form a region. Note that each cell in a region is connected to at least one other cell in the region but is not necessarily directly connected to all the other cells in the region.

Task 
Given an  matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.

Input Format

The first line contains an integer, , denoting the number of rows in the matrix. 
The second line contains an integer, , denoting the number of columns in the matrix. 
Each line  of the  subsequent lines contains  space-separated integers describing the respective values filling each row in the matrix.

Constraints

Output Format

Print the number of cells in the largest region in the given matrix.

Sample Input

4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
Sample Output

5
Explanation

The diagram below depicts two regions of the matrix; for each region, the component cells forming the region are marked with an X:

X X 0 0     1 1 0 0
0 X X 0     0 1 1 0
0 0 X 0     0 0 1 0
1 0 0 0     X 0 0 0
The first region has five cells and the second region has one cell. Because we want to print the number of cells in the largest region of the matrix, we print .
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int r, c;
    static int len;
    public static void dfs(int x, int y, int[][] matrix, boolean[][] visited){
        visited[x][y] = true;
        len++;
        int[][] moves = {{0, 1},{0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};
        for(int i=0; i<8; ++i){
            int x1 = x + moves[i][0];
            int y1 = y + moves[i][1];
            if(x1 < 0 || x1 >= r || y1 < 0 || y1 >= c || visited[x1][y1] || matrix[x1][y1] == 0)
                continue;
            dfs(x1, y1, matrix, visited);
        }
    }    
    
    public static int getBiggestRegion(int[][] matrix) {
        r = matrix.length;
        c = matrix[0].length;
        boolean[][] visited = new boolean[r][c];
        for(boolean[] t : visited)
            Arrays.fill(t, false);

        int result = 0;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(matrix[i][j] == 1){
                    len = 0;
                    visited[i][j] = true;
                    dfs(i, j, matrix, visited);
                    result = Math.max(result, len);
                }
            }        
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(getBiggestRegion(grid));
    }
}
