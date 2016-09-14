public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> setNumbers = new TreeSet<Long>();
        int start = 0;
        for(int n : nums){
            // Typecasted to handle overflow. Example: nums[i] = Integer.MAX_VALUE
            // If any number contains such that difference between nums[i] and nums[j] is at most t, then return true 
            // At any time, number of elements in the set <= k
            long left = (long)n - t;
            // Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
            Long leastElem = setNumbers.ceiling(left); 
            if(leastElem != null && leastElem <= n)
                return true;
            
            long right = (long)n + t;
            // Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
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