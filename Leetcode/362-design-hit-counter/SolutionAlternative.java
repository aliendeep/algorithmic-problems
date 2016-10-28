// Alternative
public class HitCounter {
    // last 5 minutes
    public static final int INTERVAL = 300;
    int[] hits;
    int[] timestamps;

    /** Initialize your data structure here. */
    public HitCounter() {
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