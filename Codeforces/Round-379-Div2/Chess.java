/*
http://codeforces.com/contest/734/problem/D
Anton likes to play chess. Also, he likes to do programming. That is why he decided to write the program that plays chess. However, he finds the game on 8 to 8 board to too simple, he uses an infinite one instead.

The first task he faced is to check whether the king is in check. Anton doesn't know how to implement this so he asks you to help.

Consider that an infinite chess board contains one white king and the number of black pieces. There are only rooks, bishops and queens, as the other pieces are not supported yet. The white king is said to be in check if at least one black piece can reach the cell with the king in one move.

Help Anton and write the program that for the given position determines whether the white king is in check.

Remainder, on how do chess pieces move:

Bishop moves any number of cells diagonally, but it can't "leap" over the occupied cells.
Rook moves any number of cells horizontally or vertically, but it also can't "leap" over the occupied cells.
Queen is able to move any number of cells horizontally, vertically or diagonally, but it also can't "leap".
Input
The first line of the input contains a single integer n (1 ≤ n ≤ 500 000) — the number of black pieces.

The second line contains two integers x0 and y0 ( - 109 ≤ x0, y0 ≤ 109) — coordinates of the white king.

Then follow n lines, each of them contains a character and two integers xi and yi ( - 109 ≤ xi, yi ≤ 109) — type of the i-th piece and its position. Character 'B' stands for the bishop, 'R' for the rook and 'Q' for the queen. It's guaranteed that no two pieces occupy the same position.

Output
The only line of the output should contains "YES" (without quotes) if the white king is in check and "NO" (without quotes) otherwise.

Examples
input
2
4 2
R 1 1
B 1 5
output
YES
input
2
4 2
R 3 3
B 1 5
output
NO
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class Chess{
  static class Pair{
    int r;
    int c;
    // type = 0 (rook)
    int type;
    public Pair(int i, int j, int t){
      r = i;
      c = j; 
      type = t;
    }
  }
  public static int dir(int x){
    return (x > 0) ? 1 : ((x < 0) ? -1 : 0);
  }

  public static int getDirection(int dx, int dy){
      int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

      for(int j=0; j<8; ++j){
        // row
        if(dir(dx) != moves[j][0])
          continue;

        // col
        if(dir(dy) != moves[j][1])
          continue;

        if((dx != 0 && dy != 0) && (dx*moves[j][0] != dy*moves[j][1]))
            continue;
        return j;
      }
      return -1;
  }

  public static long distance(int r, int c, int x, int y){
    return Math.max(Math.abs(x - r), Math.abs(y - c)); 
  }

  public static void main(String[] args){
    // king
    int x, y;
    int n;
    Scanner scan = new Scanner(System.in);    
    n = Integer.parseInt(scan.nextLine());

    String s1 = scan.nextLine();
    String[] k = s1.split(" ");
    x = Integer.parseInt(k[0]);  
    y = Integer.parseInt(k[1]);  

    int[] px =  new int[n+1];
    int[] py =  new int[n+1];
    int[] type =  new int[n+1];

    Pair[] closest = new Pair[10];
    Arrays.fill(closest, new Pair(-1, -1, -1));

    long[] diff = new long[10];
    Arrays.fill(diff, Long.MAX_VALUE);

    for(int i=0; i<n; ++i){
      String s2 = scan.nextLine();
      String[] k1 = s2.split(" ");
      // Rook - 0, Bishop 1 Queen 2
      type[i] = (k1[0].equals("R")) ? 0 : ((k1[0].equals("B")) ? 1 : 2);
      px[i] = Integer.parseInt(k1[1]);  
      py[i] = Integer.parseInt(k1[2]);  
      int d = getDirection(x - px[i], y - py[i]);
      if(d == -1) 
          continue;
      long dist = distance(px[i], py[i], x, y);
      if(diff[d] > dist){
        diff[d] = dist;
        closest[d] = new Pair(px[i], py[i], type[i]);
      }
    }

    boolean flag = false;
    for(int j=0; j<8; ++j){
      Pair c = closest[j];
      // rook or queen
      if((c.type == 0  || c.type == 2) && (c.r == x || c.c == y)){
        System.out.println("YES"); 
        flag = true;
        break;
      }
      // bishop or queen
      if((c.type == 1  || c.type == 2) && (Math.abs(c.r - x) == Math.abs(c.c - y))){
        System.out.println("YES"); 
        flag = true;
        break;
      }
    }
    if(flag == false){
        System.out.println("NO");       
    }
  }
}

