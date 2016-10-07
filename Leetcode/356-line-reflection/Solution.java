/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

1. Find the smallest and largest x-value for all points.
2. If there is a line then it should be at y = (minX + maxX) / 2.
3. For each point, make sure that it has a reflected point in the opposite side.
*/
// Note: reflect all given points
public class Solution {
    public boolean isReflected(int[][] points) {
        int n = points.length;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        
        Set<String> set = new HashSet<>();
        for(int i=0; i<n; i++){
            minX = Math.min(minX, points[i][0]);
            maxX = Math.max(maxX, points[i][0]);
            // Add # to divide x and y coordinates
            String str = points[i][0] + "#" + points[i][1];
            set.add(str);
        }
        int sum = minX + maxX;
        for(int i=0; i<n; i++){
            String val = (sum - points[i][0]) + "#" + points[i][1];
            if(!set.contains(val))
                return false;
        }
        return true;
    }
}