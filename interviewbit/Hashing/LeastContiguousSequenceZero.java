/*
Find the largest continuous sequence in a array which sums to zero.

Example:


Input:  {1 ,2 ,-2 ,4 ,-4}
Output: {2 ,-2 ,4 ,-4}

 NOTE : If there are multiple correct answers, return the sequence which occurs first in the array. 
 */
public class Solution {
    public ArrayList<Integer> lszero(ArrayList<Integer> a) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = a.size();
        int maxLength = 0;
        int rstart = -1;
        map.put(0, -1);
        int sum = 0;
        for(int i=0; i<n; ++i){
            sum += a.get(i);
            if(map.containsKey(sum)){
                int index = map.get(sum);
                if(maxLength < i - index){
                    rstart = index + 1;
                    maxLength = i - index;
                }
            }
            else{
                map.put(sum, i);
            }
        }
        ArrayList<Integer> r = new ArrayList<>();
        for(int i=rstart; i<rstart+maxLength; ++i)
            r.add(a.get(i));
        return r;
    }
}
