/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
// Minor tweak
public class Solution {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> pascal = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> cur = new ArrayList<>();
            // first col
            cur.add(1);
            if(i > 0){
                for(int j=1; j<i; j++){
                   cur.add(pascal.get(i-1).get(j-1) + pascal.get(i-1).get(j)); 
                }
                // last col
                cur.add(1);
            }
            pascal.add(cur);
        }
        return pascal;
    }
}