/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/
import java.util.*;

class BinaryIndexedTree{
  int[] bit;

  public BinaryIndexedTree(int[] a){
    int n = a.length;
    bit = new int[n+1];
  }
  
  public int lsb(int index){
    return index & -index;
  }

  // 1 indexing
  public void addDiff(int index, int diff){
    while(index < bit.length){
      bit[index] += diff;
      index = index + lsb(index);
    }    
  }

  // Get sum of (0..index)
  public int getSum(int index){
    index++;
    int sum = 0;
    while(index > 0){
      sum += bit[index];
      index = index - lsb(index);
    }
    return sum;
  }

  // i: 0 indexing. add the diff
  public void addDifference(int i, int diff){
    addDiff(i+1, diff);
  }
}

public class Solution{
  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    BinaryIndexedTree bit = new BinaryIndexedTree(nums);
    int[] t = nums.clone();
    Arrays.sort(t);

    Integer[] result = new Integer[n];
    for(int i=n-1; i>=0; --i){
      int pos = Arrays.binarySearch(t, nums[i]);
      result[i] = bit.getSum(pos-1);
      bit.addDifference(pos, 1);      
    }
    return Arrays.asList(result);
  }  
}

public class Solution2{
  // Alternative
  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    BinaryIndexedTree bit = new BinaryIndexedTree(nums);
    int[] t = nums.clone();
    Arrays.sort(t);
    
    int[] posArray = new int[n];
    for(int i=0; i<n; i++){
        int pos = Arrays.binarySearch(t, nums[i]);
        bit.addDifference(pos, 1);  
        posArray[i] = pos;
    }
    
    Integer[] result = new Integer[n];
    for(int i=0; i<n; ++i){
      result[i] = bit.getSum(posArray[i]-1);
      bit.addDifference(posArray[i], -1);      
    }
    return Arrays.asList(result);
  }  
}