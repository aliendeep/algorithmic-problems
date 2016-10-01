/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and 
each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

public class Solution {
    public void bktk(int k, int target, int prev_i, int sum, List<Integer> cur, List<List<Integer>> result ){
        //  k numbers
        if(cur.size() == k){
            if(sum == target){
                // add to the result set
                result.add(new ArrayList<Integer>(cur));
            }
            return;
        }
        
        // prune early if sum of the numbers is greater than target
        if(sum > target)
            return;
            
        for(int i=prev_i; i<=9; i++){
            sum += i;
            cur.add(i);

            bktk(k, target, i+1, sum, cur, result);
            
            // undo
            sum -= i;
            cur.remove(cur.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();

        bktk(k, n, 1, 0, cur, result);
        return result;
        
    }
}