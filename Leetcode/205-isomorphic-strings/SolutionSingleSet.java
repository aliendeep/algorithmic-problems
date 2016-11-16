public class Solution {
    // Alternative: three set
    public boolean isIsomorphic(String s, String t) {
        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();
        Set<String> ss = new HashSet<>();
        int n = s.length();
        for(int i=0; i<n; ++i){
            s1.add(s.charAt(i));
            s2.add(t.charAt(i));
            ss.add(s.charAt(i) + " " + t.charAt(i));
       }
       return (s1.size() == s2.size()) && (ss.size() == s1.size());
    }
}