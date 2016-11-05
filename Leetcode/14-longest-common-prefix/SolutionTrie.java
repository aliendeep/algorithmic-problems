// Write a function to find the longest common prefix string amongst an array of strings.

class TrieNode{
    char c;
    TrieNode[] child;
    boolean isEnd;

    // lowercase letters
    public TrieNode(char val){
        this.c = val;
        child = new TrieNode[26];
        for(int i=0; i<26; ++i){
            child[i] = null;
        }
        isEnd = false;
    }
}

class Trie{
    TrieNode root;
    public Trie(){
        root = new TrieNode('0');
    }
    
    public void insert(String x){
        TrieNode cur = root;
        // if the string is empty, then mark the isEnd of root as true
        if(x.length() == 0){
            root.isEnd = true;
            return;
        }
        for(int i=0; i<x.length(); ++i){
            char c = x.charAt(i);
            if(cur.child[c - 'a'] == null){
               cur.child[c - 'a'] = new TrieNode(c);
            }    
            cur = cur.child[c - 'a'];
            if(i == x.length()-1){
                cur.isEnd = true;
            }
        }
    }
    
    // Find the longest path that contains only one path and none of the node is marked as end
    public String longestPrefix(){
        TrieNode cur = root;
        if(root.isEnd)      return "";
        StringBuilder str = new StringBuilder();
        while(cur != null){
            int cnt = 0;
            boolean end = false;
            int index = -1;
            for(int i=0; i<26; i++){
                if(cur.child[i] != null){
                    cnt++;
                    index = i;
                    if(cur.child[i].isEnd){
                        end = true;
                    }
                }
            }
            if(cnt > 1 || index == -1)
                return str.toString();
            str.append((char)(index + 'a'));
            cur = cur.child[index];
            if(cur.isEnd == true){
                return str.toString();
            }
        }
        return str.toString();
    }
}
public class Solution {
    /*
    // https://leetcode.com/articles/longest-common-prefix/
    Given a set of keys S = [S_1,S_2 ... S_n], find the longest common prefix among a string q and S. This LCP query will be called frequently.
    */
    // Trie
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if(strs == null || n == 0)
            return "";
            
        Trie t = new Trie();        
        for(int i=0; i<n; ++i){
            t.insert(strs[i]);
        }
        
        return t.longestPrefix();
    }
}