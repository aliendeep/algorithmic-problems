/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

import java.util.*;

public class ValidWordAbbr {
    // key, original string
    HashMap<String, String> map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for(String s : dictionary){
            String key = getAbbr(s);
            if(!map.containsKey(key))
                map.put(key, s);
            // if same word repeated multiple times in the dictioanry
            else if(map.get(key).compareTo(s) != 0)
                map.put(key, "");
        }
    }
    
    public String getAbbr(String s){
        int n = s.length();
        if(n <= 2)
            return s;
        return s.charAt(0) +  Integer.toString(n-2) + s.charAt(n-1);
    }
    
    public boolean isUnique(String word) {
        String key = getAbbr(word);
        if(!map.containsKey(key) || map.get(key).compareTo(word) == 0)
            return true;
        return false;
    }

    public static void main(String[] args){
      //String[] dictionary = {"deer", "door", "cake", "card", "hello"};
      String[] dictionary = {"a", "a"};
      ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
      System.out.println(vwa.isUnique("dear"));
      System.out.println(vwa.isUnique("cart"));
      System.out.println(vwa.isUnique("cane"));
      System.out.println(vwa.isUnique("make"));
      System.out.println(vwa.isUnique("a"));
    }
}
// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");