// Maximum sum rectangle in a 2d matrix
// Kadene's 2d version

// Time: O(rc^2)
// Space: O(rc)
public class MaxSumSubmatrix{
    public int maxSumSubmatrix(int[][] matrix) {
        int nr = matrix.length;
        int nc = matrix[0].length;
        
        // Keep the cumSum for row r
        int[][] cumSum = new int[nr][nc];
        for(int r=0; r<nr; ++r){
            cumSum[r][0] = matrix[r][0];
            for(int c=1; c<nc; ++c){
                cumSum[r][c] = cumSum[r][c-1] + matrix[r][c]; 
            }
        }
        
        int maxSum = Integer.MIN_VALUE;
        for(int c1=0; c1<nc; ++c1){
            for(int c2=c1;c2<nc; ++c2){
                int run = 0;
                for(int r=0; r<nr; ++r){
                    run += cumSum[r][c2];
                    if(c1 > 0)
                        run -= cumSum[r][c1-1];
                    if(run > maxSum)    
                        maxSum = run;
                    if(run < 0)
                        run = 0;
                }
            }
        }
        return maxSum;
    }
    public static void main(String args[]){
        int input[][] = {
                        {1, 2, -1, -4, -20},
                        {-8, -3, 4, 2, 1},
                        {3, 8, 10, 1, 3},
                        {-4, -1, 1, 7, -6}
                        };
        MaxSumSubmatrix ob = new MaxSumSubmatrix();
        System.out.println(ob.maxSumSubmatrix(input));

        MaxSumSubmatrixLessSpace ob1 = new MaxSumSubmatrixLessSpace();
        System.out.println(ob1.maxSumSubmatrix(input));
    }       
}

// Less space
// O(r) Space solution
class MaxSumSubmatrixLessSpace{
    public int maxSumSubmatrix(int[][] matrix) {
        int nr = matrix.length;
        int nc = matrix[0].length;
        
        int maxSum = Integer.MIN_VALUE;
        for(int c1=0; c1<nc; ++c1){
            // Calculate sum between current left and right for every row 'r'
            int[] sum = new int[nr];    

            for(int c2=c1; c2<nc; ++c2){
                for(int r=0; r<nr; r++)
                    sum[r] += matrix[r][c2];

                // kadene
                int run = 0;
                for(int r=0; r<nr; r++){
                    run += sum[r];
                    if(run > maxSum)    
                        maxSum = run;
                    if(run < 0)
                        run = 0;
                }
            }
        }
        return maxSum;
    }
    public static void main(String args[]){
        int input[][] = {
                        {1, 2, -1, -4, -20},
                        {-8, -3, 4, 2, 1},
                        {3, 8, 10, 1, 3},
                        {-4, -1, 1, 7, -6}
                        };
        MaxSumSubmatrix ob = new MaxSumSubmatrix();
        System.out.println(ob.maxSumSubmatrix(input));
    }       
}
