/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/
public class Solution {
    class Info{
        // ascii 
        char code;
        int cnt;
        public Info(char c, int cnt){
            this.code = c;
            this.cnt = cnt;
        }
    }
    public String frequencySort(String str) {
        int n = str.length();
        if(n <= 1)
            return str;
            
        int[] cnt = new int[256];
        for(int i=0; i<n; ++i){
            cnt[(int)str.charAt(i)]++;
        }
        
        List<Info> ob = new ArrayList<>();        
        for(int i=0; i<256; ++i){
            if(cnt[i] == 0) 
                continue;
            ob.add(new Info((char)i, cnt[i]));
        }
        
        Collections.sort(ob, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                // Decreasing order
                // If count is same, then return arbitrarily
                return Integer.compare(b.cnt, a.cnt);
            }
        });
        
        StringBuilder result = new StringBuilder();
        for(int i=0; i<ob.size(); ++i){
            Info t = ob.get(i);
            if(t.cnt == 1){
                result.append(t.code);
            }
            else{
                char[] repeat = new char[t.cnt];
                Arrays.fill(repeat, t.code);
                result.append(repeat);
            }
        }
        return result.toString();
    }
}

// Alternative: Without Sort O(n) Solution using extra memory
class Solution2 {
    public String frequencySort(String s) {
        int n = s.length();
        if(n <= 1)
            return s;
            
        int[] cnt = new int[256];
        for(int i=0; i<n; ++i){
            cnt[(int)s.charAt(i)]++;
        }
        // Max frequency can be at most n
        List<List<Integer>> bucket = new ArrayList<>();
        for(int i=0; i<=n; ++i){
            bucket.add(new ArrayList<>());
        }
        for(int i=0; i<256; ++i){
            if(cnt[i] == 0)
                continue;
            bucket.get(cnt[i]).add(i);
        }
        StringBuilder r = new StringBuilder();
        for(int freq=n; freq>0; --freq){
            if(bucket.get(freq).size() == 0)
                continue;
            List<Integer> chars = bucket.get(freq);
            for(int c : chars){
                char[] repeat = new char[freq];
                Arrays.fill(repeat, (char)c);
                r.append(repeat);
            }   
        }
        return r.toString();
    }
}

class Solution3 {
    // O(n) Solution
    public String frequencySort(String s) {
        int n = s.length();
        if(n <= 1)  return s;
        // Count, characters of frequency of count
        Map<Integer, String> map = new HashMap<>();
        int[] cnt = new int[256];
        for(int i=0; i<n; ++i){
            cnt[(int)s.charAt(i)]++;
        }
        for(int i=0; i<256; ++i){
            int freq = cnt[i];
            if(freq == 0)
                continue;
            StringBuilder cur = new StringBuilder();
            if(map.containsKey(freq)){
                cur.append(map.get(freq));
            }
            char[] fill = new char[freq];
            Arrays.fill(fill, (char)i);
            cur.append(fill);
            map.put(freq, cur.toString()); 
        }
        StringBuilder r = new StringBuilder();
        // max cnt can be n
        for(int freq=n; freq>0; freq--){
            if(!map.containsKey(freq))
                continue;
            r.append(map.get(freq));
        }
        return r.toString();
    }
}
