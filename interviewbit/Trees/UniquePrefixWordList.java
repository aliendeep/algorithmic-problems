/*
Find shortest unique prefix to represent each word in the list.

Example:

Input: [zebra, dog, duck, dove]
Output: {z, dog, du, dov}
where we can see that
zebra = z
dog = dog
duck = du
dove = dov
NOTE : Assume that no word is prefix of another. In other words, the representation i
s always possible. 
*/

class TrieNode {
    // Initialize your data structure here.
    public char val;
    // Number of children at this node
    public TrieNode[] child;
    public boolean isEnd;
    public int wordCount;
    
    public TrieNode(char v){
        this.val = v;
        child = new TrieNode[26];
        for(int i=0; i<26; i++){
            child[i] = null;
        }
        wordCount = 0;
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    // Inserts a word into the trie.
    public void add(String word) {
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c -'a';
            if(cur.child[index] == null)
                cur.child[index] = new TrieNode(c);
            
            cur = cur.child[index];
            cur.wordCount = cur.wordCount + 1;

            if(i == word.length()-1)
                cur.isEnd = true;
        }
    }
    
    public String findUniquePrefix(String s){
        StringBuilder r = new StringBuilder();
        TrieNode cur = root;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            int index = c -'a';
            r.append(c);
            cur = cur.child[index];
            if(cur.wordCount <= 1)
                return r.toString();
        }
        return s;
    }
}

public class Solution {
  public ArrayList<String> prefix(ArrayList<String> a) {
      Trie t = new Trie();
      for(String s : a){
          t.add(s);
      }
      ArrayList<String> r = new ArrayList<>();
      for(String s : a){
          r.add(t.findUniquePrefix(s));
      }
      return r;
  }
}
