/*
Sample Input:
[1,2,2,5,3,5]
Sample Output:
2
*/
public class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length == 1)        return nums[0];
        if(nums.length == 2)        return Math.max(nums[0], nums[1]);
        
        int a = Integer.MIN_VALUE, b = Integer.MIN_VALUE, c = Integer.MIN_VALUE;
        int cnt = 0;
        boolean flag = false;

        for(int n : nums){
            if(n == Integer.MIN_VALUE)
                flag = true;
            if(a == Integer.MIN_VALUE){
                a = n;
            }
            else if(a < n){
                c = b;
                b = a;
                a = n;
            }
            // a >= n
            else if(a > n && b < n){
                c = b;
                b = n;
            }
            // a >= n && b >= n
            else if(b > n && c < n){
                c = n;
            }
        }
        return !flag && c == Integer.MIN_VALUE ? a : c;
    }
}