/*
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

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
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
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
public class Solution {
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