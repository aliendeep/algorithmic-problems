/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/
// Binary Indexed tree
public class Solution {
    int getSum(int[] bit, int index){
        int sum = 0;
        while(index > 0){
            sum += bit[index];
            // get parent
            index = index - (index & -index);
        }
        return sum;
    }
    
    void update(int[] bit, int index, int val){
        while(index < bit.length){
            bit[index] += val;
            // get next
            index = index + (index & -index);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] t = nums.clone();
        Arrays.sort(t);
        for(int i=0; i<n; i++){
            nums[i] = Arrays.binarySearch(t, nums[i]) + 1;
        }
        
        int[] bit = new int[n+1];
        Integer[] r = new Integer[n];
        for(int i=n-1; i>=0; i--){
            r[i] = getSum(bit, nums[i] - 1);
            update(bit, nums[i], 1);
        }
        return Arrays.asList(r);
    }
}