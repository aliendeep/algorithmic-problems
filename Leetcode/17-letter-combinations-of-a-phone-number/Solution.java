/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
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