import java.util.*;

class Main{
  static String processLine(String[] word, int index) {
    StringBuilder r = new StringBuilder();
    int i = 0;
    for(String w : word) {
      if(i == index)
        r.append(w.toUpperCase()).append(" ");
      else
        r.append(w).append(" ");
      ++i;
    }
    return r.toString().trim();
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    while(in.hasNext()) {
      String line = in.nextLine();
      Set<String> ignore = new HashSet<>();
      while(!line.equals("::")) {
        ignore.add(line.toLowerCase());
        line = in.nextLine();        
      }

      Map<String, List<String>> map = new TreeMap<>();
      while(in.hasNext()) {
        line = in.nextLine();
        line = line.toLowerCase();
        String[] keywords = line.split(" ");
        for(int i=0; i<keywords.length; ++i) {
          String key = keywords[i];

          if(ignore.contains(key)) {
            continue;
          }

          if(!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
          }
          map.get(key).add(processLine(keywords, i));
        }
      }

      List<String> keys = new ArrayList<>();
      for(String key : map.keySet()) {
        keys.add(key);
      }
      Collections.sort(keys);

      for(String key : keys) {
        List<String> lines = map.get(key);
        for(String l : lines) {
          System.out.println(l);
        }
      }
    }
  }  
}
