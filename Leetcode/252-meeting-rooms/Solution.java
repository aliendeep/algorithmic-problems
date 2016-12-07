/*
Given an array of meeting time intervals consisting of start and end times 
[[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
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

// O(nlogn) Solution
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals.length <= 1)
          return true;
        
        // Sort by start time
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                if(a.start == b.start)
                    return Integer.compare(a.end, b.end);
                return Integer.compare(a.start, b.start);
            }
        });
        
        // If there is any overlap, return false
        for(int i=1; i<intervals.length; i++){
            if(intervals[i-1].end > intervals[i].start)
                return false;
        }
        return true;
    }
}

// Alternative: Sorting by end time works too
class Solution2 {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals.length <= 1)
          return true;
        
        // Sort by end time
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                if(a.end == b.end)
                    return Integer.compare(a.start, b.start);
                return Integer.compare(a.end, b.end);
            }
        });
        
        for(int i=1; i<intervals.length; i++){
            if(intervals[i-1].end > intervals[i].start)
                return false;
        }
        return true;
    }
}