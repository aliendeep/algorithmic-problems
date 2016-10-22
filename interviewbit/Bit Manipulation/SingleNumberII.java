/*
Given an array of integers, every element appears thrice except for one which occurs once.

Find that element which does not appear thrice.

Note: Your algorithm should have a linear runtime complexity.

Could you implement it without using extra memory?

Example :

Input : [1, 2, 4, 3, 3, 2, 2, 3, 1, 1]
Output : 4
*/

public class Solution {
  // DO NOT MODIFY THE LIST
  // O(1) space
  public int singleNumber(final List<Integer> a) {
        int r = 0;
        for(int i=31; i>=0; i--){
            int cnt = 0;
            for(int num : a){
                if((num & (1<<i)) != 0)
                    cnt++;
            }
            
            r = r<<1;
            if(cnt % 3 != 0){
                r |= 1;
            }
        }
        
        return r;
    }
}
