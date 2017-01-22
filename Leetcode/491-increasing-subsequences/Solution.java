/*
Given an integer array, your task is to find all the different possible increasing 
subsequences of the given array, and the length of an increasing subsequence 
should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
- The length of the given array will not exceed 15.
- The range of integer in the given array is [-100,100].
- The given array may contain duplicates, and two equal integers should also be 
considered as a special case of increasing sequence.
*/
public class Solution {
    int[] nums;
    Map<Integer, List<List<Integer>>> dp;
    
    public List<List<Integer>> getSubsequence(int n){
        List<List<Integer>> r = new ArrayList<>();
        if(n < 0){
            r.add(new ArrayList<>());
            return r;
        }
        if(dp.containsKey(n))
            return dp.get(n);
            
        List<List<Integer>> prev = getSubsequence(n-1);
        r.addAll(prev);
        for(List<Integer> list : prev){
            List<Integer> temp = new ArrayList<>();
            temp.addAll(list);
            if(list.size()  == 0 || list.get(list.size()-1) <= nums[n]){
                temp.add(nums[n]);
                r.add(temp);
            }
        }
        dp.put(n, r);
        return r;
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        dp = new HashMap<>();
        List<List<Integer>> t = getSubsequence(n-1);
        Set<List<Integer>> r = new HashSet<>();
        for(List<Integer> list : t){
            if(list.size() >= 2){
                r.add(list);
            }
        }
        return new ArrayList<>(r);
    }
}
