/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/expression-evaluation
@Language: Java
@Datetime: 16-11-25 00:48
*/

import java.util.*;

public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
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

    public List<String> infixToPostFix(String[] infix){
      List<String> r = new ArrayList<>();
      Deque<String> stk = new LinkedList<>();
      for(int i=0; i<infix.length; ++i){
        String c = infix[i];
        if(c.equals("(")){
          stk.push(c);
        }
        else if(c.equals(")")){
          while(!stk.isEmpty() && !stk.peekFirst().equals("(")){
              r.add(stk.pop());
          }
          stk.pop();          
        }
        else if(isOperator(c)){
            while(!stk.isEmpty() && precedence(c) <= precedence(stk.peekFirst()))
                r.add(stk.pop());
            stk.push(c);
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

    public int evaluateExpression(String[] expression) {
        List<String> rpn = infixToPostFix(expression);
        Deque<Integer> stk = new LinkedList<>();
        for(int i=0; i<rpn.size(); ++i){
          String c = rpn.get(i);
          if(!isOperator(c)){
            stk.push(Integer.parseInt(c));  
          }
          // operator
          else{
            int b = stk.pop();
            int a = stk.pop();
            if(c.equals("+"))
                stk.push(a+b);
            else if(c.equals("-"))
                stk.push(a-b);
            else if(c.equals("*"))
                stk.push(a*b);
            else
                stk.push(a/b);
          }
        }
        if(stk.isEmpty())
            return 0;
        return stk.pop();
    }
};