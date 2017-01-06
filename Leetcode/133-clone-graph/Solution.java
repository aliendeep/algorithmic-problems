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
        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        mapping.put(node, new UndirectedGraphNode(node.label));
        queue.add(node);
        
        while(!queue.isEmpty()){
            UndirectedGraphNode t = queue.remove();
            for(UndirectedGraphNode adjacentNode : t.neighbors){
                if(mapping.containsKey(adjacentNode) == false){
                    mapping.put(adjacentNode, new UndirectedGraphNode(adjacentNode.label));
                    // Add this neighbor to the queue
                    queue.add(adjacentNode);
                }
                mapping.get(t).neighbors.add(mapping.get(adjacentNode));
            }
        }
        return mapping.get(node);
    }
}

class Solution2 {
    // Use a hashmap
    // BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)    
            return null;

        // label to node
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        Queue<UndirectedGraphNode> Q = new LinkedList<UndirectedGraphNode>();
        Q.add(node);
        visited.add(node.label);
        
        while(!Q.isEmpty()){
            UndirectedGraphNode front = Q.remove();
            
            if(!map.containsKey(front.label)){
               UndirectedGraphNode copied = new UndirectedGraphNode(front.label);
               map.put(front.label, copied);
            }
            
            for(UndirectedGraphNode neighbor : front.neighbors){
                int nlabel = neighbor.label;
                if(!map.containsKey(nlabel)){
                   UndirectedGraphNode copied = new UndirectedGraphNode(nlabel);
                   map.put(nlabel, copied);
                }
                map.get(front.label).neighbors.add(map.get(nlabel));
                if(!visited.contains(nlabel)){
                    Q.add(neighbor);
                    visited.add(nlabel);
                }
            }
        }
        return map.get(node.label);
    }
}

class SolutionDFS {
    public void dfs(UndirectedGraphNode node, Set<Integer> visited, Map<Integer, UndirectedGraphNode> map){
        visited.add(node.label);
        
        if(!map.containsKey(node.label)){
            map.put(node.label, new UndirectedGraphNode(node.label));
        }
        
        for(UndirectedGraphNode neighbor : node.neighbors){
            if(!map.containsKey(neighbor.label)){
                map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
            }
            map.get(node.label).neighbors.add(map.get(neighbor.label));

            if(!visited.contains(neighbor.label))
                dfs(neighbor, visited, map);
        }
    }
    
    // DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)    return null;
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        dfs(node, visited, map);
        return map.get(node.label);
    }
}
