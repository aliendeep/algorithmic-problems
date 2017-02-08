/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and 
empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Input: 
"0"
"3+2*2"
" 3/2 "
" 3+5 / 2 "
"100000000/1/2/3/4/5/6/7/8/9/10"
"14/3*2"
Output:
0
7
1
5
27
8
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

// Precision Error
// Using two stacks
class Solution2 {
    Deque<Integer> numStk;
    Deque<Character> opStk;
    
    boolean isOperator(char c){
        if(c == ' ')
            return false;
        return (Character.isDigit(c) == false);
    }    
    
    int priority(char c){
        if(c == '+')    return 1;
        else if(c == '-')    return 1;
        else if(c == '*')    return 2;
        else if(c == '/')    return 2;
        // space
        return 0;
    }
    
    void collapse(char operatorCur){
        while(opStk.size() >= 1 && numStk.size() >= 2 && priority(operatorCur) <= priority(opStk.peekFirst())){
            double b = numStk.pop();
            double a = numStk.pop();
            char op = opStk.pop();
            double r = 0;
            if(op == '+')            r = a + b;
            else if(op == '-')       r = a - b;
            else if(op == '*')       r = a * b;
            else if(op == '/')       r = a / b;
            numStk.push((int)r);
        }
    }
    
    int len;
    public double getNextNumber(String s, int start){
        int n = s.length();
        double num = 0;
        len = 0;
        while(start < n && Character.isDigit(s.charAt(start))){
            char c = s.charAt(start);
            num = num*10 + c - '0';
            start++;
            len++;
        }
        return num;
    }
    
    public int calculate(String s) {
        int n = s.length();
        numStk = new LinkedList<>();
        opStk = new LinkedList<>();
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                double num = getNextNumber(s, i);
                numStk.push((int)num);
                // increment i
                i += len - 1;
            }
            else{
                if(isOperator(c)){
                    collapse(c);
                    opStk.push(c);
                }
            }
        }
        // final collapse
        // priority 0
        collapse(' ');
        
        double result = 0;
        if(numStk.size() == 1 && opStk.size() == 0)
            result = numStk.pop();
        return (int)result;
    }
}
