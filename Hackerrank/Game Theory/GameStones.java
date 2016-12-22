/*
Two players (numbered  and ) are playing a game with  stones. Player  always plays 
first, and the two players move in alternating turns. The game's rules are as follows:

In a single move, a player can remove either , , or  stones from the game board.
If a player is unable to make a move, that player loses the game.
Given the number of stones, find and print the name of the winner (i.e.,  or ) 
on a new line. Each player plays optimally, meaning they will not make a move 
that causes them to lose the game if some better, winning move exists.

Input Format

The first line contains an integer, , denoting the number of test cases. 
Each of the  subsequent lines contains a single integer, , denoting the number of 
stones in a test case.

Constraints

Output Format

On a new line for each test case, print  if the first player is the winner; 
otherwise, print .

Sample Input

8
1
2
3
4
5
6
7
10
Sample Output

Second
First
First
First
First
First
Second
First
*/
import java.io.*;
import java.util.*;

public class Solution {
    // true - first
    // false - second
    static int[] move = {2, 3, 5};
    public static boolean canWin(int n, Map<Integer, Boolean> dp){
        if(n == 1)
            return false;

        if(n == 2 || n == 3 || n == 5)
            return true;

        if(dp.containsKey(n))
            return dp.get(n);
        
        for(int i=0; i<3; ++i){
            if(n >= move[i]){
                int next = n - move[i];
                if(!canWin(next, dp)){
                    dp.put(n, true);
                    return true;
                }
            }
        }
        dp.put(n, false);
        return false;
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, Boolean> dp = new HashMap<>();
        while(t-- > 0){
            int n = in.nextInt();
            if(canWin(n, dp))
                System.out.println("First");
            else
                System.out.println("Second");                
        }
    }
}

class Solution2 {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            if(n % 7 == 0 || n % 7 == 1)
                System.out.println("Second");
            else
                System.out.println("First");
                
        }
    }
}
