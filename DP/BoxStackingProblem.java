// http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
/*
You are given a set of n types of rectangular 3-D boxes, where the i^th box has 
height h(i), width w(i) and depth d(i) (all real numbers). You want to create a 
stack of boxes which is as tall as possible, but you can only stack a box on top 
of another box if the dimensions of the 2-D base of the lower box are each strictly 
larger than those of the 2-D base of the higher box. Of course, you can rotate a 
box so that any side functions as its base. It is also allowable to use multiple 
instances of the same type of box.
*/
import java.util.*;

// 3d LIS
class BoxStackingProblem{
  public void print(int[][] a){
    for(int[] x : a){
      for(int y: x){
        System.out.print(y+ " ");
      }
      System.out.println();
    }
  }  
  // 0 - height
  // 1 - weight
  // 2 - depth
  // width < depth
  public void copy(int[][] box, int i, int h, int w, int d){
    box[i][0] = h;
    box[i][1] = w;
    box[i][2] = d;    
  }

  public int maxHeightLIS(int[][] a){
    // Create all rotations of the box
    int n = a.length;
    n = 3*n;
    int[][] box = new int[n][3];
    int index = 0;
    for(int[] b : a){
      copy(box, index++, b[0], b[1], b[2]);      
      copy(box, index++, b[1], Math.min(b[2], b[0]), Math.max(b[2], b[0]));      
      copy(box, index++, b[2], Math.min(b[1], b[0]), Math.max(b[0], b[1]));      
    }   
    // if the dimensions of the 2-D base of the lower box are each strictly larger 
    // than those of the 2-D base of the higher box.
    // Decreasing base area
    Arrays.sort(box, new Comparator<int[]>(){
        @Override
        public int compare(int[] x, int[] y){
          return Integer.compare(y[1]*y[2], x[1]*x[2]);
        }
    });    

    print(box);

    int[] dp = new int[n];
    for(int i=0; i<n; ++i){
      // height
      dp[i] = box[i][0];
    }

    for(int i=0; i<n; ++i){
      for(int j=0; j<i; ++j){
        if(box[i][1] < box[j][1] && box[i][2] < box[j][2]){
          dp[i] = Math.max(dp[i], dp[j] + box[i][0]);
        }
      }
    }
    int maxHeight = 0;
    for(int i=0; i<n; i++)
        maxHeight = Math.max(maxHeight, dp[i]);
    return maxHeight;    
  }

  public static void main(String[] args){
    BoxStackingProblem ob = new BoxStackingProblem();
    int[][] a = {{4, 6, 7}, {1, 2, 3},{4, 5, 6},{10, 12, 32}};
    System.out.println(ob.maxHeightLIS(a));
  }  
}
