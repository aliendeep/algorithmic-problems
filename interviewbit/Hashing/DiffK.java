/*
Given an array A of integers and another non negative integer k, find if there 
exists 2 indices i and j such that A[i] - A[j] = k, i != j.

Example :

Input :

A : [1 5 3]
k : 2
Output :

1
as 3 - 1 = 2

Return 0 / 1 for this problem.
*/
public class Solution {
  public int diffPossible(final List<Integer> nums, int k) {
        int n = nums.size();
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> r = new ArrayList<>();
        for(int i=0; i<n; i++){
            int num = nums.get(i);
            if(set.contains(num - k) || set.contains(num + k)){
                return 1;
            }
            set.add(num);
        }
        return 0;
  }
}
