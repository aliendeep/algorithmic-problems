/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to 
[5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways 
to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
*/

// Solution 1
// O(1)
public class Solution {
    public void reverse(int[] A, int start, int end){
        for(int i=start, j=end; i<j; i++, j--){
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)
            return;
        k = k % n;
        // reverse the whole array
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
}

// Solution 2
public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] a = new int[n];
        System.arraycopy(nums, 0, a, 0, n);
        for(int i=0; i<n; ++i){
            nums[(i+k) % n] = a[i];
        }
    }
}
