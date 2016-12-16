/*
You want to build a house on an empty land which reaches all buildings in the 
shortest amount of distance. You can only move up, down, left and right. You are 
given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel 
distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house 
according to the above rules, return -1.
*/

public class Solution {
    class Pair{
        int x, y;
        public Pair(int i, int j){
            x = i;
            y = j;
        }
    }
    ;
    int r, c;
    int[][] grid;
    int[][] cumSum;

    public void init(int[][] t, int val){
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                t[i][j] = val;
            }
        }
    }
    
    public void initCumSum(){
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(grid[i][j] == 0)
                    cumSum[i][j] = 0;
                else
                    cumSum[i][j] = Integer.MAX_VALUE;
            }
        }
    }
        
    public void bfs(int srcR, int srcC){
        Queue<Pair> Q = new LinkedList<>();
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[r][c];

        int[][] d = new int[r][c];
        init(d, Integer.MAX_VALUE);
        // Source
        d[srcR][srcC] = 0;
        visited[srcR][srcC] = true;
        Q.add(new Pair(srcR, srcC));
        
        while(!Q.isEmpty()){
            Pair front = Q.remove();
            for(int i=0; i<4; i++){
                int x1 = front.x + move[i][0];
                int y1 = front.y + move[i][1];
                if(x1 < 0 || x1 >= r || y1 < 0 || y1 >= c)
                    continue;
                // Skip other buildings and obstacles
                if(grid[x1][y1] != 0 || visited[x1][y1])
                    continue;
                d[x1][y1] = d[front.x][front.y] + 1; 
                visited[x1][y1] = true;
                Q.add(new Pair(x1, y1));
            }
        }
        
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                // not reachable or existing building
                if(grid[i][j] != 0 || cumSum[i][j] == Integer.MAX_VALUE)
                    continue;
                if(d[i][j] == Integer.MAX_VALUE)
                    cumSum[i][j] = Integer.MAX_VALUE;
                else
                    cumSum[i][j] += d[i][j];
            }
        }
    }
    // Time: O(R^2C^2)
    // Assuming number of existing building < number of empty spots
    public int shortestDistance(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        this.grid = grid;
        cumSum = new int[r][c];
        initCumSum();
        
        // Start BFS from all existing building
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                // start new bfs
                if(grid[i][j] == 1){
                    bfs(i, j);
                }
            }
        }
        // Find the spot which has the smallest sum
        int minSum = Integer.MAX_VALUE;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(grid[i][j] != 0)
                    continue;
                minSum = Math.min(minSum, cumSum[i][j]);
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
   }
}


// Alternative: 
// Assuming number of existing building > number of empty spots
class Solution2{
    class Pair{
        int x, y;
        public Pair(int i, int j){
            x = i;
            y = j;
        }
    }
    ;
    int r, c;
    int[][] grid;

    public void init(int[][] t, int val){
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                t[i][j] = val;
            }
        }
    }
    
    // Returns the maximum sum
    public int bfs(int srcR, int srcC){
        Queue<Pair> Q = new LinkedList<>();
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[r][c];
        int[][] d = new int[r][c];
        init(d, Integer.MAX_VALUE);
        // Source
        d[srcR][srcC] = 0;
        visited[srcR][srcC] = true;
        Q.add(new Pair(srcR, srcC));
        
        while(!Q.isEmpty()){
            Pair front = Q.remove();
            for(int i=0; i<4; i++){
                int x1 = front.x + move[i][0];
                int y1 = front.y + move[i][1];
                if(x1 < 0 || x1 >= r || y1 < 0 || y1 >= c)
                    continue;
                    
                // Skip other obstacles or house or already visited state
                if(grid[x1][y1] == 2 || visited[x1][y1])
                    continue;
                d[x1][y1] = d[front.x][front.y] + 1; 
                visited[x1][y1] = true;
                // it's a building, don't push it into the queu
                if(grid[x1][y1] == 0)
                    Q.add(new Pair(x1, y1));
            }
        }
        
        // Minimize total travel distance
        int sum = 0;        
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                if(grid[i][j] == 2)         continue;
                if(grid[i][j] == 1){
                    if(d[i][j] == Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;
                    sum += d[i][j];
                }
            }
        }
        return sum;
    }
    public int shortestDistance(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        this.grid = grid;

        // Start BFS from all empty spots
        int minSum = Integer.MAX_VALUE;
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                // start new bfs if it's a possible candidate
                if(grid[i][j] == 0){
                    minSum = Math.min(minSum, bfs(i, j));
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
   }
}

// Another Idea: Instead of keeping seperate visited for all bfs, only visit the
// cell that have been visited before
