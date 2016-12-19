/*
You are given a tree (a simple connected graph with no cycles). The tree has N
nodes numbered from  to  and is rooted at node .

Find the maximum number of edges you can remove from the tree to get a forest 
such that each connected component of the forest contains an even number of vertices.

Input Format

The first line of input contains two integers  and .  is the number of vertices, 
and  is the number of edges. 
The next  lines contain two integers  and  which specifies an edge of the tree.

Constraints

Note: The tree in the input will be such that it can always be decomposed into 
components containing an even number of nodes.

Output Format

Print the number of removed edges.

Sample Input

10 9
2 1
3 1
4 3
5 2
6 1
7 2
8 6
9 8
10 8
Sample Output

2
*/
import java.io.*;
import java.util.*;

public class Solution {
    static class Node{
        public int val;
        public  List<Node> children;
        public Node(int v){
            val = v;
            children = new ArrayList<>();
        }
    }
    
    static class Pair{
        public int cnt;
        public int removedEdges;
        public Pair(int c, int r){
            cnt = c;
            removedEdges = r;
        }
    }
    // Count the number of nodes in the subtrees rooted at node (number of nodes, # of removed edges)
    public static Pair countNodes(Node node, Node parent){
        Pair r = new Pair(0, 0);
        int cnt = 0, removedEdges = 0;
        for(Node child : node.children){
            if(child == parent)
                continue;
            Pair c = countNodes(child, node);
            r.removedEdges += c.removedEdges;
            // removed the subtree
            if(c.cnt % 2 == 0){
                r.removedEdges++;
            }
            // increment the cnt of the number of nodes
            else{
                r.cnt += c.cnt;
            }
        }    
        // include node
        r.cnt++;
        return r;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);    
        int n = scan.nextInt();
        int m = scan.nextInt();
        Node[] nodes = new Node[n+1];
        for(int i=1; i<=n; ++i){  
            nodes[i] = new Node(i);
        }
        for(int i=0; i<m; ++i){  
            int a = scan.nextInt();
            int b = scan.nextInt();
            nodes[a].children.add(nodes[b]);
            nodes[b].children.add(nodes[a]);
        }
        Pair r = countNodes(nodes[1], new Node(0));
        System.out.println(r.removedEdges);
    }
}
