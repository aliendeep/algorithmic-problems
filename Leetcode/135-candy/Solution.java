/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/
// Greedy
public class Solution {
    // Two pass
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] c = new int[n];
        Arrays.fill(c, 1);
        
        // left to right
        for(int i=1; i<n; i++){
            if(ratings[i-1] < ratings[i])
                c[i] = c[i-1] + 1;
        }
        
        // right to left
        for(int i=n-2; i>=0; i--){
            if(ratings[i] > ratings[i+1])
                c[i] = Math.max(c[i], c[i+1] + 1);
        }
        
        int sum = 0;
        for(int i=0; i<n; i++)
            sum += c[i];
        return sum;
    }
}
