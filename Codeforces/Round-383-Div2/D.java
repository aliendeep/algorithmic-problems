/*
Just to remind, girls in Arpa's land are really nice.

Mehrdad wants to invite some Hoses to the palace for a dancing party. Each Hos has 
some weight wi and some beauty bi. Also each Hos may have some friends. Hoses are divided 
in some friendship groups. Two Hoses x and y are in the same friendship group if and only 
if there is a sequence of Hoses a1, a2, ..., ak such that ai and ai + 1 are friends 
for each 1 ≤ i < k, and a1 = x and ak = y.


Arpa allowed to use the amphitheater of palace to Mehrdad for this party. Arpa's 
amphitheater can hold at most w weight on it.

Mehrdad is so greedy that he wants to invite some Hoses such that sum of their 
weights is not greater than w and sum of their beauties is as large as possible. 
Along with that, from each friendship group he can either invite all Hoses, or no more than one. 
Otherwise, some Hoses will be hurt. Find for Mehrdad the maximum possible total beauty of 
Hoses he can invite so that no one gets hurt and the total weight doesn't exceed w.

Input
The first line contains integers n, m and w (1  ≤  n  ≤  1000, , 1 ≤ w ≤ 1000) — 
the number of Hoses, the number of pair of friends and the maximum total weight of those who are invited.

The second line contains n integers w1, w2, ..., wn (1 ≤ wi ≤ 1000) — the weights of the Hoses.

The third line contains n integers b1, b2, ..., bn (1 ≤ bi ≤ 106) — the beauties of the Hoses.

The next m lines contain pairs of friends, the i-th of them contains two integers 
xi and yi (1 ≤ xi, yi ≤ n, xi ≠ yi), meaning that Hoses xi and yi are friends. 
Note that friendship is bidirectional. All pairs (xi, yi) are distinct.

Output
Print the maximum possible total beauty of Hoses Mehrdad can invite so that 
no one gets hurt and the total weight doesn't exceed w.

Examples
input
3 1 5
3 2 5
2 4 2
1 2
output
6
input
4 2 11
2 4 6 6
6 4 2 1
1 2
2 3
output
7
Note
In the first sample there are two friendship groups: Hoses {1, 2} and Hos {3}. 
The best way is to choose all of Hoses in the first group, sum of their weights 
is equal to 5 and sum of their beauty is 6.

In the second sample there are two friendship groups: Hoses {1, 2, 3} and Hos {4}. 
Mehrdad can't invite all the Hoses from the first group because their total weight 
is 12 > 11, thus the best way is to choose the first Hos from the first group and 
the only one from the second group. The total weight will be 8, and the total beauty will be 7.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

// Union find + Knapsack
public class D{
  static int[] parent;
  static int[] rank;

  public static int findSet(int node){
    if(parent[node] != node){
      return findSet(parent[node]);
    }
    else
      return node;
  }

  public static void link(int u, int v){
    if(rank[u] > rank[v])
      parent[v] = u;
    else{
      parent[u] = v;
      if(rank[u] == rank[v])
        rank[v] = rank[v] + 1;
    }
  }

  public static void union(int x, int y){
    link(findSet(x), findSet(y));
  }

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);    
    int n = scan.nextInt();
    int m = scan.nextInt();
    int capacity = scan.nextInt();
    int[] weights = new int[n+1];

    for(int i=1; i<=n; ++i){
      weights[i] = scan.nextInt();      
    }    
    int[] values = new int[n+1];
    for(int i=1; i<=n; ++i){
      values[i] = scan.nextInt();      
    }  

    // Form the group
    parent = new int[n+1];
    rank = new int[n+1];   
    Arrays.fill(rank, 0);

    for(int i=1; i<=n; ++i){
      parent[i] = i;
    }

    for(int i=0; i<m; ++i){
      int a = scan.nextInt();
      int b = scan.nextInt();
      union(a, b);
    }
    List<List<Integer>> graph = new ArrayList<>();
    // init
    for(int i=0; i<=n; ++i)
      graph.add(new ArrayList<>());

    for(int i=1; i<=n; ++i){
      graph.get(findSet(i)).add(i);
    }

    int[] dp = new int[capacity+1];
    Arrays.fill(dp, 0);

    // Knapsack for all groups
    for(int i=1; i<=n; i++){
      int leader = findSet(i);
      // if i is not the leader of the its group
      if(leader != i)
          continue;
      for(int w=capacity; w >= 0; w--){
        int groupW = 0;
        int groupV = 0;
        List<Integer> members = graph.get(leader);  
        for(int member : members){
          if(weights[member] <= w){
            // take this member
            dp[w] = Math.max(dp[w], dp[w - weights[member]] + values[member]);
          }
          groupW += weights[member];
          groupV += values[member];
        }
        if(groupW <= w){
          dp[w] = Math.max(dp[w], dp[w - groupW] + groupV);
        }
      }
    }
    System.out.println(dp[capacity]);
  }
}
