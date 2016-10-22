/*
Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
If there are multiple such sequence of shortest length, return all of them. Refer to the example for more details.

Example :

Given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return

  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
 Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class WordLadderAllPaths {
    // BFS + DFS
    // BFS to find the shortest distance to all nodes 
    // DFS to print the path
  public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dictList) {
      Set<String> dict = new HashSet<>(dictList);
      // end is not included in the dictionary
        dict.add(end);
        // <node, neighbors> mapping
        Map<String, ArrayList<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        // bfs to compute distance
        bfs(start, end, dict, neighbors, distance);
        // DFS to print all the paths
        return findPaths(start, end, neighbors, distance);
  }
  
    public ArrayList<ArrayList<String>> findPaths(String word, String end, Map<String, ArrayList<String>> neighbors, 
                                        Map<String, Integer> distance){
        ArrayList<ArrayList<String>> result = new ArrayList();

        if(word.equals(end)){
            ArrayList<String> p = new ArrayList<>();
            p.add(word);
            result.add(p);
            return result;
        }

        ArrayList<String> adjacentNodes = neighbors.get(word);
        if(adjacentNodes != null){
            for(String adjacent : adjacentNodes){
                if(distance.get(adjacent) == distance.get(word) + 1){
                    ArrayList<ArrayList<String>> next = findPaths(adjacent, end, neighbors, distance);
                    for(ArrayList<String> t : next){
                        ArrayList<String> path = new ArrayList<>();
                        path.add(word);
                        path.addAll(t);
                        result.add(path);
                    }
                }
            }
        }
        return result;
    }
      
  
    public void bfs(String start, String end, Set<String> dict, 
                    Map<String, ArrayList<String>> neighbors,  Map<String, Integer> distance){
        Queue<String> Q = new LinkedList<>();
        Q.add(start);
        distance.put(start, 0);
        
        while(!Q.isEmpty()){
            String front = Q.remove();
            if(front.equals(end))
                break;    
            // Find all adjacent nodes
            StringBuilder node = new StringBuilder(front);
            int dist = distance.get(front);
            ArrayList<String> adjacent =  new ArrayList<>();

            for(int l=0; l<node.length(); ++l){
                // change at most one character
                for(int i=0; i<26; ++i){
                    char c = (char)(i + 'a');
                    // same character
                    if(front.charAt(l) == c)
                        continue;
                    
                    node.setCharAt(l, c);

                    String t = node.toString();

                    //  valid word and not visited yet or end word (doesn't exist in the dictionary)
                    if(dict.contains(t) && !distance.containsKey(t)){
                        Q.add(t);
                        adjacent.add(t);
                        distance.put(t, dist + 1);
                    }
                    else if(distance.containsKey(t) && distance.get(t) == dist + 1){
                        adjacent.add(t);
                    }
                    // revert
                    node.setCharAt(l, front.charAt(l));
                }
            }
            // directed edge
            neighbors.put(front, adjacent);
        }        
    } 
}
