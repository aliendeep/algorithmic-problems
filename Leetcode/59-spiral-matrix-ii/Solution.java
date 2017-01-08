/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 
in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] A = new int[n][n];
        int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int dir = 0;
        int nextX, nextY;
        for(int i=1; i<=n*n; i++){
            A[x][y] = i;
            nextX = x + shift[dir][0];
            nextY = y + shift[dir][1];
            // if not valid or already set, then change direction
            if(nextX < 0 || nextX >= n ||
                nextY < 0 || nextY >= n || A[nextX][nextY] != 0){
                dir = (dir + 1) % 4;
                nextX = x + shift[dir][0];
                nextY = y + shift[dir][1];
            }
            x = nextX;
            y = nextY;
        }
        return A;
    }
}