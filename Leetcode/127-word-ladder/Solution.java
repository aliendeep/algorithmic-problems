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

public class Solution {
    // BFS
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        
        int resultCnt = 0;
        int levCnt = 1;
        while(!q.isEmpty()){
            String t = q.remove();
            levCnt--;
            for(int pos=0; pos<t.length(); pos++){
                for(int i=0; i<26; i++){
                    // same character
                    if(t.charAt(pos) == i + 'a')
                        continue;
                        
                    // Change one character
                    StringBuilder str = new StringBuilder(t);
                    str.setCharAt(pos, (char)(i + 'a'));
                    String newWord = str.toString();
                    // Reached the end word (+2 for start and end word)
                    if(newWord.compareTo(endWord) == 0)
                        return resultCnt + 2;
                        
                    if(wordList.contains(newWord)){
                        q.add(newWord);
                        // remove it from the dictionary
                        wordList.remove(newWord);
                    }
                }                
            }
            
            if(levCnt == 0){
                levCnt = q.size();
                resultCnt++;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
      Solution s = new Solution();
      Set<String> wordList = new HashSet<>();
      wordList.add("hot");
      wordList.add("dot");
      wordList.add("dog");
      wordList.add("lot");
      wordList.add("log");
      System.out.println(s.ladderLength("hit", "cog", wordList));
    } 
}
