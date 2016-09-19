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