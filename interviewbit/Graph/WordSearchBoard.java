/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The cell itself does not count as an adjacent cell. 
The same letter cell may be used more than once.

Example :

Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns 1,
word = "SEE", -> returns 1,
word = "ABCB", -> returns 1,
word = "ABFSAB" -> returns 1
word = "ABCD" -> returns 0
Note that 1 corresponds to true, and 0 corresponds to false.
*/
public class Solution {
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int r, c;
    boolean isValid(int x, int y){
        if(x >= 0 && x < r && y >= 0 && y < c )
            return true;
        return false;
    }
    boolean dfs(ArrayList<String> board, int r, int c, String word){
        if(word.length() == 0)
            return true;
            
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1))
                continue;
            
            if(word.charAt(0) == board.get(r1).charAt(c1)){
                if(dfs(board, r1, c1, word.substring(1)))
                    return true;
            }
        }
        return false;
    }
    
    // DFS
    public int exist(ArrayList<String> board, String word) {
        r = board.size();
        if(r == 0)  return 0;
        c = board.get(0).length();
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(word.charAt(0) == board.get(i).charAt(j)){
                    if(dfs(board, i, j, word.substring(1)))
                        return 1;
                }
            }
        }
        return 0;
    }
}    