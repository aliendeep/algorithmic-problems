import java.util.*;

class Main{
  static List<Set<Integer>> graph;
  static Map<Integer, Integer> indegree;
  static int n;

  public static void bktk(List<Integer> cur, int lev, Set<Integer> visited) {
    if(lev == n) {
      for(int x : cur) {
        System.out.print((char)(x+'a'));
      }
      System.out.println();
    }

    for(int i=0; i<26; ++i) {
      int node = i;
      if(!indegree.containsKey(node))
        continue;

      if(indegree.get(node) == 0 && !visited.contains(node)) {
        Set<Integer> adj = graph.get(node);
        for(int neighbor : adj) {
          indegree.put(neighbor,  indegree.get(neighbor) - 1);
        }
        cur.add(node);
        visited.add(node);

        bktk(cur, lev+1, visited);

        //bktk
        for(int neighbor : adj) {
          indegree.put(neighbor,  indegree.get(neighbor) + 1);
        }
        cur.remove(cur.size()-1);
        visited.remove(node);
      }
    }
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    boolean first = true;

    while(in.hasNext()) {
      if(!first)
        System.out.println();
      String line = in.nextLine();
      String[] w = line.split(" ");
      
      graph = new ArrayList<>();
      indegree = new HashMap<>();

      for(int i=0; i<26; ++i) {
        graph.add(new HashSet<>());
      }

      for(String c : w){
        indegree.put(c.charAt(0) - 'a', 0);
      }

      n = indegree.size();

      line = in.nextLine();
      String[] constraint = line.split(" ");
      for(int i=0; i<constraint.length; i += 2) {
        int x = constraint[i].charAt(0) - 'a';
        int y = constraint[i+1].charAt(0) - 'a';
        graph.get(x).add(y);
        indegree.put(y,  indegree.get(y) + 1);
      }

      Set<Integer> visited = new HashSet<>();
      bktk(new ArrayList<>(), 0, visited);
      first = false;
    }
  }  
}
