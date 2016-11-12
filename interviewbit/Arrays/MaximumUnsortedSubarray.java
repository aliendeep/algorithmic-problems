import java.util.*;

public class MaximumUnsortedSubarray{
    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = A.size();
        int left = 0;
        for(left=0; left<n-1; ++left){
            if(A.get(left) > A.get(left+1))
                break;
        }
        if(left == n-1){
            // whole array is sorted
            result.add(-1);
            return result;
        }
        
        int right = n-1;
        for(; right>0; --right){
            if(A.get(right) < A.get(right-1))
                break;
        }
        // Expand if needed
        int max = A.get(left); 
        int min = A.get(left);
        for(int i = left + 1; i <= right; ++i){
            int v = A.get(i);
            if(v > max)
              max = v;
            if(v < min)
              min = v;
        }
        for(int i = 0; i < left; ++i){
            if(A.get(i) > min){  
              left = i;
              break;
            }      
        } 
        for(int i=n-1; i>=right+1; --i){
            if(A.get(i) < max){
              right = i;
              break;
            } 
        }  
      
        result.add(left);
        result.add(right);
        return result;
    }

    public static void main(String[] args){
      MaximumUnsortedSubarray ob = new MaximumUnsortedSubarray();
      //int[] a = {1, 3, 2, 4, 5 };
      int[] a = {3, 3, 4, 5, 5, 9, 11, 13, 14, 15, 15, 16, 15, 20, 16};
      ArrayList<Integer> A = new ArrayList<>();       
      for(int i=0; i<a.length; ++i)
        A.add(a[i]);

      ArrayList<Integer> r = ob.subUnsort(A);
      for(int t : r){
        System.out.println(t);
      }
    }
}