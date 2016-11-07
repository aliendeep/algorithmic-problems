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
        int n = word.length();
        for(int i=0; i<n; i++){
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode(c);
            cur = cur.children[c - 'a'];
            if(i == n-1)
                cur.isEnd = true;
        }
    }
    
    public boolean search(String word, boolean isFullWord){
        TrieNode cur = root;
        int n = word.length();
        for(int i=0; i<n; i++){
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null)
                return false;
            cur = cur.children[c - 'a']; 
            if(i == n-1 && isFullWord == true)
                return (cur.isEnd == true);
        } 
        return true;
    }
    
    public boolean isPrefix(String word){
        return search(word, false);
    }

    public boolean isWord(String word){
        return search(word, true);
    }
}


public class Solution {
    Set<String> set;
    Trie trie;
    
    boolean isValid(int x, int y, int r, int c){
        if(x >= 0 && x < r && y >= 0 && y < c )
            return true;
        return false;
    }

    public void bktk(char[][] board, boolean[][] visited, StringBuilder cur, int r, int c){
        if(trie.isWord(cur.toString())){
            set.add(new String(cur.toString()));
            // Don't return as this valid cur word can be a prefix of further valid word 
            //return;
        }
        int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for(int i=0; i<4; ++i){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1, board.length, board[0].length) || visited[r1][c1])
                continue;
            
            int sl = cur.length();
            cur.append(board[r1][c1]);
            visited[r1][c1] = true;
            if(trie.isPrefix(cur.toString())){
                bktk(board, visited, cur, r1, c1);
            }
            visited[r1][c1] = false;
            cur.setLength(sl);
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        int r = board.length;
        if(r == 0)  return result;
        int c = board[0].length;
        
        set = new HashSet<>();        
        trie = new Trie();
        for(String w : words)
            trie.insert(w);
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(trie.isPrefix(board[i][j]+"")){
                    boolean[][] visited = new boolean[r][c];
                    StringBuilder cur = new StringBuilder();
                    cur.append(board[i][j]);
                    visited[i][j] = true;                    
                    bktk(board, visited, cur, i, j);                        
                }
            }
        }
        
        result.addAll(set);
        return result;
    }
}