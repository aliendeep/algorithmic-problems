/*
Given a matrix of m x n elements (m rows, n columns), return all elements of 
the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

*/
// Using visited and direction
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int r = matrix.length;
        if(r == 0)  
            return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<>();
        int c = matrix[0].length;
        int x = 0, y = 0;
        int dir = 0;
        // Number of elements = m*n;
        for(int i=0; i<r*c; i++){
            result.add(matrix[x][y]);
            matrix[x][y] = 0;
            int x1 = x + shift[dir][0];
            int y1 = y + shift[dir][1];
            // if not valid or already visited, then change direction
            if(x1 < 0 || x1 >= r ||
                y1 < 0 || y1 >= c || matrix[x1][y1] == 0){
                dir = (dir+1)%4;
                // go to the next direction
                x1 = x + shift[dir][0];;
                y1 = y + shift[dir][1];
            }
            x = x1;
            y = y1;
        }
        return result;       
    }
}
