/*
http://codeforces.com/contest/734/problem/B
Recently Anton found a box with digits in his room. There are k2 digits 2, k3 digits 3, k5 digits 5 and k6 digits 6.

Anton's favorite integers are 32 and 256. He decided to compose this integers from digits he has. He wants to make the sum of these integers as large as possible. Help him solve this task!

Each digit can be used no more than once, i.e. the composed integers should contain no more than k2 digits 2, k3 digits 3 and so on. Of course, unused digits are not counted in the sum.

Input
The only line of the input contains four integers k2, k3, k5 and k6 — the number of digits 2, 3, 5 and 6 respectively (0 ≤ k2, k3, k5, k6 ≤ 5·106).

Output
Print one integer — maximum possible sum of Anton's favorite integers that can be composed using digits from the box.

Examples
input
5 1 3 4
output
800
input
1 1 1 1
output
256
Note
In the first sample, there are five digits 2, one digit 3, three digits 5 and four digits 6. Anton can compose three integers 256 and one integer 32 to achieve the value 256 + 256 + 256 + 32 = 800. Note, that there is one unused integer 2 and one unused integer 6. They are not counted in the answer.

In the second sample, the optimal answer is to create on integer 256, thus the answer is 256.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

// Greedy
public class Digit{
  public static void main(String[] args){
    int a, b, c, d;
    Scanner scan = new Scanner(System.in);    
    a = scan.nextInt();
    b = scan.nextInt();
    c = scan.nextInt();
    d = scan.nextInt();
    // create as many 256 as possible
    int n256 = Math.min(a, Math.min(c, d));
    if(n256 > 0){
      a =  a - n256; 
      c =  c - n256; 
      d =  d - n256; 
    }  

    int n32 = Math.min(a, b);
    long sum = 256*n256 + n32*32;
    System.out.println(sum);      
  }
}
