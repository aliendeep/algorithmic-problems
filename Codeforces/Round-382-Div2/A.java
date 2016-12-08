/*
On the way to Rio de Janeiro Ostap kills time playing with a grasshopper he took 
with him in a special box. Ostap builds a line of length n such that some cells 
of this line are empty and some contain obstacles. Then, he places his grasshopper 
to one of the empty cells and a small insect in another empty cell. The grasshopper wants to eat the insect.

Ostap knows that grasshopper is able to jump to any empty cell that is exactly 
k cells away from the current (to the left or to the right). Note that it doesn't 
matter whether intermediate cells are empty or not as the grasshopper makes a jump over them. 
For example, if k = 1 the grasshopper can jump to a neighboring cell only, and if k = 2 the grasshopper can jump over a single cell.

Your goal is to determine whether there is a sequence of jumps such that grasshopper 
will get from his initial position to the cell with an insect.

Input
The first line of the input contains two integers n and k (2 ≤ n ≤ 100, 1 ≤ k ≤ n - 1) — 
the number of cells in the line and the length of one grasshopper's jump.

The second line contains a string of length n consisting of characters '.', '#', 'G' and 'T'. 
Character '.' means that the corresponding cell is empty, character '#' means that the corresponding 
cell contains an obstacle and grasshopper can't jump there. Character 'G' means that 
the grasshopper starts at this position and, finally, 'T' means that the target insect is 
located at this cell. It's guaranteed that characters 'G' and 'T' appear in this line exactly once.

Output
If there exists a sequence of jumps (each jump of length k), such that the grasshopper can get 
from his initial position to the cell with the insect, print "YES" (without quotes) in 
the only line of the input. Otherwise, print "NO" (without quotes).

Examples
input
5 2
#G#T#
output
YES
input
6 1
T....G
output
YES
input
7 3
T..#..G
output
NO
input
6 2
..GT..
output
NO
Note
In the first sample, the grasshopper can make one jump to the right in order to get from cell 2 to cell 4.

In the second sample, the grasshopper is only able to jump to neighboring cells but the way to the insect is free — he can get there by jumping left 5 times.

In the third sample, the grasshopper can't make a single jump.

In the fourth sample, the grasshopper can only jump to the cells with odd indices, thus he won't be able to reach the insect.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class A{
  public static void main(String[] args){
    int n, k;
    Scanner scan = new Scanner(System.in);    
    n = scan.nextInt();
    k = scan.nextInt();
    // read the new line
    scan.nextLine();
    String s = scan.nextLine();
    int g = -1;
    int insect = -1;
    // find g
    for(int i=0; i<n; ++i){
      if(s.charAt(i) == 'G'){
        g = i;
      }
      else if(s.charAt(i) == 'T'){
        insect = i;
      }
    }    
    int l = Math.min(g, insect);
    int h = Math.max(g, insect);
    int i;
    for(i=l; i<=h; i+=k){
      if(s.charAt(i) == '#')
        break;
    }
    if(i == h+k){
      System.out.println("YES");
    }
    else{
      System.out.println("NO");      
    }
  }
}
