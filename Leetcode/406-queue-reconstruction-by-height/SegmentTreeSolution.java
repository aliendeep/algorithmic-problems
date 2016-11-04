import java.util.*;
/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/
// Alternative
class SegmentTree{
    // no of leaves in the segment tree
    int n;
    int _n;
    int[] node;
    
    // Root Node Index : 1
    public SegmentTree(int _n){
        this._n = _n;
        // Find the closest power of 2
        n = 1;
        while(n < _n){
          n *= 2;
        }
        node = new int[n*2];
    }

    public void addDiffRecursive(int nodeIndex, int low, int high, int arrIndex, int diff){
        if(low == high){
            // leaf
            node[nodeIndex] += diff;
            return;
        }
        int mid = (int)(low+high)/2;
        if(arrIndex > mid)
            addDiffRecursive(2*nodeIndex+1, mid+1, high, arrIndex, diff);
        if(arrIndex <= mid)
            addDiffRecursive(2*nodeIndex, low, mid, arrIndex, diff);
    
        node[nodeIndex] = node[2*nodeIndex] + node[2*nodeIndex+1];
    }
    // Add difference
    public void addDiff(int i, int diff){
        addDiffRecursive(1, 0, _n-1, i, diff);
    }
    
    public int rangeSumRecursive(int nodeIndex, int low, int high, int i, int j){
        // return range sum of a[i...j]
        // outside range
        if(high < i || low > j)
            return 0;
            
        // completely within range
        if(low >= i && high <= j)
            return node[nodeIndex];
            
        int mid = (int)(low+high)/2;
        if(i > mid){
            // right 
            return rangeSumRecursive(nodeIndex*2 + 1, mid+1, high, i, j);
        }        
        if(j <= mid){
            // left
            return rangeSumRecursive(nodeIndex*2, low, mid, i, j);
        }
        return rangeSumRecursive(nodeIndex*2, low, mid, i, mid) + 
                            rangeSumRecursive(nodeIndex*2+1, mid+1, high, mid+1, j);
    }
    public int rangeSum(int i, int j){
        return rangeSumRecursive(1, 0, _n-1, i, j);
    }

    public int findIndex(int sum){
        int low = 0;
        int high = _n-1;
        while(high - low > 3){
            int m = (low + high)/2;
            int f = rangeSum(0, m);
            if(f < sum){
                // go to the right subtree
                low = m + 1;
            }
            else{
                // hi can be solution
                high = m;
            }
        }
        for(int x=low; x<=high; ++x){
            if(rangeSum(0, x) >= sum)
                return x;
        }
        return -1;
    }
}

// Time Complexity: O(nlog^2n)
public class Solution{
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        // Sort by height increasing, sort by weight decreasing
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                // k
                if(a[0] == b[0])
                    return Integer.compare(b[1], a[1]);
                return Integer.compare(a[0], b[0]);
            }
        });
        
        SegmentTree st = new SegmentTree(n);
        // Add 1 to all position (all positions are available)
        for(int i=0; i<n; ++i)
            st.addDiff(i, 1);

        int[][] result = new int[n][2];
        for(int i=0; i<n; ++i){
            // empty kth spot (out of remaining position)
            int index = st.findIndex(people[i][1]+1);
            result[index][0] = people[i][0];
            result[index][1] = people[i][1];            
            // mark that position as occupied
            st.addDiff(index, -1);
        }
        return result;
    }
}