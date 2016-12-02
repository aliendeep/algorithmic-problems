public class Solution {
    public String sortString(String a){
        char[] x = a.toCharArray();
        Arrays.sort(x);
        String sortedA = new String(x);
        return sortedA;
    }
  public ArrayList<ArrayList<Integer>> anagrams(final List<String> strs) {
        // Sorted version of the string is the key
        Map<String, ArrayList<Integer>> mapping = new HashMap<>();        
        for(int i=0; i<strs.size(); ++i){
            String s = strs.get(i);
            String key = sortString(s);
            if(!mapping.containsKey(key)){
                mapping.put(key, new ArrayList<>());
            }
            mapping.get(key).add(i+1);
        }
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        // Iterate the hashmap
        for(Map.Entry<String, ArrayList<Integer>> entry : mapping.entrySet()){
            ArrayList<Integer> v = entry.getValue();
            r.add(v);
        }
        return r;
    }    
}
