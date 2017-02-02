/*
There is a ball in a maze with empty spaces and walls. The ball can go through 
empty spaces by rolling up, down, left or right, but it won't stop rolling until 
hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest 
distance for the ball to stop at the destination. The distance is defined by the 
number of empty spaces traveled by the ball from the start position (excluded) to 
the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the 
empty space. You may assume that the borders of the maze are all walls. The start 
and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be 
at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), 
but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
public class Solution {
    class State{
        int r, c;
        int dir;
        int distance;
        public State(int r1, int c1, int d, int dist){
            r = r1;
            c = c1;
            dir = d;
            distance = dist;
        }
    }
    
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    boolean[][][] visited;    
    int[][] maze;
    
    boolean isValid(int r, int c, int dir){
        if(r < 0 || r >= m || c < 0 || c >= n || maze[r][c] == 1 || visited[r][c][dir])      return false;
        return true;        
    }
    
    boolean isDestination(State s, int[] destination){
        return s.r == destination[0] && s.c == destination[1];
    }
    
    boolean canStop(State s){
        int r = s.r;
        int c = s.c;
        int r1 = r + move[s.dir][0];
        int c1 = c + move[s.dir][1];    
        return !isValid(r1, c1, s.dir);
    }
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        this.maze = maze;
        m = maze.length;
        n = maze[0].length;
        Queue<State> Q = new LinkedList<>();
        visited = new boolean[m][n][4];
        // Add the initial states
        for(int d=0; d<4; ++d){
            int r1 = start[0] + move[d][0];
            int c1 = start[1] + move[d][1];
            if(isValid(r1, c1, d)){
                Q.add(new State(r1, c1, d, 1));
                visited[r1][c1][d] = true;
            }
        }
        
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;
        while(!Q.isEmpty()){
            State f = Q.remove();
            if(isDestination(f, destination) && canStop(f)){
                return f.distance;
            }
            int r1 = f.r + move[f.dir][0];
            int c1 = f.c + move[f.dir][1];
            if(isValid(r1, c1, f.dir)){
                visited[r1][c1][f.dir] = true;
                Q.add(new State(r1, c1, f.dir, f.distance + 1));
            }
            else{
                // change direction
                for(int d=0; d<4; ++d){
                    r1 = f.r + move[d][0];
                    c1 = f.c + move[d][1];
                    if(!isValid(r1, c1, d))  
                        continue;
                    
                    State next = new State(r1, c1, d, f.distance + 1);
                    visited[r1][c1][d] = true;
                    Q.add(next);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;                
    }
}
