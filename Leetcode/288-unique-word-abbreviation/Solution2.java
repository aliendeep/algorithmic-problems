import java.util.*;

public class ValidWordAbbr {
    // abbr, multiple
    Map<String, Boolean> map;
    Set<String> dict;
    
    public String getAbbr(String s){
        int n = s.length();
        if(n <= 2)
            return s;
        return s.charAt(0) + Integer.toString(n-2) + s.charAt(n-1);
    }
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        dict = new HashSet<>();
        for(String s : dictionary){
            dict.add(s);
        }        
        for(String s :dict){
            dict.add(s);
            String abbr = getAbbr(s);
            if(map.containsKey(abbr))
                map.put(abbr, true);
            else
                map.put(abbr, false);
        }
    }

    public boolean isUnique(String word) {
        String a = getAbbr(word);
        if(!map.containsKey(a))
            return true;
        // not multiple occurrence
        if(map.get(a) == false){
            // Same word
            if(dict.contains(word))
                return true;
        }
        return false;
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");