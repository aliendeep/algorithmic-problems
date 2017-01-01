/*
Find the largest continuous sequence in a array which sums to zero.

Example:

Input:  {1 ,2 ,-2 ,4 ,-4}
Output: {2 ,-2 ,4 ,-4}

NOTE : If there are multiple correct answers, return the sequence which occurs 
first in the array. 
 */
public class Solution {
    public ArrayList<Integer> lszero(ArrayList<Integer> a) {
        int n = a.size();
        // Sum, Leftmost index
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int start = -1;

        map.put(0, -1);
        int cumSum = 0;
        for(int i=0; i<n; ++i){
            cumSum += a.get(i);  
            // Left most index
            if(!map.containsKey(cumSum)){
                map.put(cumSum, i);
            }
            // Same number occurred before
            else{
                int index = map.get(cumSum);
                if(len < i - index){
                   len = i - index;
                   start = index + 1;
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        if(start == -1)
            return result;
        for(int i=start; i<start+len; ++i){
            result.add(a.get(i));
        }
        return result;
    }
}
