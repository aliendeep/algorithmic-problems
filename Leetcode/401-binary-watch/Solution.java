/*
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 
6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are 
currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, 
it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for
example "10:2" is not valid, it should be "10:02".
*/
import java.util.*;

class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> r = new ArrayList<>();
        for(int h=0; h<12; ++h){
            for(int m=0; m<60; ++m){
                if(Integer.bitCount(h) + Integer.bitCount(m) == num){
                   r.add(String.format("%d:%02d", h, m)); 
                }  
            }
        }
        return r;
    }
}

class Solution2 {
    // cur contains indices
    // 0 - 5 mintues
    // 6 - 9 hours
    void bktk(int n, int prev_i, List<Integer> cur, List<String> result){
        if(cur.size() == n){
            int h = 0;
            int m = 0;
            for(int i=0; i<cur.size(); i++){
                if(cur.get(i) < 6)   
                    m |= (1<<cur.get(i));
                else
                    h |= (1<<(cur.get(i)-6));
            }
            
            if(m < 60 && h < 12){
                result.add(String.format("%d:%02d", h, m)); 
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
