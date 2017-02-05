/*
Given a circular array (the next element of the last element is the first element 
of the array), print the Next Greater Number for every element. The Next Greater 
Number of a number x is the first greater number to its traversing-order next 
in the array, which means you could search circularly to find its next greater 
number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/

public class Solution {
    class Pair{
        int num;
        int index;
        public Pair(int n, int i){
            num = n;
            index = i;
        }
    }

    public int[] nextGreaterElements(int[] a) {
        int n = a.length;
        if(n == 0)      return new int[0];
        
        int[] nums = new int[2*n];
        for(int i=0; i<n; ++i){
            nums[i] = a[i];
            nums[n+i] = a[i];
        }
        
        Deque<Pair> stk = new LinkedList<Pair>();
        stk.push(new Pair(nums[0], 0));
        // index, result
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=1; i<nums.length; ++i){
            int num = nums[i];
            if(!stk.isEmpty()){
                Pair top = stk.peekFirst();
                while(top.num < num){
                    stk.pop();
                    
                    if(!map.containsKey(top.index)){
                              // index
                        map.put(top.index, num);
                    }
                    if(stk.isEmpty())   break;
                    top = stk.peekFirst();
                }
            }
            stk.push(new Pair(num, i));
        }
        int[] r = new int[n];
        for(int i=0; i<n; ++i){
            if(map.containsKey(i)){
                r[i] = map.get(i); 
            }
            else
                r[i] = -1;
        }
        return r;
    }
}
