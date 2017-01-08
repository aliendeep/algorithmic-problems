/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        // Sort the intervals
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                return Integer.compare(a.start, b.start);
            }
        });
        
        List<Interval> result = new ArrayList<>();
        int n = intervals.size();
        if(n == 0)
            return result;

        Interval cur = intervals.get(0);
        for(int i=1; i<n; i++){
            Interval t = intervals.get(i);
            // overlaps
            if(t.start <= cur.end){
                cur.end = Math.max(cur.end, t.end); 
            }
            else{
                result.add(new Interval(cur.start, cur.end));
                cur = t;
            }
        }
        // last interval
        result.add(new Interval(cur.start, cur.end));
        return result;
    }
}
