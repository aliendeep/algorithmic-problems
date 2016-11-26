/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/majority-number-ii
@Language: Java
@Datetime: 16-11-26 03:29
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        int len = nums.size();
        // no, count
        int k = 3;
        Map<Integer, Integer> hash = new HashMap<>();    
        for(int n : nums){
            hash.put(n, hash.containsKey(n) ? hash.get(n) + 1 : 1);
            if(hash.size() == k){
                List<Integer> del = new ArrayList<>();
                // decrement k unique items
                for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
                    int key = entry.getKey();
                    int cnt = entry.getValue();
                    if(cnt - 1 == 0)
                        del.add(key);
                    else{
                        hash.put(key, cnt-1);
                    }
                }                
                // delete
                for(int key : del){
                    hash.remove(key);
                }
            }
        }
        // Check the candidate whether they occur more than k times
        // reset counter
        for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
            int key = entry.getKey();
            hash.put(key, 0);
        }
        
        for(int n : nums){
            if(hash.containsKey(n))
                hash.put(n, hash.get(n)+1);
        }
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : hash.entrySet()){
            if(entry.getValue() > (len * 1.0 / k))
                result.add(entry.getKey());
        }
        return result.get(0);
    }
}
