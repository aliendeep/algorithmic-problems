// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
// O(n^2)
// [[1, 2], [3, 6], [5, 6]]
public class Solution {
    public void print(Point p){
        System.out.print("[ "+p.x + " , " + p.y +" ] ");
    }
    public int maxPoints(Point[] points) {
        int n = points.length;
        int maxPoints = 0;
        for(int i=0; i<n; i++){
            // (Slope, count)
            Map<Double, Integer> map = new HashMap<Double, Integer>();
            int samePoints = 1;
            for(int j=i+1; j<n; j++){
                // If same point
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    samePoints++;
                }
                // vertical (slope infinity)
                else if(points[i].x == points[j].x){
                    if(map.containsKey(Double.MAX_VALUE))
                        map.put(Double.MAX_VALUE, map.get(Double.MAX_VALUE)+1);
                    else
                        map.put(Double.MAX_VALUE, 1);
                }
                else{
                    double slope = 0.0;
                    if(points[i].y != points[j].y)
                        slope = (double)(points[i].y - points[j].y)/(double)(points[i].x - points[j].x);
                    if(map.containsKey(slope))
                        map.put(slope, map.get(slope)+1);
                    else
                        map.put(slope, 1);
                }
            }
            int localMax = 0;
            for(Map.Entry<Double, Integer> entry : map.entrySet()){
                localMax = Math.max(localMax, entry.getValue());
            }
            localMax += samePoints;
            maxPoints = Math.max(maxPoints, localMax);
        }
        return maxPoints;
    }
}