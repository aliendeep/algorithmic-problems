import java.util.SortedMap;
// Topological Sort + DP
public class Solution {
    class Pair{
        public int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        int nRow = matrix.length;
        if(nRow == 0)
            return 0;
        int nCol = matrix[0].length;
        
        // TreeMap Sorted by key value
        SortedMap<Integer, ArrayList<Pair>> mapping = new TreeMap<Integer, ArrayList<Pair>>();
        // Construct the graph
        for(int i=0; i<nRow; i++){
            for(int j=0; j<nCol; j++){
                if(mapping.containsKey(matrix[i][j])){
                    ArrayList<Pair> l = mapping.get(matrix[i][j]);
                    Pair p = new Pair(i, j);
                    l.add(p);
                    mapping.put(matrix[i][j], l);
                }
                else{
                    ArrayList<Pair> l = new ArrayList<Pair>();
                    l.add(new Pair(i, j));
                    mapping.put(matrix[i][j], l);
                }
            }
        }
        
        int[][] move= {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int x, y, x1, y1;
        int cnt = 0;
        int[][] dp = new int[nRow][nCol]; 
        int maxLength = 0;
        for(Map.Entry<Integer, ArrayList<Pair>> entry : mapping.entrySet()){
            int key = entry.getKey();
            List<Pair> indices = entry.getValue();
            for(Pair p : indices){
                x = p.x;
                y = p.y;
                if(cnt == 0){
                    dp[x][y] = 1;             
                }
                else{
                    int maxValue = 0;
                    for(int i=0; i<4; i++){
                       x1 =  x + move[i][0];     
                       y1 =  y + move[i][1];
                       if(!(x1 >= 0 && x1 < nRow && y1 >= 0 && y1 < nCol))
                            continue;
                       // All numbers adjacent to position (x, y) >= matrix[x][y]
                       if(maxValue < dp[x1][y1]  && matrix[x1][y1] != matrix[x][y]){
                           maxValue = dp[x1][y1];
                       }
                    }
                    dp[x][y] = maxValue + 1;
                }
                if(maxLength < dp[x][y])
                    maxLength = dp[x][y];
            }
            cnt++;
        }
        return maxLength;
    }
}