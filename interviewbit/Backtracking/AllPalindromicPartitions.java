/*
Given a string s, partition s such that every string of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["a","a","b"]
    ["aa","b"],
  ]
 Ordering the results in the answer : Entry i will come before Entry j if :
len(Entryi[0]) < len(Entryj[0]) OR
(len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR
*
*
*
(len(Entryi[0]) == len(Entryj[0]) AND … len(Entryi[k] < len(Entryj[k]))
In the given example,
["a", "a", "b"] comes before ["aa", "b"] because len("a") < len("aa")
*/

public class AllPalindromicPartitions {
    boolean isPalindrome(String s){
        for(int i=0, j=s.length()-1; i<j; i++, j--){
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    
    void getPalin(String s, int start, ArrayList<String> cur, ArrayList<ArrayList<String>> r){
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
     
  public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> r = new ArrayList<>();
        ArrayList<String> cur = new ArrayList<>();
        getPalin(s, 0, cur, r);
        return r;
  }
}
