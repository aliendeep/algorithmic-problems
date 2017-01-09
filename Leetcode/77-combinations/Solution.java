/*
Given two integers n and k, return all possible combinations of k numbers out of 
1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/
public class Solution {
    void bktk(int n, int k, int prev_i, List<Integer> cur, List<List<Integer>> r){
        if(cur.size() == k){
            r.add(new ArrayList<>(cur));
            return;
        }
        for(int i=prev_i+1; i<=n; i++){
            cur.add(i);
            bktk(n, k, i, cur, r);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList();
        bktk(n, k, 0, cur, r);
        return r;
    }
}

// Subset type combinations
class Solution2 {
    List<List<Integer>> r;
    int[] nums;
    void bktk(int lev, int k, List<Integer> cur){
        if(lev == nums.length){
            if(cur.size() == k)
                r.add(new ArrayList<>(cur));
            return;
        }
        // without
        bktk(lev+1, k, cur);
        // with
        cur.add(nums[lev]);
        bktk(lev+1, k, cur);
        cur.remove(cur.size()-1);
    }
    public List<List<Integer>> combine(int n, int k) {
        r = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList();
        nums = new int[n];
        for(int i=0; i<n; ++i)
            nums[i] = i+1;
        bktk(0, k, cur);
        return r;
    }
}
