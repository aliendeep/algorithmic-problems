/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
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