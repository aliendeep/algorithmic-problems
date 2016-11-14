import java.util.*;

public class Equal {
    class Pair{
        int x;
        int y;
        public Pair(int x1, int y1){
            x = x1;
            y = y1;
        }
    }    
    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        int n = a.size();
        ArrayList<Integer> result = new ArrayList<>();
        if(n < 4)   return result;
        Map<Integer, ArrayList<Pair>> map = new HashMap<>();
        for(int i=0; i<n; ++i){
            for(int j=i+1; j<n; ++j){
                int sum = a.get(i) +  a.get(j);
                if(map.containsKey(sum)){
                    ArrayList<Pair> list = map.get(sum);
                    Pair p = list.get(0);
                    if(p.x != i && p.y != j  && p.y != i && p.x != j){
                        list.add(new Pair(i, j));
                        map.put(sum,list);
                    }
                }
                else {
                    ArrayList<Pair> list = new ArrayList<>();
                    list.add(new Pair(i, j));
                    map.put(sum, list);
                }            
            }
        }
        
        for(Map.Entry<Integer, ArrayList<Pair>> entry : map.entrySet()){
            ArrayList<Pair> list = entry.getValue();
            if(list.size() >= 2){
                Pair p = list.get(0);
                Pair q = list.get(1);
                if(result.size() == 0){
                    result.add(p.x);
                    result.add(p.y);
                    result.add(q.x);
                    result.add(q.y);                    
                }                
                else{
                    if(result.get(0) >  p.x || 
                    (result.get(0) == p.x && result.get(1) > p.y) ||
                    (result.get(0) == p.x  && result.get(1) == p.y && result.get(2) > q.x) || 
                    (result.get(0) == p.x  && result.get(1) == p.y && result.get(2) == q.x && 
                        result.get(3) > q.y)){
                        result.set(0, p.x);
                        result.set(1, p.y);
                        result.set(2, q.x);
                        result.set(3, q.y);
                    }
                }
            }
        }
        return result;
    }
    public void print(ArrayList<Integer> l, ArrayList<Integer> a){
        for(int t : l){
            System.out.print(" nums[" + t + "] = " + a.get(t) + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] t = {3, 4, 7, 1, 2, 9, 8};
        ArrayList<Integer> a = new ArrayList<>();
        for(int n : t)
            a.add(n);
        Equal ob = new Equal();
        ArrayList<Integer> r = ob.equal(a);
        ob.print(r, a);
    }    
}