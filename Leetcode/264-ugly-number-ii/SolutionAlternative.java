public class Solution {
    // Alternative
    public int nthUglyNumber(int n) {
        List<Integer> r = new ArrayList<>();
        r.add(1);
        int i = 0, j = 0, k = 0;
        while(r.size() < n){
            int x = Math.min(r.get(i)*2, r.get(j)*3);
            int y = Math.min(x, r.get(k)*5);
            r.add(y);
            if(y == r.get(i)*2) ++i; 
            if(y == r.get(j)*3) ++j; 
            if(y == r.get(k)*5) ++k; 
        }
        return r.get(n-1);
    }
}