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