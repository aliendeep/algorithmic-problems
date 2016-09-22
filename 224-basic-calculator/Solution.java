public class Solution {
    // Use two stacks
    public int calculate(String s) {
        Deque<Integer> numStk = new LinkedList<>();
        Deque<Integer> opStk = new LinkedList<>();
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
                    numStk.addFirst(result);
                    opStk.addFirst(sign);
                    // reset the sign
                    result = 0;
                    sign = 1;
                }
                else if(c == ')' && opStk.size() > 0){
                    int op = opStk.removeFirst();
                    int n = numStk.removeFirst();
                    result = op*result + n;
                }
            }
        }
        result += sign*num;
        return result;
    }
}