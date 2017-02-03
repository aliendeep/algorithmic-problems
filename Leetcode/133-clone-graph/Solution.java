/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/
         
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
            
        // Original node to copy mapping
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        map.put(node, new UndirectedGraphNode(node.label));
        Q.add(node);
        
        while(!Q.isEmpty()){
            UndirectedGraphNode t = Q.remove();
            for(UndirectedGraphNode adj : t.neighbors){
                if(!map.containsKey(adj)){
                    map.put(adj, new UndirectedGraphNode(adj.label));
                    // Add this neighbor to the queue
                    Q.add(adj);
                }
                // Add this adj node to the neighbor list of copied versin of t
                map.get(t).neighbors.add(map.get(adj));
            }
        }
        return map.get(node);
    }
}

// <Label to node mapping>
class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)    
            return null;

        // label to node
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();

        Queue<UndirectedGraphNode> Q = new LinkedList<UndirectedGraphNode>();
        Q.add(node);
        map.put(node.label, new UndirectedGraphNode(node.label));

        while(!Q.isEmpty()){
            UndirectedGraphNode front = Q.remove();
            for(UndirectedGraphNode neighbor : front.neighbors){
                int nlabel = neighbor.label;
                if(!map.containsKey(nlabel)){
                   map.put(nlabel, new UndirectedGraphNode(nlabel));
                   Q.add(neighbor);
                }
                map.get(front.label).neighbors.add(map.get(nlabel));
            }
        }
        return map.get(node.label);
    }
}

class SolutionDFS {
    public void dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map){
        for(UndirectedGraphNode neighbor : node.neighbors){
            if(!map.containsKey(neighbor.label)){
                map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                dfs(neighbor, map);
            }
            map.get(node.label).neighbors.add(map.get(neighbor.label));
        }
    }
    
    // DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)    return null;
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(node.label, new UndirectedGraphNode(node.label));
        dfs(node, map);
        return map.get(node.label);
    }
}
