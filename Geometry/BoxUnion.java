import java.util.*;

class YRange{
  public int y1, y2, tag;
  public YRange(int y1, int y2, int tag){
    this.y1 = y1;
    this.y2 = y2;
    this.tag = tag;    
  }
}

class Events{
  public List<YRange> in;
  public List<YRange> out;
  public Events(){
    in = new ArrayList<>();
    out = new ArrayList<>();
  }
}

public class BoxUnion{
  public int area(String[] rectangles){
    // Sorted. unique x, all events at that particular x
    TreeMap<Integer, Events> events = new TreeMap<>();

    int tag = 0;
    for(String r : rectangles){
      tag++;
      Scanner sc = new Scanner(r);
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      if(!events.containsKey(x1))
        events.put(x1, new Events());
      if(!events.containsKey(x2))
        events.put(x1, new Events());

      events.get(x1).in.add(new YRange(y1, y2, tag));
      events.get(x2).out.add(new YRange(y1, y2, tag));
    }

    // Active set of tags for each unique y
    // we need to identify where each each rectangle starts & end
    // positve tag - start 
    // negative tag - end
    // We need all of them sorted
    TreeMap<Integer, TreeSet<Integer>> active = new TreeMap<>();
    int area = 0;
    for(Map.Entry<Integer, Events> entry : events.entrySet()){
      int x = entry.getKey();
      Integer next_x = events.higherKey(x);
      // processed last unique x
      if(next_x == null)    break;
      Events e = entry.getValue();

      // Construct the current active set
      for(YRange y : e.in){
        if(!active.containsKey(y.y1))     active.put(y.y1, new TreeSet<>());
        if(!active.containsKey(y.y2))     active.put(y.y2, new TreeSet<>());

        active.get(y.y1).add(y.tag);
        active.get(y.y2).add(-y.tag);
      }
      for(YRange y : e.out){
        active.get(y.y1).remove(y.tag);
        active.get(y.y2).remove(-y.tag);
      }
      // Compute union of y segments
      Integer lasty = null;
      int ySum = 0;
      int cnt = 0;
      for(Map.Entry<Integer, TreeSet<Integer>> ytags : active.entrySet()){
        int y = ytags.getKey();
        if(cnt == 0){
          lasty = y;
        }
        for(int ytag : ytags.getValue()){
          // closing
          if(ytag < 0)   cnt--;
          else          cnt++;
        }

        if(cnt == 0){
          ySum += y - lasty;
          // reset lasty
          lasty = null;
        }
      }
      area += (next_x - x)*ySum;
    }
    return area;
  }  
}

