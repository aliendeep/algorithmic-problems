/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
*/

public class Solution {
    public static String reverseWords(String str) {
        String[] s = str.trim().split(" ");
        StringBuilder result = new StringBuilder();
        for(int i=s.length-1; i>=0; i--){
          if(s[i].length() > 0){
            result.append(s[i]);
            if(i > 0)
              result.append(" ");
          }
        }
        return result.toString();
    }
    public static void main(String[] strs){
      System.out.println(reverseWords("   a   b "));
      System.out.println(reverseWords("the sky is blue"));
    }
}
