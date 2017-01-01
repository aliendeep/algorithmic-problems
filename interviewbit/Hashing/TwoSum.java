/*
Given an array of integers, find two numbers such that they add up to a specific 
target number.

The function twoSum should return indices of the two numbers such that they add 
up to the target, where index1 < index2. Please note that your returned answers 
(both index1 and index2 ) are not zero-based. 
Put both these numbers in order in an array and return the array from your 
function ( Looking at the function signature will make things clearer ). 
Note that, if no pair exists, return empty list.

If multiple solutions exist, output the one where index2 is minimum. If there 
are multiple solutions with the minimum index2, choose the one with minimum 
index1 out of them.

Input: [2, 7, 11, 15], target=9
Output: index1 = 1, index2 = 2
*/
public class Solution {
  public ArrayList<Integer> twoSum(final List<Integer> nums, int target) {
        ArrayList<Integer> r = new ArrayList<>();
        int n = nums.size();
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        for(int i=0; i<n; i++){
            int diff = target - nums.get(i);
            if(hmap.containsKey(diff)){
                int index = hmap.get(diff);
                if(index != i){
                    r.add(index+1);
                    r.add(i+1);
                    break;
                }
            }
            // left most index
            if(!hmap.containsKey(nums.get(i))){
                hmap.put(nums.get(i), i);
            }
        }
        return r;
  }
}
