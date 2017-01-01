/*
Given n points on a 2D plane, find the maximum number of points that lie on the 
same straight line.

Sample Input :

(1, 1)
(2, 2)
Sample Output :

2
You will be give 2 arrays X and Y. Each point is represented by (X[i], Y[i])
*/
class Pair{
    int dx; 
    int dy;
    public Pair(int x, int y){
        dx = x;
        dy = y;
    }

    @Override
    public boolean equals(Object ob){
        if(ob instanceof Pair){
            Pair p = (Pair)ob;
            return p.dx == dx && p.dy == dy;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return dx*10000003 + dy;    
    }
}

public class Solution {
    public int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }

  public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
      int n = a.size();
      int max = 0;
      int same = 0;
      for(int i=0; i<n; ++i){
          Map<Pair, Integer> map = new HashMap<>();
          same = 1;
          for(int j=i+1; j<n; ++j){
              if(a.get(i) == a.get(j) && b.get(i) == b.get(j)){
                  same++;
                  continue;
              }
              int dx = a.get(j) - a.get(i);     
              int dy = b.get(j) - b.get(i);
              if(dx < 0){
                  dx *= -1;
                  dy *= -1;
              }
              else if(dx == 0 && dy < 0){
                  dy *= -1;
              }
                int g = gcd(Math.abs(dx), Math.abs(dy));
                Pair p = new Pair(dx/g, dy/g);
                map.put(p, map.getOrDefault(p, 0) + 1);
          }
          int local = 0;
          for(Map.Entry<Pair, Integer> entry : map.entrySet()){
              local = Math.max(local, entry.getValue());
          }
          max = Math.max(max, local + same);
      }
      return  max;
  }
}
