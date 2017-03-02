import java.util.*;

class Main{
  static int binarySearch(List<Integer> r, int l, int h, int val) {
    while(h - l > 3) {
      int mid = (l + h)/2;
      if(r.get(mid) < val) {
        l = mid + 1;
      }
      else
        h = mid;
    }
    for(int i=l; i<=h; ++i)
      if(r.get(i) >= val)
        return i;

    return h + 1;
  }
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while(t-- > 0) {
      int m = in.nextInt();
      int n = in.nextInt();

      int[] nums = new int[m];
      for(int i=0; i<m; ++i) {
        nums[i] = in.nextInt();
      }

      int u, a = 0;
      ArrayList<Integer> r = new ArrayList<>();
      for(int i=0; i<n; ++i) {
        u = in.nextInt();
        while(a < u) {
          int index = binarySearch(r, 0, r.size()-1, nums[a]);
          if(index < 0)   
            index = - (index + 1);

          r.add(index, nums[a]);
          ++a;
        }
        System.out.println(r.get(i));
      }

      if(t > 0)   System.out.println();
    }
  }  
}
