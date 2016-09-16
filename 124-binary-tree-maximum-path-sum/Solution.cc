/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    // (best path that ends at the root, best answer)
    #define best_path first
    #define best_result second
    #define INF 100000000
    pair<int, int> getMaxPath(TreeNode* node){
        if(node == NULL)    return {-INF, -INF};
        pair<int, int> r = {node->val, node->val};
        if(node->left == NULL && node->right == NULL)    
            return r;
            
        pair<int, int> left = getMaxPath(node->left);
        pair<int, int> right = getMaxPath(node->right);
        r.best_path = node->val + max(max(left.best_path, right.best_path),0);
        r.best_result = max(max(left.best_result, right.best_result), node->val + max(left.best_path, 0) + max(right.best_path, 0));
        return r;
    }
    
    int maxPathSum(TreeNode* root) {
        pair<int, int> r = getMaxPath(root);
        return r.best_result;
    }
};