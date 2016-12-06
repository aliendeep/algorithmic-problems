/*
This is a follow up of Shortest Word Distance. The only difference is now 
word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest 
distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/
// Time: O(n)
// Space: O(1)
/*
Testcases:
["practice", "makes", "perfect", "coding", "makes"]
"makes"
"coding"
["practice", "makes", "perfect", "coding", "makes"]
"makes"
"makes"
*/
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int n = words.length;
        boolean sameWord = word1.equals(word2);

        long minDiff = Integer.MAX_VALUE;
        long p1 = minDiff, p2 = -minDiff;
        for(int i=0; i<n; i++){
            if(words[i].compareTo(word1) == 0) {
                if(sameWord){
                    p1 = p2;
                    p2 = i;
                }            
                else
                    p1 = i;
            }
            else if(words[i].compareTo(word2) == 0)      
                p2 = i;
            minDiff = Math.min(minDiff, Math.abs(p1-p2));
        }
        return (int)minDiff;
    }
}

class Solution2 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int n = words.length;
        boolean sameWord = word1.equals(word2);

        int p1 = -1, p2 = -1;
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            if(words[i].compareTo(word1) == 0) {
                if(sameWord){
                    if(p1 == -1)    
                        p1 = i;
                    else{
                        if(p2 != -1){
                            p1 = p2;
                            p2 = i;
                        }
                        else
                            p2 = i;
                    }
                }            
                else
                    p1 = i;
            }
            else if(words[i].compareTo(word2) == 0)      
                p2 = i;
            if(p1 != -1 && p2 != -1)
                minDiff = Math.min(minDiff, Math.abs(p1-p2));
        }
        return minDiff;
    }
}