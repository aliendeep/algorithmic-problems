/*
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
*/

public class Solution {
    public long getSqrDistance(int[] a, int[] b){
        return (a[0] - b[0])*(a[0] - b[0]) + (a[1] - b[1])*(a[1] - b[1]);
    }
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if(n <= 2)
            return 0;

        int cnt = 0;
        for(int i=0; i<n; ++i){
            Map<Long, Integer> map = new HashMap<>();
            for(int j=0; j<n; ++j){
                if(i == j)
                    continue;
                long dist = getSqrDistance(points[i], points[j]);
                Integer t = map.get(dist);
                if(t == null){
                    map.put(dist, 1);
                }
                else{
                    map.put(dist, t + 1);
                }
            }
            // traverse all entries
            for(Map.Entry<Long, Integer> entry : map.entrySet()){
                int val = entry.getValue();
                cnt += val*(val-1);
            }
        }
        
        return cnt;
    }
}