/*
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
*/
public class Solution {
    // Observations:
    // Large rectangle area sum should be equal to the sum of the smaller rectangles
    // Count of all points should be even
    // Count of the corner points of the large rectangle should be one
    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles.length == 0)
            return true;
        // Corner points (x1, y1), (x2, y2)
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0;
        Set<String> set = new HashSet<>();
        // Find the corner points
        for(int[] rect : rectangles){
            x1 = Math.min(x1, rect[0]);            
            y1 = Math.min(y1, rect[1]);            
            x2 = Math.max(x2, rect[2]);            
            y2 = Math.max(y2, rect[3]);            
            
            area += (rect[2] - rect[0])*(rect[3] - rect[1]);
            String[] cornerPoints = new String[4];
            cornerPoints[0]  = rect[0] + "#" + rect[1]; 
            cornerPoints[1] = rect[0] + "#" + rect[3]; 
            cornerPoints[2] = rect[2] + "#" + rect[1]; 
            cornerPoints[3] = rect[2] + "#" + rect[3]; 
            // Count of all points should be even
            for(String point : cornerPoints){
                if(set.contains(point))
                    set.remove(point);
                else
                    set.add(point);
            }
        }
        String[] cornerPoints = new String[4];
        cornerPoints[0] = x1 + "#" + y1; 
        cornerPoints[1] = x1 + "#" + y2; 
        cornerPoints[2] = x2 + "#" + y1; 
        cornerPoints[3] = x2 + "#" + y2; 
        
        if(set.size() != 4) 
            return false;
        // Count of the corner points of the large rectangle should be one
        for(String point : cornerPoints){
            if(!set.contains(point))
                return false;
        }
        
        // Large rectangle area sum should be equal to the sum of the smaller rectangles
        return (x2 - x1) * (y2 - y1) == area;
    }
}