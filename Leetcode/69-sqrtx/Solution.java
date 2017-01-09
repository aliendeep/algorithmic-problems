/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/
class Solution {
    // Return largest integer whose square is less than or equal to the x 
    public int mySqrt(int x) {
        int low  = 0, high = x/2 + 1;
        while(high - low > 3){
            int mid = (low+high)/2;
            long sqr = (long)mid*mid;
            if(sqr <= x)
                low = mid;
            else
                high = mid - 1;
        }
        for(int i=high; i>=low; --i){
            long sqr = (long)i*i;
            if(sqr <= x)
                return i;
        }
        return -1;
    }
}
