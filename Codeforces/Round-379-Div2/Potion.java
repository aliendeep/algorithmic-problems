/*
http://codeforces.com/contest/734/problem/C
Anton is playing a very interesting computer game, but now he is stuck at one of the levels. To pass to the next level he has to prepare n potions.

Anton has a special kettle, that can prepare one potions in x seconds. Also, he knows spells of two types that can faster the process of preparing potions.

Spells of this type speed up the preparation time of one potion. There are m spells of this type, the i-th of them costs bi manapoints and changes the preparation time of each potion to ai instead of x.
Spells of this type immediately prepare some number of potions. There are k such spells, the i-th of them costs di manapoints and instantly create ci potions.
Anton can use no more than one spell of the first type and no more than one spell of the second type, and the total number of manapoints spent should not exceed s. Consider that all spells are used instantly and right before Anton starts to prepare potions.

Anton wants to get to the next level as fast as possible, so he is interested in the minimum number of time he needs to spent in order to prepare at least n potions.

Input
The first line of the input contains three integers n, m, k (1 ≤ n ≤ 2·109, 1 ≤ m, k ≤ 2·105) — the number of potions, Anton has to make, the number of spells of the first type and the number of spells of the second type.

The second line of the input contains two integers x and s (2 ≤ x ≤ 2·109, 1 ≤ s ≤ 2·109) — the initial number of seconds required to prepare one potion and the number of manapoints Anton can use.

The third line contains m integers ai (1 ≤ ai < x) — the number of seconds it will take to prepare one potion if the i-th spell of the first type is used.

The fourth line contains m integers bi (1 ≤ bi ≤ 2·109) — the number of manapoints to use the i-th spell of the first type.

There are k integers ci (1 ≤ ci ≤ n) in the fifth line — the number of potions that will be immediately created if the i-th spell of the second type is used. It's guaranteed that ci are not decreasing, i.e. ci ≤ cj if i < j.

The sixth line contains k integers di (1 ≤ di ≤ 2·109) — the number of manapoints required to use the i-th spell of the second type. It's guaranteed that di are not decreasing, i.e. di ≤ dj if i < j.

Output
Print one integer — the minimum time one has to spent in order to prepare n potions.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

// Binary Search
public class Potion{
  public static void main(String[] args){
    int n, m;
    int k;
    Scanner scan = new Scanner(System.in);    
    n = scan.nextInt();
    m = scan.nextInt();
    k = scan.nextInt();

    long x, s;
    x = scan.nextLong();
    s = scan.nextLong();
    long[] a = new long[m+1];
    long[] b = new long[m+1];
    for(int i=0; i<m; ++i){
      a[i] = scan.nextLong();      
    }
    // extra element  
    a[m] =  x;

    for(int i=0; i<m; ++i){
      b[i] = scan.nextLong();      
    }    
    // extra element  
    b[m] =  0;

    long[] c = new long[k+1];
    long[] d = new long[k+1];
    for(int i=0; i<k; ++i){
      c[i] = scan.nextLong();      
    }    
    for(int i=0; i<k; ++i){
      d[i] = scan.nextLong();      
    }    

    long result = n*x;
    long p, t;
    for(int i=0; i<=m; ++i){
      if(b[i] > s)
          continue;
      long point = s;
      p = n;
      // time
      t = a[i];
      point -= b[i];
      // binary Search
      int l = 0;
      int h = k-1;
      while(h - l > 3){
        int mid = (h-l)/2 + l;
        if(d[mid] > point){
          h = mid - 1;
        }
        // d[mid] <= point
        else{
          l = mid;
        }
      }
      for(int j=h; j>=l; --j){
        if(d[j] <= point){
          p = Math.max(n - c[j], 0);
          break;
        }
      }

      result = Math.min(result, t*p);      
    }

    System.out.println(result);
  }
}
