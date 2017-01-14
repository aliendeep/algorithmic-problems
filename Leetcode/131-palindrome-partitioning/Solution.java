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
// Backtracking
public class Solution {
    String s;
    List<List<String>> r;
    int n;
    boolean isPalindrome(String str){
        for(int i=0, j=str.length()-1; i<j; i++, j--){
            if(str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }
    
    public void helper(int lev, List<String> cur){
        if(lev == n){
            r.add(new ArrayList<>(cur));
            return;
        }
        for(int l=lev+1; l<=n; ++l){
            String first = s.substring(lev, l);
            if(isPalindrome(first)){
                cur.add(first);
                helper(l, cur);
                cur.remove(cur.size()-1);
            }
        }
    }

    public List<List<String>> partition(String str) {
        s = str;
        n = s.length();
        r = new ArrayList<>();
        helper(0, new ArrayList<>());
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

        for(int k=i+1; k<=j; ++k){
            String first = s.substring(i, k);
            if(isPalindrome(first)){
                if(k == j){
                    List<String> temp = new ArrayList<>();
                    temp.add(first);
                    result.add(temp);
                }
                else{
                    List<List<String>> rest = helper(k, j);
                    for(List<String> t : rest){
                        List<String> temp = new ArrayList<>();
                        temp.add(first);
                        temp.addAll(t);
                        result.add(temp);
                    }
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
