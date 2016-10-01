public class Solution {
    // Binary trick
    // logb(n) = log(n) / log(b)
    public static double getLog2(int n){
        return Math.log(n) / Math.log(2);
    }
    // No of subsets = 2^n
    // Get the lowest set bit, s = n & ~(n-1)
    // Say, s = 2^4 = 16 = (Binary 10000)
    // Get the index of the lowest set bit = log2(s) = log2(16) = log2(2^4) = 4
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int nSubsets = (1<<nums.length);
        for(int i=0; i<nSubsets; i++){
            int n = i;
            
            List<Integer> t = new ArrayList<Integer>(); 
            while(n != 0){
                // get the lowest set bit
                int lowestSetBit = n & ~(n-1);
                int index = (int)getLog2(lowestSetBit);
                t.add(nums[index]);
                // clear last set bit
                n = n & (n-1);
            }
            result.add(t);
        }
        return result;
    }
}