public class Solution {
    private static final int SUBSEQ_LEN = 10, kBase = 4, kMod = 7919;
    
    public static int ACGT(char c){
        int r = -1;
        switch(c){
            case 'A': 
                r = 0;
                break;
            case 'C': 
                r  = 1;
                break;
            case 'G':
                r = 2;
                break;
            case 'T': 
                r = 3;
                break;
            default:
                r = -1;
        }
        return r;
    }
    // Rabin Karp
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> r = new ArrayList<String>();
        int n = s.length();
        if(n < SUBSEQ_LEN)
            return r;
        
        HashMap<Integer, ArrayList<String>> charMap = new HashMap<Integer, ArrayList<String>>();
        int sHash = 0;
        int power = 1;
        // first 10 characters
        for(int i=0; i < SUBSEQ_LEN; i++){
            power = (i > 0) ? (power*kBase) % kMod : 1;
            sHash = (sHash*kBase + ACGT(s.charAt(i)))%kMod;     
        }
        ArrayList<String> aList = new ArrayList<String>();
        aList.add(s.substring(0, SUBSEQ_LEN));
        charMap.put(sHash, aList);

        Set<String> set_result = new HashSet<String>();
        for(int i=SUBSEQ_LEN; i<n; i++){
            sHash -= (ACGT(s.charAt(i-SUBSEQ_LEN))*power) % kMod;
            if(sHash < 0)
                sHash += kMod;
            sHash = (sHash*kBase + ACGT(s.charAt(i))) % kMod;
            String substr = s.substring(i-SUBSEQ_LEN+1, i+1);
            if(charMap.containsKey(sHash) == true){
                ArrayList<String> cur = charMap.get(sHash);
                boolean flag = false;
                for(String str : cur){
                    if(substr.equals(str)){
                        set_result.add(substr);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    // update the hashmap
                    cur.add(substr);
                    charMap.put(sHash, cur);
                }
            }                        
            else{
                ArrayList<String> t = new ArrayList<String>();
                t.add(substr);
                charMap.put(sHash, t);
            }
        }
        r.addAll(set_result);
        return r;
    }
}