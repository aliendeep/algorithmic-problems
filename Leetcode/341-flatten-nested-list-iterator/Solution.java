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
public class NestedIterator implements Iterator<Integer> {
    Deque<NestedInteger> stk;

    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new LinkedList<>();
        for(int i=nestedList.size()-1; i>=0; i--)
            stk.addFirst(nestedList.get(i));
    }

    @Override
    public Integer next() {
        return stk.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            NestedInteger top = stk.peekFirst();
            if(top.isInteger())
                return true;
            
            // Otherwise it's a list
            stk.removeFirst();
            List<NestedInteger> subList = top.getList();
            for(int i=subList.size()-1; i>=0; i--)
                stk.addFirst(subList.get(i));
        } 
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */