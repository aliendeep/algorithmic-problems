/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/
public class Solution {
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

class Solution2 {
    String s;
    
    boolean isPalindrome(String str){
        for(int i=0, j=str.length()-1; i<j; i++, j--){
            if(str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }
    
    List<List<String>> helper(int i, int j){
        List<List<String>> result = new ArrayList<>();
        String full = s.substring(i, j);
        if(isPalindrome(full)){
            List<String> temp = new ArrayList<>();
            temp.add(full);
            result.add(temp);
        }
        
        for(int k=i+1; k<j; ++k){
            String first = s.substring(i, k);
            if(isPalindrome(first)){
                List<List<String>> rest = helper(k, j);
                for(List<String> t : rest){
                    List<String> temp = new ArrayList<>();
                    temp.add(first);
                    temp.addAll(t);
                    result.add(temp);
                }
            }
        }
        return result;
    } 
    
    public List<List<String>> partition(String str) {
        s = str;
        int n = s.length();
        List<List<String>> r = new ArrayList<>();
        return helper(0, n);
    }
}

// Adding memoization to Solution2
class Solution3 {
    String s;
    Map<String, List<List<String>>> dp;
    
    boolean isPalindrome(String str){
        for(int i=0, j=str.length()-1; i<j; i++, j--){
            if(str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }
    
    List<List<String>> helper(int i, int j){
        List<List<String>> result = new ArrayList<>();
        
        String full = s.substring(i, j);
        if(dp.containsKey(full))
            return dp.get(full);

        if(isPalindrome(full)){
            List<String> temp = new ArrayList<>();
            temp.add(full);
            result.add(temp);
        }
        
        for(int k=i+1; k<j; ++k){
            String first = s.substring(i, k);
            if(isPalindrome(first)){
                List<List<String>> rest = helper(k, j);
                for(List<String> t : rest){
                    List<String> temp = new ArrayList<>();
                    temp.add(first);
                    temp.addAll(t);
                    result.add(temp);
                }
            }
        }
        dp.put(full, result);
        return result;
    } 
    
    public List<List<String>> partition(String str) {
        s = str;
        int n = s.length();
        dp = new HashMap<>();
        List<List<String>> r = new ArrayList<>();
        return helper(0, n);
    }
}
