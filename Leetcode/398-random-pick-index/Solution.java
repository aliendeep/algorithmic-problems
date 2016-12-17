/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/
// Sort + Binary Search
public class Solution {
    class Info{
        int num;
        int index;
        public Info(int n, int i){
            num = n;
            index = i;
        }
    }    
    Info[] numIndex;
    int n;
    
    // Sort: O(nlogn)
    public Solution(int[] nums) {
        this.n = nums.length;
        numIndex = new Info[n];
        
        for(int i=0; i<n; ++i){
            numIndex[i] = new Info(nums[i], i);            
        }
        
        // Sort by the num
        Arrays.sort(numIndex, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                return Integer.compare(a.num, b.num);
            }
        });
    }
    
    // O(logn)
    int lowerBound(int target){
        // Find the first index of the target that occurred in the info array
        int l = 0, h = n-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(numIndex[mid].num > target)
                h = mid-1;
            else
                l = mid;
        }
        for(int i=l; i<=h; ++i){
            if(numIndex[i].num == target)
                return i;
        }
        return -1;
    }

    // O(logn)
    int upperBound(int target){
        // Find the last index of the target that occurred in the info array
        int l = 0, h = n-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(numIndex[mid].num > target)
                h = mid-1;
            else
                l = mid;
        }
        for(int i=h; i>=l; --i){
            if(numIndex[i].num == target)
                return i;
        }
        return -1;
    }
    public int pick(int target) {
        Random rand = new Random();
        int lowerIndex = lowerBound(target);
        int upperIndex = upperBound(target);
        int randIndex = lowerIndex + rand.nextInt(upperIndex - lowerIndex + 1);
        return numIndex[randIndex].index;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */


// Reservoir Sampling
class Solution2 {
    public static final int FIXED_NUM = 0;
    Random randomNum;
    int[] a;
    
    public Solution(int[] nums) {
        randomNum = new Random();
        a = nums;
    }
    
    public int pick(int target) {
        int resultIndex = -1;
        int cntTarget = 1;
        for(int i=0; i<a.length; i++){
            if(a[i] == target){
                // Change Index with fixed probability (first element is chosen with probability 1 because cntTarget = 1 intiially)
                if(randomNum.nextInt(cntTarget) == FIXED_NUM)
                    resultIndex = i;
                cntTarget++;
            }
        }
        return resultIndex;
    }
}
