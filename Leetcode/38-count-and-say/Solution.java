/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class Solution {
    String getNextNumber(String s){
        StringBuilder result = new StringBuilder();
        int i = 0;

        while(i<s.length()){
            int cnt = 1;
            while(i + 1 < s.length() && s.charAt(i) == s.charAt(i+1)){
                i++;
                cnt++;
            }
            result.append(Integer.toString(cnt) + s.charAt(i));
            i++;
        }
        return result.toString();
    }
    public String countAndSay(int n) {
        String s = "1";
        int i = 1;
        while(i<n){
            s = getNextNumber(s);            
            i++;
        }
        return s;
    }
}