// Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    class GraphNode{
        UndirectedGraphNode node;
        public GraphNode(UndirectedGraphNode n){
            node = n;
        }
        
        @Override
        public boolean equals(Object ob){
            if(ob instanceof GraphNode) {
                GraphNode p = (GraphNode)ob;
                if(p.node.label != node.label)
                    return false;
                
                List<UndirectedGraphNode> x = p.node.neighbors;
                List<UndirectedGraphNode> y = node.neighbors;
                return x.equals(y);
            }
            return false;
        }
        
        @Override
        public int hashCode(){
            List<UndirectedGraphNode> x = node.neighbors;
            int r = node.label;
            for(UndirectedGraphNode neigh : x){
                r = r*10 + neigh.label;
            }
            return r;
        }
    }
    Map<GraphNode, UndirectedGraphNode> map;
    Set<GraphNode> visited;
    
    void cloneRecursive(UndirectedGraphNode node){
        GraphNode p = new GraphNode(node);
        if(!map.containsKey(p))
            map.put(p, new UndirectedGraphNode(node.label));

        List<UndirectedGraphNode> neighbors = node.neighbors;
        for(UndirectedGraphNode adj : neighbors){
            GraphNode q = new GraphNode(adj);
            if(!map.containsKey(q))
                map.put(q, new UndirectedGraphNode(adj.label));
            map.get(p).neighbors.add(map.get(q));
            if(visited.contains(q))
                continue;

            visited.add(q);
            cloneRecursive(adj);
        }
    }
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        GraphNode p = new GraphNode(node);
        map = new HashMap<>();
        visited = new HashSet<>();
        visited.add(p);
        cloneRecursive(node);
        return map.get(p);
    }
}
