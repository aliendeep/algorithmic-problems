/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.
*/
public class SolutionBinarySearch {
    // Alternative: O(1) Space
    public int binarySearch(int[] a, int val){
        // Count number of elements smaller than val in the array
        int low = 0;
        int high = a.length - 1;
        
        while(high - low > 3){
            int mid = (low + high)/2;
            if(a[mid] > val){
                high = mid - 1;
            }
            else{
                low = mid; 
            }
        }
        int i;
        for(i=low; i<=high; ++i){
            if(a[i] > val)
                return i;
        }
        return i;
    }
    // Number of element smaller than val in the matrix
    public int countSmaller(int[][] matrix, int val){
        // for all rows
        int cnt = 0;
        for(int r=0; r<matrix.length; ++r){
            cnt += binarySearch(matrix[r], val);
        }
        return cnt;
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while(low < high){
            int mid = (low + high)/2;
            // Number of elements smaller than mid
            int cnt = countSmaller(matrix, mid);
            if(cnt < k)
                low = mid + 1;                
            // cnt >= k
            else
                high = mid;
        }
        return low;
    }

    public static void main(String[] args) {
        SolutionBinarySearch ob = new SolutionBinarySearch();
        int[] a = {1, 2, 3, 4, 11, 56, 344};
        System.out.println("Number of elements smaller than 355 is " + ob.binarySearch(a, 355));    
        int[] b = {1, 2};
        System.out.println("Number of elements smaller than 4 is " + ob.binarySearch(b, 4));    
    }
}
