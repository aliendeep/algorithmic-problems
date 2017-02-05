/*
Given a matrix of M x N elements (M rows, N columns), return all elements of 
the matrix in diagonal order.

Example 1:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
*/
public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if(row == 0)    return new int[0];
        int col = matrix[0].length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<row; ++i){
            for(int j=0; j<col; ++j){
                int k = i + j;
                if(!map.containsKey(k)){
                    map.put(k, new ArrayList<>());
                }
                map.get(k).add(matrix[i][j]);
            }
        }
        
        int i = 0;
        int k = 0;
        int[] result = new int[row*col];
        while(i < (row+col-1)){
            if(i % 2 == 0){
                List<Integer> t = map.get(i);
                for(int j=t.size()-1; j>=0; --j){
                    result[k++] = t.get(j);
                }
            }
            else{
                List<Integer> t = map.get(i);
                for(int j=0; j<t.size(); ++j){
                    result[k++] = t.get(j);
                }
            }
            ++i;
        }
        return result;
    }
}
