import java.util.*;

class Main{
  static class Pair{
    int t;
    int cur;
    int interval;

    public Pair(int x, int current, int i){
      t = x;
      cur = current;
      interval = i;
    }
  }
  // find the smallest index where the element should be inserted  
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(100, new Comparator<Pair>(){
      @Override
      public int compare(Pair a, Pair b){
        if(a.cur == b.cur)
          return Integer.compare(a.t, b.t);
        return Integer.compare(a.cur, b.cur);
      }
    });

    String x = in.next();
    while(!x.equals("#")){
      int t = in.nextInt();
      int interval = in.nextInt();
      minHeap.add(new Pair(t, interval, interval));
      x = in.next();
    }
    int k = in.nextInt();
    while(k-- > 0 && minHeap.size() > 0){
      Pair top = minHeap.remove();
      System.out.println(top.t);
      minHeap.add(new Pair(top.t, top.cur + top.interval, top.interval));
    }
  }  
}