/*
Consider a 2-D map with a horizontal river passing through its center. There are 
n cities on the southern bank with x-coordinates a(1) ... a(n) and n cities on 
the northern bank with x-coordinates b(1) ... b(n). You want to connect as many 
north-south pairs of cities as possible with bridges such that no two bridges cross. 
When connecting cities, you can only connect city i on the northern bank to city i 
on the southern bank. 
*/
// Variation of LIS
// https://people.cs.clemson.edu/~bcdean/dp_practice/dp_6.swf
import java.util.*;

// O(n^2)
public class BuildingBridgesProblem{
  class Pair{
    int x;
    int index;
    public Pair(int x1, int i){
      x = x1;
      index = i;
    }
  }

  public int getMaxNumberBridges(int[] north, int[] south){
    int n = north.length;
    // Cities in the south array are sorted in increasing order

    // x(i) : index of corresponding value of south[i] in the north array
    Map<Integer, Integer> map = new HashMap<>();
    for(int i=0; i<n; ++i)
      map.put(north[i], i);

    int[] x = new int[n];
    for(int i=0; i<n; ++i){
      x[i] = map.get(south[i]);
    }

    // LIS of the x array        
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    
    for(int i=0; i<n; ++i){
        for(int j=0; j<i; ++j){
            if(x[i] > x[j]){
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    int maxLength = 1;
    for(int i=0; i<n; i++)
        maxLength = Math.max(maxLength, dp[i]);
    return maxLength;    
  }
  public static void main(String[] args){
    BuildingBridgesProblem ob = new BuildingBridgesProblem();    
    int[] n = {8, 1, 4, 3, 5, 2, 6, 7};
    int[] s = {1, 2, 3, 4, 5, 6, 7, 8};
    System.out.println(ob.getMaxNumberBridges(n, s));
  }   
}
