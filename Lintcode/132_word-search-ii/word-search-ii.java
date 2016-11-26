/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/word-search-ii
@Language: Java
@Datetime: 16-11-26 01:45
*/

class TrieNode{
    public char val;
    public TrieNode[] child;
    public boolean isEnd;
    
    public TrieNode(char v){
        this.val = v;
        child = new TrieNode[26];
        for(int i=0; i<26; i++){
            child[i] = null;
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
            char c = word.charAt(i);
            int index = c-'a';
            if(cur.child[index] == null)
                cur.child[index] = new TrieNode(c);
            cur = cur.child[index];
            if(i == word.length()-1)
                cur.isEnd = true;
        }
    }
    
    public boolean search(String word, boolean isFullWord){
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(cur.child[index] == null)
                return false;
            cur = cur.child[index]; 
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
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    Trie trie;

    boolean isValid(int x, int y, int r, int c){
        if(x >= 0 && x < r && y >= 0 && y < c)
            return true;
        return false;
    }
    
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    void bktk(char[][] board, int r, int c, StringBuffer curWord, 
             boolean[][] visited, Set<String> result){

        if(!trie.prefix(curWord.toString()))
            return;
            
        if(trie.wordExists(curWord.toString())){
            if(curWord.length() > 0)
                result.add(new String(curWord));
        }
        
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1, board.length, board[0].length) || visited[r1][c1] == true)
                continue;

            visited[r1][c1] = true;
            curWord.append(board[r1][c1]);    

            bktk(board, r1, c1, curWord, visited, result);

            curWord.deleteCharAt(curWord.length()-1);
            visited[r1][c1] = false;
        }
    }
    
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        ArrayList<String> t = new ArrayList<>();
        int r = board.length;
        if(r == 0)  return t;
        int c = board[0].length;
        
        trie = new Trie();
        // insert all dictionary words
        for(String word : words)
            trie.insert(word);

        Set<String> result = new HashSet<>();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                boolean[][] visited = new boolean[r][c];
                visited[i][j] = true;
                
                StringBuffer cur = new StringBuffer();
                cur.append(board[i][j]);
                if(trie.prefix(cur.toString()) == true){
                    bktk(board, i, j, cur, visited, result);
                }
            }
        }
        ArrayList<String> t1 = new ArrayList<>(result);
        return t1;
    }
}