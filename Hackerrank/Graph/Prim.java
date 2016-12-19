// https://www.hackerrank.com/challenges/primsmstsub
import java.io.*;
import java.util.*;

// Time Complexity: O(n^2)
public class Solution {
    // extractMin : O(n)
    public static int extractMin(boolean[] taken, int[] key){
        int n = key.length;
        int r = -1;
        int val = Integer.MAX_VALUE;
        for(int i=1; i<n; ++i){
            if(taken[i] == true)
                continue;
            if(val > key[i]){
                val = key[i];
                r = i;
            }
        }
        return r;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();                
        int x, y, w;
        
        List<List<Integer>> graph = new ArrayList<>();  
        for(int i=0; i<=n; ++i)
            graph.add(new ArrayList<>());
        
        int[][] matrix = new int[n+1][n+1];
        for(int i=0; i<m; ++i){
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextInt();
            graph.get(x).add(y);
            graph.get(y).add(x);
            matrix[x][y] = w;
            matrix[y][x] = w;
        }
        // Start is the arbitrary starting vertex
        int start = in.nextInt();        
        int[] parent = new int[n+1];
        for(int i=1; i<=n; ++i)
            parent[i] = i;
        
        int[] key = new int[n+1];
        Arrays.fill(key, Integer.MAX_VALUE);
        
        boolean[] taken = new boolean[n+1];
        key[start] = 0;

        // Number of edges = n-1
        for(int i=0; i<n-1; ++i){
            int u = extractMin(taken, key);
            taken[u] = true;
            List<Integer> adj = graph.get(u);
            // relax
            if(adj == null)
                continue;
            for(int v : adj){
                if(taken[v])
                    continue;
                if(matrix[u][v] < key[v]){
                   key[v] = matrix[u][v];
                   parent[v] = u;
                }
            }
        }
        int sum = 0;
        for(int i=1; i<=n; ++i){
            sum += matrix[i][parent[i]];
        }
        System.out.println(sum);
    }
}