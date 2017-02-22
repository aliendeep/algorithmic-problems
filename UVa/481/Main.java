// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=422
import java.util.*;

class Main{
  // find the smallest index where the element should be inserted  
  public static int binarySearch(List<Integer> nums, int[] dp, int n, int target){
    int l = 0, h = n - 1;
    while(h - l > 3){
      int mid = (l+h)/2;
      if(nums.get(dp[mid]) < target){
        l = mid + 1;
      }
      else
        h = mid;
    }
    for(int i=l; i<=h; ++i){
      if(nums.get(dp[i]) >= target)
        return i;
    }
    return h + 1;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    List<Integer> a = new ArrayList<>();
    while(in.hasNext()){
      a.add(in.nextInt());
    }

    // LIS
    int n = a.size();
    int[] par = new int[n];
    Arrays.fill(par, -1);
    // store the indices
    int[] dp = new int[n];

    int len = 1;
    for(int i=1; i<n; ++i){
      // smallest value
      if(a.get(i) < a.get(dp[0])){
        par[i] = -1;
        dp[0] = i;
      }
      else if(a.get(i) > a.get(dp[len-1])){
        par[i] = dp[len-1];
        dp[len] = i;
        len++;
      }
      else{
        int index = binarySearch(a, dp, len, a.get(i));
        dp[index] = i;
        par[i] = dp[index - 1];
      }
    }

    System.out.println(len);
    System.out.println("-");
    // print the one that occurs last in the input file
    int curIndex = dp[len-1];
    List<Integer> r = new ArrayList<>();
    while(curIndex != -1){  
      r.add(a.get(curIndex));
      curIndex = par[curIndex];
    }
    for(int i=r.size()-1; i>=0; --i){
      System.out.println(r.get(i));      
    }
  }  
}