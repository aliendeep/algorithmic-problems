/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/
public class Solution {
    // O(k) space
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        for(int i=0; i<=rowIndex; i++){
            if(i == 0){
                prev.add(1);
                continue;
            }
            List cur = new ArrayList<>();
            // first column
            cur.add(1);
            for(int j=1; j<i; j++){
                cur.add(prev.get(j-1) + prev.get(j));
            }
            // last column
            cur.add(1);
            prev = cur;
        }
        
        return prev;
    }
}