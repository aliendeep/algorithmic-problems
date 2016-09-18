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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> r;
        if(root == NULL)        return r;
        queue<TreeNode*> Q;
        Q.push(root);

        int cnt = Q.size();
        bool right_to_left = false;
        vector<int> t;
        while(!Q.empty()){
            TreeNode* cur = Q.front();
            t.emplace_back(cur->val);
            Q.pop();
            cnt--;
            
            if(cur->left)
                Q.push(cur->left);
            if(cur->right)
                Q.push(cur->right);

            if(!cnt){
                if(right_to_left) 
                    reverse(t.begin(), t.end());
                r.push_back(t);
                // toggle order
                cnt = Q.size();
                right_to_left = !right_to_left;
                t.clear();
            }
        }
        return r;
    }
};