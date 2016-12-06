/*
Given a string of numbers and operators, return all possible results from computing 
all the different possible ways to group numbers and operators. 
The valid operators are +, - and *.

Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/
// Recursive
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            // c is operator
            if(Character.isDigit(c) == false){
                List<Integer> leftResult = diffWaysToCompute(input.substring(0, i));
                // Skip the operator sign
                List<Integer> rightResult = diffWaysToCompute(input.substring(i+1));
                for(int l : leftResult){
                    for(int r : rightResult){
                        if(c == '+'){
                            result.add(l + r);
                        }
                        else if(c == '-'){
                            result.add(l - r);
                        }
                        else{
                            result.add(l * r);
                        }
                    }
                }
            }
        }
        if(result.size() == 0)
            result.add(Integer.parseInt(input));
        return result;
    }
}

class Solution2 {
    //Memoization
    Map<String, List<Integer>> dp;
    
    public List<Integer> compute(String input){
        if(input.length() == 0)
            return new ArrayList<>();
        
        try{
            int number = Integer.parseInt(input);
            List<Integer> t = new ArrayList<>();
            t.add(number);
            return t;
        }
        catch(NumberFormatException ex){
            
        }

        if(dp.containsKey(input))
            return dp.get(input);
            
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<input.length(); ++i){
            char c = input.charAt(i);
            // operator
            if(!Character.isDigit(c)){
                List<Integer> left = compute(input.substring(0, i));
                // skip the operator
                List<Integer> right = compute(input.substring(i+1));
                for(int l : left){
                    for(int r : right){
                        if(c == '+'){
                            result.add(l+r);    
                        }
                        else if(c == '-'){
                            result.add(l-r);    
                        }
                        else if(c == '*'){
                            result.add(l*r);    
                        }
                    }
                }
           }
        }
        dp.put(input, result);
        return result;
    }
    
    public List<Integer> diffWaysToCompute(String input) {
        dp = new HashMap<>();
        return compute(input);
    }
}
