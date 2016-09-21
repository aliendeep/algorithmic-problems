public class Solution {
    // Rabin Karp
    // O(m + n)
    final static int kBase = 26;
    final static int kMod = 997;

    public int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length())
            return -1;

        int hayHash = 0;
        int needleHash = 0;
        int power = 1;
        for(int i=0; i<needle.length(); i++){
            power = (i == 0) ? 1 : power*kBase % kMod;
            hayHash = (hayHash*kBase + haystack.charAt(i)) % kMod;
            needleHash = (needleHash*kBase + needle.charAt(i)) % kMod;
        }
        
            
        for(int i=needle.length(); i<haystack.length(); i++){
            String x = haystack.substring(i - needle.length(), i);
            if(hayHash == needleHash && x.compareTo(needle) == 0)
                return i - needle.length();
            
            // Removing the i - needle.size() th char
            hayHash -= (haystack.charAt(i - needle.length())*power) % kMod;
            if(hayHash < 0){
                hayHash += kMod;    
            }
            // Addding the ith char
            hayHash = (hayHash*kBase + haystack.charAt(i)) % kMod;
        }
        // last string
        String x = haystack.substring(haystack.length() - needle.length());
        if(hayHash == needleHash && x.compareTo(needle) == 0)
            return haystack.length() - needle.length();
        return -1;
    }
}