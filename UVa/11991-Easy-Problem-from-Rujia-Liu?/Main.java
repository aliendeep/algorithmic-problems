import java.util.*;
import java.io.*;

class Main{
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while((line = in.readLine()) != null) {
      String[] w = line.split(" ");
      int n = Integer.parseInt(w[0]);
      int m = Integer.parseInt(w[1]);

      Map<Integer, List<Integer>> map = new HashMap<>();
      line = in.readLine();
      String[] nums = line.split(" ");
      for(int i=0; i<n; ++i) {
        int x = Integer.parseInt(nums[i]);
        if(!map.containsKey(x)) {
          map.put(x, new ArrayList<>());
        }
        map.get(x).add(i+1);
      }

      for(int i=0; i<m; ++i) {
        line = in.readLine();
        w = line.split(" ");
        int k = Integer.parseInt(w[0]);
        int v = Integer.parseInt(w[1]);
        if(!map.containsKey(v) || map.get(v).size() < k)
          System.out.println(0);
        else {
          System.out.println(map.get(v).get(k-1));          
        }
      }
    }
  }  
}
