/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/count-of-smaller-number-before-itself
@Language: Java
@Datetime: 16-11-25 04:49
*/

class SegmentTree{
    // number of leaves in the segment tree
    int n;
    // number of elements in the array
    int _n;
    int[] nodes;
    
    public SegmentTree(int _n){
        this._n = _n;
        n = 1;
        while(n < _n){
            n *= 2;
        }
        // total no of nodes 2n - 1
        nodes = new int[2*n];
    }
    public void addDiffRecursive(int nodeIndex, int low, int high, int arrIndex, int diff){
        // reached a leaf
        if(low == high){
            nodes[nodeIndex] += diff;
            return;
        }
        int mid = (low + high)/2;
        if(mid >= arrIndex){
            // left subtree
            addDiffRecursive(2*nodeIndex, low, mid, arrIndex, diff);
        }
        else if(mid < arrIndex){
            // right subtree
            addDiffRecursive(2*nodeIndex + 1, mid+1, high, arrIndex, diff);
        }
        nodes[nodeIndex] = nodes[2*nodeIndex] + nodes[2*nodeIndex+1];
    }
    
    public void addDiff(int i, int diff){
        // start from root
        addDiffRecursive(1, 0, _n-1, i, diff);
    }

    public int rangeSumRecursive(int nodeIndex, int low, int high, int i, int j){
        // outside range
        if(low > j || high < i)
            return 0;
        // completely within range
        if(low >= i && high <= j)
            return nodes[nodeIndex];
        
        int mid = (low + high)/2;
        if(mid >= j){
            // left subtree
            return rangeSumRecursive(2*nodeIndex, low, mid, i, j);
        }
        else if(mid < i){
            // right subtree
            return rangeSumRecursive(2*nodeIndex + 1, mid+1, high, i, j);
        }
        return rangeSumRecursive(2*nodeIndex, low, mid, i, mid) + 
               rangeSumRecursive(2*nodeIndex + 1, mid+1, high, mid+1, j);
    }

    public int rangeSum(int i, int j){
        return rangeSumRecursive(1, 0, _n-1, i, j);
    }
}
public class Solution {
   /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is 
     *          smaller than it and return count number array
     */ 
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        int n = A.length;
        int[] B = A.clone();
        Arrays.sort(B);
        
        SegmentTree tree = new SegmentTree(n);
        ArrayList<Integer> r = new ArrayList<>();
        for(int i=0; i<n; ++i){
            int index = Arrays.binarySearch(B, A[i]);
            r.add(tree.rangeSum(0, index-1));
            tree.addDiff(index, 1);
        }
        return r;
    }
}
