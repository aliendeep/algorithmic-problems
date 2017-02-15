/*
Follow up for H-Index: What if the citations array is sorted in ascending order? 
Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/

public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0;
        int high = n;
        while(low < high){
            int mid = (low + high)/2;
            // There are citations[mid] papers that have at least citations[mid] citations.
            if(citations[mid] == n - mid)
                return n - mid;
            else if(citations[mid] < n - mid)
                low = mid + 1;
            else
                high = mid;
        }
        return n - low;
    }        
}
