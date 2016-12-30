/*
Remove the minimum number of invalid parentheses in order to make the input 
string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

// Queue O(n2^n)
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        List<String> result = new ArrayList<>();
        Q.add(s);
        visited.add(s);
        
        boolean found = false;
        while(!Q.isEmpty()){
            String front = Q.remove();    
            if(isValid(front)){
                result.add(front);
                found = true;
            }
            if(found)
                continue;
            // Generate all possible states
            for(int i=0; i<front.length(); i++){
                // Only remove ( or )
                if(front.charAt(i) != '(' && front.charAt(i) != ')')
                    continue;
                // remove ith char
                String rest = front.substring(0, i) + front.substring(i+1);
                if(!visited.contains(rest)){
                    Q.add(rest);
                    visited.add(rest);
                }
            }
        }
        return result;
    }
    
    public boolean isValid(String s){
        int cnt = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(')
                cnt++;
            else if(s.charAt(i) == ')'){
                if(cnt == 0)
                    return false;
                cnt--;
            }
        }
        return cnt == 0;
    }
}

// Recursive Solution
class Solution2 {
    /*
        - Count the maximum number of left paren and right parent to be remove. Keep a counter to keep track of unbalanced 
        parentheses
    */
    // Alternative
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();
        // Maximum number of left and right parenthesis that needed to be removed
        int maxLeft = 0, maxRight = 0;
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            if(c == '('){
                maxLeft++;
            }
            else if(c == ')'){
                if(maxLeft != 0){
                    maxLeft--;
                }
                else{
                    // extra )
                    maxRight++;
                }
            }
        }
        removeHelper(s, 0, set, new StringBuilder(), maxLeft, maxRight, 0);
        return new ArrayList<String>(set);
    }
    
    public void removeHelper(String s, int i, Set<String> set, StringBuilder cur, int removeLeftRem, 
                                int removeRightRem, int openParen){
        if(removeLeftRem < 0 || removeRightRem < 0 || openParen < 0)
            return;
        if(i == s.length()){
            if(openParen == 0 && removeLeftRem == 0 && removeRightRem == 0){
                set.add(cur.toString());
            }
            return;
        }
        char c = s.charAt(i);
        if(c == '('){
            // Don't use (
            removeHelper(s, i+1, set, cur, removeLeftRem-1, removeRightRem, openParen);
            // Use (
            cur.append('(');
            removeHelper(s, i+1, set, cur, removeLeftRem, removeRightRem, openParen + 1);
            cur.deleteCharAt(cur.length()-1);
        }
        else if(c == ')'){
            // Don't use )
            removeHelper(s, i+1, set, cur, removeLeftRem, removeRightRem-1, openParen);
            // Use )
            cur.append(')');
            // decreases the number of openParent cnt
            removeHelper(s, i+1, set, cur, removeLeftRem, removeRightRem, openParen - 1);
            cur.deleteCharAt(cur.length()-1);
        }
        // character
        else{
            cur.append(c);
            removeHelper(s, i+1, set, cur, removeLeftRem, removeRightRem, openParen);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}

// DP
class Solution3 {
    int n;
    String s;
    // Set of optimal strings [i..j]
    List<List<Set<String>>> dp;
    
    public Set<String> compute(int i, int j){
        Set<String> result = new HashSet<>();
        if(i > j){
            result.add("");
            return result;
        }
        // one char
        if(i == j){
            char c = s.charAt(i);
            // if letter
            if(c != '(' && c != ')'){
                StringBuilder t = new StringBuilder();
                t.append(c);
                result.add(t.toString());
            }
            else
                result.add("");
            return result;
        }
        
        if(dp.get(i).get(j) != null)
            return dp.get(i).get(j);
        
        result.add("");
        if(s.charAt(i) == '(' && s.charAt(j) == ')'){
            result.clear();
            Set<String> set = compute(i+1, j-1);
            for(String mid : set){
                StringBuilder t = new StringBuilder();
                t.append("(").append(mid).append(")");
                result.add(t.toString());
            }
        }
        for(int k = i; k < j; ++k){
            Set<String> left = compute(i, k);
            Set<String> right = compute(k+1, j);
            int curLen = result.iterator().next().length();
            int newLen = left.iterator().next().length() + right.iterator().next().length(); 
            if(curLen > newLen)
                continue;
            if(curLen < newLen)
                result.clear();

            for(String l : left){
                for(String r : right){
                    StringBuilder t = new StringBuilder();
                    t.append(l).append(r);
                    result.add(t.toString());
                }
            }
        }
        dp.get(i).set(j, result);
        return result;
    }
    
    public List<String> removeInvalidParentheses(String str) {
        s = str;
        n = s.length();
        dp = new ArrayList<>();
        for(int i=0; i<n; ++i){
            dp.add(new ArrayList<>());
            for(int j=0; j<n; ++j){
                dp.get(i).add(null);
            }
        }
        return new ArrayList<>(compute(0, n-1));
    }
}
