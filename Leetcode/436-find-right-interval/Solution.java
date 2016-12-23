/*
Given a set of intervals, for each of the interval i, check if there exists an 
interval j whose start point is bigger than or equal to the end point of the 
interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means 
that the interval j has the minimum start point to build the "right" relationship 
for interval i. If the interval j doesn't exist, store -1 for the interval i. 
Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
Show Tags
Show Similar Problems
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// O(n^2) Solution
public class Solution {
    class Info{
        Interval interval;
        int index;
        public Info(Interval i, int in){
            interval = i;
            index = in;
        }
    }
    
    public int[] findRightInterval(Interval[] intervals) {
        int n = intervals.length; 
        if(n == 0)      return new int[0];
        if(n == 1){
            int[] t = new int[1];
            Arrays.fill(t, -1);
            return t;
        }
        
        Info[] info = new Info[n];
        for(int i=0; i<n; ++i){
            info[i] = new Info(intervals[i], i);
        }
        
        // Sort by the start time
        Arrays.sort(info, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                return Integer.compare(a.interval.start, b.interval.start);
            }
        });
        
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        for(int i=0; i<n-1; ++i){
            for(int j=i+1; j<n; j++){
                // minimum right point found
                if(info[i].interval.end <= info[j].interval.start){
                    result[info[i].index] = info[j].index;
                    break;
                }
            }
        }
        return result;
    }
}

// O(nlogn) Solution
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int n = intervals.length; 
        int[] result = new int[n];

        // Start, index
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0; i<n; ++i){
            map.put(intervals[i].start, i);    
        }

        for(int i=0; i<n; ++i){
            Integer key = map.ceilingKey(intervals[i].end);
            result[i] = (key == null) ? -1 : map.get(key);
        }
        
        return result;
    }
}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Info{
    int start;
    int index;
    public Info(int i, int in){
        start = i;
        index = in;
    }
}

// Binary Search
class Solution2 {
    // Find the index of least start value, that is equal or larger than target
    public int binarySearch(Info[] a, int target){
        int low = 0;
        int high = a.length-1;
        while(high - low > 3){
            int mid = (low+high)/2;
            if(a[mid].start < target){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        for(int i=low; i<=high; ++i){
            if(a[i].start >= target)
                return a[i].index;
        }
        return -1;
    }
    
    public int[] findRightInterval(Interval[] intervals) {
        int n = intervals.length; 
        Info[] info = new Info[n];
        for(int i=0; i<n; ++i){
            info[i] = new Info(intervals[i].start, i);
        }
        
        // Sort by the start time
        Arrays.sort(info, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                return Integer.compare(a.start, b.start);
            }
        });
        
        int[] result = new int[n];
        for(int i=0; i<n; ++i){
            result[i] = binarySearch(info, intervals[i].end);
        }
        
        return result;
    }
}
