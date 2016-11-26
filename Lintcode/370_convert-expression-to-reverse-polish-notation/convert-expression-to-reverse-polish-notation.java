/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/convert-expression-to-reverse-polish-notation
@Language: Java
@Datetime: 16-11-25 00:30
*/

public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    boolean isOperator(String c){
        int n = precedence(c);
        return (n >= 1 && n <=2);
    }
    
    public int precedence(String c){
        // ( has lowest precedence
        if(c.equals("+"))        return 1;
        if(c.equals("-"))        return 1;
        if(c.equals("*"))        return 2;
        if(c.equals("/"))        return 2;
        return -1;
    }
    
    public ArrayList<String> convertToRPN(String[] expression) {
        Deque<String> stk = new LinkedList<>();
        ArrayList<String> r = new ArrayList<>();
        for(int i=0; i<expression.length; ++i){
            String c = expression[i];
            if(isOperator(c)){
                while(!stk.isEmpty() && precedence(c) <= precedence(stk.peekFirst()))
                    r.add(stk.pop());
                stk.push(c);
            }
            else if(c.equals("(")){
                stk.push(c);
            }
            else if(c.equals(")")){
                while(!stk.isEmpty() && !stk.peekFirst().equals("(")){
                    r.add(stk.pop());
                }
                // pop the )
                stk.pop();
            }
            // number
            else{
                r.add(c);
            }
        }
        
        while(!stk.isEmpty())
            r.add(stk.pop());

        return r;
     }
}