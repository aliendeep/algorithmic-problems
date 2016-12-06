/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct.
*/

// O(n) time and O(n) space
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for(int n : nums){
            if(s.contains(n))
                return true;
            s.add(n);
        }
        return false;
    }
}

// O(nlogn) Solution
class Solution2 {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0; i<nums.length; ++i){
            if(nums[i] == nums[i-1])
                return false;
        }
        return true;
    }
}