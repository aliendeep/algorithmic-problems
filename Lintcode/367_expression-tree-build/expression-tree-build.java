/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/expression-tree-build
@Language: Java
@Datetime: 16-11-25 00:32
*/

/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
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
    public ExpressionTreeNode buildExpressionTree(List<String> expression) {
        Deque<ExpressionTreeNode> stk = new LinkedList<>();
        for(int i=0; i<expression.size(); ++i){
            String s = expression.get(i);
            if(isOperator(s)){
                ExpressionTreeNode right = stk.pop();
                ExpressionTreeNode left = stk.pop();
                ExpressionTreeNode node = new ExpressionTreeNode(s);
                node.left = left;
                node.right = right;
                stk.push(node);
            }
            else{
                ExpressionTreeNode node = new ExpressionTreeNode(s);
                stk.push(node);
            }
        }
        if(!stk.isEmpty())
            return stk.pop();
        return null;
    }

    public ExpressionTreeNode build(String[] expression) {
        List<String> postfix = convertToRPN(expression);
        return buildExpressionTree(postfix);
    }
}