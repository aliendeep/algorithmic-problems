/*
Given an array with n objects colored red, white or blue, 
sort them so that objects of the same color are adjacent, with the colors in the 
order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and 
blue respectively.

Note: Using library sort function is not allowed.

Example :

Input : [0 1 2 0 1 2]
Modify array so that it becomes : [0 0 1 1 2 2]
*/
public class Solution {
  public void sortColors(ArrayList<Integer> a) {
      int i = 0;
      int s = 0, e = a.size() - 1;
      while(i <= e){
          if(a.get(i) == 0){
              a.set(i++, a.get(s));
              a.set(s++, 0);
          }
          else if(a.get(i) == 2){
              a.set(i, a.get(e));
              a.set(e--, 2);
          }
          else
              i++;
      }
  }
}
