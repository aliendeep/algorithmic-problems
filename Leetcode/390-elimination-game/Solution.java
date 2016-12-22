/*
390. Elimination Game  QuestionEditorial Solution  My Submissions
Total Accepted: 1953
Total Submissions: 7332
Difficulty: Medium
There is a list of sorted integers from 1 to n. Starting from left to right, 
remove the first number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the 
right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, 
until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6
*/
public class Solution {
    /*  Remaining element if we remove element from left to right (1..n) +  
        Remaining element if we remove element from right to left (n .. 1)  = n + 1 (for all n>=2)
        After first elimination cycle, all elements are even
        By dividing by 2, we get a continuous sequence from 1 to n
     */
    public int lastRemaining(int n) {
        if(n == 1)
            return 1;
        return 2*(n/2 + 1 - lastRemaining(n/2));
    }
}

// Iterative
// O(logn)
class Solution2 {
    /* Idea is to update the head in each turn
    Head will be changed in the following two cases
    - If we move from left to right
    - If we move from right to left and remaining number of elements is odd
    */
    public int lastRemaining(int n) {
        boolean left = true;
        int inc = 1;
        int head = 1;
        int remaining = n;
        while(remaining > 1){
            if(left || remaining % 2 == 1){
                head += inc;
            }            
            inc *= 2;
            remaining /= 2;
            left = !left;
        }
        return head;
    }
}
