/*
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/

public class Solution {
    // Alternative: Iterative
    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        
        int prev = 1;
        r.add(1);
        for(int i=1; i<n; ++i){
            if(prev*10 <= n){
                prev = prev*10;
            }
            else{
                // If prev = 49, then next digit should be 5
                while(prev == n || prev % 10 == 9){
                    prev /= 10;
                }
                prev++;
            }
            r.add(prev);
        }
        return r;
    }
}