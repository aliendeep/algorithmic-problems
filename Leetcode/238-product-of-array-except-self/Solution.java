/*
Given an array of n integers where n > 1, nums, return an array output such that 
output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not 
count as extra space for the purpose of space complexity analysis.)
*/

// O(n) time and O(n) space
public class Solution {
    // Simulate with an example [1, 2, 3, 4]
    // left to right product:   [1, 1, 2, 6]
    // right to left product:   [24, 12, 4, 1]
    // result:                  [24, 12, 8, 6]    
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prod = new int[n];
        Arrays.fill(prod, 1);
        
        // left to right pass
        for(int i=1; i<n; i++){
            prod[i] = prod[i-1]*nums[i-1];
        }
        // right to left pass
        int product = 1;
        for(int i=n-2; i>=0; i--){
            product *= nums[i+1];
            prod[i] *= product;
        }
        return prod;
    }
}
