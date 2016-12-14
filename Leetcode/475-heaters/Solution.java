/*
Winter is coming! Your first job during the contest is to design a standard heater 
with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out 
minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your 
expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 
1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use 
radius 2 standard, then all the houses can be warmed.
*/

public class Solution {
    public boolean isPossible(int[] houses, TreeSet<Integer> heaters, int radius){
        // For each house, check if there is a heater within house pos + radius or house pos - radius
        for(int pos : houses){
            if(heaters.contains(pos))
                continue;
            // Returns the least element in this set greater than or equal to the given element, or null if there is no such element.                
            Integer ceil = heaters.ceiling(pos - radius);
            if(ceil == null)
                return false;
            if(!(ceil >= pos - radius && ceil <= pos + radius))
                return false;
        }
        return true;
    }
    
    public int findRadius(int[] houses, int[] heaters) {
        int n = houses.length;
        if(n == 0)
            return 0;
        Arrays.sort(houses);
        TreeSet<Integer> set = new TreeSet<>();
        for(int pos : heaters){
            set.add(pos);
        }
        
        int l = 0, h = Math.max(houses[n-1], set.last()) - Math.min(houses[0], set.first());
        while(h - l > 3){
            // radius
            int mid = (h - l)/2 + l;
            if(isPossible(houses, set, mid)){
                h = mid;
            }
            // Not possible to cover all houses with this radius
            // Need to increase radious 
            else{
                l = mid + 1;
            }
        }
        // Return the minimum radius
        for(int i=l; i<=h; ++i){
            if(isPossible(houses, set, i))
                return i;
        }
        return -1;
    }
}

// Alternative
class Solution2 {
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int pos : heaters){
            set.add(pos);
        }

        int maxRadius = 0;
        for(int house : houses){
            Integer ceil = set.ceiling(house);            
            Integer floor = set.floor(house);
            
            int d = Integer.MAX_VALUE;
            if (floor != null) d = Math.min(d, house - floor);
            if (ceil != null) d = Math.min(d, ceil - house);
            maxRadius = Math.max(maxRadius, d);
        }
        return maxRadius;
    }
}

// Alternative: Sorting
class Solution3{
    // Alternative
    public int findRadius(int[] houses, int[] heaters) {
        int n = houses.length;
        if(n == 0)
            return 0;
        int m = heaters.length;   
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int radius = Integer.MIN_VALUE;
        int j = 0;
        for(int i=0; i<n; ++i){
            while(j+1 < m && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j+1])){
                j++;
            }
            radius = Math.max(radius, Math.abs(houses[i] - heaters[j]));
        }
        return radius;
    }
}