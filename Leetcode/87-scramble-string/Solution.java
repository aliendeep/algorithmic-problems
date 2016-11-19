/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/
/*
https://www.interviewbit.com/problems/scramble-string/

Lets first think of a bruteforce solution. 
Obviously the 2 strings need to have the same number of characters and the same character set, otherwise the answer is definitely no.

In the bruteforce solution, we loop to find out the root of the tree. 
Lets say the root is the ith character of string s1. Then the first part of s1 [0…i) can either match ( be a scrambled string of ) to s2[0…i) or s2(i+1 .. L]. 
Depending on which part s1[0…i) matches to, we match the remaining part of s1 to remaining part of s2. Just to clarify when we say s1 matches s2, 
we mean s1 is a scrambled string of s2.
*/

public class Solution {
    // Recursive
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;
        
        if(s1.equals(s2))
            return true;
        
        // check whether both string contains same characters
        int cnt[] = new int[26];
        for(int i=0;i<s1.length(); i++){
            cnt[s1.charAt(i) - 'a']++;
            cnt[s2.charAt(i) - 'a']--;
        }
        for(int i=0; i<26; i++)
            if(cnt[i] != 0)
                return false;

        // find all cuts
        for(int i=1; i<s1.length(); i++){
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;

            if(isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && 
                    isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
                return true;
        }
        return false;
    }
}