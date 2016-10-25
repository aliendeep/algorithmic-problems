// Memory Limit Exceeded
import java.util.*;

public class SolutionFollowUp {
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

            l.add(i);
            map.set(c, l);
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