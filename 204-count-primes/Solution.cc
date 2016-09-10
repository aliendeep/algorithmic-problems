class Solution {
public:
    // https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
    // Optimization j += 2i, as sum of two odd number is even, (j += i)
    int countPrimes(int n) {
        if(n <= 2)  return 0;
        vector<bool> flag(n+1, true);
        // only odd numbers
        // say i = 19, then 1*19, 2*19, .... 18*19, already crossed of, so we can start from 19*19
        for(int i=3; i*i<=n; i+=2){
            if(flag[i]){
                for(int j=i*i; j<=n; j+=2*i){
                    flag[j] = false;       
                }
            }
        }
        
        int cnt = 1;
        for(int i=3; i<n; i+=2)
            if(flag[i]) cnt++;
        return cnt;
    }
};