/*
You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions 
after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first 
bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether 
version is bad. Implement a function to find the first bad version. You should 
minimize the number of calls to the API.
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(isBadVersion(1) == true)     
            return 1;
        int low = 2;
        int high = n;
        int mid;
        while(low < high){
            mid = low + (high-low)/2;
            if(isBadVersion(mid) == true)       
                high = mid;
            else                        
                low = mid+1;
        }
        return low;        
        
    }
}

// Version 2
class Solution2 extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        while(h - l > 2){
            int mid = l + (h-l)/2;
            if(isBadVersion(mid))       
                h = mid;
            else                        
                l = mid + 1;
        }
        for(int i=l; i<=h; ++i)
            if(isBadVersion(i)) 
                return i;
        return -1;        
    }
}
