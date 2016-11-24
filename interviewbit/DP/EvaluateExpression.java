/*
Given expression with operands and operators (OR , AND , XOR) , in how many ways can you evaluate the expression to true, by grouping in different ways? Operands are only true and false.
Input:
 string : T|F&T^T here '|' will represent or operator '&' will represent and operator '^' will represent xor operator 'T' will represent true operand 'F' will false 

Output:
 different ways % MOD where MOD = 1003 

Example:
 string : T|F only 1 way (T|F) => T so output will be 1 % MOD = 1 
*/
public class Solution {
    public final long MOD = 1003;

    public long evaluate(String s, boolean result, Map<String, Long> dp){
        int n = s.length();
        if(n == 0)      return 0;
        if(n == 1){      
            if(result == true){
                return s.charAt(0) == 'T' ? 1 : 0;
            }
            else{
                return s.charAt(0) == 'F' ? 1 : 0;
            }
        }
        if(dp.containsKey(result+s))
            return dp.get(result+s);
        
        long cnt = 0;
        for(int i=1; i<n; i+=2){
            String left = s.substring(0, i);            
            String right = s.substring(i+1);            
            long leftTrue = evaluate(left, true, dp);
            long leftFalse = evaluate(left, false, dp);
            long rightTrue = evaluate(right, true, dp);
            long rightFalse = evaluate(right, false, dp);
            long totalWays = (leftTrue + leftFalse)*(rightTrue + rightFalse);
            char c = s.charAt(i);
            long totalTrue = 0;
            if(c == '&'){
                totalTrue = (leftTrue*rightTrue);
            }
            else if(c == '|'){
                totalTrue = (leftTrue*rightFalse + leftFalse*rightTrue + leftTrue*rightTrue);
            }
            // xor
            else{
                totalTrue = (leftTrue*rightFalse + leftFalse*rightTrue);
            }
            
            cnt = (cnt + (result ? totalTrue : totalWays - totalTrue)) % MOD;
        }
        dp.put(result+s, cnt);
        return cnt;
    }
    public int cnttrue(String a) {
      Map<String, Long> dp = new HashMap<>();
      return (int)(evaluate(a, true, dp) % MOD);
    }
    public static void main(String[] args){
        EvaluateExpression ob = new EvaluateExpression();
        System.out.println(ob.cnttrue("F|T^T&F"));
    }  
}
