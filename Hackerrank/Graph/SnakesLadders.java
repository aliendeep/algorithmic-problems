/*
Markov takes out his Snakes and Ladders game and stares at the board, and wonders: If he had absolute control on the die (singular), and could get it to generate any number (in the range ) he desired, what would be the least number of rolls of the die in which he'd be able to reach the destination square (Square Number ) after having started at the base square (Square Number )?

Rules

Markov has total control over the die and the face which shows up every time he tosses it. You need to help him figure out the minimum number of moves in which he can reach the target square (100) after starting at the base (Square 1).

A die roll which would cause the player to land up at a square greater than 100, goes wasted, and the player remains at his original square. Such as a case when the player is at Square Number 99, rolls the die, and ends up with a 5.

If a person reaches a square which is the base of a ladder, (s)he has to climb up that ladder, and he cannot come down on it. If a person reaches a square which has the mouth of the snake, (s)he has to go down the snake and come out through the tail - there is no way to climb down a ladder or to go up through a snake.

Constraints

The board is always of the size  and Squares are always numbered  to .

 
 

Square number 1 and 100 will not be the starting point of a ladder or a snake. 
No square will have more than one of the starting or ending point of a snake or ladder (e.g. snake 56 to 44 and ladder 44 to 97 is not possible because 44 has both ending of a snake and a starting of a ladder)

Input Format

The first line contains the number of tests, T. T testcases follow.

For each testcase, the first line contain N(Number of ladders) and after that N line follow. Each of the N line contain 2 integer representing the starting point and the ending point of a ladder respectively.

The next line contain integer M(Number of snakes) and after that M line follow. Each of the M line contain 2 integer representing the starting point and the ending point of a snake respectively.

Output Format

For each of the T test cases, output one integer, each in a new line, which is the least number of moves (or rolls of the die) in which the player can reach the target square (Square Number 100) after starting at the base (Square Number 1). This corresponds to the ideal sequence of faces which show up when the die is rolled. 
If there is no solution, print -1.

Sample Input

2
3
32 62
42 68
12 98
7
95 13
97 25
93 37
79 27
75 19
49 47
67 17
4
8 52
6 80
26 42
2 72
9
51 19
39 11
37 29
81 3
59 5
79 23
53 7
43 33
77 21 
Sample Output

3
5
Explanation

For the first test: To traverse the board via the shortest route, the player first rolls the die to get a 5, and ends up at square 6. He then rolls the die to get 6. He ends up at square 12, from where he climbs the ladder to square 98. He then rolls the die to get '2', and ends up at square 100, which is the target square. So, the player required 3 rolls of the die for this shortest and best case scenario. So the answer for the first test is 3.
*/
// Key Idea: Map for keeping track of snakes and ladders

import java.io.*;
import java.util.*;

public class Solution {
    // Shortest path
    public static int minDistance(Map<Integer, Integer> ladders, Map<Integer, Integer> snakes){
        int steps = 0;
        boolean[] visited = new boolean[101];
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        visited[1] = true;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                int n = Q.remove();
                if(n == 100)
                    return steps;
                for(int face=1; face<=6; ++face){
                    int next = n + face;
                    if(next > 100)
                        continue;
                    if(ladders.containsKey(next)){
                        int up = ladders.get(next);
                        if(visited[up] == true)
                            continue;
                        visited[up] = true;
                        Q.add(up);
                    }
                    else if(snakes.containsKey(next)){
                        int down = snakes.get(next);
                        if(visited[down] == true)
                            continue;
                        visited[down] = true;
                        Q.add(down);
                    }
                    else{
                        if(visited[next] == true)
                            continue;
                        visited[next] = true;
                        Q.add(next);
                    }
                }
            }
            steps++;
        }    
        return -1;
    }

    public static void main(String[] args) {
        int a, b;
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            Map<Integer, Integer> ladders = new HashMap<>();
            for(int i=0; i<n; ++i){
                a = in.nextInt();
                b = in.nextInt();
                ladders.put(a, b);
            }
            int m = in.nextInt();
            Map<Integer, Integer> snakes = new HashMap<>();
            for(int i=0; i<m; ++i){
                a = in.nextInt();
                b = in.nextInt();
                snakes.put(a, b);
            }
            System.out.println(minDistance(ladders, snakes));
        }  
    }
}
