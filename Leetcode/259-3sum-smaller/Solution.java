/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n^2) runtime?
*/
// O(n^2)
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        // Need to find three different indices
        int n = nums.length;
        // Sort the array
        Arrays.sort(nums);
        int cnt = 0;
        for(int i=0; i<n-2; i++){
            cnt += twoSumSmaller(nums, i+1, target - nums[i]);
        }
        return cnt;
    }
    
    public int twoSumSmaller(int[] nums, int startIndex, int target){
        int cnt = 0;
        int start = startIndex;
        int end = nums.length - 1;
        while(start < end){
            if(nums[start] + nums[end] < target){
                cnt += end - start;
                start++;
            }
            else
                end--;
        }
        return cnt;
    }
}

class Solution2 {
    public int threeSumSmaller(int[] nums, int target) {
        // Need to find three different indices
        int n = nums.length;
        // Sort the array
        Arrays.sort(nums);
        int cnt = 0;
        for(int i=0; i<n-2; i++){
            int mid = i+1;
            int end = n-1;
            while(mid < end){
                if(nums[i] + nums[mid] + nums[end] < target){
                    cnt += end - mid;
                    mid++;
                }
                // sum >= target
                else{
                    end--;    
                }
            }
        }
        return cnt;
    }
}