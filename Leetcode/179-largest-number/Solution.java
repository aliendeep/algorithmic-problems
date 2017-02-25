/*
Given a list of non negative integers, arrange them such that they form the 
largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of 
an integer.

*/
public class Solution {
    public String largestNumber(int[] nums) {
        Comparator<Integer> comp = new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                String x = Integer.toString(a);    
                String y = Integer.toString(b);
                String t1 = x + y;
                String t2 = y + x;
                int t = t1.compareTo(t2);
                if(t < 0)
                    return 1;
                else if(t == 0)
                    return 0;
                else
                    return -1;
            }
        };
        ArrayList<Integer> x = new ArrayList<Integer>(nums.length);
        for(int n : nums)
            x.add(n);
        Collections.sort(x, comp);
        StringBuffer r = new StringBuffer();
        for(int n : x){
            r.append(n);
        }
        if(r.charAt(0) == '0')
            return "0";
        return r.toString();
    }
}