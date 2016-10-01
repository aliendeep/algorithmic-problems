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