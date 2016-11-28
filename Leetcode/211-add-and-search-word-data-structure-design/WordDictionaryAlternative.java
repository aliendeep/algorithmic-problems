// Trie
class TrieNode {
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

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c -'a';
            if(cur.child[index] == null)
                cur.child[index] = new TrieNode(c);
                
            cur = cur.child[index];
            if(i == word.length()-1)
                cur.isEnd = true;
        }
    }

    public boolean dfs(String word, int n, int lev, TrieNode cur){
        if(lev < n){
            char c = word.charAt(lev);
            int index = c - 'a';
            if(c != '.'){
                if(cur.child[index] == null)
                    return false;
                if(lev == n-1)
                    return cur.child[index].isEnd;
                return dfs(word, n, lev+1, cur.child[index]);
            }
            else{
                // char is .
                for(int i=0; i<26; ++i){
                    if(cur.child[i] != null){
                        if(lev == n-1 && cur.child[i].isEnd == true)
                            return true;                             
                        if(dfs(word, n, lev+1, cur.child[i]))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean search(String word){
        int n = word.length();
        return dfs(word, n, 0, root);
    }
}

public class WordDictionary {
    Trie trie;
    public WordDictionary(){
        trie = new Trie();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        trie.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return trie.search(word);
    }
    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));        
        System.out.println(wordDictionary.search("mad"));        
        System.out.println(wordDictionary.search("bad"));        
        System.out.println(wordDictionary.search("b.."));        
        System.out.println(wordDictionary.search(".ad"));       
        wordDictionary.addWord("a");
        wordDictionary.addWord("ab");
        wordDictionary.addWord("add");
        wordDictionary.addWord("adds");
        System.out.println(wordDictionary.search("ran"));       
        System.out.println(wordDictionary.search("add."));       
    }

}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");