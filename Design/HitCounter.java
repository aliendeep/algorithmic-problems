/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/

// Solution 1
public class HitCounter {
    public static final int interval = 300;
    Queue<Integer> Q;
    /** Initialize your data structure here. */
    public HitCounter() {
        Q = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        // Remove all entries that occurs before 5mins = 300 seconds
        // Notice all timestamps are monotonically increasing
        while(!Q.isEmpty() && timestamp - Q.peek() >= interval){
            Q.remove();            
        }
        // Add current timestamp
        Q.add(timestamp); 
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!Q.isEmpty() && (timestamp - Q.peek()) >= interval)
            Q.remove();            
        return Q.size();
    }
}

// Alternative: Better Solution
class HitCounter2 {
    // last 5 minutes
    public static final int INTERVAL = 300;
    int[] hits;
    int[] timestamps;

    /** Initialize your data structure here. */
    public HitCounter2() {
        hits = new int[INTERVAL];
        timestamps = new int[INTERVAL];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    // Time complexity : O(1)
    public void hit(int timestamp) {
        int index = timestamp % INTERVAL;
        if(timestamps[index] != timestamp){
            // reset
            timestamps[index] = timestamp;
            hits[index] = 1;
        }
        else{
            hits[index]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    // O(1)
    public int getHits(int timestamp) {
        // 300 iterations
        int cnt = 0;
        for(int i=0; i<INTERVAL; ++i){
            if(hits[i] != 0 && timestamp - timestamps[i] < INTERVAL){
                cnt += hits[i];
            }
        }
        return cnt;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */