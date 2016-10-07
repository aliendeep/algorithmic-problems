/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/

// Time: O(2^n)
public class Solution {
    // For every character, we can either keep it or abbreviate it
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        bktk("", word, 0, 0, result);
        return result;
    }
    
    void bktk(String cur, String word, int lev, int cnt, List<String> result){
        if(lev == word.length()){
            if(cnt > 0)
                cur += cnt;
            result.add(cur);
            return;
        }
        // Skip the character but increment the count
        bktk(cur, word, lev+1, cnt+1, result);
        // Include the character
        bktk(cur + (cnt > 0 ? cnt : "") + word.charAt(lev) , word, lev+1, 0, result);
    }
}