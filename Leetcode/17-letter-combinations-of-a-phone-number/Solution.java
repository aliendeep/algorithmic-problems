/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
// Time Complexity: O(n*4^n) where n is the number of digits in the phone number
// At each level, maximum number of recursive call is 4
// So, 4^n. 
// Base Case: Copying the cur array to string taken O(n) time
// Total time complexity: O(n*4^n)
public class Solution {
    void bktk(String digits, StringBuilder cur, int lev, List<String> r, HashMap<Character, String> hmap){
        if(lev == digits.length()){
            r.add(cur.toString());
            return;
        }
        String s = hmap.get(digits.charAt(lev));
        for(int i=0; i<s.length(); i++){
            cur.setCharAt(lev, s.charAt(i));
            bktk(digits, cur, lev+1, r, hmap);
            cur.setCharAt(lev, '0');
        }
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> r = new ArrayList<String>();
        if(digits.isEmpty())
            return r;
        HashMap<Character, String> hmap = new HashMap<Character, String>();
        hmap.put('2', "abc");
        hmap.put('3', "def");
        hmap.put('4', "ghi");
        hmap.put('5', "jkl");
        hmap.put('6', "mno");
        hmap.put('7', "pqrs");
        hmap.put('8', "tuv");
        hmap.put('9', "wxyz");
        
        StringBuilder cur = new StringBuilder(digits);
        bktk(digits, cur, 0, r, hmap);
        return r;
    }
}

class Solution2 {
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    String digits;
    public void gen(StringBuilder cur, List<String> r){
        int lev = cur.length();
        if(lev == digits.length()){
            r.add(cur.toString());
            return;
        }
        String map = mapping[digits.charAt(lev) - '0'];
        for(int i=0; i<map.length(); ++i){
            cur.append(map.charAt(i));
            gen(cur, r);
            cur.deleteCharAt(cur.length()-1);
        }
    }
    public List<String> letterCombinations(String d) {
        List<String> r = new ArrayList<>();
        if(d.length() == 0)
            return r;
        digits = d;
        gen(new StringBuilder(), r);
        return r;
    }
}

class Solution3 {
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    String digits;
    public void gen(char[] cur, int lev, List<String> r){
        if(lev == digits.length()){
            r.add(new String(cur));
            return;
        }
        String map = mapping[digits.charAt(lev) - '0'];
        for(int i=0; i<map.length(); ++i){
            cur[lev] = map.charAt(i);
            gen(cur, lev+1, r);
            cur[lev] = '0';
        }
    }
    public List<String> letterCombinations(String d) {
        List<String> r = new ArrayList<>();
        if(d.length() == 0)
            return r;
        digits = d;
        char[] cur = new char[d.length()];
        gen(cur, 0, r);
        return r;
    }
}
