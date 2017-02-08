/*
Design a logger system that receive stream of messages along with its timestamps, 
each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message 
should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/

/*
Sample Input:
["Logger","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage"]
[[],[1,"foo"],[2,"bar"],[3,"foo"],[8,"bar"],[10,"foo"],[11,"foo"]]
["Logger","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage"]
[[],[0,"A"],[0,"B"],[0,"C"],[0,"A"],[0,"B"],[0,"C"],[0,"A"],[0,"B"],[0,"C"],[0,"A"]]
Sample Output:
[null,true,true,false,false,false,true]
[null,true,true,true,false,false,false,false,false,false,false]
*/
public class Logger {
    // 10 s
    public static final int interval = 10;
    // (Msg, timestamp of last printed msg)
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, 
        otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message, timestamp);
            return true;
        }
        else{
            int prevTime = map.get(message);
            if(timestamp - prevTime < interval)
                return false;
            // replace value
            map.put(message, timestamp);
            return true;
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

// Alternative: When it's okay to print message
// Instead of keeping timestamps of all messages
class Logger {
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // If this message comes earlier than allowed timestamp, then return false
        if(timestamp < map.getOrDefault(message, 0))
            return false;
        // it's okay to print the same message after 10 second
        map.put(message, timestamp+10);
        return true;
    }
}

// Solution 3: Use LinkedHashMap to throw out old messages
class Logger {
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
