public class Solution {
    // Alternative : Sorting technique
    public String sortString(String a){
        char[] x = a.toCharArray();
        Arrays.sort(x);
        String sortedA = new String(x);
        return sortedA;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String a, String b){
                String sortedA = sortString(a);
                String sortedB = sortString(b);
                return sortedA.compareTo(sortedB);
            }
        });
        
        List<List<String>> r = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        for(String s : strs){
            if(cur.size() == 0)
                cur.add(s);
            else{
                String sortedPrev = sortString(cur.get(cur.size()-1));
                String sortedCur = sortString(s);
                if(sortedPrev.compareTo(sortedCur) != 0){
                    r.add(new ArrayList<>(cur));
                    cur.clear();
                }
                cur.add(s);
            }
        }
        // insert the last entry
        if(cur.size() > 0)
            r.add(new ArrayList<>(cur));
        return r;
    }
}