public class Solution {
    /*
        n = 3^x
    =>  log n = x log 3
    =>      x = log n / log 3
    */
    public boolean isPowerOfThree(int n){
        if(n == 0)
            return false;
        double x = Math.round((Math.log10(n) / Math.log10(3)));  
        return (Math.pow(3, x) == n);
    }
}