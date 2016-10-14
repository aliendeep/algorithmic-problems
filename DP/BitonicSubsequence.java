/*
Given an array arr[0 … n-1] containing n positive integers, a subsequence of arr[] is called Bitonic 
if it is first increasing, then decreasing. Write a function that takes an array as argument and 
returns the length of the longest bitonic subsequence.

Link: 
http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
*/

// Variant of LIS
import java.util.*;

// Time complexity: O(n^2)
// Space: O(n)
class BitonicSubsequence{
  public int getLength(int[] a){
    int n = a.length;
    if(n == 0)    return 0;
    // Longest increasing sequence
    int[] lis = new int[n];
    Arrays.fill(lis, 1);

    for(int i=1; i<n; i++){
      for(int j=0; j<i; j++){
        if(a[i] > a[j]){
          lis[i] = Math.max(lis[i], lis[j] + 1);
        }
      }
    }
    // Longest decreasing sequence
    int[] lds = new int[n];
    Arrays.fill(lds, 1);

    for(int i=n-2; i>=0; i--){
      for(int j=n-1; j>i; j--){
        if(a[i] > a[j]){
          lds[i] = Math.max(lds[i], lds[j] + 1);
        }
      }
    }

    // Find the maximum
    int maxLength = lis[0] + lds[0] - 1;
    for(int i=1; i<n; i++){
      if(maxLength < lis[i] + lds[i] - 1)
        maxLength = lis[i] + lds[i] - 1;
    }
    return maxLength;
  }

   public static void main(String args[]){
        BitonicSubsequence ob = new BitonicSubsequence();
        int[] a = {1, 11, 2, 10, 4, 5, 2, 1};
        int r = ob.getLength(a);
        System.out.println(r);
    }  
}