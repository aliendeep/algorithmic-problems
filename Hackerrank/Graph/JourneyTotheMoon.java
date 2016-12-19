import java.io.*;
import java.util.*;

public class Solution {
    // dfs - count number of components and component at each element
    static  int cnt = 0;
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void dfs(int node){
        cnt++;
        List<Integer> adj = graph.get(node);
        for(int neighbor : adj){
            if(visited[neighbor])
                continue;
            visited[neighbor] = true;
            dfs(neighbor);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);    
        int n = scan.nextInt();
        int I = scan.nextInt();        
        graph = new ArrayList<>();
        for(int i=0; i<n; ++i)
            graph.add(new ArrayList<>());

        for(int i=0; i<I; ++i){
            int a = scan.nextInt();
            int b = scan.nextInt();
            // undirected
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        visited = new boolean[n];
        List<Integer> comp =  new ArrayList<>();
  
        for(int i=0; i<n; ++i){
            if(visited[i] == false){
                cnt = 0;
                visited[i] = true;
                dfs(i);
                comp.add(cnt);
            }
        }
        int ncomp = comp.size();
        long[] cumsum = new long[ncomp];
        long[] sumProd = new long[ncomp];
        long[] cumSumProd = new long[ncomp];
        // DP (Sum of Pairwise Product) Interesting O(n)
        // 4 numbers say: a, b, c, d
        // Result: a x b + (a + b) x c + (a + b + c) x d 
        cumsum[0] = 0;
        sumProd[0] = 0;
        cumSumProd[0] = 0;
        for(int i=1; i<ncomp; ++i){
            cumsum[i] = cumsum[i-1] + comp.get(i-1);
            sumProd[i] =  cumsum[i]*comp.get(i);
            cumSumProd[i] = cumSumProd[i-1] + sumProd[i];
        }
        long result = cumSumProd[ncomp-1];
        System.out.println(result);
    }
}