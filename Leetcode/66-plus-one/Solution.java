public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n-1]++;
        int i;
        for(i=n-1; i>0 && digits[i]==10;i--){
            digits[i] = 0;
            digits[i-1]++;
        }
        if(i == 0 && digits[i] == 10){
            int[] r =  new int[n+1];
            digits[0] = 0;
            System.arraycopy(digits, 0, r, 1, n);
            r[0] = 1;
            return r;
        }
        return digits;
    }
}