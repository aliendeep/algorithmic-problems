/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

class TrieNode {
    // Initialize your data structure here.
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

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
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

    public boolean searchWord(String word, boolean isFullWord){
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

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return searchWord(word, true);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchWord(prefix, false);
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");