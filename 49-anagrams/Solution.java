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
                List<String> l = new ArrayList<>();
                l.add(s);
                mapping.put(key, l);
            }
            else{
                List<String> l = mapping.get(key);
                l.add(s);
                mapping.put(key, l);
            }
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