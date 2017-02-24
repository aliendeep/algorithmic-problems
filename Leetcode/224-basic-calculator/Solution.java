/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + 
or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.

Input:
"1 + 1"
" 2-1 + 2 "
"(1+(4+5+2)-3)+(6+8)" 
*/

public class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new LinkedList<>();
        int sign = 1;
        int num = 0;
        int result = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }
            else{
                result += sign * num;
                // reset the number
                num = 0;
                if(c == '+')
                    sign = 1;
                else if(c == '-')
                    sign = -1;
                else if(c == '('){
                    stk.addFirst(result);
                    stk.addFirst(sign);
                    // reset the sign
                    result = 0;
                    sign = 1;
                }
                else if(c == ')' && stk.size() > 1){
                    int op = stk.removeFirst();
                    int n = stk.removeFirst();
                    result = op*result + n;
                }
            }
        }
        result += sign*num;
        return result;
    }
}

// Slightly different
class Solution2 {
    public int calculate(String s) {
        // store the signs
        Deque<Integer> stk = new LinkedList<>();       
        int sign = 1;
        stk.push(sign);
        
        int n = s.length();
        int num = 0;
        int r = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }
            else if(c == '+' || c == '-'){
                r += sign*num;
                num = 0;
                sign = stk.peekFirst()*(c == '+' ? 1 : -1);
            }
            else if(c == '('){
                stk.push(sign);
            }
            else if(c == ')'){
                stk.pop();
            }
        }
        r += sign*num;
        return r;
    }
}

// Recursive Solution
class Solution3 {
    // Recursive
    int i;
    int n;
    public int eval(String s){
        int sign = 1;
        int result = 0;
        while(i < n && s.charAt(i) != ')'){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = 0;
                while(i < n && Character.isDigit(s.charAt(i))){
                    num = num*10 + s.charAt(i) - '0';
                    ++i;
                }
                result += sign * num;
                // reset sign
                sign = 1;
            }
            else{ 
                if(c == '+' || c == '-' || c == ' '){
                    sign = (c == '-') ? -1 : 1; 
                    ++i;
                }
                else if(c == '('){
                    // skip (
                    ++i;
                    result += sign*eval(s);
                    // reset sign
                    sign = 1;
                }
            }
        }
        // skip the last ) if there is any
        ++i;
        return result;
    }
    
    public int calculate(String s) {
        i = 0;
        n = s.length();
        return eval(s);
    }
}
