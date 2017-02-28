import java.util.*;

class Main{
  // find the smallest index where the element should be inserted  
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = Integer.parseInt(in.nextLine());
    // blank line
    in.nextLine();
    for(int k=0; k<t; ++k){
      if(k > 0)
        System.out.println();
      TreeMap<String, Integer> map = new TreeMap<>();
      int cnt = 0;
      while(in.hasNext()){
        String key = in.nextLine();
        // blank line
        if(key.length() == 0)
          break;
        map.put(key, map.getOrDefault(key, 0) + 1);
        cnt++;
      }
      for(Map.Entry<String, Integer> e : map.entrySet()){
        double x = (100.0*e.getValue()) / cnt; 
        System.out.print(e.getKey() + " ");
        System.out.printf("%.4f\n", x);
      }
    }
  }  
}