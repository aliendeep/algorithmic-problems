/*
Given an array of integers that is already sorted in ascending order, find two 
numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they 
add up to the target, where index1 must be less than index2. Please note that your 
returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
// O(n)
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i = 0, j = numbers.length-1;
        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(sum == target){
                result[0] = i+1;
                result[1] = j+1;
                break;
            }
            else if(sum < target)   
                i++;
            else    
                j--;
        }
        return result;
    }
}

// O(nlogn) Solution
class Solution {
    // Sorted Array
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] r = new int[2];
        for(int i=0; i<n-1; ++i){
            int index = binarySearch(numbers, i+1, target - numbers[i]);            
            if(index != -1){
                r[0] = i + 1;
                r[1] = index + 1;
                return r;
            }
        }    
        return r;
    }
    
    public int binarySearch(int[] nums, int start, int target){
        int n = nums.length;
        int l = start, h = n-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target){
                h = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        for(int i=l; i<=h; ++i)
            if(nums[i] == target)
                return i;
        return -1;
    }
}
