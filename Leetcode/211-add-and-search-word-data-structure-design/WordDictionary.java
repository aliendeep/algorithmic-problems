/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing 
only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
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

public class WordDictionary {
    private TrieNode root;

    public WordDictionary(){
        root = new TrieNode('0');
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word, TrieNode cur) {
        if(cur == null)
            return false;
        if(word.length() == 0)
            return (cur.isEnd == true);
        
        if(word.charAt(0) == '.'){
            for(int i=0; i<26; i++){
                if(search(word.substring(1), cur.children[i]))
                    return true;
            }
        }
        else{
            int index = word.charAt(0) - 'a';
            if(cur.children[index] == null)
                return false;
            cur = cur.children[index];
            return search(word.substring(1), cur);
        }
        return false;
    }

    public boolean search(String word){
        return search(word, root);
    }

    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));        
        System.out.println(wordDictionary.search("bad"));        
        System.out.println(wordDictionary.search("b.."));        
        System.out.println(wordDictionary.search(".ad"));       
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

// Without using substring
class Solution2{
    public boolean search(String word, int lev, TrieNode cur) {
        if(lev == word.length()){
            return cur.isEnd;
        }
        if(word.charAt(lev) == '.'){
            // try all children
            for(int i=0; i<26; ++i){
                if(cur.child[i] == null)
                    continue;
                if(search(word, lev+1, cur.child[i]))
                    return true;
            }
        }
        else{
            char c = word.charAt(lev);
            if(cur.child[c - 'a'] == null)
                return false;
            if(search(word, lev+1, cur.child[c - 'a']))
                return true;
        }
        // no match found
        return false;
    }

    public boolean search(String word){
        return search(word, 0, root);
    }
}
