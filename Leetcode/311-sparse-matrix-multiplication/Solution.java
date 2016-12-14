/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/* Test case:
[[1,0,0],[-1,0,3]]
[[7,0,0],[0,0,0],[0,0,1]]
[[1,-5]]
[[12],[-1]]
*/

public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int ra = A.length;
        int ca = A[0].length;

        int rb = B.length;
        int cb = B[0].length;
        
        int[][] r = new int[ra][cb];

        for(int k=0; k<rb; ++k){
            for(int i=0; i<ra; ++i){
                if(A[i][k] == 0)
                    continue;
                for(int j=0; j<cb; ++j){
                    r[i][j] += A[i][k]*B[k][j]; 
                }
            }
        }
        return r;
    }
}

class Solution2 {
    // Alternative : One table for B's
    public int[][] multiply(int[][] A, int[][] B) {
        int ra = A.length;
        int ca = A[0].length;
        int rb = B.length;
        int cb = B[0].length;
        // table for B
        // (row , non zero cols)
        Map<Integer, Set<Integer>> table = new HashMap<>();
        for(int i=0; i<rb; ++i){
            table.put(i, new HashSet<>());
            for(int j=0; j<cb; ++j){
                if(B[i][j] != 0){
                    table.get(i).add(j);
                }
            }
        }
        
        int[][] r = new int[ra][cb];
        for(int i=0; i<ra; ++i){
            for(int k=0; k<rb; ++k){
                if(A[i][k] != 0){
                    for(int j : table.get(k)){
                        r[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        
        return r;
    }
}

class Solution3 {
    // Alternative: two tables
    public int[][] multiply(int[][] A, int[][] B) {
        int ra = A.length;
        int ca = A[0].length;
        int rb = B.length;
        int cb = B[0].length;
        // table for A
        // (row , non zero cols)
        Map<Integer, Set<Integer>> tableA = new HashMap<>();
        for(int i=0; i<ra; ++i){
            for(int j=0; j<ca; ++j){
                if(A[i][j] != 0){
                    if(!tableA.containsKey(i))
                        tableA.put(i, new HashSet<>());
                    tableA.get(i).add(j);
                }
            }
        }

        // table for B
        // (row , non zero cols)
        Map<Integer, Set<Integer>> tableB = new HashMap<>();
        for(int i=0; i<rb; ++i){
            for(int j=0; j<cb; ++j){
                if(B[i][j] != 0){
                    if(!tableB.containsKey(i))
                        tableB.put(i, new HashSet<>());
                    tableB.get(i).add(j);
                }
            }
        }
        
        int[][] C = new int[ra][cb];
        for(int i=0; i<ra; ++i){
            if(!tableA.containsKey(i))
                continue;
            for(int k : tableA.get(i)){
                if(!tableB.containsKey(k))
                    continue;
                for(int j : tableB.get(k)){
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return C;
    }
}
