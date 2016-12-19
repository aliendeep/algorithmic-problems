/*
There is a row of seats. Assume that it contains N seats adjacent to each other. 
There is a group of people who are already seated in that row randomly. i.e. 
some are sitting together & some are scattered.

An occupied seat is marked with a character 'x' and an unoccupied seat is marked 
with a dot ('.')

Now your target is to make the whole group sit together i.e. next to each other, 
without having any vacant seat between them in such a way that the total number 
of hops or jumps to move them should be minimum.

Return minimum value % MOD where MOD = 10000003

Example

Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14) -

              . . . . x . . x x . . . x . .

Now to make them sit together one of approaches is -
                  . . . . . . x x x x . . . . .

Following are the steps to achieve this -
1 - Move the person sitting at 4th index to 6th index -  
    Number of jumps by him =   (6 - 4) = 2

2 - Bring the person sitting at 12th index to 9th index - 
    Number of jumps by him = (12 - 9) = 3

So now the total number of jumps made = 
    ( 2 + 3 ) % MOD = 
    5 which is the minimum possible jumps to make them seat together.

There are also other ways to make them sit together but the number of jumps will 
exceed 5 and that will not be minimum.

For example bring them all towards the starting of the row i.e. start placing them 
from index 0. 
In that case the total number of jumps will be 
    ( 4 + 6 + 6 + 9 )%MOD 
    = 25 which is very costly and not an optimized way to do this movement
*/

public class Solution {
  public int seats(String a) {
      int n = a.length();
      int min = -1;
      int max = -1;
      for(int i=0; i<n; ++i){
          if(a.charAt(i) == 'x'){
              min = i;
              break;
          }
      }
      for(int i=n-1; i>=0; --i){
          if(a.charAt(i) == 'x'){
              max = i;
              break;
          }
      }

      List<Integer> persons = new ArrayList<>();
      for(int i=0; i<n; ++i){
        if(a.charAt(i) == 'x'){
          persons.add(i);
        }
      }
      if(persons.size() == 0)
        return 0;
      int medianIndex = persons.size()/2;
      int median = persons.get(medianIndex);
      long sum = 0;
      int extra = 1;
      for(int i=medianIndex-1; i>=0; --i){
        int person = persons.get(i);
        sum += Math.abs(person - median) - extra;
        extra++;
      }
      extra = 1;
      for(int i=medianIndex+1; i<persons.size(); ++i){
        int person = persons.get(i);
        sum += Math.abs(person - median) - extra;
        extra++;
      }
      return (int)(sum % 10000003);
  }
}
