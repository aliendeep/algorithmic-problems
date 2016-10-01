public class Solution {
    class ArrayEntry{
        int val;
        int x;
        int y;
        ArrayEntry(int val, int x, int y){
            this.val = val;
            this.x = x;
            this.y = y;
        }
    };
    // min heap
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if(n == 0)
            return -1;
        int[][] visited = new int[n][n];
        for(int[] row : visited)
            Arrays.fill(row, 0);
        
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<ArrayEntry>(k, new Comparator<ArrayEntry>(){
            @Override
            public int compare(ArrayEntry a, ArrayEntry b){
                return Integer.compare(a.val, b.val);
            }
        });
        
        minHeap.add(new ArrayEntry(matrix[0][0], 0, 0));
        visited[0][0] = 1;
        
        int cnt = 0;
        int result = matrix[0][0];
        while(cnt < k){
            ArrayEntry p = minHeap.remove();
            int x = p.x;
            int y = p.y;
            result = p.val;
            if(x+1 < n && visited[x+1][y] == 0){
                minHeap.add(new ArrayEntry(matrix[x+1][y], x+1, y));
                visited[x+1][y] = 1;
            }
            if(y+1 < n && visited[x][y+1] == 0){
                minHeap.add(new ArrayEntry(matrix[x][y+1], x, y+1));
                visited[x][y+1] = 1;
            }
            cnt++;
       }
       return result;
    }
}