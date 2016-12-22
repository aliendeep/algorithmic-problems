/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation 
of this target string with the smallest possible length such that it does not 
conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, 
the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may 
return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that 
m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include 
"ap3", "a3e", "2p2", "3le", "3l1").
*/
public class Solution {
    Set<String> generateAbbr(String target){
        int n = target.length();

        List<String> abbrs = new ArrayList<>();
        // include the first character
        abbrs.add(new String(target.charAt(0) + ""));
        // Or don't include the first character (Just add the count)
        abbrs.add("1");
        
        for(int i=1; i<n; ++i){
            // include the current char or not
            String next = new String(target.charAt(i) + "");
            List<String> newAbbrs = new ArrayList<>();
            for(String s : abbrs){
                newAbbrs.add(s + next);
                newAbbrs.add(s + "1");
            }
            abbrs = newAbbrs;
        }
        
        // Compute the final result (Add all 1s)
        Set<String> result = new HashSet<>();
        for(String s : abbrs){
            int cnt = 0;
            StringBuilder str = new StringBuilder();
            for(int i=0; i<s.length(); ++i){
                char c = s.charAt(i);
                if(Character.isDigit(c)){
                   cnt++; 
                }
                else{
                    // if cnt is not zero (Add the previous cnt)
                    if(cnt != 0){
                        str.append(cnt);
                        // reset cnt    
                        cnt = 0;
                    }
                    // Append current character
                    str.append(c);
                }
            }
            // Append the last cnt if needed
            if(cnt != 0)
                str.append(cnt);
            result.add(str.toString());
        }
        return result;
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