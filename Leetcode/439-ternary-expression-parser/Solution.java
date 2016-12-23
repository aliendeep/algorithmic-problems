/*
Given a string representing arbitrarily nested ternary expressions, calculate the 
result of the expression. You can always assume that the given expression is valid 
and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, 
it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, 
it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
*/
          
public class Solution {
    int start;
    int n;
    public String parse(String expression) {
        if(start + 1 < n && expression.charAt(start + 1) == '?'){
            char c = expression.charAt(start);
            start += 2;
            String trueResult = parse(expression);
            start++;
            String falseResult = parse(expression);
            return c == 'T' ? trueResult : falseResult;
        }
        else{
            start += 1;
            return expression.substring(start-1, start);
        }
    }
    
    public String parseTernary(String expression) {
        n = expression.length();
        start = 0;
        return parse(expression);
    }
}

// Stack
class Solution2 {
    public String parseTernary(String expression) {
        Deque<Character> stk = new LinkedList<>();
        
        for(int i=expression.length()-1; i>=0; i--){
            char c = expression.charAt(i);
            // If this character is either T or F 
            if(!stk.isEmpty() && stk.peekFirst() == '?'){
                // pop ?
                stk.pop();
                char a = stk.pop();
                // pop :
                stk.pop();
                char b = stk.pop();
                
                if(c == 'T')
                    stk.push(a);
                else
                    stk.push(b);
            }    
            else{
                stk.push(c);
            }
        }
        return String.valueOf(stk.pop());
    }
}


class Solution3 {
    public String parseTernary(String expression) {
        Deque<String> stk = new LinkedList<>();
        
        int n = expression.length();
        int i = n-1;
        StringBuilder str = new StringBuilder();

        while(i >= 0){
            char c = expression.charAt(i);
            if(c == ':'){
                if(str.length() > 0){
                    stk.addFirst(str.toString());
                }
                str.setLength(0);
            }    
            else if(c == '?'){
                if(str.length() > 0){
                    stk.addFirst(str.toString());
                    str.setLength(0);
                }
                i--;
                c = expression.charAt(i);
                String a = stk.removeFirst();
                String b = stk.removeFirst();
                if(c == 'T'){
                    stk.addFirst(a);
                }
                else{
                    stk.addFirst(b);
                }
            }
            else{
                str.setLength(0);
                str.append(c);
            }
            i--;
        }
        return stk.removeFirst();
    }
}
