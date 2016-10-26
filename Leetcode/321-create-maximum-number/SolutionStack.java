import java.util.*;

public class SolutionStack {
    // Find maximum number by removing k digits
    // Greedy
    public int[] findMaximumNumber(int[] nums, int k){
        if(nums.length == 0 || k == 0)     return nums;
        // Return empty array
        if(nums.length == k)               return new int[0];        

        Deque<Integer> stk = new LinkedList<>();
        for(int i=0; i<nums.length; ++i){
            while(k > 0 && !stk.isEmpty() && stk.peekFirst() < nums[i]){
                System.out.println("k " + k + " " + nums[i]);
                stk.removeFirst();
                k--;
            }
            stk.addFirst(nums[i]);
        }
        
        // For cases like: 111, 2222 etc
        while(k > 0 ){
            stk.removeFirst();
            k--;
        }
        
        // Reverse order        
        int[] result = new int[stk.size()];
        int i = stk.size() - 1;
        while(!stk.isEmpty()){
            result[i] = stk.removeFirst();
            i--;
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
        System.out.println();
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
            int a = len1 - i;
            int b = len2 - (k - i);
            // Number of digits taken from Array 1: i
            int[] t1 = findMaximumNumber(nums1, len1 - i);
            // Number of digits taken from Array 2: k - i
            int[] t2 = findMaximumNumber(nums2, len2 - (k - i));

            int[] t = mergeTwoArrays(t1, t2, k);

            if(compareArrays(t, 0, result, 0)){
                result = t;
            }
        }
        return result;
    }

    public static void main(String[] args){
        SolutionStack s = new SolutionStack();
        int[] a = {8,6,9};
        int[] b = {1,7,5};
        int[] r = s.maxNumber(a, b, 3);
        s.print(r);
    }    
}