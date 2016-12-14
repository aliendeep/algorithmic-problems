/*
Given a nested list of integers, return the sum of all integers in the list 
weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers 
or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 
at depth 3; 1 + 4*2 + 6*3 = 27)
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int getSubListSum(NestedInteger ni, int depth){
        if(ni.isInteger())
            return ni.getInteger()*depth;
            
        int sum = 0;
        List<NestedInteger> sublist = ni.getList();
        for(NestedInteger n : sublist){
            sum += getSubListSum(n, depth+1);
        }
        return sum;
    }
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for(NestedInteger ni :  nestedList){
            sum += getSubListSum(ni, 1);
        }
        return sum;
    }
}

// Alternatve: Iterative: Use queue
class Solution2 {
    class NInfo{
        NestedInteger ni;
        int depth;
        public NInfo(NestedInteger i, int d){
            ni = i;
            depth = d;
        }
    }
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList.size() == 0)
            return 0;
            
        Queue<NInfo> Q = new LinkedList<>();
        // Add all items to the queue
        for(NestedInteger n : nestedList)
            Q.add(new NInfo(n, 1));
        
        int sum = 0;
        while(!Q.isEmpty()){
            NInfo front = Q.remove();
            if(front.ni.isInteger())
                sum += front.ni.getInteger()*front.depth;
            else{
                // get the sublist
                List<NestedInteger> subList = front.ni.getList();
                for(NestedInteger n : subList){
                    Q.add(new NInfo(n, front.depth + 1));
                }
            }
        }
        return sum;
    }
}