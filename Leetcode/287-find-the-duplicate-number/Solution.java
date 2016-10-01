public class Solution {
    // O(nlogn) Solution
    // Pigeonhole principle
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return -1;
        int low = 1;
        int high = n - 1;
        while(low < high){
            int mid = (high - low)/2 + low;
            int cnt  = 0;
            // Count number of items < mid
            for(int i : nums){
                if(i <= mid)
                    cnt++;
            }
            // duplicate number is on the other side
            if(cnt <= mid){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return low;
    }
}