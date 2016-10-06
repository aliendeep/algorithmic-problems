/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

public class Solution {
    class Pair{
        int x;
        int y;
        public Pair(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    // BFS
    public void wallsAndGates(int[][] rooms) {
        int r = rooms.length;
        if(r == 0)
            return;

        int c = rooms[0].length;
        boolean[][] visited = new boolean[r][c];  
        Queue<Pair> Q = new LinkedList<>();
        // Push coordinates of all gates into the queue
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(rooms[i][j] == 0){
                    Q.add(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }
        
        int move[][] = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        while(!Q.isEmpty()){
            Pair front = Q.remove();
            for(int i=0; i<4; i++){
                int x1 = front.x + move[i][0];
                int y1 = front.y + move[i][1];
                if(x1 >= 0 && x1 < r && y1 >= 0 && y1 < c){
                    // obstacle
                    if(rooms[x1][y1] == -1)
                        continue;
                    if(!visited[x1][y1]){
                        rooms[x1][y1] = rooms[front.x][front.y] + 1;
                        visited[x1][y1] = true;
                        Q.add(new Pair(x1, y1));
                    }
                }
            }            
        }
    }
}