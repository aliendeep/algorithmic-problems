// Solution 3: Use LinkedHashMap to throw out old messages
public class Logger {
    Map<String, Integer> map;
    int latestTimeStamp;
    
    /** Initialize your data structure here. */
    public Logger() {
        // LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
        map = new LinkedHashMap<String, Integer>(10, 1.0f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest){
                return latestTimeStamp - eldest.getValue() > 10;
            }
        };    
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        latestTimeStamp = timestamp;
        if(!map.containsKey(message) || timestamp - map.get(message) >= 10){
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */