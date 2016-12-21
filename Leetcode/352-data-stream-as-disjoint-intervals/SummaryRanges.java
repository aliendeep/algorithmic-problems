/*
Given a data stream input of non-negative integers a1, a2, ..., an, ..., 
summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., 
then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small 
compared to the data stream's size?
*/
/**
 * Definition for an interval.
 */

import java.util.*;

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
 }
 
public class SummaryRanges {
    public void print(List<Interval> a){
        for(Interval t : a){
          System.out.print("[" +t.start + " " + t.end + "] ");
        }
        System.out.println();
    }
        // start, interval
    TreeMap<Integer, Interval> map;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    // AddNum : O(logn)
    public void addNum(int val) {
        if(map.containsKey(val))
            return;
        // Returns the greatest key strictly less than the given key, or null if there is no such key.
        Integer left = map.lowerKey(val);
        // Returns the least key strictly greater than the given key, or null if there is no such key.
        Integer right = map.higherKey(val);
        if(left != null && right != null && map.get(left).end + 1 == val && right == val + 1){
            // Merge 
            map.get(left).end = map.get(right).end;
            // remove the larger element
            map.remove(right);
        }
        else if(left != null && map.get(left).end + 1 >= val){
            map.get(left).end = Math.max(map.get(left).end, val);
        }
        else if(right != null && right == val + 1){
            map.put(val, new Interval(val, map.get(right).end));
            // remove the larger element
            map.remove(right);
        }
        else{
            // insert new interval
            map.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(map.values());    
    }

    public static void main(String[] args){
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(7);
        obj.addNum(2);
        obj.addNum(1);
        List<Interval> r = obj.getIntervals();
        obj.print(r);
    }     
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
