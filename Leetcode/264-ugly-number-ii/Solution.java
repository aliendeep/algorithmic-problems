public class Solution {
    public int nthUglyNumber(int n) {
        if(n <= 1)   return 1;
        Queue<Long> q2 = new LinkedList<Long>();
        Queue<Long> q3 = new LinkedList<Long>();
        Queue<Long> q5 = new LinkedList<Long>();
        q2.add(1L);
        long val = 1;
        for(int i=1; i<=n; i++){
            long v2 = q2.size() > 0 ? q2.peek() : Integer.MAX_VALUE;
            long v3 = q3.size() > 0 ? q3.peek() : Integer.MAX_VALUE;
            long v5 = q5.size() > 0 ? q5.peek() : Integer.MAX_VALUE;
            val = Math.min(v2, Math.min(v3, v5));
            if(val == v2){
                // remove : Retrieves and removes the head (first element) of this list.
                q2.remove();
                q2.add(val*2);
                q3.add(val*3);
            }
            else if(val == v3){
                q3.remove();
                q3.add(val*3);
            }
            else{
                q5.remove();
            }
            q5.add(val*5);
        }
        return (int)val;
    }
}