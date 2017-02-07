/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        List<String> r = new ArrayList<>();
        int start = 0;
        int end = 0;
        for(int i=1; i<n; i++){
            if(nums[i] == nums[i-1] + 1)
                end++;
            else{
                // only one element
                if(start == end){
                    StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
                    r.add(s.toString());
                }
                else{
                    StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
                    s.append("->");
                    s.append(Integer.toString(nums[end]));
                    r.add(s.toString());
                }
                start = end+1;
                end = start;
            }
        }
        if(start == end){
            StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
            r.add(s.toString());
        }
        else{
            StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
            s.append("->");
            s.append(Integer.toString(nums[end]));
            r.add(s.toString());
        }
        return r;
    }
}

// Alternative Solution
// https://leetcode.com/articles/summary-ranges/
public class Solution2 {
    public List<String> summaryRanges(int[] nums) {
        List<String> r = new ArrayList<>();
        int n = nums.length; 
        for(int start = 0, end = 0; end < n; ++end){
            if(end+1 < n && nums[end+1] == nums[end] + 1)
                continue;
            // only one element
            if(start == end){
                r.add(nums[start] + "");
            }
            else{
                r.add(nums[start] + "->" + nums[end]);
            }
            // start the next range
            start = end + 1;
        }
        return r;
    }
}

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> r = new ArrayList<>();
        int n = nums.length; 
        for(int start = 0, end = 0; end < n; ++end){
             // try to extend the range [nums[i], nums[j]]
            while(end+1 < n && nums[end+1] == nums[end] + 1){
                end++;
            }
            // only one element
            if(start == end){
                r.add(nums[start] + "");
            }
            else{
                r.add(nums[start] + "->" + nums[end]);
            }
            // start the next range
            start = end + 1;
        }
        return r;
    }
}
