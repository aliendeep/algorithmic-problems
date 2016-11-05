/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// Alternative: Sort by end time
public class Solution {
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