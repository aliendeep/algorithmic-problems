import java.util.*;

class Main{
  public static void inc(TreeMap<Integer, Integer> map, int key){
    map.put(key, map.getOrDefault(key, 0) + 1);
  }

  public static void dec(TreeMap<Integer, Integer> map, int key){
    int nC = map.get(key) - 1;
    if(nC == 0)
      map.remove(key);
    else
      map.put(key, nC);
  }

  public static void print(TreeMap<Integer, Integer> map){
    for(Map.Entry<Integer, Integer> e : map.entrySet()){
      int k = e.getKey();
      int cnt = e.getValue();
      while(cnt-- > 0)
        System.out.println(k);
    }
  }
  // find the smallest index where the element should be inserted  
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int k=0; k<t; ++k){
      if(k > 0)
        System.out.println();
      int battleField = in.nextInt();
      int sg = in.nextInt();
      int sb = in.nextInt();
      // Multi TreeSet
      int gcnt = 0;
      TreeMap<Integer, Integer> green = new TreeMap<>(Collections.reverseOrder());
      for(int i=0; i<sg; ++i){
        inc(green, in.nextInt());
        gcnt++;
      }

      int bcnt = 0;
      TreeMap<Integer, Integer> blue = new TreeMap<>(Collections.reverseOrder());
      for(int i=0; i<sb; ++i){
        inc(blue, in.nextInt());
        bcnt++;
      }

      while(bcnt > 0 && gcnt > 0){
        List<Integer> blueQ = new ArrayList<>();
        List<Integer> greenQ = new ArrayList<>();

        int size = Math.min(battleField, bcnt);
        size = Math.min(size, gcnt);

        for(int f=0; f<size; ++f){
          int b = blue.firstKey(); 
          int g = green.firstKey(); 

          dec(blue, b);
          bcnt--;
          
          dec(green, g);
          gcnt--;

          if(b > g){          
            blueQ.add(b - g);
          }
          else if(b < g){
            greenQ.add(g - b);
          }
        }

        for(int x : blueQ){
          inc(blue, x);
          bcnt++;
        }

        for(int y : greenQ){
          inc(green, y);    
          gcnt++;
        }    
      }

      if(blue.size() == 0 && green.size() == 0){
        System.out.println("green and blue died");
      }
      else if(blue.size() > 0){
        System.out.println("blue wins");            
        print(blue);
      }
      else if(green.size() > 0){
        System.out.println("green wins");            
        print(green);
      }
    }
  }  
}