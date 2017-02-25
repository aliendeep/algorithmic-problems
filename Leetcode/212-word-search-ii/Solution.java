/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where 
"adjacent" cells are those horizontally or vertically neighboring. The same letter 
cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
// Trie, Backtracking
class TrieNode{
    public char val;
    public TrieNode[] children;
    public boolean isEnd;
    public TrieNode(char v){
        this.val = v;
        children = new TrieNode[26];
        for(int i=0; i<26; i++){
            children[i] = null;
        }
        isEnd = false;
    }
}

class Trie{
    public TrieNode root;
    public Trie(){
        root = new TrieNode('0');
    }
    
    public void insert(String word){
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(cur.children[index] == null)
                cur.children[index] = new TrieNode(word.charAt(i));
            cur = cur.children[index];
            if(i == word.length()-1)
                cur.isEnd = true;
        }
    }
    
    public boolean search(String word, boolean isFullWord){
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(cur.children[index] == null)
                return false;
            cur = cur.children[index]; 
            if(i == word.length()-1 && isFullWord == true)
                return cur.isEnd == true;
        } 
        return true;
    }
    
    public boolean prefix(String word){
        return search(word, false);
    }

    public boolean wordExists(String word){
        return search(word, true);
    }
}

public class Solution {
    Trie trie;
    Set<String> result;
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    
    boolean isValid(int x, int y, int r, int c){
        if(x >= 0 && x < r && y >= 0 && y < c )
            return true;
        return false;
    }
    
    void bktk(char[][] board, int r, int c, boolean[][] visited, StringBuilder cur){
        if(!trie.prefix(cur.toString()))
            return;
        
        // Full word is in the dictionary
        if(trie.wordExists(cur.toString())){
            if(cur.length() > 0)
                result.add(cur.toString());
        }
        
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1, board.length, board[0].length) || visited[r1][c1] == true)
                continue;
                
            visited[r1][c1] = true;
            cur.append(board[r1][c1]);    

            bktk(board, r1, c1, visited, cur);

            cur.deleteCharAt(cur.length()-1);
            visited[r1][c1] = false;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> t = new ArrayList<>();
        int r = board.length;
        if(r == 0)  return t;
        int c = board[0].length;
        
        trie = new Trie();
        // insert all dictionary words
        for(String word : words)
            trie.insert(word);

        result = new HashSet<>();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                boolean[][] visited = new boolean[r][c];
                visited[i][j] = true;

                StringBuilder cur = new StringBuilder();
                cur.append(board[i][j]);
                bktk(board, i, j, visited, cur);
            }
        }
        return new ArrayList<>(result);
    }
}
