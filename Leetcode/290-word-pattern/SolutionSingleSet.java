public class Solution {
    public boolean wordPattern(String pattern, String str) {
        int n = pattern.length();
        String[] s = str.split(" ");
        if(n != s.length)
            return false;

        Set<Character> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        Set<String> ss = new HashSet<>();
        for(int i=0; i<n; ++i){
            s1.add(pattern.charAt(i));
            s2.add(s[i]);
            ss.add(pattern.charAt(i) + " " + s[i]);
       }
       return (s1.size() == s2.size()) && (ss.size() == s1.size());
   }
}