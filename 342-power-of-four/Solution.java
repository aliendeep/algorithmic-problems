public class Solution {
    public boolean isPowerOfFour(int n) {
        if(n == 0)
            return false;
        double x = Math.round((Math.log10(n) / Math.log10(4)));  
        return (Math.pow(4, x) == n);          
    }
}