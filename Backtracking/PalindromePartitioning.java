// Given a string s, partition s such that every substring of the partition is a palindrome.

public class PalindromePartitioning {
    boolean isPalindrome(String s){
        for(int i=0, j=s.length()-1; i<j; i++, j--){
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    
    void getPalin(String s, int start, List<String> cur, List<List<String>> r ){
        if(start == s.length()){
            r.add(new ArrayList<>(cur));
            return;
        }
        for(int i=start+1; i<=s.length(); i++){
            // If any prefix of s is a palindrome, then we can recurse on the leftover of the s
            String prefix = s.substring(start, i);
            if(isPalindrome(prefix)){
                cur.add(prefix);
                getPalin(s, i, cur, r);
                cur.remove(cur.size()-1);
            }            
        }
    }
    
    public List<List<String>> partition(String s) {
        List<List<String>> r = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        getPalin(s, 0, cur, r);
        return r;
    }
}