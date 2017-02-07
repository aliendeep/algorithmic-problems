/*
A group of friends went on holiday and sometimes lent each other money. For example, 
Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi 
ride. We can model each transaction as a tuple (x, y, z) which means person x gave 
person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively 
(0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], 
[2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number 
of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we 
could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person 
#0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/
public class Solution {
    int[] balance;
    int[] dp;
    Map<Integer, Integer> bal = new HashMap<>();
    
    int getMaxZeroGroup(int state){
        if(state == 0)          return 0;        
        if(dp[state] != -1)     return dp[state];
        
        int maxZeroGroups = Integer.MIN_VALUE;
        for(int subMask = 1; subMask <= state; ++subMask){
            // check if the submask has 1 in any other different place than state
            if((subMask & ~state) != 0)     continue;

            // if the balance in this submask is not 0, then continue
            if(balance[subMask] != 0)       continue;

            // the balance of this submask is 0
            // find the 0 subgroup for the rest of the submask
            int cnt = getMaxZeroGroup(state ^ subMask);
            if(cnt < 0)     continue;
            maxZeroGroups = Math.max(maxZeroGroups, cnt + 1);
        }
        dp[state] = maxZeroGroups;
        return maxZeroGroups;
    }
    
    public void updateBalance(int id, int amount){
        Integer balance = bal.get(id);
        if(balance != null){
            balance += amount;
        }
        else{
            balance = amount;
        }
        bal.put(id, balance);
    }
    
    public int minTransfers(int[][] transactions) {
        bal = new HashMap<>();
        for(int[] trans : transactions){
            int a = trans[0];
            int b = trans[1];
            int amount = trans[2];
            updateBalance(a, -amount);
            updateBalance(b, amount);
        }
        
        int[] nums = new int[bal.size()];
        int n = 0;
        for(int value : bal.values()){
            nums[n++] = value;
        }
        int nStates = (1<<n);
    
        // Precalculate balance of all possible state
        balance = new int[nStates];
        for(int mask = 0; mask < nStates; ++mask){
            int total = 0;
            for(int i=0; i<n; ++i){
                if((mask & (1<<i)) == 0) continue;
                total += nums[i];
            }
            balance[mask] = total;
        }
        
        dp = new int[nStates];
        Arrays.fill(dp, -1);
        
        return n - getMaxZeroGroup((1<<n)-1);
    }
}
