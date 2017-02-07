/*
Given a stream of integers and a window size, calculate the moving average of 
all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/
// Time: O(1)
// Space: O(k)
public class MovingAverage {
    Queue<Integer> Q;
    int k;
    int runningSum = 0;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        Q = new LinkedList<>();
        k = size;
    }
    
    public double next(int val) {
        runningSum += val;
        if(Q.size() < k){
            Q.add(val);
            return (double)runningSum/(double)Q.size();
        }
        Q.add(val);
        runningSum -= Q.remove();
        return (double)runningSum/(double)k;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
class MovingAverage2 {
    int size;
    Queue<Integer> Q;
    int curSum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        Q = new LinkedList<>();
        curSum = 0;
    }
    
    public double next(int val) {
        curSum += val;
        Q.add(val);
        // remove the earliest element
        if(Q.size() > size){
            curSum -= Q.remove();
        }
        return curSum*1.0 / Q.size();
    }
}

// Using array instead of queue
public class MovingAverage {
    int[] window;
    long sum;
    int i;
    // length of the window
    int n;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];    
        sum = 0;
        i = 0;
        n = 0;
    }
    
    public double next(int val) {
        if(n < window.length)   ++n;

        sum -= window[i];
        // update sum and window
        sum += val;
        window[i] = val;
        i = (i + 1) % window.length;
        return (sum*1.0)/n;
    }
}
