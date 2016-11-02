/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
1. You may assume all letters are in lowercase.
2. If the order is invalid, return an empty string.
3. There may be multiple valid order of letters, return any one of them is fine.
*/


/*
Sample Input:
["wrt","wrf","er","ett","rftt"]
["baa", "abcd", "abca", "cab", "cad"]
["caa", "aaa", "aab"]

Sample Output:
"wertf"
"bdac"
"cab"
*/

public class Solution {
    public final static int White = 0;
    public final static int Gray = 1;
    public final static int Black = 2;
    
    public void print(List<Integer> r){
        for(int n : r){
            char c = (char)(n + 'a');
            System.out.print(c + " ");
        }
        System.out.println();
    }
    
    public void printMap(Map<Integer, List<Integer>> graph){
        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()){
            char c = (char)(entry.getKey() + 'a');
            System.out.println("Node: "+c);
            System.out.println("Adjacency List: ");
            List<Integer> edge = graph.get(entry.getKey());
            print(edge);
        }
    }
    
    // Topological Sort
    public String alienOrder(String[] words) {
        // Create the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> color = new HashMap<>();
        List<Integer> nodes = new ArrayList<>();
        
        int n = words.length;
        int startNode = -1;
        
        // init color
        for(int k=0; k<n; k++){
            String x = words[k];
            for(int i=0; i<x.length(); i++){
                int key = x.charAt(i) - 'a';
                if(!color.containsKey(key))
                    nodes.add(key);
                color.put(key, White);
            }
        }

        // Adjacent pair
        for(int k=0; k<n-1; k++){
            String x = words[k];
            String y = words[k+1];
            int i = 0, j = 0;
            // Find first mismatched pair
            while(i<x.length() && j<y.length()){
                // You may assume all letters are in lowercase.
                int p = x.charAt(i) - 'a';
                int q = y.charAt(j) - 'a';
                
                if(p != q){                
                    if(startNode == -1)
                        startNode = p;
                    // Directed
                    List<Integer> edge = graph.get(p);
                    if(edge == null)
                        edge = new ArrayList<>();

                    edge.add(q);
                    graph.put(p, edge);    
                    break;
                }
                i++;
                j++;
            }
            // handle invalid case
            if(i < x.length() && j == y.length()){
                int p = x.charAt(i) - 'a';
                List<Integer> edge = graph.get(p);
                if(edge == null)
                    edge = new ArrayList<>();

                edge.add(-1);
                graph.put(p, edge);    
            }
        }
        
        // To handle special case like ["wrtkj","wrt"]. Create a dummy node -1
        graph.put(-1, nodes);
        color.put(-1, White);
        
        List<Integer> r = new ArrayList<>();
        for(int key : color.keySet()){
            if(color.get(key) == White){
                char c = (char)(key + 'a');
                if(!dfs(graph, color, key, r))
                    return "";
            }            
        }

        StringBuilder s = new StringBuilder();
        for(int i=r.size()-1; i>=0; i--){
            s.append((char)(r.get(i) + 'a'));
        }
        return s.toString();
    }
    
    // returns false if cycle exists
    public boolean dfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> color, int node, List<Integer> r){
        char c = (char)(node + 'a');
        color.put(node, Gray);
        List<Integer> edges = graph.get(node);
        if(edges != null){
            for(int adj : edges){
                // if color is gray, then cycle exists
                if(color.get(adj) == Gray)                return false;
                if(color.get(adj) == Black)                continue;
                if(!dfs(graph, color, adj, r))
                    return false;
            }
        }
        color.put(node, Black);
        // Skip the dummy node
        if(node != -1)
            r.add(node);
        return true;
    }    
}