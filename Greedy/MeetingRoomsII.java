/*
https://leetcode.com/problems/meeting-rooms-ii/

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/
// Greedy
/*

Sort the intervals by start time.

Min Heap stores all concurrent conflicting meetings. Those kind of meetings need separate rooms. The front of the queue has the 
conflicting element that has the earliest end time. 

When a new event X comes (already sorted by start time), we see whether it's possible to reuse one of the rooms in the min heap. 
Let the event at the front of the priority queue = E. If E's end time is less that X's start time, we can use E's room by updating the end time of E. 
Otherwise, we have to allocate a new room.
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
 
/* 
Greedy
Min Heap stores all concurrent conflicting meetings. Those kind of meetings need separate rooms. The front of the queue has the conflicting element that has the earliest end time. 

When a new event X comes (already sorted by start time), we see whether it's possible to reuse one of the rooms in the min heap. Let the event at the front of the priority queue = E. If E's end time is less that X's start time, we can use E's room by updating the end time of E. Otherwise, we have to allocate a new room

Answer: heap size 
*/
public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        int n = intervals.length;
        if(n <= 1)
            return n; 

        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>(){
           @Override
           public int compare(Interval a, Interval b){
               return Integer.compare(a.start, b.start);
           }
        });
        
        // Top of the min heap contains the meeting room that has the earliest end time
        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(new Comparator<Interval>(){
           @Override
           public int compare(Interval a, Interval b){
               return Integer.compare(a.end, b.end);
           }
        });
        
        // Add the first interval in the heap
        minHeap.add(intervals[0]);
        for(int i=1; i<n; i++){
            // Pop the meeting room that finishes earliest
            Interval t = minHeap.poll();
            // If we can use the same room, then update the end time
            if(t.end <= intervals[i].start)
                t.end = intervals[i].end;    
            // overlaps, need a new room
            else
                minHeap.add(intervals[i]);

            // push the previous meeting room t again
            minHeap.add(t);
        }
        return minHeap.size();
    }
}
