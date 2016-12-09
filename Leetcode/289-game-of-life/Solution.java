/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
is a cellular automaton devised by the British mathematician 
John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their 
updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, 
which would cause problems when the 
active area encroaches the border of the array. How would you address these problems?

Sample Input:
[[]]
[[1],[1],[1],[1],[0],[0],[1],[0],[1],[1]]
[[0,0,0,0,0],[0,0,1,0,0],[0,0,1,0,0],[0,0,1,0,0],[0,0,0,0,0]]

Sample Output:
[[]]
[[0],[1],[1],[0],[0],[0],[0],[0],[0],[0]]
[[0,0,0,0,0],[0,0,0,0,0],[0,1,1,1,0],[0,0,0,0,0],[0,0,0,0,0]]
*/

// Bit Manipulation
public class Solution {
    boolean isValid(int i, int j, int r, int c){
        if(i >= 0  && i<r && j >= 0  && j<c)
            return true;
        return false;
    }
    
    // Use 2 bits to store state [next state, current state]
    // Compute the next state (after one update) of the board given its current state.
    public void gameOfLife(int[][] board) {
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {1,-1}, {-1, 1}};
        int r = board.length;
        int c = board[0].length;
        
        int liveCells = 0;
        for(int i=0;i<r; i++){
            for(int j=0;j<c; j++){
                liveCells = 0;
                for(int k=0; k<8; k++){
                    int x = i + move[k][0];
                    int y = j + move[k][1];
                    if(!isValid(x, y, r, c))
                        continue;
                    liveCells += (board[x][y] & 1);
                }
                // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                if(board[i][j] == 0 && liveCells == 3)
                    board[i][j] = 0b10;
                if(board[i][j] == 1 && liveCells >= 2 && liveCells <= 3)
                    board[i][j] = 0b11;
            }
        }
        for(int i=0;i<r; i++){
            for(int j=0;j<c; j++){
                  board[i][j] >>= 1;
            }
        }
    }
}

// Follow up : Infinite board
class Solution2 {
    int row, col;
    public static final int largePrime = 86028157;
    class Coord{
        int r, c;
        public Coord(int r1, int c1){
            r = r1;
            c = c1;
        }
        @Override
        public boolean equals(Object ob){
            if(ob instanceof Coord){
                Coord p = (Coord)ob;
                return p.r == r && p.c == c;
            }
            return false;
        }
        @Override
        public int hashCode(){
            return r*largePrime + c;
        }        
    }
    boolean isValid(int i, int j){
        if(i >= 0  && i<row && j >= 0  && j<col)
            return true;
        return false;
    }
    
    int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {1,-1}, {-1, 1}};
    public Set<Coord> gameOfLiveInfinite(Set<Coord> liveCells){
        // Keep the count in a map
        // Coord, Cnt
        Map<Coord, Integer> map = new HashMap<>();
        for(Coord coord : liveCells){
            for(int k=0; k<8; k++){
                int r = coord.r + move[k][0];
                int c = coord.c + move[k][1]; 
                if(!isValid(r, c))
                    continue;
                Coord t = new Coord(r, c);
                if(!map.containsKey(t))
                    map.put(t, 0);
                map.put(t, map.get(t)+1);
            }
        }        
        
        Set<Coord> newLiveCells = new HashSet<>();
        for(Map.Entry<Coord, Integer> entry : map.entrySet()){
            Coord key = entry.getKey();
            int cnt = entry.getValue();
            if(cnt == 3 || (cnt == 2 && liveCells.contains(key)))
                newLiveCells.add(key);
        }
        return newLiveCells;
    }
    
    public void gameOfLife(int[][] board) {
        row = board.length;
        col = board[0].length;
        Set<Coord> liveCells = new HashSet<>();
        for(int i=0;i<row; i++){
            for(int j=0;j<col; j++){
                if(board[i][j] == 1){
                    liveCells.add(new Coord(i, j));
                }
            }
        }
        Set<Coord> newLiveCells = gameOfLiveInfinite(liveCells);
        // Reset board
        for(int[] t : board)
            Arrays.fill(t, 0);
        for(int i=0;i<row; i++){
            for(int j=0;j<col; j++){
                if(newLiveCells.contains(new Coord(i, j))){
                    board[i][j] = 1;
                }
            }
        }
    }
}