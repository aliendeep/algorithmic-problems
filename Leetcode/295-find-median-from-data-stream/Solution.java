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

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();

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

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();