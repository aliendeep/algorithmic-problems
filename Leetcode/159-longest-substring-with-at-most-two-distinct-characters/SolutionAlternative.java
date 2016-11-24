/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
/*
Test case:
"ccaabbb"
"eceba"
"llohhheheehllo"
"llohhheheehlloaabbbababbbabaca"
"ab"
"aaa"
"tcbbbbaaaaa"
"ccaabbb"
Sample Output:
5
3
8
14
2
3
9
5
*/

public class Solution {
    // HashMap solution
    // O(n)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n <= 2)
            return n;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        while(end < n){
            char c = s.charAt(end);
            if(map.size() <= 2){
                map.put(c, end);
                end++;
            }
            if(map.size() > 2){
                // Find the leftmost Index
                int leftIndex = n;
                for(int i : map.values()){
                    leftIndex = Math.min(leftIndex, i);
                }
                map.remove(s.charAt(leftIndex));
                start = leftIndex + 1;
            }
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }
}