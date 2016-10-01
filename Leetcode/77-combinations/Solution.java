public class Solution {
    void bktk(int n, int k, int lev, int prev_i, ArrayList<Integer> cur, List<List<Integer>> r){
        if(lev == k){
            r.add(new ArrayList<Integer>(cur));
            return;
        }
        for(int i=prev_i+1; i<=n; i++){
            cur.set(lev, i);
            bktk(n, k, lev+1, i, cur, r);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        ArrayList<Integer> cur = new ArrayList<Integer>(Collections.nCopies(k, -1));
        bktk(n, k, 0, 0, cur, r);
        return r;
    }
}