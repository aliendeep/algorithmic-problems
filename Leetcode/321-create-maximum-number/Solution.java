/*
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
*/
public class Solution {
    public int[] findMaximumNumber(int[] A, int k){
        int n = A.length;
        if(n <= k)       return A;
        int[] result = new int[k];
        int id = 0;
        for(int i=0; i<n; i++){
            // If we find a number > last element of r and if we still have enough number to choose from, then we can discard the last number of r (pop)
            while(id > 0  && k - id < n - i && result[id-1] < A[i]){
                id--;
            }
            // push
            if(id < k)
                result[id++] = A[i];
        }
        return result;
    }
    
    public int[] mergeTwoArrays(int[] t1, int[] t2, int k){
        int[] t = new int[k];
        int p1 = 0, p2 = 0;
        int id = 0;
        while(p1 < t1.length && p2 < t2.length){
            if(t1[p1] == t2[p2]){
                t[id++] = compareArrays(t1, p1+1, t2, p2+1) ? t1[p1++] : t2[p2++];
            }
            else{
                t[id++] = (t1[p1] > t2[p2]) ? t1[p1++] : t2[p2++];
            }
        } 
        
        while(p1 < t1.length)
            t[id++] = t1[p1++];
            
        while(p2 < t2.length)
            t[id++] = t2[p2++];
        return t;
    }

    public boolean compareArrays(int[] nums1, int p1, int[] nums2, int p2){
        while(p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] > nums2[p2])
                return true;
            if(nums1[p1] < nums2[p2])
                return false;
            p1++;
            p2++;
        }
        // Num1 has still number than return true
        return p1 < nums1.length;
    }
    
    public void print(int[] A){
        for(int i=0; i<A.length; i++)
            System.out.print(A[i] + " ");
        System.out.println();
    }
    
    // Number of digits taken from Array 1: i
    // Number of digits taken from Array 2: k - i
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        for(int i=0; i<=k; i++){
            if(len1 < i || len2 < k - i)
                continue;
            int[] t1 = findMaximumNumber(nums1, i);
            int[] t2 = findMaximumNumber(nums2, k - i);
            int[] t = mergeTwoArrays(t1, t2, k);

            if(compareArrays(t, 0, result, 0)){
                result = t;
            }
        }
        return result;
    }
}