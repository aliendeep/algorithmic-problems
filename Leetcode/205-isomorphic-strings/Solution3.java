// Easiest
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        if(n == 0)
            return true;
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer i=0; i<n; ++i){
            int a = s.charAt(i);
            int b = t.charAt(i) + 256;

            if(map.put(a, i) != map.put(b, i)){
                return false;
            }
        }        
        return true;
    }
}

// Single Map
public class Solution2 {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        if(n == 0)
            return true;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; ++i){
            int a = s.charAt(i);
            int b = t.charAt(i) + 256;
            
            Integer x = map.put(a, i);
            Integer y = map.put(b, i);
            if(x == null && y == null)
                continue;
            if(x == null || y == null)
                return false;

            if(Integer.compare(x, y) != 0){
                return false;
            }
        }        
        return true;
    }
}

// Single Map (String to Integer)
class Solution3 {
    public boolean isIsomorphic(String s, String t) {
    int n = s.length();
    if(n == 0)
        return true;
    Map<String, Integer> map = new HashMap<>();
    for(int i=0; i<n; ++i){
        StringBuilder p = new StringBuilder();
        p.append(s.charAt(i));
        
        StringBuilder q = new StringBuilder();
        q.append(t.charAt(i));
        q.append(t.charAt(i));
        
        Integer x = map.put(p.toString(), i);
        Integer y = map.put(q.toString(), i);
        if(x == null && y == null)
            continue;
        if(x == null || y == null)
            return false;

        if(Integer.compare(x, y) != 0){
            return false;
        }
    }        
    return true;
    }
}