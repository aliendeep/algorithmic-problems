/*
There exists an island called Arpa’s land, some beautiful girls live there, as ugly ones do.

Mehrdad wants to become minister of Arpa’s land. Arpa has prepared an exam. 
Exam has only one question, given n, print the last digit of 1378n.


Mehrdad has become quite confused and wants you to help him. Please help, although it's a naive cheat.

Input
The single line of input contains one integer n (0  ≤  n  ≤  109).

Output
Print single integer — the last digit of 1378n.

Examples
input
1
output
8
input
2
output
4
Note
In the first example, last digit of 13781 = 1378 is 8.

In the second example, last digit of 13782 = 1378·1378 = 1898884 is 4.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class A{
  // compute pow 8^n % 10
  public static long pow(long n){
    if(n == 0)
      return 1;

    long t = pow(n/2) % 10;
    long r = (t * t) % 10;
    // odd
    if(n % 2 == 1){
      return (8*r)%10;
    }
    return r;
  }

  public static void main(String[] args){
    int n;
    Scanner scan = new Scanner(System.in);    
    n = scan.nextInt();
    System.out.println(pow(n));      
  }
}
