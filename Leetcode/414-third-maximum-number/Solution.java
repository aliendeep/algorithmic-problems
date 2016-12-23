/*
Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
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

// Solution 2
class Solution2 {
    public int thirdMax(int[] nums) {
        if(nums.length == 0)
            return 0;
        TreeSet<Integer> set = new TreeSet<>();
        for(int n : nums){
            // Add, Remove: log3
            set.add(n);
            // remove the smallest number
            if(set.size() > 3)
                set.remove(set.first());
        }
        return set.size() == 3 ? set.first() : set.last();
    }
}
