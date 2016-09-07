/**
 * Definition for undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
 
class Solution {
public:
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        if(node == NULL)
            return node;
        // Original node to copy mapping
        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> M;
        M[node] = new UndirectedGraphNode(node->label);
                
        queue<UndirectedGraphNode*> Q;
        Q.push(node);
        
        while(!Q.empty()){
            auto t = Q.front();
            Q.pop();
            // For all neigbors of this node t
            for(auto adjacent : t->neighbors){
                // create a copy if needed
                if(M.find(adjacent) == M.end()){
                    M[adjacent] = new UndirectedGraphNode(adjacent->label);
                    Q.push(adjacent);
                }
                M[t]->neighbors.push_back(M[adjacent]);
            }
        }
        return M[node];
    }
};