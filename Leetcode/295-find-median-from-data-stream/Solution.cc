class MedianFinder {
public:
    // Two heaps
    // larger half
    priority_queue<int, vector<int>, greater<int>> min_heap;
    // smaller half
    priority_queue<int, vector<int>> max_heap;
    // Adds a number into the data structure.
    void addNum(int num) {
        if(min_heap.empty()){
          // this is the first element
          min_heap.emplace(num);
        }
        else{
          if(num >= min_heap.top())
            min_heap.emplace(num);
          else
            max_heap.emplace(num);
        }
        // then ensure size
        if(min_heap.size() > max_heap.size() + 1){
            max_heap.emplace(min_heap.top());
            min_heap.pop();
        }    
        else if(max_heap.size() > min_heap.size()){
            min_heap.emplace(max_heap.top());
            max_heap.pop();          
        }
    }

    // Returns the median of current data stream
    double findMedian() {
        return (min_heap.size() == max_heap.size() ) ? (min_heap.top() + max_heap.top())*0.5 : min_heap.top();
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf;
// mf.addNum(1);
// mf.findMedian();