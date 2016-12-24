/*
There are a number of spherical balloons spread in two-dimensional space. For each 
balloon, provided input is the start and end coordinates of the horizontal diameter. 
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of 
start and end of the diameter suffice. Start is always smaller than end. There 
will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. 
A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
There is no limit to the number of arrows that can be shot. An arrow once shot 
keeps travelling up infinitely. The problem is to find the minimum number of 
arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] 
and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
*/

// Greedy: O(n^2)
public class Solution {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n == 0)
            return 0;
        // Sort by end point
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return Integer.compare(a[1], b[1]);    
            }
        });
        
        boolean[] done = new boolean[n];
        int cnt = 0;
        for(int i=0; i<n; ++i){
            if(done[i])
                continue;
            // Shot the arrow at the end point of the ith ballon
            cnt++;
            int point = points[i][1];
            // burst other ballons by the same arrow if possible
            for(int j=i+1; j<n; ++j){
                if(points[j][0] <= point && points[j][1] >= point)
                    done[j] = true;
            }
        }
        return cnt;
    }
}

// O(nlogn) Solution
class Solution2 {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n == 0)
            return 0;
        // Sort by end point
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return Integer.compare(a[1], b[1]);    
            }
        });
        
        // Shot the arrow at the end point of the ballon
        int arrowPos = points[0][1];
        int cnt = 1;
        for(int i=1; i<n; ++i){
            // Prev arrow shoots ith ballon (overlaps)
            if(points[i][0] <= arrowPos && points[i][1] >= arrowPos)
                continue;
            // Need to shoot another arrow, update arrow position
            arrowPos = points[i][1];
            cnt++;
        }
        return cnt;
    }
}
