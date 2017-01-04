/*

Problem Statement
    
Charlie has a grid of n rows by m columns. The rows are numbered 0 through n-1 from top to bottom. 
The columns are numbered 0 through m-1 from left to right.
Each cell of the grid contains a positive integer. The integers in Charlie's grid are a permutation of 
the numbers 1 through n*m. (I.e., each of these numbers occurs in the grid exactly once.)
Given a grid, its value list is a sequence constructed by listing all values in the grid in row major order. 
That is, we first list the values in row 0 from left to right, then the values in row 1 from left to right, and so on.
You are given the ints n and m: the dimensions of Charlie's grid. You are also given a int[] grid: the value list 
for Charlie's grid. (Formally, grid[i*m+j] is the value stored in row i, column j of the grid.)
Charlie can modify his grid in two ways: He may swap any two rows, and he may swap any two columns. Charlie now wonders 
whether there is a sequence of swaps that would sort his grid - that is, rearrange its elements in such a way that the 
value list of the new grid will be the ordered sequence (1,2,3,...,n*m).
If it is possible to sort Charlie's grid, return "Possible". Otherwise, return "Impossible".
Definition
    
Class:
GridSort
Method:
sort
Parameters:
int, int, int[]
Returns:
String
Method signature:
String sort(int n, int m, int[] grid)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
n,m will be between 1 and 50, inclusive.
-
grid will be a permutation of [1,...,n*m].
Examples
0)

    
2
2
{
 1,2,
 3,4
}
Returns: "Possible"
This grid is already sorted, so Charlie doesn't need to do anything.
1)

    
2
2
{
 3,4,
 1,2
}
Returns: "Possible"
Charlie can sort this grid by swapping rows 0 and 1.
2)

    
2
2
{
 4,3,
 1,2
}
Returns: "Impossible"

3)

    
1
10
{4,5,1,2,9,8,3,10,7,6}
Returns: "Possible"

4)

    
3
5
{
 10,6,8,9,7,
 5,1,3,4,2,
 15,11,13,14,12
}
Returns: "Possible"

5)

    
6
2
{
 11,12,
 2,1,
 9,10,
 7,8,
 6,5,
 3,4
}
Returns: "Impossible"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. 
Any unauthorized use or reproduction of this information without the prior written 
consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class GridSort
{ 
  public void print(int[][] a){
    for(int i =0; i<a.length; ++i){
      for(int j =0; j<a[0].length; ++j){
          System.out.print(a[i][j] + " ");
        }
        System.out.println();
    }
  }

  class Info{
    int x, y;
    public Info(int x1, int y1){
      x = x1; y=y1;
    }
  }

  public void swapCol(int[][] arr, int prev, int nr, Map<Integer, Info> map){
    int r = arr.length;
    int[] t = new int[r];
    for(int i=0; i<r; ++i){
        t[i] = arr[i][prev];
    }
    for(int i=0; i<r; ++i){
        arr[i][prev] = arr[i][nr];
        map.put(arr[i][prev], new Info(i, prev));
    }
    for(int i=0; i<r; ++i){
        arr[i][nr] = t[i];
        map.put(arr[i][nr], new Info(i, nr));
    }
  }

  public void swapRows(int[][] arr, int prev, int next, Map<Integer, Info> map){
    int c = arr[0].length;
    int[] t = new int[c];
    for(int i=0; i<c; ++i){
        t[i] = arr[prev][i];
    }
    for(int i=0; i<c; ++i){
        arr[prev][i] = arr[next][i];
        map.put(arr[prev][i], new Info(prev, i));
    }
    for(int i=0; i<c; ++i){
        arr[next][i] = t[i];
        map.put(arr[next][i], new Info(next, i));
    }
  }

  boolean isValid(int[][] arr){
    int n = arr.length;
    int m = arr[0].length;
    int cur = 1;
    for(int i=0; i<n; ++i){
      for(int j=0; j<m; ++j){
          if(arr[i][j] != cur)
              return false;
          cur++;
      }
    }
    return true;    
  }

  public String sort(int n, int m, int[] grid)
  {   
    Map<Integer, Info> map = new HashMap<>();
    int r = 0, c = 0;
    int[][] arr = new int[n][m];

    for(int i=0; i<grid.length; ++i){
      arr[r][c] = grid[i];
      map.put(grid[i], new Info(r, c));
      c++;
      if(c == m){
        c = 0;
        r++;
      }
    }
    for(int i=1; i<=n*m; ++i){
      // move val to 0th row and ith column from current position
      Info p = map.get(i);      
      int nr =  (i-1) / m;
      int nc =  (i-1) % m;
      swapRows(arr, p.x, nr, map);   
      swapCol(arr, p.y, nc, map);
      if(isValid(arr))
          return "Possible";
    }
    return "Impossible";
  }

/*
{33, 6, {163, 164, 167, 168, 165, 166, 79, 80, 83, 84, 81, 82, 115, 116, 119, 120, 117, 118, 55, 56, 59, 60, 57, 58, 103, 104, 107, 108, 105, 106, 121, 122, 125, 126, 123, 124, 19, 20, 23, 24, 21, 22, 193, 194, 197, 198, 195, 196, 97, 98, 101, 102, 99, 100, 127, 128, 131, 132, 129, 130, 67, 68, 71, 72, 69, 70, 145, 146, 149, 150, 147, 148, 73, 74, 77, 78, 75, 76, 181, 182, 185, 186, 183, 184, 85, 86, 89, 90, 87, 88, 49, 50, 53, 54, 51, 52, 157, 158, 161, 162, 159, 160, 13, 14, 17, 18, 15, 16, 1, 2, 5, 6, 3, 4, 169, 170, 173, 174, 171, 172, 133, 134, 137, 138, 135, 136, 25, 26, 29, 30, 27, 28, 43, 44, 47, 48, 45, 46, 91, 92, 95, 96, 93, 94, 151, 152, 155, 156, 153, 154, 37, 38, 41, 42, 39, 40, 7, 8, 11, 12, 9, 10, 109, 110, 113, 114, 111, 112, 61, 62, 65, 66, 63, 64, 31, 32, 35, 36, 33, 34, 187, 188, 191, 192, 189, 190, 139, 140, 143, 144, 141, 142, 175, 176, 179, 180, 177, 178}}

Expected:
"Possible"

Received:
"Impossible"
*/
  public static void main(String[] args){
    GridSort ob = new GridSort();
    int[] a = {163, 164, 167, 168, 165, 166, 79, 80, 83, 84, 81, 82, 115, 116, 119, 120, 117, 118, 55, 56, 59, 60, 57, 58, 103, 104, 107, 108, 105, 106, 121, 122, 125, 126, 123, 124, 19, 20, 23, 24, 21, 22, 193, 194, 197, 198, 195, 196, 97, 98, 101, 102, 99, 100, 127, 128, 131, 132, 129, 130, 67, 68, 71, 72, 69, 70, 145, 146, 149, 150, 147, 148, 73, 74, 77, 78, 75, 76, 181, 182, 185, 186, 183, 184, 85, 86, 89, 90, 87, 88, 49, 50, 53, 54, 51, 52, 157, 158, 161, 162, 159, 160, 13, 14, 17, 18, 15, 16, 1, 2, 5, 6, 3, 4, 169, 170, 173, 174, 171, 172, 133, 134, 137, 138, 135, 136, 25, 26, 29, 30, 27, 28, 43, 44, 47, 48, 45, 46, 91, 92, 95, 96, 93, 94, 151, 152, 155, 156, 153, 154, 37, 38, 41, 42, 39, 40, 7, 8, 11, 12, 9, 10, 109, 110, 113, 114, 111, 112, 61, 62, 65, 66, 63, 64, 31, 32, 35, 36, 33, 34, 187, 188, 191, 192, 189, 190, 139, 140, 143, 144, 141, 142, 175, 176, 179, 180, 177, 178};
    System.out.println(ob.sort(33, 6, a));
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!