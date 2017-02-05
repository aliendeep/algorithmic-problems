/*
There is a ball in a maze with empty spaces and walls. The ball can go through 
empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't 
stop rolling until hitting a wall. When the ball stops, it could choose the next 
direction. There is also a hole in this maze. The ball will drop into the hole 
if it rolls on to the hole.

Given the ball position, the hole position and the maze, your job is to find out 
how the ball could drop into the hole by moving shortest distance in the maze. 
The distance is defined by the number of empty spaces the ball go through from 
the start position (exclude) to the hole (include). Output the moving directions 
by using 'u', 'd', 'l' and 'r'. Since there may have several different shortest 
ways, you should output the lexicographically smallest way. If the ball cannot 
reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the 
empty space. You may assume that the borders of the maze are all walls. The ball 
and hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller 
because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There are only one ball and one hole in the maze.
The ball and hole will only exist in the empty space, and they will not at the 
same position initially.
The given maze doesn't contain border (like the red rectangle in the example 
pictures), but you should assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the length and width of the maze 
won't exceed 30.
*/

public class Solution {
    class State{
        int r, c;
        String move;
        int dir;

        public State(int r1, int c1, String s, int d){
            r = r1;
            c = c1;
            move = s;
            dir = d;
        }
    }
    
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    String direction(int dir){
        if(dir == 0)    return "r";        
        if(dir == 1)    return "l";        
        if(dir == 2)    return "d";        
        if(dir == 3)    return "u"; 
        return "";
    }
    
    int m, n;
    boolean isValid(int[][] maze, int r, int c, int dir){
        if(r < 0 || r >= m || c < 0 || c >= n || maze[r][c] == 1 || visited[r][c][dir])      return false;
        return true;        
    }
    
    boolean[][][] visited;    
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        m = maze.length;
        if(m == 0)      return "";
        n = maze[0].length;
        Queue<State> Q = new LinkedList<>();
        visited = new boolean[m][n][4];
        
        for(int d=0; d<4; ++d){
            int r1 = ball[0] + move[d][0];
            int c1 = ball[1] + move[d][1];
            if(isValid(maze, r1, c1, d)){
                Q.add(new State(r1, c1, direction(d), d));
            }
        }
        
        boolean found = false;
        String result = null;

        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                State f = Q.remove();
                visited[f.r][f.c][f.dir] = true;                
                if(f.r == hole[0] && f.c == hole[1]){
                    System.out.println(f.move);
                    found = true;
                    if(result == null)  
                        result = f.move;
                    else{
                        if(result.compareTo(f.move) > 0)
                            result = f.move;
                    }
                }
                
                int dir = f.dir;
                int r1 = f.r + move[dir][0];
                int c1 = f.c + move[dir][1];
                if(isValid(maze, r1, c1, f.dir)){
                    Q.add(new State(r1, c1, f.move, f.dir));
                }
                else{
                    // change direction
                    for(int d=0; d<4; ++d){
                        if(d == f.dir)  continue;
                        r1 = f.r + move[d][0];
                        c1 = f.c + move[d][1];
                        if(!isValid(maze, r1, c1, d))  
                            continue;
                        Q.add(new State(r1, c1, f.move + direction(d), d));
                    }
                }
            }
            if(found)
                break;
        }
        return result == null ? "impossible" : result;
    }
}

// Alternative: Dijkstra
public class Solution {
    class Node implements Comparable<Node> {
        int r, c;
        int distance;
        public Node(int r1, int c1, int d){
            distance = d;
            this.r = r1;
            this.c = c1;
        }
        @Override
        public int compareTo(Node n){
            if (distance != n.distance) return Integer.compare(distance, n.distance);
            if (r != n.r) return Integer.compare(r, n.r);
            return Integer.compare(c, n.c);
        }
    }
    int row, col;
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] maze;
    int[] hole;
    String[][] path;

    String direction(int dir){
        if(dir == 0)    return "r";        
        if(dir == 1)    return "l";        
        if(dir == 2)    return "d";        
        if(dir == 3)    return "u"; 
        return "";
    }
    
    boolean isValid(int r, int c){
        if(r < 0 || r >= row || c < 0 || c >= col || maze[r][c] == 1)     
            return false;
        return true;        
    }
    
    public Node roll(int r, int c, int dir, int distance){
        int dist = distance;
        int r1, c1;
        while(true){
            r1 = r + move[dir][0];
            c1 = c + move[dir][1];
            if(!isValid(r1, c1))
                break;
            dist++;
            r = r1;
            c = c1;
            if(r == hole[0] && c == hole[1])    break;
        }            
        return new Node(r, c, dist);
    }
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.maze = maze;
        this.hole = hole;
        row = maze.length;
        col = maze[0].length;

        int[][] distance = new int[row][col];
        for(int[] d : distance)
            Arrays.fill(d, Integer.MAX_VALUE);
        
        path = new String[row][col];
        
        TreeSet<Node> nodeSet = new TreeSet<>();
        nodeSet.add(new Node(ball[0], ball[1], 0));
        distance[ball[0]][ball[1]] = 0;
        path[ball[0]][ball[1]] = "";
        
        while(!nodeSet.isEmpty()){
            Node t = nodeSet.first();
            nodeSet.remove(t);
            for(int i=0; i<4; ++i){
                Node adj = roll(t.r, t.c, i, t.distance);
                if(adj.r == t.r && adj.c == t.c)
                    continue;

                if(distance[adj.r][adj.c] > adj.distance){
                    Node prev = new Node(adj.r, adj.c, distance[adj.r][adj.c]);
                    if(nodeSet.contains(prev)){
                        nodeSet.remove(prev);                    
                    }
                    
                    nodeSet.add(adj);
                    // update distance
                    distance[adj.r][adj.c] = adj.distance;
                    path[adj.r][adj.c] = path[t.r][t.c] + direction(i);
                }
                // update shortest path string
                else if(distance[adj.r][adj.c] == adj.distance){
                    // lexicographicall smallest
                    String nr = path[t.r][t.c] + direction(i);
                    if(path[adj.r][adj.c].compareTo(nr) > 0){
                        path[adj.r][adj.c] = nr;
                    }
                }
            }
        } 
        if(distance[hole[0]][hole[1]] == Integer.MAX_VALUE)
            return "impossible";
        return path[hole[0]][hole[1]];
    }
}
