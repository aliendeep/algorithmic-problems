/*
Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]
*/

// Parabola
// if a >= 0, we will get the min value after differentiation. Start from two end points towards the min value. Start from high to low
// if a < 0, e will get the min value after differentiation. Start from low index to the high index

public class Solution {
    public int f(int x, int a, int b, int c){
        return a*x*x + b*x + c;
    }
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        int start = 0;
        int end = n - 1;
        int index = (a >= 0) ? n - 1 : 0;
        
        while(start <= end){
            int p = f(nums[start], a, b, c);
            int q = f(nums[end], a, b, c);
            if(a >= 0){
                if(p >= q){
                    result[index--] = p;
                    start++;
                }
                else{
                    result[index--] = q;
                    end--;
                }
            }
            else{
                if(p <= q){
                    result[index++] = p;
                    start++;
                }
                else{
                    result[index++] = q;
                    end--;
                }
            }
        }
        return result;
    }
}