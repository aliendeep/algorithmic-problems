/*
Given an array of size n, find the majority element. The majority element is the 
element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist 
in the array.
*/
// O(n) Moore Voting Algorithm
public class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int cnt = 1;
        int x = nums[0];
        int i = 1;
        while(i < n){
            if(nums[i] == x){
                cnt++;
            }
            else{
                cnt--;
                if(cnt == 0){
                    x = nums[i];
                    cnt = 1;
                }
            }
            i++;
        }
        return x;
    }
}

// O(nlogn)
// If the array occurs more than n/2 times, then n/2 is the result
public class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n/2];
    }
}

// HashMap O(n) space and time
public class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.get(num) > n/2)
                return num;
        }
        return -1;
    }
}

public class Solution {
    // Divide & Conquer
    // O(nlogn)
    public int count(int[] nums, int l, int r, int target){
        int cnt = 0;
        for(int i=l; i<=r; ++i){
            if(nums[i] == target)
                cnt++;
        }    
        return cnt;
    }
    
    public int findMajority(int[] nums, int l, int r){
        if(l == r)  return nums[l];
        int mid = l + (r-l)/2;
        int lm = findMajority(nums, l, mid);
        int rm = findMajority(nums, mid+1, r);
        if(lm == rm)    return lm;
        return count(nums, l, r, lm) > count(nums, l, r, rm) ? lm : rm;
    }
    
    public int majorityElement(int[] nums) {
        return findMajority(nums, 0, nums.length-1);        
    }
}
