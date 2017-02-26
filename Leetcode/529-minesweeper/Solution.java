/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an 
unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a 
revealed blank square that has no adjacent (above, below, left, right, and all 
4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent 
to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the 
unrevealed squares ('M' or 'E'), return the board after revealing this position 
according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to 
revealed blank ('B') and all of its adjacent unrevealed squares should be revealed 
recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change 
it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
Example 1:
Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:
Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Note:
The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also 
means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example, 
you don't need to reveal all the unrevealed mines when the game is over, consider 
any cases that you will win the game or flag any squares.
*/
public class Solution {
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    int r, c;

    class Pair{
        int r, c;
        public Pair(int r1, int c1){
            r = r1;
            c = c1;
        }
    }
    
    public char[][] updateBoard(char[][] board, int[] click) {
        r = board.length;
        c = board[0].length;
        
        int cr = click[0];    
        int cc = click[1];
        if(board[cr][cc] == 'M'){
            board[cr][cc] = 'X';
            return board;
        }
        
        int[][] mineCount = new int[r][c];
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                for(int k=0; k<8; ++k){
                    int r1 = i + move[k][0];
                    int c1 = j + move[k][1];
                    if(r1 < 0 || r1 >= r || c1 < 0 || c1 >= c || board[r1][c1] != 'M')
                        continue;
                    mineCount[i][j]++;
                }
            }
        }
        
        boolean[][] visited = new boolean[r][c];
        Queue<Pair> Q = new LinkedList<>();
        board[cr][cc] = mineCount[cr][cc] == 0 ? 'B' : (char)(mineCount[cr][cc] + '0');
        if(mineCount[cr][cc] == 0)
            Q.add(new Pair(cr, cc));
        visited[cr][cc] = true;
        
        while(!Q.isEmpty()){
            Pair t = Q.remove();
            for(int k=0; k<8; ++k) {
                int r1 = t.r + move[k][0];
                int c1 = t.c + move[k][1];
                if(r1 < 0 || r1 >= r || c1 < 0 || c1 >= c || visited[r1][c1] || board[r1][c1] != 'E')
                    continue;
                board[r1][c1] = mineCount[r1][c1] == 0 ? 'B' : (char)(mineCount[r1][c1] + '0');
                if(mineCount[r1][c1] == 0)
                    Q.add(new Pair(r1, c1));
                visited[r1][c1] = true;
            }
        }
        return board;
    }
}
