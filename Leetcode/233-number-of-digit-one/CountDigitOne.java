public class CountDigitOne {
    public int countDigitOne(int n) {
        if(n < 0)
            return 0;
        long startOffset = 1; // 1, 10, 100, 1000
        long endOffset = 2;  // 2, 20, 200, 2000 (exclusive)
        long power = 1;
        long period = 10;
        int cnt = 0;
        while(power <= n){
            long numPeriod = (int)(n / period);
            long remaining = (int)(n % period);
            cnt += numPeriod*power;
            if(remaining < startOffset){
                cnt += 0;                
            }
            else if(remaining >= endOffset){
                cnt += endOffset - startOffset;              
            }
            else{
                cnt += remaining - startOffset + 1;              
            }
            power *= 10;
            period *= 10;
            startOffset *= 10;
            endOffset *= 10;
        }
        return cnt;
    }
}