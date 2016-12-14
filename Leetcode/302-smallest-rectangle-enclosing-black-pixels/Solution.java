/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a 
black pixel. The black pixels are connected, i.e., there is only one black region. 
Pixels are connected horizontally and vertically. Given the location (x, y) of 
one of the black pixels, return the area of the smallest (axis-aligned) 
rectangle that encloses all black pixels.
For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/
// O(rlogc + clogr) Solution (Binary Search)
// Solve the 1D version of this problem first
public class Solution {
    public boolean isAnyRowContainsOne(char[][] image, int c){
        int r = image.length;
        for(int i=0; i<r; ++i){
            if(image[i][c] == '1')
                return true;
        }
        return false;
    }
    
    public boolean isAnyColumnContainsOne(char[][] image, int r){
        int c = image[0].length;
        for(int j=0; j<c; ++j){
            if(image[r][j] == '1')
                return true;
        }
        return false;
    }    
    
    public int getLeft(char[][] image, int l, int h){
        while(h - l > 3){
            int mid = (l+h)/2;
            if(isAnyRowContainsOne(image, mid)){
                h = mid;
            }
            else
                l = mid + 1;
        }    
        for(int i=l; i<=h; ++i){
            if(isAnyRowContainsOne(image, i))
                return i;
        }
        return 0;
    }
    
    public int getRight(char[][] image, int l, int h){
        while(h - l > 3){
            int mid = (l+h)/2;
            if(isAnyRowContainsOne(image, mid)){
                l = mid;
            }
            else
                h = mid - 1;
        }    
        for(int i=h; i>=l; --i){
            if(isAnyRowContainsOne(image, i))
                return i;
        }
        return 0;
    }

    public int getTop(char[][] image, int l, int h){
        while(h - l > 3){
            int mid = (l+h)/2;
            if(isAnyColumnContainsOne(image, mid)){
                h = mid;
            }
            else
                l = mid + 1;
        }    
        for(int i=l; i<=h; ++i){
            if(isAnyColumnContainsOne(image, i))
                return i;
        }
        return 0;
    }

    public int getBottom(char[][] image, int l, int h){
        while(h - l > 3){
            int mid = (l+h)/2;
            if(isAnyColumnContainsOne(image, mid)){
                l = mid;
            }
            else
                h = mid - 1;
        }    
        for(int i=h; i>=l; --i){
            if(isAnyColumnContainsOne(image, i))
                return i;
        }
        return 0;
    }

    public int minArea(char[][] image, int x, int y) {
        int r = image.length;
        int c = image[0].length;
        int left = getLeft(image, 0, y);   
        int right = getRight(image, y, c-1);  
        int top = getTop(image, 0, x);
        int bottom = getBottom(image, x, r-1);
        return (right-left + 1)*(bottom-top+1);
    }
}

// O(rc) Bruteforce
class Solution2 {
    public int minArea(char[][] image, int x, int y) {
        int r = image.length;
        int c = image[0].length;
        int rightX = 0, rightY = 0;
        int leftX = r, leftY = c;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(image[i][j] == '1'){
                    leftX = Math.min(leftX, i);
                    leftY = Math.min(leftY, j);
                    rightX = Math.max(rightX, i);
                    rightY = Math.max(rightY, j);
                }
            }
        }
        return (rightX - leftX + 1)*(rightY - leftY + 1);
    }
}

// BFS : Worst Case O(rc)
class Solution3 {
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

// Related: 1D Problem
class Solution1D{
// int[] a = {0, 1, 1, 1, 1, 1, 0, 0};
// Output: 5
  int binarySearchRight(int[] a, int l, int h){
    while(h - l > 3){
      int mid = (l+h)/2;
      if(a[mid] == 1){
        l = mid;
      }
      // a[mid] == 0
      else{
        h = mid - 1;
      }
    }
    for(int i=h; i>=l; --i)
      if(a[i] == 1)
          return i;
    return -1;
  }

  int binarySearchLeft(int[] a, int l, int h){
    while(h - l > 3){
      int mid = (l+h)/2;
      if(a[mid] == 0){
        l = mid + 1;
      }
      // a[mid] == 1
      else{
        h = mid;
      }
    }
    for(int i=l; i<=h; ++i)
      if(a[i] == 1)
          return i;
    return -1;
  }
  public int length(int[] a, int x){
    int n = a.length;
    int right = binarySearchRight(a, x, n-1);
    int left = binarySearchLeft(a, 0, x);
    return right - left + 1;
  }
}
