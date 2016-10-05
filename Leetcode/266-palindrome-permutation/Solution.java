/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

// Time  : O(n)
// Space: O(1)
public class Solution {
    public boolean canPermutePalindrome(String s) {
      int[] cnt = new int[256];
      for(int i=0; i<s.length(); i++){
          cnt[s.charAt(i)]++;
      }
      
      int odd = 0;
      for(int i=0; i<256; i++){
          if(cnt[i] == 0)
              continue;
          if(cnt[i] % 2 != 0)
            odd++;
      }  
      // 
      if(odd == 0)
          return true;
      // At most one odd number
      return (odd == 1);        
    }
}