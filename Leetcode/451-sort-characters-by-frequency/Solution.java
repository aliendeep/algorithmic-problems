// Given a string, sort it in decreasing order based on the frequency of characters. For example: `tree` -> `eert` or `eetr`

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