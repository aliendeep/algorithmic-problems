/*
Write a function that takes a string as input and reverse only the vowels of 
a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/

public class Solution {
    public String reverseVowels(String s) {
        Set<Character> charSet = new HashSet<Character>();
        charSet.add('a');
        charSet.add('e');
        charSet.add('i');
        charSet.add('o');
        charSet.add('u');
        charSet.add('A');
        charSet.add('E');
        charSet.add('I');
        charSet.add('O');
        charSet.add('U');
        
        StringBuffer vowel = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(charSet.contains(c))
                vowel.append(c);
        }
        StringBuffer r = new StringBuffer();
        int end = vowel.length() - 1;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(charSet.contains(c)){
                r.append(vowel.charAt(end--));
            }
            else
                r.append(c);
        }
        return r.toString();
    }
}

// Alternative: Two pointer solution
class Solution2 {
    public final static String vowels ="aeiouAEIOU";
    public String reverseVowels(String s) {
        StringBuilder r = new StringBuilder(s);
        int i = 0, j = s.length()-1;
        while(i < j){
            // Find the vowel from left to right
            while(i < j && vowels.indexOf(r.charAt(i)) == -1)
                ++i;
            // Find the vowel from right to left
            while(i < j && vowels.indexOf(r.charAt(j)) == -1)
                --j;
            // swap the two characters
            char t = r.charAt(i);
            r.setCharAt(i, s.charAt(j));
            r.setCharAt(j, t);
            i++;
            j--;
        }
        return r.toString();
    }
}
