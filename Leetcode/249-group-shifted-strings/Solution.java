/*
Given a string, we can "shift" each of its letter to its successive letter, 
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings 
that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/
import java.util.*;
// Time: O(n*m) where m is the length of the largest string
// Space: O(n)
public class Solution {
    public String getValue(String s){
        int n = s.length();
        StringBuilder hash = new StringBuilder();
        for(int i=1; i<n; i++){
            int key = (s.charAt(i) - s.charAt(i-1) + 26) % 26;
            hash.append(key);
            // Add + in order to distinguish digits (2, 1 vs 21)
            hash.append("+");
        }
        return hash.toString();
    }
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            String key = getValue(s);
            if(map.containsKey(key)){
                map.get(key).add(s);
            }
            else{
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(key, l);        
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args){
        String[] a = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        Solution ob = new Solution();
        System.out.println(ob.groupStrings(a));
    }    
}

class Solution2 {
    public String getValue(String s){
        StringBuilder hash = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            int key = (s.charAt(i) - s.charAt(0) + 26) % 26;
            hash.append(key);
            // Add marker in order to distinguish digits (2, 1 vs 21)
            hash.append("#");
        }
        return hash.toString();
    }
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            String key = getValue(s);
            if(map.containsKey(key)){
                map.get(key).add(s);
            }
            else{
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(key, l);        
            }
        }
        return new ArrayList<>(map.values());
    }
}

class Solution3 {
    public String getValue(String s){
        StringBuilder hash = new StringBuilder();
        for(int i=1; i<s.length(); i++){
            int key = (s.charAt(i) - s.charAt(i-1) + 26) % 26;
            hash.append(key);
            hash.append("#");
        }
        return hash.toString();
    }
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            String key = getValue(s);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
