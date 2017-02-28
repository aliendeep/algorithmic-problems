import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int x;
    while((x = in.nextInt()) != 0) {
      int n = x;
      int maxSum = Integer.MIN_VALUE;
      int run = 0;
      for(int i=0; i<n; ++i) {
        int num = in.nextInt();
        run += num;
        if(run < 0)
          run = 0;
        maxSum = Math.max(maxSum, run);
      }
      if(maxSum <= 0)
        System.out.println("Losing streak.");
      else
        System.out.println("The maximum winning streak is " + maxSum + ".");
    }
  }  
}
