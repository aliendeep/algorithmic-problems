/*
Median is the middle value in an ordered integer list. If the size of the list is 
even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from 
the very left of the array to the very right. You can only see the k numbers in 
the window. Each time the sliding window moves right by one position. Your job 
is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
*/
public class Solution {
    // larger element
    TreeMap<Integer, Integer> x;
    int xCnt;
    // smaller element
    TreeMap<Integer, Integer> y;
    int yCnt;
    Queue<Integer> Q;

    public void increment(TreeMap<Integer, Integer> map, int k){
        map.put(k, map.getOrDefault(k, 0) + 1);
    }
    
    public void decrement(TreeMap<Integer, Integer> map, int k){
        int newCnt = map.get(k) - 1;
        if(newCnt == 0)
            map.remove(k);
        else
            map.put(k, newCnt);
    }
    
    public void adjustSize(){
        if(xCnt > yCnt + 1){
            int smallest = x.firstKey();
            decrement(x, smallest);
            xCnt--;
            increment(y, smallest);
            yCnt++;        
        }
        else if(xCnt < yCnt){
            int largest = y.lastKey();
            decrement(y, largest);
            yCnt--;
            increment(x, largest);
            xCnt++;        
        }        
    }

    // Add the number
    public void add(int num){
        if(xCnt == 0){
            increment(x, num);
            xCnt++;
        }
        else{
            if(num >= x.firstKey()){
                increment(x, num);
                xCnt++;
            }
            else{
                increment(y, num);
                yCnt++;
            }
        }
        adjustSize();
    }
    
    public void deleteNum(int num){
        if(x.containsKey(num)){
            decrement(x, num);
            xCnt--;
        }
        else if(y.containsKey(num)){
            decrement(y, num);
            yCnt--;
        }
        adjustSize();
    }
    
    public double median(){
        if(xCnt > yCnt){
            return x.firstKey();
        }
        else{
            return ((double)x.firstKey() + (double)y.lastKey())*0.5;
        }
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        x = new TreeMap<>();
        y = new TreeMap<>();
        Q = new LinkedList<>();
        xCnt = 0;
        yCnt = 0;
        List<Double> result = new ArrayList<>();
        int i = 0;
        // First k element
        for(i=0; i<k; ++i){
            Q.add(nums[i]);
            add(nums[i]);
        }
        while(i < n){
            result.add(median());
            int lastElement = Q.remove();
            deleteNum(lastElement);
            
            Q.add(nums[i]);
            add(nums[i]);
            ++i;
        }    
        // last result
        result.add(median());
        double[] r = new double[result.size()];
        i = 0;
        for(double med : result){
            r[i++] = med;
        }
        return r;
    }
}
