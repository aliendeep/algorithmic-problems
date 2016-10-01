/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

// Time Complexity: O(n)
// Space: O(n)
public class Solution {
    public int maximumGap(int[] nums) {
        if(nums.length < 2)
            return 0;

        int n = nums.length;
        int minNum = nums[0];
        int maxNum = nums[0];
        for(int i=1; i<n; i++){
            minNum = Math.min(minNum, nums[i]);
            maxNum = Math.max(maxNum, nums[i]); 
        }
        
        // To handle cases like: [1,1,1,1]
        if(minNum == maxNum)
            return 0;
            
        // Delete minimum value from all number to make the bucket indexing easier
        for(int i=0; i<n; i++){
            nums[i] -= minNum; 
        }
        
        // The maximum gap must be > (max - min)/(n-1) because of bucket sort
        // ceiling
        int minGap = (maxNum - minNum + n - 2)/(n-1);
        
        // Number of buckets = range / (minGap)  = range / (range/(n-1)) = ~n
        int[] minBucket = new int[n];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        int[] maxBucket = new int[n];
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        
        for(int num : nums){
            int bucketId = num/minGap;
            minBucket[bucketId] = Math.min(minBucket[bucketId], num);
            maxBucket[bucketId] = Math.max(maxBucket[bucketId], num);
       }
       
       int result = minGap;
       int prevMaxValue = 0;
       // for all buckets
       for(int i=0; i<n; i++){
            if(minBucket[i] == Integer.MAX_VALUE)
                continue;
            result = Math.max(result, minBucket[i] - prevMaxValue);
            prevMaxValue = maxBucket[i]; 
       }
       return result;
    }
}