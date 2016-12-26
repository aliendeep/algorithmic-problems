/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        String op = new String("+-*/");
        for(String s : tokens){
            int index = op.indexOf(s);
            if(index == -1){
                stk.push(Integer.parseInt(s));
            }
            else{
                int b = stk.pop();
                int a = stk.pop();
                if(index == 0)      
                    stk.push(a + b);
                if(index == 1)      
                    stk.push(a - b);
                if(index == 2)      
                    stk.push(a * b);
                if(index == 3)      
                    stk.push(a / b);
            }
        }
        return stk.peek();
    }
}

// Recursive (TLE)
public class Solution {
    int index;
    public int eval(String[] tokens) {
        String token = tokens[index];
        try{
           int r = Integer.parseInt(token);
           return r;
        }
        catch(Exception e){
            // operator
            index--;
            int b = eval(tokens);
            index--;
            int a = eval(tokens);
            if(token.charAt(0) == '+')      
                return a + b;
            else if(token.charAt(0) == '-')      
                return a - b;
            else if(token.charAt(0) == '*')      
                return a * b;
            else      
                return a / b;
        }
    }
    
    public int evalRPN(String[] tokens) {
        index = tokens.length - 1;
        return eval(tokens);
    }
}
