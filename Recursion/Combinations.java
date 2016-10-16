// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

public class Combinations {
    void bktk(int n, int k, int prev_i, List<Integer> cur, List<List<Integer>> r){
        if(cur.size() == k){
            r.add(new ArrayList<Integer>(cur));
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