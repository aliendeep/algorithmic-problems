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