/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
*/
public class Solution {
    // Hashtable
    public String sortString(String a){
        char[] x = a.toCharArray();
        Arrays.sort(x);
        String sortedA = new String(x);
        return sortedA;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        // Sorted version of the string is the key
        Map<String, List<String>> mapping = new HashMap<String, List<String>>();        
        for(String s : strs){
            String key = sortString(s);
            if(!mapping.containsKey(key)){
                mapping.put(key, new ArrayList<>());
            }
            mapping.get(key).add(s);
        }
        List<List<String>> r = new ArrayList<>();
        // Iterate the hashmap
        for(Map.Entry<String, List<String>> entry : mapping.entrySet()){
            List<String> v = entry.getValue();
            r.add(v);
        }
        return r;
    }
}