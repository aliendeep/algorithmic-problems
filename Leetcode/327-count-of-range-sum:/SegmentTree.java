public class Solution {
    class SegmentTree{
        int n;
        int[] node;

        public SegmentTree(int _n){
            n = 1;
            // closest power of 2
            while(n < _n){
                n *= 2;
            }
            // Number of leaves = n
            // 1 to 2n-1 are used.
            node = new int[n*2 + 1];            
        }  
        
        public void updateRecur(int index, int diff, int nodeIndex, int lowIndex, int upperIndex){
            node[nodeIndex] += diff;
            // if it's leaf
            if(upperIndex - lowIndex == 1)      return;
            int midIndex = (upperIndex + lowIndex)/2;            
            if(index < midIndex){
                // left child
                updateRecur(index, diff, 2*nodeIndex, lowIndex, midIndex);
            }
            else{
                // right child
                updateRecur(index, diff, 2*nodeIndex + 1, midIndex, upperIndex);
            }
        }
            
        public void update(int index, int val){
            updateRecur(index, val, 1, 0, n);
        }

        public int rangeSumRecursive(int lower, int upper, int nodeIndex, int lowIndex, int upperIndex){
            // If the lowIndex and upperIndex is within lower & upper, then return
            if(lower >= lowIndex && upperIndex <= upper)
                return node[nodeIndex];
            // If the lowIndex and upperIndex is outside of lower & upper, then return 0
            if(lower >= upperIndex || upper <= lowIndex)
                return 0;
                
            int midIndex = (upperIndex + lowIndex)/2;            
            return rangeSumRecursive(lower, upper, 2*nodeIndex, lowIndex, midIndex) +
                   rangeSumRecursive(lower, upper, 2*nodeIndex + 1, midIndex, upperIndex);
        }

        public int rangeSum(int lower, int upper){
            return rangeSumRecursive(lower, upper, 1, 0, n);
        }
    }
    
    class Pair{
        int sum;
        int index;
        public Pair(int s, int i){
            sum = s;
            index = i;
        }
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if(n == 0)          return 0;
        if(lower > upper)   return 0;
        
        SegmentTree sTree = new SegmentTree(n+1);
        for(int i=0; i<=n; ++i){
            sTree.update(i, 1);
        }
        
        int[] cumSum = new int[n];
        cumSum[0] = nums[0];
        for(int i=1; i<n; ++i){
            cumSum[i] = cumSum[i-1] + nums[i]; 
        }
        
        TreeSet<Pair> sortedSum = new HashSet<>();
        for(int i=0; i<n; ++i){
            sortedSum.add(new Pair(cumSum[i], i));
        }
        sortedSum.add(new Pair(Integer.MAX_VALUE, n));
    }
}