// Write a function to generate the generalized abbreviations of a word.
// https://leetcode.com/problems/generalized-abbreviation/

// Time: O(2^n)
import java.util.*;

public class GeneralizedAbbreviation{
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

// Time: O(2^n)
class GeneralizedAbbreviation2{
    // For every character, we can either keep it or abbreviate it
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        bktk(cur, word, 0, 0, result);
        return result;
    }
    
    void bktk(StringBuilder cur, String word, int lev, int cnt, List<String> result){
        int len = cur.length();
        if(lev == word.length()){
            if(cnt > 0)     
                cur.append(cnt);
            result.add(cur.toString());
        }
        else{
            // Skip the character but increment the count
            bktk(cur, word, lev+1, cnt+1, result);
            // Include the character
            if(cnt > 0)  cur.append(cnt);
            cur.append(word.charAt(lev));
            // reset the cnt
            bktk(cur, word, lev+1, 0, result);
        }
        cur.setLength(len);
    }
}
