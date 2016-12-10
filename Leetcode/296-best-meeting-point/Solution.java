/*
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone 
in the group. The distance is calculated using Manhattan Distance, 
where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 
2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the 
two dimension case?
*/

// Collect points in sorted order
public class Solution {
    public int minDistance(List<Integer> A, int median){
        int dist = 0;
        for(int n : A){
            dist += Math.abs(n - median);    
        }
        return dist;
    }
    
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == 1){
                    x.add(i);
                }
            }
        }
        for(int j=0; j<c; j++){
             for(int i=0; i<r; i++){
                if(grid[i][j] == 1){
                    y.add(j);
                }
            }
        }
        int medianX = x.get(x.size()/2);
        int medianY = y.get(y.size()/2);
        return minDistance(x, medianX) + minDistance(y, medianY);
    }
}

class Solution2 {
    // two pointer approach to find median
    public int minDistance(List<Integer> A){
        int i = 0, j = A.size()-1;
        int dist = 0;
        while(i < j){
            dist += A.get(j) - A.get(i);
            i++;
            j--;
        }
        return dist;
    }
    
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        // Collect both the row and column coordinates in sorted order.
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == 1){
                    x.add(i);
                }
            }
        }
        for(int j=0; j<c; j++){
             for(int i=0; i<r; i++){
                if(grid[i][j] == 1){
                    y.add(j);
                }
            }
        }
        return minDistance(x) + minDistance(y);
    }
}
