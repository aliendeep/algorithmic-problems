/*
Given an array of integers and an integer k, find out whether there are two distinct 
indices i and j in the array such that nums[i] = nums[j] and the difference between 
i and j is at most k.
*/
// O(n) time solution
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length <= 1 || k == 0)
            return false;        
        Set<Integer> occurrence = new HashSet<Integer>();
        int start = 0;
        for(int n : nums){
            if(!occurrence.add(n))
                return true;
            if(occurrence.size() > k){
                occurrence.remove(nums[start++]);
            } 
        }
        return false;
    }
}