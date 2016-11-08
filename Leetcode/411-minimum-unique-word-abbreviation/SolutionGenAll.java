/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
*/

//Alternative: Bruteforce
public class Solution {
    void bktk(String target, int lev, int cnt, StringBuilder cur, Set<String> result){
        int len = cur.length();
        if(lev == target.length()){
            if(cnt > 0)
                cur.append(cnt);
            result.add(cur.toString());
            // revert
            cur.setLength(len);
            return;
        }
        
        // Skip the current char but increase count        
        bktk(target, lev+1, cnt+1, cur, result);
        // Include the current char
        if(cnt > 0)
            cur.append(cnt);
        cur.append(target.charAt(lev));
        // reset cnt
        bktk(target, lev+1, 0,  cur, result);
        // revert
        cur.setLength(len);
    }

    Set<String> generateAbbr(String target){
        Set<String> r = new HashSet<>();
        bktk(target, 0, 0, new StringBuilder(), r);
        return r;
    }

    public boolean isValidWordAbbr(String s, String abbr){
        int i = 0, j = 0;
        int sl = s.length();
        int al = abbr.length();
        
        int num = 0;    
        while(i < abbr.length()){
            char c = abbr.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + (c - '0'); 
            }
            else{
                // Skip num character
                j += num;
                if(j >= sl || c != s.charAt(j))
                    return false;
                
                // reset num
                num = 0;
                j++;
            }
            i++;
        }
        if(num > 0)
            j += num;
        // Consumed all letter
        return (i == al && j == sl);
    }
    
    public String minAbbreviation(String target, String[] dictionary) {
        int n = target.length();
        if(n == 0)      return "";
        // Find words in the dictionary that have the same length as of target
        List<String> words = new ArrayList<>();
        for(String word : dictionary){
            if(word.length() != n)
                continue;
            words.add(word);
        }
        
        // No other words in the dictionary has the same length as of targer
        if(words.size() == 0)
            return String.valueOf(n);
            
        // generate all abbreviations
        Set<String> abbrs = generateAbbr(target);
        // Remove all conflicting abbreviations
        for(String s : dictionary){
            int len = 0;
            Set<String> same = new HashSet<>();
            for(String a : abbrs){
                // if a is an valid abbr of some other word in the dictionary
                if(isValidWordAbbr(s, a))
                    same.add(a);
            }
            // Remove all matched abbrs from the set
            abbrs.removeAll(same);
        }
        
        if(abbrs.size() == 0)
            return "";
            
        // Sort
        List<String> validAbbrs = new ArrayList<>(abbrs);
        Collections.sort(validAbbrs, new Comparator<String>(){
            @Override
            public int compare(String x, String y){
                if(x.length() == y.length())
                    return x.compareTo(y);
                return Integer.compare(x.length(), y.length());
            }
        });
        // return the min
        return validAbbrs.get(0);
    }
}