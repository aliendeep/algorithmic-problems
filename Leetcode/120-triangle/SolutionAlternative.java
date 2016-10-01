/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/
public class Solution {
    // O(n) space
    // Only need the previous row info
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0)
            return 0;
        
        List<Integer> prev = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> row = triangle.get(i);
            if(i == 0){
                prev.add(row.get(0));
                continue;
            }
            List cur = new ArrayList<>();
            // first column, only one option
            cur.add(prev.get(0) + row.get(0));
            for(int j=1; j<row.size()-1; j++){
                int minVal = Math.min(prev.get(j-1), prev.get(j)) + row.get(j);        
                cur.add(minVal);
            }
            // last column, only one option
            cur.add(prev.get(row.size()-2) + row.get(row.size()-1));
            // Assign cur to prev
            prev = cur;
        }
        
        int minSum = Integer.MAX_VALUE;
        for(int j=0; j<prev.size(); j++){
            minSum = Math.min(minSum, prev.get(j));
        }
        return minSum;
    }
}