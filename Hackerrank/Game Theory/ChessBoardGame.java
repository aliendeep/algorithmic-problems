/*
Two players are playing a game on a  chessboard. The rules of the game are as follows:

The game starts with a single coin located at some  coordinate. The coordinate of 
the upper left cell is , and the coordinate of the lower right cell is .
In each move, a player must move the coin from cell  to one of the following locations:

Note: The coin must remain inside the confines of the board.

The players move in alternating turns. The first player who is unable to make a 
move loses the game.

The figure below shows all four possible moves:

chess(1)

Note: While the figure shows a  board, this game is played on a  board.

Given the initial coordinate of the coin, determine which player will win the game. 
Assume both players always move optimally and the first player always moves first.

Input Format

The first line contains an integer, , denoting the number of test cases. 
Each of the  subsequent lines contains  space-separated integers describing the 
respective  and  values of the coin's coordinate.

Constraints

Output Format

On a new line for each test case, print  if the first player is the winner; 
otherwise, print .

Sample Input

3
5 2
5 3
8 8
Sample Output

Second
First
First
*/
import java.io.*;
import java.util.*;

public class Solution {
    static int[][] move = {{-2, 1}, {-2, -1}, {1, -2}, {-1, -2}};
    public final static int boardSize = 15;
    public static boolean isValid(int r, int c){
        if(r <= 0 || r > 15 || c <= 0 || c > 15)
            return false;
        return true;    
    }
    public static Map<Integer, Boolean> dp;

    public static boolean canWin(int r, int c){
        if(!isValid(r, c))
            return false;
        
        if(dp.containsKey(r*boardSize+c))
            return dp.get(r*boardSize+c);
        
        for(int i=0; i<4; ++i){
            int r1 = r + move[i][0];
            int c1 = c + move[i][1];
            if(!isValid(r1, c1))
                continue;
            if(!canWin(r1, c1)){
                dp.put(r*boardSize+c, true);
                return true;
            }
        }
        dp.put(r*boardSize+c, false);
        return false;        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        dp = new HashMap<>();
        while(t-- > 0){
            int r = in.nextInt();
            int c = in.nextInt();
            if(canWin(r, c))
                System.out.println("First");
            else
                System.out.println("Second");                
        }
    }
}