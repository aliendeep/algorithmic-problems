/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.
*/
public class Solution {
    // At most two majority elements can exist
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> r = new ArrayList<>();
        int a = -1, ca = 0;
        int b = -1, cb = 0;        
        for(int n : nums){
            if(a == n)
                ca++;
            else if(b == n)
                cb++;
            else if(ca == 0){
                a = n;
                ca = 1;
            }
            else if(cb == 0){
                b = n;
                cb = 1;
            }
            else{
                ca--;
                cb--;
            }
        }
        // verify 
        ca = 0;
        cb = 0;
        for(int n : nums){
            if(n == a)
                ca++;
            else if(n == b)
                cb++;
        }
        if(ca > nums.length/3)    r.add(a);
        if(cb > nums.length/3)    r.add(b);
        return r;
    }
}