public class Solution {
    public int findMin(int[] v) {
        int low = 0;
        int high = v.length - 1;
        
        while(high - low > 5){
            int mid = low + (high-low)/2;
            if(v[mid] > v[high])
                low = mid + 1;
            else if(v[mid] < v[high])
                high = mid;
            // A[mid] & A[high] same
            else 
                high = high - 1;
        }
        int r = v[low];
        for(int i=low+1; i<=high; i++)
            r = Math.min(r, v[i]);
        return r;
    }
}