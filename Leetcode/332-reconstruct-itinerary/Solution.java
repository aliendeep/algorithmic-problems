/*
Given a list of airline tickets represented by pairs of departure and arrival 
airports [from, to], reconstruct the itinerary in order. All of the tickets 
belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that 
has the smallest lexical order when read as a single string. For example, the 
itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. 
But it is larger in lexical order.
*/
public class Solution {
    // DFS
    void dfs(String start, Map<String, PriorityQueue<String>> graph, List<String> it){
        if(graph.containsKey(start)){
            PriorityQueue<String> adjacency = graph.get(start);
            while(!adjacency.isEmpty()){
                dfs(adjacency.poll(), graph, it);
            }
        }
        it.add(start);
    }
    
    public List<String> findItinerary(String[][] tickets) {
        // Use hashmap to build the graph (priority queue to maintain lexical order)
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(int i=0; i<tickets.length; i++){
            String x = tickets[i][0];
            String y = tickets[i][1];
            PriorityQueue<String> adjacency = graph.get(x);
            if(adjacency == null){
                adjacency = new PriorityQueue<>();
            }
            adjacency.add(y);
            graph.put(x, adjacency);
        }
        
        String start = new String("JFK");   
        // use the it as stack
        List<String> it = new ArrayList<>();
        dfs(start, graph, it);
        Collections.reverse(it);
        return it;
    }
}
