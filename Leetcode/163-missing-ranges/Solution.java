/*
Given a sorted integer array where the range of elements are in the inclusive range 
[lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", 
"4->49", "51->74", "76->99"].
*/

/*
Sample Input:
[0,1,3,50,75]
0
99
[0,1,3,50,75]
0
[-2147483648,-2147483648,0,2147483647,2147483647]
-2147483648
2147483647
99
[-1]
-1
-1

Sample Output:
["2","4->49","51->74","76->99"]
["2","4->49","51->74","76->99"]
["-2147483647->-1","1->2147483646"]
[]
*/

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> r = new ArrayList<>();
        int n = nums.length;
        long start = lower;
        long next = -1;
        for(int i=0; i<n; ++i){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            long num = (long)nums[i];
            if(num == start){
                start++;
                continue;
            }
            next = num - 1;   
            StringBuilder t = new StringBuilder();
            if(start < next){
                t.append(start).append("->").append(next);
            }
            else if(start < upper){
                t.append(start);
            }
            if(t.length() > 0)
                r.add(t.toString());
            start = num + 1;
        }
        // last window
        StringBuilder t = new StringBuilder();
        if(start == upper)
            t.append(upper);
        else if(start < upper){
            t.append(start).append("->").append(upper);
        }
        if(t.length() > 0) 
            r.add(t.toString());
        return r;
    }
}

class Solution2 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> r = new ArrayList<>();
        long l = lower;
        for(int i=0; i<nums.length; ++i){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            long a = nums[i];
            long less = a - 1;
            StringBuilder t = new StringBuilder();
            if(l == less){  
                t.append(l);
            }
            else if(l < less){
                t.append(l).append("->").append(less);
            }
            if(t.length() > 0) 
                r.add(t.toString());
            l = a + 1;
        }     
        // last window
        StringBuilder t = new StringBuilder();
        if(l == upper)
            t.append(l);
        else if(l < upper){
            t.append(l).append("->").append(upper);
        }
        if(t.length() > 0) 
            r.add(t.toString());
        return r;
    }
}
