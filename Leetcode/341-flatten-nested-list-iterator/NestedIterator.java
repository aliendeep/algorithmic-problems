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
// Alternative: Using Iterator
public class NestedIterator implements Iterator<Integer> {
    Deque<Iterator<NestedInteger>> stk;
    NestedInteger nextNI;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new LinkedList<>();
        stk.addFirst(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextNI != null ? nextNI.getInteger() : null;    
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            // Empty list
            if(!stk.peekFirst().hasNext())
                stk.removeFirst();
            else{
                nextNI = stk.removeFirst().next(); 
                // Next element is an integer
                if(nextNI.isInteger()){
                    return true;
                }
                else{
                    // sublist
                    stk.addFirst(nextNI.getList().iterator());
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */