/*
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TriangleMaking
{
  public int maxPerimeter(int a, int b, int c){
    int result = 0;
    for(int i=1; i<=a; ++i){
      for(int j=1; j<=b; ++j){
        for(int k=1; k<=c; ++k){
          if(i + j > k && j + k > i && k+i > j){
            result = Math.max(result, i + j + k);
          }
        }
      }
    }
    return result;    
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
*/

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TriangleMaking
{
  public int maxPerimeter(int a, int b, int c){
    int result = 0;
    for(int i=1; i<=a; ++i){
      for(int j=1; j<=b; ++j){
        for(int k=1; k<=c; ++k){
          if(i + j > k && j + k > i && k+i > j){
            result = Math.max(result, i + j + k);
          }
        }
      }
    }
    return result;    
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!