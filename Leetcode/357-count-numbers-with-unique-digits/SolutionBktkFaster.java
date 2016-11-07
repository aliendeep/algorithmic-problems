// Alternative 2: backtracking
public class Solution {
    long[] uniqueCnt;
    int cnt;

    public void bktk(int n, int lev, List<Integer> cur, boolean[] taken){
        uniqueCnt[lev]++;
        
        if(lev == n)
            return;
            
        for(int i=0; i<=9; ++i){
            // Cant's start with 0 if multiple digits
            if(taken[i] || (lev > 0 && cur.get(0) == 0))
                continue;
            taken[i] = true;
            cur.add(i);
            bktk(n, lev+1, cur, taken);
            cur.remove(cur.size()-1);
            taken[i] = false;
        }
    }
    
    public void compute(int n){    
        uniqueCnt = new long[n+1]; 
        boolean[] taken = new boolean[10];
        List<Integer> cur = new ArrayList<>();
        bktk(n, 0, cur, taken);
    }

    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0)  return 1;
        if(n > 10)  return countNumbersWithUniqueDigits(10);
        compute(n);
        int r = 0;
        for(int i=1; i<=n; i++)
            r += uniqueCnt[i];
        return r;
    }
}