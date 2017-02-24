/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> r = new ArrayList<Integer>();
        int x, y;
        int i = 0, j = 0;
        while(i<nums1.length && j<nums2.length){
            x = nums1[i];
            y = nums2[j];
            if(x == y){
                // Each element in the result must be unique.
                if(r.size() == 0 || r.get(r.size()-1) != x)
                    r.add(x);
                i++;
                j++;
            } 
            else if(x < y){
                i++;
            }
            else
                j++;
        }

        int[] t = new int[r.size()];
        for(i=0; i<r.size(); i++){
            t[i]= r.get(i);
        }
        return t;
    }
}

// Set Solution
class Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        // Use less memory
        if(nums1.length > nums2.length)
            return intersection(nums2, nums1);

        // num
        // Each element in the result must be unique.
        Set<Integer> set = new HashSet<>();
        for(int n : nums1){
            set.add(n);
        }
        
        List<Integer> r = new ArrayList<Integer>();
        for(int num : nums2){
            if(set.contains(num)){
                r.add(num);
                set.remove(num);
            }           
        }

        int[] t = new int[r.size()];
        for(int i=0; i<r.size(); i++){
            t[i]= r.get(i);
        }
        return t;         
    }
}
