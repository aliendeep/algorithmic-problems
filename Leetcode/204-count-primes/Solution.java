public class Solution {
    public int countPrimes(int n) {
        if(n <= 2)  return 0;
    
        boolean[] flag = new boolean[n+1];
        for(int i=3; i*i<=n; i+=2){
            if(flag[i] == false){
                for(int j=i*i; j<=n; j+=2*i){
                    flag[j] = true;
                }
            }
        }
        int cnt = 1;
        for(int i=3; i<n; i+=2)
            if(flag[i] == false)
                cnt++;
        return cnt;
    }
}