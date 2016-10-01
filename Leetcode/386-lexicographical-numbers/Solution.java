/*
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/

public class Solution {
    public void recursive(int num, int n, List<Integer> result){
        for(int i=0; i<=9; i++){
            int t = num*10 + i;
            if(t > n)   
                break;
            result.add(t);
            recursive(t, n, result);
        }
    }
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for(int i=1; i<=n && i<=9; i++){
            result.add(i);
            recursive(i, n, result);
        }
        return result;
    }
}