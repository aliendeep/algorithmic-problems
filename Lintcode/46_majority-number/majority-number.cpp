/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/majority-number
@Language: C++
@Datetime: 15-09-30 21:29
*/

class Solution {
public:
    /**
     * @param nums: A list of integers
     * @return: The majority number
     */
    int majorityNumber(vector<int> nums) {
        // write your code here
        int majIndex = 0;
        int c = 1;
        
        for(int i=0; i<nums.size(); i++){
            if(nums[majIndex] == nums[i]){
                c++;
            }
            else{
                c--;
            }
            if(c == 0){
                // Reset the counter
                majIndex = i;
                c = 1;
            }
        }
        
        return nums[majIndex];
    }
};

