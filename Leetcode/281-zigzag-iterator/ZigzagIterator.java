/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements 
returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

// Cleaner: Follow up
public class ZigzagIterator {
    // list of iterator
    List<Iterator<Integer>> list;
    int turn;
    // number of vectors
    int k;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        k = 2;
        list = new ArrayList<>();
        list.add(v1.iterator());
        list.add(v2.iterator());
        turn = 0;     
    }

    public int next() {
        // check if the current vector has sum elements
        while(!list.get(turn).hasNext()){
            turn = (turn + 1) % k;   
        }
        int ret = list.get(turn).next();
        // update turn
        turn = (turn + 1) % k;
        return ret;
    }

    public boolean hasNext() {
        for(Iterator it : list){
            if(it.hasNext())
                return true;
        }
        return false;
    }
}

public class ZigzagIterator {
    Iterator<Integer> it1, it2;
    boolean first;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        it1 = v1.iterator();        
        it2 = v2.iterator();        
        first = true;
    }

    public int next() {
        // Handle special cases
        if(first && !it1.hasNext()){
            return it2.next();
        }
        if(!first && !it2.hasNext()){
            return it1.next();
        }
        int r;
        if(first)
            r = it1.next();
        else
            r = it2.next();
        first = !first;
        return r;
    }

    public boolean hasNext() {
        return (it1 != null && it1.hasNext()) || (it2 != null && it2.hasNext()) ;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */