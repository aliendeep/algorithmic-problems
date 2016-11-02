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
    // Topological Sort
    public String alienOrder(String[] words) {
        int n = words.length;
        // Create the graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<27; ++i){
            graph.add(new ArrayList<>());
        }

        int[] visited = new int[27];
        Set<Integer> nodes = new HashSet<>();
        // All nodes
        for(int k=0; k<n; k++){
            String x = words[k];
            for(int i=0; i<x.length(); i++){
                nodes.add(x.charAt(i) - 'a');
            }
        }
        
        // Adjacent pair
        for(int k=0; k<n-1; k++){
            String x = words[k];
            String y = words[k+1];
            int i = 0;
            // Find first mismatched pair
            while(i < Math.min(x.length(), y.length())){
                int p = x.charAt(i) - 'a';
                int q = y.charAt(i) - 'a';
                // Different char
                if(p != q){                
                    // Directed
                    List<Integer> edge = graph.get(p);
                    edge.add(q);
                    break;
                }
                i++;
            }
            // handle invalid case
            if(i < x.length() && i == y.length()){
                int p = x.charAt(i) - 'a';
                List<Integer> edge = graph.get(p);
                edge.add(26);
            }
        }
        
        List<Integer> allNodes = new ArrayList<>();
        allNodes.addAll(nodes);
        // To handle special case like ["wrtkj","wrt"]. Create a dummy node 27
        graph.set(26, allNodes);

        List<Integer> result = new ArrayList<>();
        // Start from the super node
        if(!dfs(26, visited, graph, result))
            return "";

        StringBuilder str = new StringBuilder();
        // Skip the dummy node
        for(int i=result.size()-2; i>=0; --i){
            str.append((char)(result.get(i) + 'a'));
        }
        return str.toString();
    }
    
    // returns false if cycle exists
    public boolean dfs(int node, int[] visited, List<List<Integer>> graph, List<Integer> result){
        visited[node] = 1;

        List<Integer> edges = graph.get(node);
        if(edges.size() > 0){
            for(int neighbor : edges){
                // if color is gray, then cycle exists
                if(visited[neighbor] == 2)               continue;
                if(visited[neighbor] == 1)               return false;
                if(!dfs(neighbor, visited, graph, result))
                    return false;
            }
        }
        
        visited[node] = 2;
        result.add(node);
        return true;
    }    
}