/*
Given an m x n matrix of positive integers representing the height of each unit 
cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 
and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
*/

public class Solution {
    // Priority Queue
    class CellInfo{
        int x, y, h;
        public CellInfo(int r, int c, int h){
            this.x = r;
            this.y = c;
            this.h = h;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int R = heightMap.length;
        if(R == 0)                  
            return 0;
        int C = heightMap[0].length;
            
        PriorityQueue<CellInfo> minHeap = new PriorityQueue<>(1, new Comparator<CellInfo>(){
            @Override
            public int compare(CellInfo a, CellInfo b){
                return Integer.compare(a.h, b.h);
            }
        });
        
        boolean[][] visited = new boolean[R][C];
        // Add all boundary cell into the min heap
        for(int r=0; r<R; ++r){
            minHeap.add(new CellInfo(r, 0, heightMap[r][0]));
            visited[r][0] = true;
            minHeap.add(new CellInfo(r, C-1, heightMap[r][C-1]));
            visited[r][C-1] = true;
        }
        for(int c=0; c<C; ++c){
            minHeap.add(new CellInfo(0, c, heightMap[0][c]));
            visited[0][c] = true;
            minHeap.add(new CellInfo(R-1, c, heightMap[R-1][c]));
            visited[R-1][c] = true;
        }
        
        int[][] move = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int vol = 0;
        while(!minHeap.isEmpty()){
            CellInfo t = minHeap.poll();
            for(int i=0; i<4; i++){
                int x1 = t.x + move[i][0];
                int y1 = t.y + move[i][1];
                if(x1 < 0 || x1 >= R || y1 < 0 || y1 >= C || visited[x1][y1])
                    continue;
                // If the water level of this adjacent cell is lower, then we can 
                // collect this water and increase the water level of that cell if needed
                if(heightMap[x1][y1] < t.h){
                    vol += t.h - heightMap[x1][y1];
                }
                                                 // adjust height
                minHeap.add(new CellInfo(x1, y1, Math.max(t.h, heightMap[x1][y1])));
                visited[x1][y1] = true;
            }
        }
        return vol;
    }
}
