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
                    map.put(Double.MAX_VALUE, map.getOrDefault(Double.MAX_VALUE, 0) + 1);
                }
                else{
                    double slope = 0.0;
                    if(points[i].y != points[j].y)
                        slope = (double)(points[i].y - points[j].y)/(double)(points[i].x - points[j].x);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
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

// Solution 2
// O(n^2)
class Pair{
    int dx; 
    int dy;
    public Pair(int x, int y){
        dx = x;
        dy = y;
    }

    @Override
    public boolean equals(Object ob){
        if(ob instanceof Pair){
            Pair p = (Pair)ob;
            return p.dx == dx && p.dy == dy;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return dx*10000003 + dy;    
    }
}

// Alternative : hashCode 
class Pair2{
    int dx; 
    int dy;
    public Pair2(int x, int y){
        dx = x;
        dy = y;
    }

    @Override
    public boolean equals(Object ob){
        if(ob instanceof Pair2){
            Pair2 p = (Pair2)ob;
            return p.dx == dx && p.dy == dy;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(dx, dy);
    }
}

// Keep (dx, dy) instead of slope
// Standardize
// Compute gcd
public class Solution {
    public int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
    
    public int maxPoints(Point[] points) {
        int n = points.length;
        int maxPoints = 0;
        for(int i=0; i<n; i++){
            Map<Pair, Integer> map = new HashMap<Pair, Integer>();
            int samePoints = 1;
            for(int j=i+1; j<n; j++){
                // If same point
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    samePoints++;
                    continue;
                }
                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;
                // Stadardize
                if(dx < 0){
                    dx *= -1;
                    dy *= -1;
                }
                else if(dx == 0 && dy < 0){
                    dy *= -1;
                }
                int g = gcd(Math.abs(dx), Math.abs(dy));
                Pair p = new Pair(dx/g, dy/g);
                map.put(p, map.getOrDefault(p, 0) + 1);
            }
            int localMax = 0;
            for(Map.Entry<Pair, Integer> entry : map.entrySet()){
                localMax = Math.max(localMax, entry.getValue());
            }
            localMax += samePoints;
            maxPoints = Math.max(maxPoints, localMax);
        }
        return maxPoints;
    }
}
