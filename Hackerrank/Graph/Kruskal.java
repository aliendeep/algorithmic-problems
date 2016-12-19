// Cormen : 569
// https://www.hackerrank.com/challenges/kruskalmstrsub

import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;
    static int[] rank;
    
    static class Edge{
        public int u, v;
        public int w;
        public Edge(int u1, int v1, int w1){
            u = u1;
            v = v1;
            w = w1;
        }
    }
    
    public static int findSet(int x){
        if(x != parent[x])
            parent[x] = findSet(parent[x]);
        return parent[x];
    }
    
    public static void link(int x, int y){
        if(rank[x] > rank[y])
            parent[y] = x;
        else{
            parent[x] = y;
            if(rank[x] == rank[y])
                rank[y] = rank[x] + 1;    
        }
    }
    
    public static void union(int x, int y){
        link(findSet(x), findSet(y));
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        // Make Set
        parent = new int[n+1];
        for(int i=0; i<=n; ++i)
            parent[i] = i;
        
        rank = new int[n+1];
        Arrays.fill(rank, 0);
        
        int x, y, w;
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<m; ++i){
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextInt();  
            edges.add(new Edge(x, y, w));
        }
        
        // Sort the edges into non decreasing order by weight w
        Collections.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge a, Edge b){
                // same weight
                if(a.w == b.w)
                    return Integer.compare(a.w + a.u + a.v, b.w + b.u + b.v);                       
                return Integer.compare(a.w, b.w);
            }
        });
        
        // Weights
        List<Integer> r = new ArrayList<>();
        for(Edge e : edges){
            int u = e.u;
            int v = e.v;
            if(findSet(u) != findSet(v)){
                r.add(e.w);
                union(u, v);
            }
        }
        // Sum of the weights
        int sum = 0;
        for(int t : r)
            sum += t;
        System.out.println(sum);
    }
}