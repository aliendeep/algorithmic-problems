/*
You have n super washing machines on a line. Initially, each washing machine has 
some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one 
dress of each washing machine to one of its adjacent washing machines at the same 
time .

Given an integer array representing the number of dresses in each washing machine 
from left to right on the line, you should find the minimum number of moves to 
make all the washing machines have the same number of dresses. If it is not 
possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation: 
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
3rd move:    2     1 <-- 3    =>    2     2     2   
Example2

Input: [0,3,0]

Output: 2

Explanation: 
1st move:    0 <-- 3     0    =>    1     2     0    
2nd move:    1     2 --> 0    =>    1     1     1     
Example3

Input: [0,2,0]

Output: -1

Explanation: 
It's impossible to make all the three washing machines have the same number of dresses. 
Note:
The range of n is [1, 10000].
The range of dresses number in a super washing machine is [0, 1e5].
*/
public class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        if(n <= 1)  return 0;
        
        int[] cumsum = new int[n+1];
        for(int i=1; i<=n; ++i)
            cumsum[i] = cumsum[i-1] + machines[i-1];
        
        if(cumsum[n] % n != 0)    return -1;

        int avg = cumsum[n]/n;
        int ans = 0;
        int l, r;
        for(int i=0; i<n; ++i){
                // sum of target dresses - given number of dresses
            l = i*avg - cumsum[i];
            r = (n - i - 1)*avg - (cumsum[n] - cumsum[i+1]);
            if(l > 0 && r > 0)
                ans = Math.max(ans, Math.abs(l) + Math.abs(r));
            else
                ans = Math.max(ans, Math.max(Math.abs(l), Math.abs(r)));
        }
        return ans;
    }
}

// Solution 2
public class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        if(n <= 1)  return 0;
        
        int sum = 0;
        for(int i=0; i<n; ++i)
            sum += machines[i];
        
        if(sum % n != 0)    return -1;

        int avg = sum/n;
        int ans = 0;
        int cur = 0;
        for(int load : machines){
            cur += load - avg;
            ans = Math.max(ans, Math.abs(cur));
            ans = Math.max(ans, load - avg);
        }
        return ans;
    }
}
