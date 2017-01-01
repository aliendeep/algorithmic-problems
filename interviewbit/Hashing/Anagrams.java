/*
Given an array of strings, return all groups of strings that are anagrams. 
Represent a group by a list of integers representing the index in the original 
list. Look at the sample case for clarification.

 Anagram : a word, phrase, or name formed by rearranging the letters of another, 
 such as 'spar', formed from 'rasp' 
 Note: All inputs will be in lower-case. 
Example :

Input : cat dog god tca
Output : [[1, 4], [2, 3]]
cat and tca are anagrams which correspond to index 1 and 4. 
dog and god are another set of anagrams which correspond to index 2 and 3.
The indices are 1 based ( the first element has index 1 instead of index 0).

 Ordering of the result : You should not change the relative ordering of the 
 words / phrases within the group. Within a group containing A[i] and A[j], 
 A[i] comes before A[j] if i < j. 
 */
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
