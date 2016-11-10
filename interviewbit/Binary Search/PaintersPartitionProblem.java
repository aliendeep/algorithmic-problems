/*
https://www.interviewbit.com/problems/painters-partition-problem/
You have to paint N boards of length {A0, A1, A2, A3 … AN-1}. There are K painters available and you are also given how much time a painter takes to paint 1 unit of board. You have to get this job done as soon as possible under the constraints that any painter will only paint contiguous sections of board.

2 painters cannot share a board to paint. That is to say, a board
cannot be painted partially by one painter, and partially by another.
A painter will only paint contiguous boards. Which means a
configuration where painter 1 paints board 1 and 3 but not 2 is
invalid.
Return the ans % 10000003

Input :
K : Number of painters
T : Time taken by painter to paint 1 unit of board
L : A List which will represent length of each board

Output:
     return minimum time to paint all boards % 10000003
Example

Input : 
  K : 2
  T : 5
  L : [1, 10]
Output : 50
*/
import java.util.*;

public class PaintersPartitionProblem {
    // Number of painters needed for allocated time to finish the job
    // O(n) time
    public static final long MOD = 10000003;
    
    public int painterNeeded(long time, ArrayList<Integer> board){
        int npainter = 1;
        // Greedy
        int n = board.size();
        int i = 0;
        long cur = 0;
        while(i < n){
            // Time needed to paint ith board
            long t = board.get(i);
            cur = cur + t;
            if(cur > time){
                // Need one more painter
                npainter++;
                // reset cur
                cur = t;
            }
            i++;
        }
        return npainter;
    }
    
    // Binary Search on time (available painter)
    public long binarySearch(int aPainter, ArrayList<Integer> board){
        // Find the length of the max board
        long maxLen = Integer.MIN_VALUE;
        // Sum of length of all boards
        long sum = 0;
        for(int boardLen : board){
            maxLen = Math.max(maxLen, boardLen);
            sum += boardLen;
        }
        // Minimum time needed: time to paint the max length board
        long low = maxLen;
        long high = sum;
        while(high - low > 3){
            long mid = (high - low)/2 + low;
            int npainter = painterNeeded(mid, board);
            // If number of painters needed is greater than available painter, then we need to 
            // increase time limit
            if(npainter > aPainter){
                low = mid + 1;    
            }
            // npainter <= aPainter
            else{
                high = mid;
            }
        }
        // min time
        for(long time=low; time<=high; ++time){
            if(painterNeeded(time, board) <= aPainter){
                return time;
            }
        }
        return -1;
    }

    public int paint(int a, int b, ArrayList<Integer> c) {
        long time = binarySearch(a, c);
        return (int)((time*b) % MOD);
    }

    public static void main(String[] args) {
        ArrayList<Integer> board = new ArrayList<>();
        //int[] a = {640, 435, 647, 352, 8, 90, 960, 329, 859};
        int[] a = {1000000, 1000000};
        for(int i=0; i<a.length; ++i)
            board.add(a[i]);
        PaintersPartitionProblem ob = new PaintersPartitionProblem();
        //System.out.println(ob.painterNeeded(1, 1000000, board));
        System.out.println(ob.paint(3, 10, board));
    }
}