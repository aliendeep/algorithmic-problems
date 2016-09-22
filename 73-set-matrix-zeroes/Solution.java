/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
*/

public class Solution {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean firstRowFlag = false;
        boolean firstColFlag = false;
        for(int i=0; i<r; i++)
            if(matrix[i][0] == 0)   
                firstColFlag = true;
        
        for(int j=0; j<c; j++){
            if(matrix[0][j] == 0)   
                firstRowFlag = true;
        }
        
        // Use the first row and first column as the temporary array to keep track of the 0s appeared in rows & columns
        for(int i=1; i<r; i++){
            for(int j=1; j<c; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int i=1; i<r; i++){
            for(int j=1; j<c; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if(firstRowFlag){
            for(int j=0; j<c; j++)
                matrix[0][j] = 0;
        }
        if(firstColFlag){
            for(int i=0; i<r; i++)
                matrix[i][0] = 0;
        }        
    }
}