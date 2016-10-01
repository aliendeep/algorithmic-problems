public class Solution {
    public int integerReplacement(int n) {
        int cnt = 0;
        while(n != 1){
            // even
            if((n & 1) == 0) 
                n >>>= 1;
            else if(n == 3 || (Integer.bitCount(n-1) < Integer.bitCount(n+1)))
                n--;
            else 
                n++;
            cnt++;
        }    
        return cnt;
    }
}