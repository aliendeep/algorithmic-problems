/*
Given two words (beginWord and endWord), and a dictionary's word list, find the 
length of shortest transformation sequence from beginWord to endWord, 
such that:

1. Only one letter can be changed at a time
2. Each intermediate word must exist in the word list

For example,
Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
import java.util.*;

import java.util.*;

public class Solution {
    // BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        
        int resultLev = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int f=0; f<size; ++f){
                String t = Q.remove();
                if(t.equals(endWord))
                    return resultLev + 1;
                for(int pos=0; pos<t.length(); pos++){
                    for(int i=0; i<26; i++){
                        // same character
                        if(t.charAt(pos) == i + 'a')
                            continue;
                        
                        // Change one character
                        StringBuilder str = new StringBuilder(t);
                        str.setCharAt(pos, (char)(i + 'a'));
                        String newWord = str.toString();
                        if(dict.contains(newWord)){
                            Q.add(newWord);
                            // remove it from the dictionary
                            dict.remove(newWord);
                        }
                    }                
                }
            }
            resultLev++;
        }
        return 0;
    }
}

