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
// Recursive
public class Solution {
    // One pass algorithm
    class Info{
        int val;
        int depth;
        public Info(int v, int d){
            val = v;
            depth = d;
        }
    }
    
    void getDistance(TreeNode node, int distance, int depth, TreeMap<Integer, List<Info>> map){
        if(node == null)
            return;
        
        List<Info> list = map.get(distance);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(new Info(node.val, depth));
        map.put(distance, list);
        
        getDistance(node.left, distance - 1, depth+1, map);        
        getDistance(node.right, distance + 1, depth+1, map);        
    }   
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null)
            return Collections.EMPTY_LIST;
            
        TreeMap<Integer, List<Info>> map = new TreeMap<>();
        getDistance(root, 0, 0, map);
        
        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<Info>> entry : map.entrySet()){
            List<Info> l = entry.getValue();
            if(l.size() > 1){
                // sort based on depth
                Collections.sort(l, new Comparator<Info>(){
                    @Override
                    public int compare(Info a, Info b){
                        return Integer.compare(a.depth, b.depth);
                    }
                });
            }
            List<Integer> t = new ArrayList<>();
            for(Info i : l)
                t.add(i.val);

            result.add(t);
        }

        return result;
    }
}''