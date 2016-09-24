/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> mapping = new HashMap<Character, Integer>();
        mapping.put('M', 1000);
        mapping.put('D', 500);
        mapping.put('C', 100);
        mapping.put('L', 50);
        mapping.put('X', 10);
        mapping.put('V', 5);
        mapping.put('I', 1); 
        
        int n = s.length();
        int sum = mapping.get(s.charAt(n-1));
        // if the symbol after the current one is greater than it, then we subtract the current symbol
        for(int i=n-2; i>=0; i--){
            int value = mapping.get(s.charAt(i));
            if(mapping.get(s.charAt(i)) < mapping.get(s.charAt(i+1)))
                sum -= value;
            else
                sum += value;
        }
        return sum;
    }
}