/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
- The number of elements of the given matrix will not exceed 10,000.
- There are at least one 0 in the given matrix.
- The cells are adjacent in only four directions: up, down, left and right.
*/

public class Solution {
    class Coord {
        int r, c;
        public Coord(int r1, int c1) {
            r = r1;
            c = c1;
        }
    }
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int row = matrix.size();
        if(row == 0)    return new ArrayList<>();
        int col = matrix.get(0).size();

        Queue<Coord> Q = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        int[][] d = new int[row][col];
        
        for(int[] t : d) {
            Arrays.fill(t, 10010);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        for(List<Integer> r : matrix) {
            List<Integer> x = new ArrayList<>();
            int j = 0;
            for(int val : r) {
                if(val == 0) {
                    Q.add(new Coord(i, j));
                    visited[i][j] = true;
                    d[i][j] = 0;
                }
                ++j;
                x.add(0);
            }
            result.add(x);
            ++i;
        }
        
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!Q.isEmpty()) {
            Coord t = Q.remove();

            for(i=0; i<4; ++i) {
                int r1 = t.r + move[i][0];                
                int c1 = t.c + move[i][1];
                if(r1 < 0 || r1 >= row || c1 < 0 || c1 >= col || visited[r1][c1])
                    continue;
                    
                d[r1][c1] = d[t.r][t.c] + 1;
                visited[r1][c1] = true;
                Q.add(new Coord(r1, c1));
            }
        }
        
        for(int r=0; r<row; ++r) {
            for(int c = 0; c < col; ++c) {
                result.get(r).set(c, d[r][c]);
            }
        }
        return result;
    }
}
