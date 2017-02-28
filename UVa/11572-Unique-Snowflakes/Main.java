import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while(t-- > 0){
      int n = in.nextInt();
      int[] nums = new int[n];
      for(int i=0; i<n; ++i){
        nums[i] = in.nextInt();
      }
      // no repeat
      Set<Integer> set = new HashSet<>();
      int start = 0, end = 0;
      int maxLen = 0;
      while(end < n){
          int c = nums[end];
          // Extend the range
          if(set.add(c)){
              end++;
              maxLen = Math.max(maxLen, end - start);
          }
          // repeat
          else{
              // Reduce the range
              while(start < end && set.contains(c)){
                  set.remove(nums[start]);
                  start++;
              }
          }
      }
      System.out.println(maxLen);
    }
  }  
}