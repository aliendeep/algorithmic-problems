import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            List<String> words = new ArrayList<>();
            Set<Integer> nodes = new HashSet<>();
            String t = in.next();
            while(!t.equals("#")) {
                words.add(t);
                for(int i=0; i<t.length(); i++){
                    nodes.add(t.charAt(i) - 'A');
                }
                t = in.next();
            }
            int n = words.size();
            // Create the graph
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0; i<26; ++i){
                graph.add(new ArrayList<>());
            }

            // Adjacent pair
            for(int k=0; k<n-1; k++){
                String x = words.get(k);
                String y = words.get(k+1);
                int i = 0;
                // Find first mismatched pair
                while(i < Math.min(x.length(), y.length())){
                    int p = x.charAt(i) - 'A';
                    int q = y.charAt(i) - 'A';
                    // Different char
                    if(p != q){                
                        // Directed
                        graph.get(p).add(q);
                        break;
                    }
                    i++;
                }
            }

            List<Integer> result = new ArrayList<>();
            int[] visited = new int[26];
            for(int node : nodes){
                if(visited[node] == 0){
                    dfs(node, visited, graph, result);
                }            
            }

            for(int i=result.size()-1; i>=0; --i){
                char c = (char)(result.get(i) + 'A');
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // returns false if cycle exists
    public static boolean dfs(int node, int[] visited, List<List<Integer>> graph, List<Integer> result){
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