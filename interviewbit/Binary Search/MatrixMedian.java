/*
Given a N cross M matrix in which each row is sorted, find the overall median 
of the matrix. Assume N*M is odd.

For example,

Matrix=
[1, 3, 5]
[2, 6, 9]
[3, 6, 9]

A = [1, 2, 3, 3, 5, 6, 6, 9, 9]

Median is 5. So, we return 5.
Note: No extra memory is allowed.
*/
import java.util.*;

public class MatrixMedian {
   public int binarySearch(ArrayList<Integer> a, int val){
        // Count number of elements smaller than val in the array
        int low = 0;
        int high = a.size() - 1;
        
        while(high - low > 3){
            int mid = (low + high)/2;
            if(a.get(mid) > val){
                high = mid - 1;
            }
            else{
                low = mid; 
            }
        }
        int i;
        for(i=low; i<=high; ++i){
            if(a.get(i) > val)
                return i;
        }
        return i;
    }
    // Number of element smaller than val in the matrix
    public int countSmaller(ArrayList<ArrayList<Integer>> matrix, int val){
        // for all rows
        int cnt = 0;
        for(int r=0; r<matrix.size(); ++r){
            cnt += binarySearch(matrix.get(r), val);
        }
        return cnt;
    }
    
    public int kthSmallest(ArrayList<ArrayList<Integer>> matrix, int k) {
        int n = matrix.size();
        int m = matrix.get(0).size();

        // Only rows are sorted, columns are not
        long low = 0;
        long high = Integer.MAX_VALUE;
        while(low < high){
            long mid = (high - low)/2 + low;

            // Number of elements smaller than mid
            int cnt = countSmaller(matrix, (int)mid);
 
            if(cnt < k)
                low = mid + 1;                
            // cnt >= k
            else
                high = mid;
        }
        return (int)low;
    }
    
    public void print(ArrayList<ArrayList<Integer>> A){
        for(ArrayList<Integer> x : A){
            for(int t : x){
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }    
    public int findMedian(ArrayList<ArrayList<Integer>> A) {
        //print(A);
        int n = A.size();
        int m = A.get(0).size();
        int k = (n*m)/2 + 1;
        System.out.println("k: " +k);
        return kthSmallest(A, k);    
    }

    public static void main(String[] args) {
        int[][] mat = {{5}, {4}, {3}, {1},
                      {3},
                      {1},
                      {4},
                      {2},
                      {5},
                      {3},
                      {3},
                    };     

        MatrixMedian ob = new MatrixMedian();
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for(int i=0; i<mat.length; ++i){
            a.add(new ArrayList<>());
        }
        for(int i=0; i<mat.length; ++i){
            for(int j=0; j<mat[0].length; ++j){
                a.get(i).add(mat[i][j]);
            }
        }
        System.out.println(ob.findMedian(a));        
    }
}
