import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class ParenthesesDiv2Medium
{
  class Pair{
    char ch;
    int index;
    public Pair(char c, int i){
      ch = c;
      index = i;
    }
  }
  
  public int[] correct(String s)
  {
    int n = s.length();
    // Remove all balanced parenthesis
    Deque<Pair> stk = new LinkedList<>();
    for(int i=0; i<n; ++i){
      char c = s.charAt(i);
      if(c == '('){
        stk.push(new Pair(c, i));
      }
      else{
        // char is )
        if(!stk.isEmpty()){
          Pair t = stk.peekFirst(); 
          if(t.ch == '(')
            stk.pop();
          else
            stk.push(new Pair(c, i));
        }
        else
          stk.push(new Pair(c, i));
      }
    } 
    // the list contains all unbalanced parenthesis
    // count the number of ( at the end of the stack
    int openIndex = -1;
    int size = stk.size();
    Pair[] unbalanced = new Pair[size];
    int i = size - 1;
    while(!stk.isEmpty()){
      Pair c = stk.pop();
      if(c.ch == ')' && openIndex == -1)
        openIndex = i+1;
      unbalanced[i--] = c;
    }
    
    int closedcnt = openIndex == -1 ? 0 : openIndex;
    int openCnt = openIndex != -1 ? size -  openIndex : size;
    
    List<Integer> r = new ArrayList<>();
    int x =  (closedcnt+1)/2;
    for(int j=0; j<x; ++j){
      r.add(unbalanced[j].index);
    }
    int y = (openCnt+1)/2;
    for(int j=size-1; j>=size-y; --j){
      r.add(unbalanced[j].index);
    }
    int[] array = new int[r.size()];
    for(i = 0; i < r.size(); i++) 
      array[i] = r.get(i);
    return array; 
  } 

  public void print(int[] a){
    for(int t : a){
      System.out.print(t + " ");
    }
    System.out.println();
  }

  public static void test(String s){
    ParenthesesDiv2Medium ob = new ParenthesesDiv2Medium();
    int[] a = ob.correct(s);
    ob.print(a);

  }
  public static void main(String[] args){
    //test("()()()()()()()()()()()()()");
    test(")))(()(())))))");
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!