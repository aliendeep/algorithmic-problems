/*
Median is the middle value in an ordered integer list. If the size of the list 
is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/
public class MedianFinder {
    private PriorityQueue<Integer> minHeap; 
    private PriorityQueue<Integer> maxHeap;
    
    public MedianFinder(){
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
    }
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(minHeap.isEmpty())
            minHeap.add(num);
        else{
            if(num >= minHeap.peek()){
                minHeap.add(num);
            }
            else{
                maxHeap.add(num);                
            }
        }
        
        if(minHeap.size() > maxHeap.size() + 1)
            maxHeap.add(minHeap.remove());
        else if(minHeap.size() < maxHeap.size())
            minHeap.add(maxHeap.remove());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(minHeap.size() > maxHeap.size())
            return minHeap.peek();
        else
            return (minHeap.peek() + maxHeap.peek())*0.5;
    }
};

// Alternative: Using minHeap for both parts
class MedianFinder2 {
    private PriorityQueue<Long> small; 
    private PriorityQueue<Long> large;
    
    public MedianFinder(){
        small = new PriorityQueue<Long>();
        large = new PriorityQueue<Long>();
    }
    // Adds a number into the data structure.
    public void addNum(int num) {
        large.add((long)num);
        small.add(-large.poll());
        if(large.size() < small.size()){
            large.add(-small.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(large.size() > small.size())
            return large.peek();
        else
            return (large.peek() - small.peek())*0.5;
    }
};

// Slight different implementation of MedianFinder2
class MedianFinder3 {
    private PriorityQueue<Long> small; 
    private PriorityQueue<Long> large;
    
    public MedianFinder(){
        small = new PriorityQueue<Long>();
        large = new PriorityQueue<Long>();
    }
    // Adds a number into the data structure.
    public void addNum(int n) {
        long num = (long)n;
        if(large.isEmpty())
            large.add(num);
        else{
            if(num >= large.peek()){
                large.add(num);
            }
            else{
                small.add(-num);                
            }
        }
        
        if(large.size() > small.size() + 1)
            small.add(-large.poll());
        else if(large.size() < small.size())
            large.add(-small.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(large.size() > small.size())
            return large.peek();
        else
            return (large.peek() - small.peek())*0.5;
    }
};

// Alternative: using TreeMap (Multi-TreeSet)
public class MedianFinder {
    TreeMap<Integer, Integer> large;
    TreeMap<Integer, Integer> small;
    int lcnt;
    int scnt;
    
    /** initialize your data structure here. */
    public MedianFinder() {
        large = new TreeMap<>();
        small = new TreeMap<>();
        lcnt = 0;
        scnt = 0;
    }
    public void decrement(int key, TreeMap<Integer, Integer> map){
        int newCnt = map.get(key) - 1;
        if(newCnt == 0)
            map.remove(key);
        else
            map.put(key, newCnt);
    }
    
    public void addNum(int num) {
        if(large.isEmpty()){
            large.put(num, 1);
            lcnt++;
        }
        else{
            if(num >= large.firstKey()){
                large.put(num, large.getOrDefault(num, 0) + 1);
                lcnt++;
            }
            else{
                small.put(num, small.getOrDefault(num, 0) + 1);                
                scnt++;
            }
        }
        
        if(lcnt > scnt + 1){
            // remove the smallest key from the large treeset
            int sm = large.firstKey();
            decrement(sm, large);
            lcnt--;
            small.put(sm, small.getOrDefault(sm, 0) + 1);
            scnt++;
        }
        else if(lcnt < scnt){
            // remove the largest key from the small treeset
            int lg = small.lastKey();
            decrement(lg, small);
            scnt--;
            large.put(lg, large.getOrDefault(lg, 0) + 1);
            lcnt++;
        }
    }
    
    public double findMedian() {
        if(lcnt > scnt)
            return large.firstKey();
        else
            return (large.firstKey() + small.lastKey())*0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
