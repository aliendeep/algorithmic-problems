/*
Mr. Funt now lives in a country with a very specific tax laws. The total income of mr. 
Funt during this year is equal to n (n ≥ 2) burles and the amount of tax he has to pay 
is calculated as the maximum divisor of n (not equal to n, of course). 
For example, if n = 6 then Funt has to pay 3 burles, while for n = 25 he needs to pay 5 and if n = 2 he pays only 1 burle.

As mr. Funt is a very opportunistic person he wants to cheat a bit. In particular, 
he wants to split the initial n in several parts n1 + n2 + ... + nk = n (here k is arbitrary, even k = 1 is allowed) 
and pay the taxes for each part separately. He can't make some part equal to 1 because it will reveal him. 
So, the condition ni ≥ 2 should hold for all i from 1 to k.

Ostap Bender wonders, how many money Funt has to pay (i.e. minimal) if he chooses and optimal way to split n in parts.

Input
The first line of the input contains a single integer n (2 ≤ n ≤ 2·109) — the total year income of mr. Funt.

Output
Print one integer — minimum possible number of burles that mr. Funt has to pay as a tax.

Examples
input
4
output
2
input
27
output
3
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class D{
  public static boolean isPrime(long n){
    if(n == 2)
      return true;
    if(n % 2 == 0)
      return false;

    for(int i=3; i*i<=n; i+=2){
      if(n % i == 0)
        return false;
    }
    return true;
  }

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);    
    long n = scan.nextLong();
    int r = isPrime(n) ? 1 : ((n % 2 == 0) ? 2 : (isPrime(n-2) ? 2 : 3));
    System.out.println(r);
  }
}
