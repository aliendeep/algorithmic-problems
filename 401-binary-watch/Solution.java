import java.util.*;

public class Solution {
    // cur contains indices
    // 0 - 5 mintues
    // 6 - 9 hours
    void bktk(int n, int prev_i, List<Integer> cur, List<String> result){
        if(cur.size() == n){
            int hourPart = 0;
            int minPart = 0;
            for(int i=0; i<cur.size(); i++){
                if(cur.get(i) < 6)   
                    minPart |= (1<<cur.get(i));
                else
                    hourPart |= (1<<(cur.get(i)-6));
            }
            
            if(minPart < 60 && hourPart < 12){
                StringBuffer r = new StringBuffer();
                // Add hour part
                r.append(Integer.toString(hourPart));
                // formatted
                String m  = String.format("%02d", minPart);
                r.append(":");
                r.append(m);
                // Add this to result
                result.add(r.toString());
            }
            return;
        }    
        for(int i=prev_i; i<10; i++){
            cur.add(i);
            bktk(n, i+1, cur, result);
            cur.remove(cur.size()-1);
        }
    }
    
    public List<String> readBinaryWatch(int num) {
        int[] taken = new int[10];
        Arrays.fill(taken, 0);
        List<Integer> cur = new ArrayList<>();
        List<String> result = new ArrayList<>();
        bktk(num, 0, cur, result);
        return result;
    }

    public static void main(String[] args){
        Solution s = new Solution();
        List<String> result = s.readBinaryWatch(1);
        for(String r : result)
            System.out.println(r);
    }
}