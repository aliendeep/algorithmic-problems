/*
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[5,5],[10,10],[10,0]]

Answer: False

Explanation:
*/
// http://mathworld.wolfram.com/PolygonArea.html
public class Solution {
    public int getArea(List<List<Integer>> points){
        int n = points.size();
        int area = 0;
        int x1, y1, x2, y2;
        for(int i=0; i<n; i++){
            x1 = points.get(i).get(0);
            y1 = points.get(i).get(1);
            x2 = points.get((i+1) % n).get(0);
            y2 = points.get((i+1) % n).get(1);
            area += (x1*y2 - x2*y1);
        }
        return area;
    }
    
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        // the area of a convex polygon is defined to be positive if the points 
        // are arranged in a counterclockwise order, and negative if they are in 
        // clockwise order 
        if(getArea(points) < 0){
            Collections.reverse(points);
        }
        
        for(int i=0; i<n; ++i){
            List<List<Integer>> sublist = new ArrayList<>();
            sublist.add(points.get(i));
            sublist.add(points.get((i+1) % n));
            sublist.add(points.get((i+2) % n));
            if(getArea(sublist) < 0)
                return false;
        }
        return true;
    }
}