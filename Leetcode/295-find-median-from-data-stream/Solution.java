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