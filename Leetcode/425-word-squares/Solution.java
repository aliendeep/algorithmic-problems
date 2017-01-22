/*
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word 
reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the 
order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
*/
import java.util.*;
// Using Trie : Build prefix list by traversing the trie structure
public class Solution {
    class Node{
        int val;
        boolean isEnd;
        Node[] child;
        public Node(int c){
            val = c;
            isEnd = false;
            child = new Node[26];
            for(int i=0; i<26; ++i)
                child[i] = null;
        }
    }

    class Trie{
        Node root;
        public Trie(){
            root = new Node('0');
        }

        public void insert(String word){
            Node cur = root;
            for(int i=0; i<word.length(); ++i){
                int c = word.charAt(i) - 'a';
                if(cur.child[c] == null){
                    cur.child[c] = new Node(c);
                }    
                cur = cur.child[c];
                if(i == word.length()-1)
                    cur.isEnd = true;
            }
        }

        public void dfs(Node node, StringBuilder cur, List<String> result){
            if(node == null)
                return;
            // end of a word (leaf)
            if(node.isEnd){
                result.add(cur.toString());
                return;
            }
            // for all child of node
            for(int i=0; i<26; i++){
                if(node.child[i] == null)
                    continue;
                cur.append((char)(i + 'a'));
                dfs(node.child[i], cur, result);
                cur.deleteCharAt(cur.length()-1);
            }
        }

        // Returns list of words that starts with the given prefix
        public List<String> startsWith(String prefix){
            Node cur = root;        
            for(int i=0; i<prefix.length(); ++i){
                int c = prefix.charAt(i) - 'a';
                // invalid prefix
                if(cur.child[c] == null)
                    return new ArrayList<>();
                cur = cur.child[c];
            }
            StringBuilder temp = new StringBuilder();  
            temp.append(prefix);
            
            List<String> prefixList = new ArrayList<>();
            // find all words starting with this prefix
            dfs(cur, temp, prefixList);
            return prefixList;
        }
    }

    List<List<String>> result;
    String[] words;
    int n;
    Trie trie;
    
    // Try with all possible word
    public void gen(List<String> cur, int levRow){
        if(levRow == n){
            result.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for(int i=0; i<levRow; ++i){
            prefix.append(cur.get(i).charAt(levRow));
        }

        List<String> prefixList = trie.startsWith(prefix.toString());
        // try with all possible words
        for(String candidate : prefixList){
            cur.add(candidate);
            gen(cur, levRow+1);
            cur.remove(cur.size()-1);
        }
    }

    public List<List<String>> wordSquares(String[] w) {
        this.result = new ArrayList<>();
        if(w.length == 0)
            return result;
        this.words = w;
        // total no of row and column in the word square
        n = words[0].length();

        trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        gen(new ArrayList<>(), 0);
        return result;
    }
}   


    public void print(List<List<String>> a){
        for(List<String> t : a){
          System.out.println("[");
          for(String x : t)
            System.out.println(x + " ");
          System.out.println("]");
        }
    }
      
    public static void main(String[] args){
        String[] words = {"abat","baba","atan","atal"};
        Solution1 ob = new Solution1();
        List<List<String>>  r = ob.wordSquares(words);
        ob.print(r);

        String[] t = {"area","lead","wall","lady","ball"};
        r = ob.wordSquares(t);
        ob.print(r);
    }        
}   

// Using Trie (Storing prefix at all node level)
class Solution1 {
    class Node{
        int val;
        boolean isEnd;
        List<String> prefixList;
        Node[] child;
        public Node(int c){
            val = c;
            isEnd = false;
            prefixList = new ArrayList<>();
            child = new Node[26];
            for(int i=0; i<26; ++i)
                child[i] = null;
        }
    }

    class Trie{
        Node root;
        public Trie(){
            root = new Node('0');
        }

        public void insert(String word){
            Node cur = root;
            for(int i=0; i<word.length(); ++i){
                cur.prefixList.add(word);
                int c = word.charAt(i) - 'a';
                if(cur.child[c] == null){
                    cur.child[c] = new Node(c);
                }    
                cur = cur.child[c];
                if(i == word.length()-1)
                    cur.isEnd = true;
            }
        }

        // Returns list of words that starts with the given prefix
        public List<String> startsWith(String prefix){
            Node cur = root;          
            for(int i=0; i<prefix.length(); ++i){
                int c = prefix.charAt(i) - 'a';
                if(cur.child[c] == null)
                    return new ArrayList<>();
                cur = cur.child[c];
                if(i == prefix.length()-1){
                    return cur.prefixList;
                }
            }
            return root.prefixList;
        }
    }

    List<List<String>> result;
    String[] words;
    int n;
    Trie trie;
    
    // Try with all possible word
    public void gen(List<String> cur, int levRow){
        if(levRow == n){
            result.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for(int i=0; i<levRow; ++i){
            prefix.append(cur.get(i).charAt(levRow));
        }

        List<String> prefixList = trie.startsWith(prefix.toString());
        // try with all possible words
        for(String candidate : prefixList){
            cur.add(candidate);
            gen(cur, levRow+1);
            cur.remove(cur.size()-1);
        }
    }

    public List<List<String>> wordSquares(String[] w) {
        this.result = new ArrayList<>();
        if(w.length == 0)
            return result;
        this.words = w;
        // total no of row and column in the word square
        n = words[0].length();

        trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        gen(new ArrayList<>(), 0);
        return result;
    }

    public void print(List<List<String>> a){
        for(List<String> t : a){
          System.out.println("[");
          for(String x : t)
            System.out.println(x + " ");
          System.out.println("]");
        }
    }
      
    public static void main(String[] args){
        String[] words = {"abat","baba","atan","atal"};
        Solution1 ob = new Solution1();
        List<List<String>>  r = ob.wordSquares(words);
        ob.print(r);

        String[] t = {"area","lead","wall","lady","ball"};
        r = ob.wordSquares(t);
        ob.print(r);
    }        
}   

// Storing all possible prefix in a hashmap
class Solution2 {
    HashMap<String, List<String>> prefixMap;
    int n;
    
    public void addToMap(String prefix, String word){
        List<String> l = prefixMap.get(prefix);
        if(l == null)
            l = new ArrayList<>();
        l.add(word);
        prefixMap.put(prefix, l);
        
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        
        this.n = words[0].length();
        prefixMap = new HashMap<>();
        
        // Compute prefix map
        for(String word : words){
            StringBuilder cur = new StringBuilder();
            cur.append("");
            addToMap(cur.toString(), word);
            
            for(int i=0; i<n; ++i){
                cur.append(word.charAt(i));
                addToMap(cur.toString(), word);
            }
        }
        
        List<String> c = new ArrayList<>();
        dfs(0, c, result);
        return result;
    }
    
    public void dfs(int levRow, List<String> cur, List<List<String>> result){
        if(levRow == n){
            result.add(new ArrayList<>(cur));
            return;
        }

        StringBuilder s =  new StringBuilder();
        for(int i=0; i<levRow; i++){
            s.append(cur.get(i).charAt(levRow));    
        }
        if(!prefixMap.containsKey(s.toString()))
            return;
            
        List<String> candidateWords = prefixMap.get(s.toString());
        for(String w : candidateWords){
            cur.add(w);
            dfs(levRow + 1, cur, result);
            cur.remove(cur.size()-1);
        }
    }
}
