/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements 
are subset of nums2. Find all the next greater numbers for nums1's elements in 
the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its 
right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for 
    it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second 
    array is 3.
    For number 2 in the first array, there is no next greater number for it in the 
    second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second 
    array is 3.
    For number 4 in the first array, there is no next greater number for it in the 
    second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/

public class Solution {
    // O(n)
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int n = nums.length;
        int m = findNums.length;
        if(n == 0 || m == 0)      return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        
        // store indices
        Deque<Integer> stk = new LinkedList<>();
        stk.push(0);
        for(int i=1; i<nums.length; ++i){
            int num = nums[i];
            if(!stk.isEmpty()){
                int index = stk.peekFirst();
                // current number is greater than stack top
                while(nums[index] < num){
                    stk.pop();
                    map.put(nums[index], num);
                    if(stk.isEmpty())   break;
                    index = stk.peekFirst();
                }
            }
            stk.push(i);
        }
        
        // Construct result
        int[] r = new int[m];
        int i = 0;
        for(int num : findNums){
            if(!map.containsKey(num)){
                r[i++] = -1;
            }
            else{
                r[i++] = map.get(num);
            }
        }
        return r;    
    }        
}

// O(n^2)
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int n = nums.length;
        int m = findNums.length;
        if(n == 0 || m == 0)      return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n-1; ++i){
            for(int j=i+1; j<n; ++j){
                if(nums[j] > nums[i]){
                    map.put(nums[i], nums[j]);
                    break;
                }
            }
        }
        int[] r = new int[m];
        int i = 0;
        for(int num : findNums){
            if(!map.containsKey(num)){
                r[i++] = -1;
            }
            else{
                r[i++] = map.get(num);
            }
        }
        return r;
    }
}
