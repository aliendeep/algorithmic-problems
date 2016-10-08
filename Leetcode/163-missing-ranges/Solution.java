/*
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

/*
Sample Input:
[1,1,1]
1
1
[2147483647]
0
2147483647
Sample Output:
[]
["0->2147483646"]
*/
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> r = new ArrayList<>();
        int missingNext = -1;
        // to avoid overflow
        long start = lower;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == start){
                start++;
                continue;
            }
            StringBuilder t = new StringBuilder();
            missingNext = nums[i] - 1;
            if(start < missingNext){
                t.append(start);
                t.append("->");
                t.append(missingNext);
            }
            else if(start < upper){
                t.append(start);
            }
            if(t.length() > 0)
                r.add(t.toString());
            //reset start
            start = (long)nums[i]+1;
        }
        // last window
        if(start == upper){
            StringBuilder t = new StringBuilder();
            t.append(start);
            r.add(t.toString());
        }
        // last window
        else if(start < upper){
            StringBuilder t = new StringBuilder();
            t.append(start);
            t.append("->");
            t.append(upper);
            r.add(t.toString());
        }
        return r;
    }
}