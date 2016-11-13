// Sum of two sides is greater than the third side
import java.util.*;

public class CountingTriangles{
  public static long MOD = 1000000007;
  public int nTriang(ArrayList<Integer> A) {
      Collections.sort(A);
      int n = A.size();
      long cnt = 0;
      for(int i=0; i<n-2; ++i){
          int k = i+2;
          for(int j=i+1; j<n; ++j){
              while(k < n && A.get(i) + A.get(j) > A.get(k))
                  k++;
              System.out.println(i + " j= " + j + "  k=" + k);
              cnt = (cnt + k - j - 1) % MOD;
          }
      }
      return (int)(cnt % MOD);
  }  

  public static void main(String[] args){
    Integer[] a = {1, 1, 1, 2, 2};
    ArrayList<Integer> A = new ArrayList<>();
    for(int t : a)
        A.add(t);
    CountingTriangles ob = new CountingTriangles();
    System.out.println(ob.nTriang(A));
  }
}