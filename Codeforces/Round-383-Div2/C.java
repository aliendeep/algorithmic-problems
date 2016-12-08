/*

As you have noticed, there are lovely girls in Arpa’s land.

People in Arpa's land are numbered from 1 to n. Everyone has exactly one crush, 
i-th person's crush is person with the number crushi.


Someday Arpa shouted Owf loudly from the top of the palace and a funny game started in
 Arpa's land. The rules are as follows.

The game consists of rounds. Assume person x wants to start a round, he calls crushx and 
says: "Oww...wwf" (the letter w is repeated t times) and cuts off the phone immediately. 
If t > 1 then crushx calls crushcrushx and says: "Oww...wwf" (the letter w is repeated t - 1 times) 
and cuts off the phone immediately. The round continues until some person receives an "Owf" (t = 1). 
This person is called the Joon-Joon of the round. There can't be two rounds at the same time.

Mehrdad has an evil plan to make the game more funny, he wants to find smallest t (t ≥ 1) such that 
for each person x, if x starts some round and y becomes the Joon-Joon of the round, then by starting from y, 
x would become the Joon-Joon of the round. Find such t for Mehrdad if it's possible.

Some strange fact in Arpa's land is that someone can be himself's crush (i.e. crushi = i).

Input
The first line of input contains integer n (1 ≤ n ≤ 100) — the number of people in Arpa's land.

The second line contains n integers, i-th of them is crushi (1 ≤ crushi ≤ n) — the number of i-th person's crush.

Output
If there is no t satisfying the condition, print -1. Otherwise print such smallest t.

Examples
input
4
2 3 1 4
output
3
input
4
4 4 4 4
output
-1
input
4
2 1 4 3
output
1
Note
In the first sample suppose t = 3.

If the first person starts some round:

The first person calls the second person and says "Owwwf", then the second person 
calls the third person and says "Owwf", then the third person calls the first person and 
says "Owf", so the first person becomes Joon-Joon of the round. So the condition is satisfied if x is 1.

The process is similar for the second and the third person.

If the fourth person starts some round:

The fourth person calls himself and says "Owwwf", then he calls himself again and 
says "Owwf", then he calls himself for another time and says "Owf", so the fourth person 
becomes Joon-Joon of the round. So the condition is satisfied when x is 4.

In the last example if the first person starts a round, then the second person 
becomes the Joon-Joon, and vice versa.

*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class C{
  public static int cycleLen;
  public final static int White = 0;
  public final static int Gray = 1;
  public final static int Black = 2;

  public static long gcd(long a, long b){
    if(b == 0)  return a;
    return gcd(b, a % b);
  }

  public static long lcm(List<Integer> len){
    long running = len.get(0);
    for(int i=1; i<len.size(); ++i){
      int a = len.get(i);
      running = (long)(a*running)/gcd(a, running);
    }
    return running;
  }    

  // directed graph
  public static boolean cycleExists(int node, int[] graph, int[] visited){
    visited[node] = Gray;
    cycleLen++;
    int adj = graph[node];
    if(visited[adj] == Gray)
        return true;
    if(visited[adj] != Black){
      // not visited before
      if(cycleExists(adj, graph, visited))
        return true;
    }
    visited[node] = Black;
    return false;
  }

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);    
    int n = scan.nextInt();
    int[] indegree = new int[n+1];
    Arrays.fill(indegree, 0);

    int[] graph = new int[n+1];
    // 1 indexing
    for(int i=1; i<=n; ++i){
      int node = scan.nextInt();;
      graph[i] = node;
      indegree[node]++;      
    }

    int[] visited = new int[n+1];
    boolean possible = true;
    for(int i=1; i<=n; ++i){
      if(indegree[i] == 0){
        possible = false;
        break;
      }
    }
    List<Integer> len = new ArrayList<>();
    if(possible){
      for(int i=1; i<=n; ++i){
        if(visited[i] == 0){
          cycleLen = 0;
          if(!cycleExists(i, graph, visited) || cycleLen == 0){
            possible = false;
            break;
          }
          else{
            // even length cycle
            if(cycleLen % 2 == 0)
              cycleLen /= 2;
            len.add(cycleLen);         
          }
        }
      }
    }
    if(possible == false)
      System.out.println(-1);
    else{
      // lcm of all cycle length
      System.out.println(lcm(len)); 
    }
  }
}
