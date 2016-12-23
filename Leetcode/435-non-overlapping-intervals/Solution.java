/*
Given a collection of intervals, find the minimum number of intervals you need 
to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already 
non-overlapping.
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
// Greedy
public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        int n = intervals.length; 
        if(n <= 1)
            return 0;
        // Sort by the start
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                return Integer.compare(a.start, b.start);
            }
        });
        
        int[] removeflag = new int[n];
        int curIndex = 0;
        for(int i=1; i<n; ++i){
            Interval cur = intervals[curIndex];
            Interval t = intervals[i];
            // overlaps
            if(cur.end > t.start){
                // remove the one which has longer interval
                // remove cur
                if(cur.end > t.end){
                    removeflag[curIndex] = 1;
                    // make t cur
                    curIndex = i;
                }
                else{
                    removeflag[i] = 1;
                }
            }
            // no overlap
            else{
                // Make i the current index
                curIndex = i;
            }
        }   
        int cnt = 0;
        for(int i=0; i<n; ++i){
            cnt += removeflag[i];
        }
        return cnt;
    }
}

// Alternative: Find the maximum number of intervals that are non overlapping
class Solution2 {
    public int eraseOverlapIntervals(Interval[] intervals) {
        int n = intervals.length; 
        if(n <= 1)      return 0;
        // Sort by the end time
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                return Integer.compare(a.end, b.end);
            }
        });
        int nonOverlappingIntervalCnt = 1;
        int end = intervals[0].end;
        for(int i=1; i<n; ++i){
            if(intervals[i].start >= end){
                end = intervals[i].end;
                nonOverlappingIntervalCnt++;
            }
        }
        return n - nonOverlappingIntervalCnt;
    }
}
