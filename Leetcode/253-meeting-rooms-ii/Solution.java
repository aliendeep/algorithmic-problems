/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
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
    class Info{
        int time;
        // isEnd = 1 if end
        // isEnd = 0 if it's start
        int isEnd;
        
        public Info(int i, int flag){
            time = i;
            isEnd = flag;
        }
    }
    // Greedy
    public int minMeetingRooms(Interval[] intervals) {
        int n = intervals.length;
        if(n == 0)      return 0;
        
        List<Info> infos = new ArrayList<>();
        for(int i=0; i<n; ++i){
            infos.add(new Info(intervals[i].start, 0));
            infos.add(new Info(intervals[i].end, 1));
        }
        
        // Sort by start time (End comes first)
        Collections.sort(infos, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                if(a.time == b.time)
                    return Integer.compare(b.isEnd, a.isEnd);
                return Integer.compare(a.time, b.time);
            }
        });
        
        int maxRooms = 0;
        int cnt = 0;
        for(Info t : infos){
            // meeting starting
            if(t.isEnd == 0){
                cnt++;
                maxRooms = Math.max(maxRooms, cnt);
            }
            // meeting ending
            else{
                cnt--;
            }
        }   
        return maxRooms;
    }
}