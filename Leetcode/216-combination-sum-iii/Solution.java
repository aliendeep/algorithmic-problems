/*
Find all possible combinations of k numbers that add up to a number n, given 
that only numbers from 1 to 9 can be used and each combination should be a 
unique set of numbers.

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
    List<List<Integer>> result;
    int target;
    int k;
    public void gen(List<Integer> cur, int start, int curSum){
        if(cur.size() == k){
            if(curSum == target)
                result.add(new ArrayList<>(cur));
            return;
        }
        // prune early if sum of the numbers is greater than target
        if(curSum > target)
            return;
        
        // each number can be used only once
        for(int i=start; i<=9; ++i){
            curSum += i;
            cur.add(i);
            
            gen(cur, i+1, curSum);

            curSum -= i;
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        target = n;
        result = new ArrayList<>();
        gen(new ArrayList<>(), 1, 0);
        return result;
    }
}
