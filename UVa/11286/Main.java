import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int n;
    while((n = in.nextInt()) != 0){
      HashMap<String, Integer> map = new HashMap<>();
      int max = 0;
      for(int i=0; i<n; ++i){
        int[] courses = new int[5];
        for(int j=0; j<5; ++j){
          int id = in.nextInt();
          courses[j] = id;
        }

        Arrays.sort(courses);
        StringBuilder t = new StringBuilder();
        for(int j=0; j<5; ++j){
          t.append(courses[j]);
        }
        int v = map.getOrDefault(t.toString(), 0) + 1;
        map.put(t.toString(), v);
        max = Math.max(max, v);  
      }

      int cnt = 0;
      for(int val : map.values()){
        cnt += (val == max) ? val : 0;
      }
      System.out.println(cnt);
    }
  }  
}