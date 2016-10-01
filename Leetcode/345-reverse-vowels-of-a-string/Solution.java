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