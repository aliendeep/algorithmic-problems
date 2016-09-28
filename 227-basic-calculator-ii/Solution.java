/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/
public class Solution {
    boolean isOperator(char c){
        if(c == ' ')
            return false;
        return (Character.isDigit(c) == false);
    }
    public int calculate(String s) {
        int n = s.length();
        if(n == 0)
            return 0;
        
        Deque<Integer> stk = new LinkedList<>();
        int num = 0;
        char sign = '+';
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c))
                num = num*10 + c - '0';
            // operator or last character (notice the if)
            if(isOperator(c) || i == n-1){
                if(sign == '+'){
                    stk.addFirst(num);    
                }
                else if(sign == '-'){
                    stk.addFirst(-num);    
                }
                else if(sign == '*'){
                    int t = stk.removeFirst()*num;
                    stk.addFirst(t);    
                }
                else if(sign == '/'){
                    stk.addFirst(stk.removeFirst() / num);    
                }
                sign = c;
                num = 0;
            }
        }  
        
        int result = 0;
        while(!stk.isEmpty()){
            result += stk.removeFirst();
        }
        return result;
    }
}