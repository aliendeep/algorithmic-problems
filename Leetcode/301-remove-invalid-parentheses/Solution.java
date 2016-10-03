/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

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