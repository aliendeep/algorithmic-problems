/*
Given an array of integers A and an integer k, find a contiguous subarray that 
contains the largest sum, subject to a constraint that the sum no larger than k
https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
*/

import java.util.*;

// Time complexity: O(nlogn)
// 1D
public class MaxSumSubarrayNoLargerThanK{
  public static int getMaxSumLessThanK(int[] a, int k){
    // Set of cumulative sum
    TreeSet<Integer> set = new TreeSet<>();
    //  Empty interval with sum zero is a valid answer
    set.add(0);

    int cumSum = 0;
    int r = 0;
    for(int num : a){
      cumSum += num;
      // retrieve from the set is the smallest number in the set such which is bigger than or equal to cumSum − k
      // Function: ceiling(E e) Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
      // Ceiling time complexity: O(logn)
      Integer num_i = set.ceiling(cumSum - k);
      if(num_i != null){
        r = Math.max(r, cumSum - num_i);
      }
      set.add(cumSum);
    }
    return r;
  }

  public static void main(String[] args){
    int[] a = {1, 2, 3, 4, 5, 6};
    System.out.println(getMaxSumLessThanK(a, 9));
  }
}