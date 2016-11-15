// http://codeforces.com/contest/734/problem/A
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class Game{
  public static void main(String[] args){
    int n;
    Scanner scan = new Scanner(System.in);    
    n = Integer.parseInt(scan.nextLine());
    String s = scan.nextLine();
    int a = 0;
    int b = 0;
    for(int i=0; i<n; ++i){
      if(s.charAt(i) == 'A')    a++;
      if(s.charAt(i) == 'D')    b++;
    }
    if(a > b){
      System.out.println("Anton");
    }
    else if(a < b){
      System.out.println("Danik");
    }
    else{
      System.out.println("Friendship");      
    }
  }
}
