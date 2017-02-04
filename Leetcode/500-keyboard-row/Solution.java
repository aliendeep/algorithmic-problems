/*
Given a List of words, return the words that can be typed using letters of alphabet 
on only one row's of American keyboard like the image below.

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
*/

public class Solution {
    String[] layout = {"0123456789", "QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
    Map<Character, Integer> map;

    public Solution(){
        map = new HashMap<>();
        for(int i=0; i<layout.length; ++i){
            String str = layout[i];
            for(char c : str.toCharArray()){
                map.put(c, i);
            }
        }
    }

    public String[] findWords(String[] words) {
        if(words.length == 0)   return new String[0];
        // Store the indices
        List<Integer> result = new ArrayList<>();
        for(int k=0; k<words.length; ++k){
            String word = words[k].toUpperCase();
            int i = 0;
            int row = map.get(word.charAt(i));
            for(i=1; i<word.length(); ++i){
                if(map.get(word.charAt(i)) != row)
                    break;
            }
            if(i == word.length())
                result.add(k);
        }
        
        String[] r = new String[result.size()];
        int i = 0;
        for(int index : result)
            r[i++] = words[index];
        return r;
    }
}
