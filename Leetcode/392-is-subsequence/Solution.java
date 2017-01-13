/*
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. 
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string 
by deleting some (can be none) of 
the characters without disturbing the relative positions of the remaining characters. 
(ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want 
to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?
*/

public class Solution {
    // Greedy
  public static boolean isSubsequence(String s, String t) {
      if(s.length() > t.length())
          return false;

      if(s.length() == 0)
          return true;

      int curPos = 0;
      for(int i=0; i<t.length(); i++){
          if(s.charAt(curPos) == t.charAt(i))
              curPos++;
          if(curPos == s.length())
              return true;
      }  
      return false;
  }
  public static void main(String[] args){
    System.out.println(isSubsequence("hello", "wollelloohrledltleeeo"));
    System.out.println(isSubsequence("hello", "hello"));
    System.out.println(isSubsequence("", ""));
    System.out.println(isSubsequence("axc", "ahbgdc"));
    System.out.println(isSubsequence("abc", "ahbgdc"));
  }
}

// Memory Limit Exceeded
class SolutionFollowUp {
    // Follow up: Use Binary Search
    public static boolean isSubsequence(String s, String t) {
        if(s.length() > t.length())     return false;
        if(s.length() == 0)             return true;
        
        // (Character, List of indices)
        List<List<Integer>> map = new ArrayList<>();   
        // init
        for(int i=0; i<26; ++i){
            map.add(new ArrayList<>());
        } 

        for(int i=0; i<t.length(); i++){
            int c = t.charAt(i) - 'a';
            List<Integer> l = map.get(c);
            if(l.size() == 0)
                l = new ArrayList<>();
            l.get(c).add(i);
        }
        
        int index = 0;
        for(int i=0; i<s.length(); ++i){
            int c = (int)(s.charAt(i) - 'a');
            // if t doesn't contain this character, then return false
            if(map.get(c).size() == 0)     return false;
            List<Integer> l = map.get(c);
            // Get a valid index
            // Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
            Integer nextIndex = Collections.binarySearch(l, index);
            // If no valid element is found, then return false
            if(nextIndex == null)       return false;
            // Update index
            index = nextIndex + 1;
        }
        return true;
    }

    public static void main(String[] args){
      System.out.println(isSubsequence("hello", "wollelloohrledltleeeo"));
      System.out.println(isSubsequence("hello", "hello"));
      System.out.println(isSubsequence("", ""));
      System.out.println(isSubsequence("axc", "ahbgdc"));
    }
}
