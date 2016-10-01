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
    TreeNode* sortedArrayToBST(vector<int>& nums, int low, int high){
        if(high < low)  return NULL;
        int mid = (low + high)/2;
        TreeNode* root = new TreeNode(nums[mid]);
        root->left = sortedArrayToBST(nums, low, mid-1);
        root->right = sortedArrayToBST(nums, mid+1, high);
        return root;
    }
    
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        if(nums.size() == 0)    return NULL;
        return sortedArrayToBST(nums, 0, nums.size()-1);
    }
};