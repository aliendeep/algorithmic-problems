public class FindPermutation {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> findPerm(final String A, int B) {
        ArrayList<Integer> r = new ArrayList<>();
        int n = A.length();
        int decrement = 0;
        for(int i=0; i<n; ++i){
            if(A.charAt(i) == 'D')      decrement++;
        }
        int inc = decrement + 1;
        r.add(inc++);
        for(int i=0; i<n; ++i){
            if(A.charAt(i) == 'D')
                r.add(decrement--);
            else
                r.add(inc++);
        }
        return r;
    }
}
