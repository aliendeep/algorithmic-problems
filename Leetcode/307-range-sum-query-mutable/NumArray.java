/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/

import java.util.*;

class SegmentTree{
    // segment tree
    int[] nodes;
    int[] A;
    int n;
    
    // Construct segment tree from an array
    public SegmentTree(int[] nums){
        int len = nums.length;
        // find nearest number which is power of 2
        int size = 1;
        while(size < len)
            size *= 2;

        n = size;
        // original elements
        // fill rest of the elements with 0
        this.A = Arrays.copyOf(nums, n);
        // Total number of nodes in the segment tree 2*n - 1
        nodes = new int[2*n-1];
        // range of the root of the segment tree [0, n-1]
        // Index of the root in the nodes array = 0
        constructSegmentTree(A, 0, n-1, 0);
    }
    
    // Index is the index of the current node in the segment tree
    // start - start index of the node
    // end - end index of the node
    public int constructSegmentTree(int[] A, int start, int end, int index){
        // leaf
        // Leaf nodes are the element of the array
        if(start == end){
            nodes[index] = A[start];
            return nodes[index];
        }
        int mid = (end - start)/2 + start;
        // For each node, index of the left child 2*i + 1
        // For each node, index of the right child 2*i + 2
        nodes[index] = constructSegmentTree(A, start, mid, 2*index+1) + 
                       constructSegmentTree(A, mid+1, end, 2*index+2);
        return nodes[index];
    }
    
    // index: index of current segment tree node
    public int sumRangeHelper(int nodeStart, int nodeEnd, int queryStart, int queryEnd, int index) {
        // query rande contains the node range
        if(queryStart <= nodeStart && nodeEnd <= queryEnd)
            return nodes[index];

        if(nodeEnd < queryStart || nodeStart > queryEnd)
            return 0;
        
        int mid = (nodeStart + nodeEnd)/2;
        return sumRangeHelper(nodeStart, mid, queryStart, queryEnd, 2*index+1) +
               sumRangeHelper(mid + 1, nodeEnd, queryStart, queryEnd, 2*index+2);
    }
    
    public int sumRange(int queryStart, int queryEnd) {
        // Range of the root [0, n-1]
        // index of the root in the nodes array: 0
        return sumRangeHelper(0, n-1, queryStart, queryEnd, 0);
    }
    
    public void updateHelper(int nodeStart, int nodeEnd, int i, int diff, int index){
        if(i < nodeStart || i > nodeEnd)
            return;
        
        // Add the difference        
        nodes[index] = nodes[index] + diff;
        // if node is an internal node
        if(nodeStart != nodeEnd){
            int mid = (nodeEnd - nodeStart)/2 + nodeStart;
            updateHelper(nodeStart, mid, i, diff, 2*index + 1);
            updateHelper(mid+1, nodeEnd, i, diff, 2*index + 2);
        }
    }
    
    // index: index of current segment tree node
    void update(int index, int val){
        int difference = val - A[index];
        A[index] = val;
        // Range of the root [0, n-1]
        // index of the root in the nodes array: 0
        updateHelper(0, n-1, index, difference, 0); 
    }
}

public class NumArray {
    SegmentTree tree;

    public NumArray(int[] nums) {
        tree = new SegmentTree(nums);        
    }

    void update(int i, int val) {
        tree.update(i, val);   
    }

    public int sumRange(int i, int j) {
        return tree.sumRange(i, j);
    }

    public static void main(String[] args){
        System.out.println("Test Case #1");
        int nums[] = {1, 2, 3, 5, 7, 9, 11};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 1));
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        numArray.update(1, 10);
        System.out.println(numArray.sumRange(1, 2));

        System.out.println("Test Case #2");
        int A[] = {1, 1, 1, 2, 3, 4, 5};
        NumArray aArray = new NumArray(A);
        System.out.println(aArray.sumRange(0, 1));
        System.out.println(aArray.sumRange(0, 2));
        aArray.update(2, 10); 
        System.out.println(aArray.sumRange(0, 2));
        System.out.println(aArray.sumRange(4, 6));
    }
}