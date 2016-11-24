/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

// Union Find - Basic Solution
// Cormen
public class Solution {
    int n;
    int[] parent;
    int[] rank;

    // Convert 2d index (r, c) to 1d index 
    // n*r + c (n is the number of rows)
    public int getID(int x, int y){
        return n*x + y;
    }
    
    public int findSet(int x){
        if(x != parent[x])
            parent[x] = findSet(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        link(findSet(x), findSet(y));
    }
    
    public void link(int x, int y){
        if(rank[x] > rank[y])
            parent[y] = x;
        else{
            parent[x] = y;
            if(rank[x] == rank[y]){
                rank[y] = rank[x] + 1;
            }
        }
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        this.n = n;
        List<Integer> result =  new ArrayList<>();
        parent = new int[m*n];
        rank = new int[m*n];
        
        Arrays.fill(parent, -1);        
        Arrays.fill(rank, 0);        
        
        int landCnt = 0;
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        for(int[] pos : positions){
            int u = getID(pos[0], pos[1]);
            // Add new land
            parent[u] = u;
            landCnt++;

            for(int i=0; i<4; i++){
                int x = pos[0] + move[i][0];
                int y = pos[1] + move[i][1];
                int v = getID(x, y);
                // invalid or water
                if(x < 0 || x >= m || y < 0 || y >= n || parent[v] == -1)
                    continue;
                
                // If neighbor is another island, then connect them and decrease land cnt
                if(findSet(u) != findSet(v)){
                    union(u, v);
                    landCnt--;
                }
            }
            result.add(landCnt);
        }
        return result;
    }
}