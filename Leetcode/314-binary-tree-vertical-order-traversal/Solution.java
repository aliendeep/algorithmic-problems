/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
Tricky case: [3,9,8,4,0,1,7,null,null,null,2,5]
[[4],[9,5],[3,0,1],[8,2],[7]]
We can't process level i+1 before inserting all relevant nodes in the ith level
*/
// BFS
public class Solution {
    class Info{
        TreeNode n;
        int dir;
        public Info(TreeNode nn, int d){
            n = nn;
            dir = d;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        if(root == null)
            return r;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Info> Q = new LinkedList<>();
        Q.add(new Info(root, 0));
        while(!Q.isEmpty()){
            Info front = Q.remove();
            if(!map.containsKey(front.dir)){
                List<Integer> l = new ArrayList<>();
                l.add(front.n.val);
                map.put(front.dir, l);
            }
            else{
                List<Integer> l = map.get(front.dir);
                l.add(front.n.val);
                map.put(front.dir, l);
            }
            if(front.n.left != null)    
                Q.add(new Info(front.n.left, front.dir - 1));
            if(front.n.right != null)    
                Q.add(new Info(front.n.right, front.dir + 1));
        }
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            r.add(entry.getValue());
        }
        return r;
    }
}