/*
Find out the maximum sub-array of non negative numbers from an array.
The sub-array should be continuous. That is, a sub-array created by choosing the second and fourth element and skipping the third element is invalid.

Maximum sub-array is defined in terms of the sum of the elements in the sub-array. Sub-array A is greater than sub-array B if sum(A) > sum(B).

Example:

A : [1, 2, 5, -7, 2, 3]
The two sub-arrays are [1, 2, 5] [2, 3].
The answer is [1, 2, 5] as its sum is larger than [2, 3]
NOTE: If there is a tie, then compare with segment's length and return segment which has maximum length
NOTE 2: If there is still a tie, then return the segment with minimum starting index
*/
import java.util.*;

public class MaxNonNegativeSubArray{
  public ArrayList<Integer> maxset(ArrayList<Integer> a) {
      int n = a.size();
      int start = 0;
      int maxLen = 0;
      long curSum = 0;
      long maxSum = Integer.MIN_VALUE;
      int i;
      int resultStart = 0;
      for(i=0; i<n; ++i){
          if(a.get(i) < 0){
             curSum = 0;
             start = i+1;            
          }
          else{
              curSum += a.get(i);
              if(curSum > maxSum){
                  maxLen = i - start + 1;
                  resultStart = start;
                  maxSum = curSum;
              }
              else if(curSum == maxSum){
                  if(maxLen < (i - start + 1)){
                      maxLen = i - start + 1;
                      resultStart = start;
                  }                
              }
          }
      }
      ArrayList<Integer> result = new ArrayList<>();
      for(i=resultStart; i<resultStart+maxLen && i<n; ++i){
          if(a.get(i) < 0){
            return result;
          }
          result.add(a.get(i));
      }
      return result;
  }  
  public static void main(String[] args){
    int[] t = {1, 2, 5, -7, 2, 5};
    //int[] t = {137806862, -982906996, -511702305, -1937477084};
    //int[] t  = {-54961, 3510, -50805, -82137, -39096, -47421};
    ArrayList<Integer> a = new ArrayList<>();
    for(int n : t)
        a.add(n);
    MaxNonNegativeSubArray ob = new MaxNonNegativeSubArray();
    System.out.println(ob.maxset(a));
  }
}