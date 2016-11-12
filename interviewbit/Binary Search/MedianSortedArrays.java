/*
There are two sorted arrays A and B of size m and n respectively.

Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).

The overall run time complexity should be O(log (m+n)).

Sample Input

A : [1 4 5]
B : [2 3]

Sample Output

3
*/

// https://www.interviewbit.com/problems/median-of-array/
public class Solution {
  // DO NOT MODIFY BOTH THE LISTS
  public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
      int m = a.size();
      int n = b.size();
      if(m > n)
          return findMedianSortedArrays(b, a);
      
      int ilow = 0;
      int ihigh = m;
        int i, j;
      while(ilow <= ihigh){
            i = (ilow + ihigh)/2;
            j = (m + n + 1)/2 - i;
            if(i > 0 && j < n && a.get(i-1) > b.get(j)){
                ihigh = i-1;
            }
            else if(j > 0 && i < m && b.get(j-1) > a.get(i)){
                ilow = i+1;
            }
            else{
                double median1 = Math.max(i == 0 ? Integer.MIN_VALUE : a.get(i-1), 
                                       j == 0 ? Integer.MIN_VALUE : b.get(j - 1));
                // if odd
                if((m+n) % 2 == 1)
                    return median1;
                double median2 = Math.min(i == m ? Integer.MAX_VALUE : a.get(i), 
                                       j == n ? Integer.MAX_VALUE : b.get(j));
                return (median1 + median2)/2.0;
            }
      }
      return -1;
  }
}
