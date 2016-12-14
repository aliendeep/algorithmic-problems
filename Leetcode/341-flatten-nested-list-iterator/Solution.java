/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be 
integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements 
returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements 
returned by next should be: [1,4,6].
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

// Alternative: Using Iterator
class NestedIterator implements Iterator<Integer> {
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

// Recursive (Solution 3)
// Flatten the whole nestedList
public class NestedIterator implements Iterator<Integer> {
    Queue<NestedInteger> Q;
    Iterator<NestedInteger> it;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        Q = new LinkedList<NestedInteger>();
        flatten(nestedList);
        it = Q.iterator();
    }
    
    public void flatten(List<NestedInteger> nestedList){
        for(NestedInteger n : nestedList){
            // Append n to the end of the flattenList
            if(n.isInteger()){
                Q.add(n);
            }
            else{
                List<NestedInteger> subList = n.getList();
                flatten(subList);
            }
        }    
    }

    @Override
    public Integer next() {
        return it.next().getInteger();    
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();    
    }
}
