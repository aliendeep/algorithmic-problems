/*
Sample Input:
["0010","0110","0100"]
0
2
Sample Output:
6
42
*/
public class Solution {
    class Pair{
        int x, y;
        public Pair(int i, int j){
            x = i;
            y = j;
        }
    }

    // Alternative
    public int minArea(char[][] image, int sx, int sy) {
        int r = image.length;
        int c = image[0].length;
        boolean[][] visited = new boolean[r][c];

        int x1, y1, x, y;
        int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<Pair> Q = new LinkedList<>();
        Q.add(new Pair(sx, sy));
        visited[sx][sy] = true;

        int leftX = sx, leftY = sy;
        int rightX = sx, rightY = sy;
        
        while(!Q.isEmpty()){
            Pair t = Q.remove();
            x = t.x;
            y = t.y;
            for(int i=0; i<4; i++){
                x1 = x + move[i][0];                
                y1 = y + move[i][1];
                if(x1 < 0 || x1 >= r || y1 < 0 || y1 >= c || visited[x1][y1] || image[x1][y1] == '0')
                    continue;
                leftX = Math.min(leftX, x1);    
                leftY = Math.min(leftY, y1);    
                rightX = Math.max(rightX, x1);    
                rightY = Math.max(rightY, y1); 
                Q.add(new Pair(x1, y1));
                visited[x1][y1] = true;
            }
        }
        return (rightX - leftX + 1)*(rightY - leftY + 1);
    }
}