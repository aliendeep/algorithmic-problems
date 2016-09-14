public class Solution {
    // Return largest integer whose square is less than or equal to the x 
    public int mySqrt(int x) {
        int low  = 0, high = x/2+1;
        // loop break when every number less than low has square equal or less than x and square of low is greater than x
        while(low <= high){
            int mid = (high - low)/2 + low;
            long sqr = mid*mid;
            if(sqr == x)
                return mid;
            else if(sqr < x)
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return low-1;
    }
}