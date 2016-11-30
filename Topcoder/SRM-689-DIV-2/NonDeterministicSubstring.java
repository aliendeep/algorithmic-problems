import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class NonDeterministicSubstring
{
  boolean match(String x, String y){
    int n = x.length();
    for(int i=0; i<n; ++i){
      if(x.charAt(i) != y.charAt(i) && y.charAt(i) != '?')
        return false; 
    }
    return true;
  }
  
  public long ways(String A, String B)
  {
    int n = A.length();
    int m = B.length();
    Set<String> set = new HashSet<>();
    for(int i=0; i+m<=n; ++i){
      set.add(A.substring(i, i+m));
    }
    long cnt = 0;
    for(String str : set){
      if(match(str, B))
        cnt++;
    } 
    return cnt;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!