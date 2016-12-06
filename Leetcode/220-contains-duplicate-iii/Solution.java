/*
Given an array of integers, find out whether there are two distinct indices i and j 
in the array such that the difference between nums[i] and nums[j] is at most t 
and the difference between i and j is at most k.
*/
// TreeSet Solution
// O(nlogk) Solution
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> setNumbers = new TreeSet<Long>();
        int start = 0;
        for(int n : nums){
            // Typecasted to handle overflow. Example: nums[i] = Integer.MAX_VALUE
            // If any number contains such that difference between nums[i] and nums[j] 
            // is at most t, then return true 
            // At any time, number of elements in the set <= k
            long left = (long)n - t;
            // Returns the least element in this set greater than or equal to the 
            // given element, or null if there is no such element.
            Long leastElem = setNumbers.ceiling(left); 
            if(leastElem != null && leastElem <= n)
                return true;
            
            long right = (long)n + t;
            // Returns the greatest element in this set less than or equal to 
            // the given element, or null if there is no such element.
            Long greatestElem = setNumbers.floor(right);
            if(greatestElem != null && greatestElem >= n)
                return true;
                
            setNumbers.add((long)n);
            if(setNumbers.size() > k)
                setNumbers.remove((long)nums[start++]);
        }
        return false;
    }
}

// Alternative: O(n) Solution
class Solution {
    // Bucket Sort
    /*
        Returns true if two numbers in the same bucket or in the neighboring buckets 
    */
    public long getBucketId(long num, long w){
        return num < 0 ? (num+1)/w - 1 : num/w;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0)
            return false;
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long)t + 1;
        for(int i=0; i<n; ++i){
            long m = getBucketId(nums[i], w);
            if(map.containsKey(m))
                return true;
            if(map.containsKey(m - 1) && Math.abs(nums[i] - map.get(m-1)) < w)
                return true;
            if(map.containsKey(m + 1) && Math.abs(nums[i] - map.get(m+1)) < w)
                return true;
            
            map.put(m, (long)nums[i]);
            if(i >= k)
                map.remove(getBucketId(nums[i-k], w));
        }
        return false;
    }
}