/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


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
public class Solution {
    // Divide & Conquer
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