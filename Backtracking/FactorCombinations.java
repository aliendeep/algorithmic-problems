// Write a function that takes an integer n and return all possible combinations of its factors.

public class FactorCombinations {
    public void bktk(int n, int start, List<Integer> cur, List<List<Integer>> r){
        if(n == 1 && cur.size() > 1){
            r.add(new ArrayList<Integer>(cur));
            return;
        }

        for(int i=start; i<=n; i++){
            if(n % i == 0) {
                cur.add(i);
                bktk(n/i, i, cur, r);
                cur.remove(cur.size()-1);
            }
        }
    }
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        bktk(n, 2, cur, r);
        return r;
    }
}