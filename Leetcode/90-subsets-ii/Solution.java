/*
Given a collection of integers that might contain duplicates, nums, return all 
possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Sample Input:
[1,2,2,2]
Sample Output:
[[],[1],[1,2],[1,2,2],[1,2,2,2],[2],[2,2],[2,2,2]]

Stdout (debug output)
Lev 0 : 1
 Lev 1 : 2
  Lev 2 : 2
   Lev 3 : 2
Lev 0 : 2
  Lev 2 : 2
   Lev 3 : 2
*/
public class Solution {
    public void genSubsets(int[] nums, int lev, List<Integer> cur, List<List<Integer>> r){
        r.add(new ArrayList<>(cur));
        for(int i=lev; i<nums.length; i++){
            // avoid duplicates (Can't pick same number multiple times in same level)
            if(i > lev && nums[i] == nums[i-1])
                continue;
            cur.add(nums[i]);
            genSubsets(nums, i+1, cur, r);
            cur.remove(cur.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        genSubsets(nums, 0, cur, r);
        return r;
    }
}

class Solution2 {
    void print(int lev){
        while(lev-- > 0)
            System.out.print(" ");
    }
    public void genSubsets(int[] nums, int lev, List<Integer> cur, List<List<Integer>> r){
        r.add(new ArrayList<>(cur));
        for(int i=lev; i<nums.length; i++){
            if(i == lev || nums[i] != nums[i-1]){
                print(lev);
                System.out.println("Lev " + lev + " : " + nums[i]);
                cur.add(nums[i]);
                genSubsets(nums, i+1, cur, r);
                cur.remove(cur.size()-1);
            }
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        genSubsets(nums, 0, cur, r);
        return r;
    }
}

class Solution3 {
    void print(int lev){
        while(lev-- > 0)
            System.out.print(" ");
    }
    public void genSubsets(int[] nums, int lev, List<Integer> cur, List<List<Integer>> r){
        r.add(new ArrayList<>(cur));
        
        List<Integer> candidateIndex = new ArrayList<>();
        for(int i=lev; i<nums.length; i++){
            if(candidateIndex.size() > 0 && nums[candidateIndex.get(candidateIndex.size()-1)] == nums[i])
                continue;
            candidateIndex.add(i);
        }
        
        for(int i : candidateIndex){
            cur.add(nums[i]);
            genSubsets(nums, i+1, cur, r);
            cur.remove(cur.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        genSubsets(nums, 0, cur, r);
        return r;
    }
}
