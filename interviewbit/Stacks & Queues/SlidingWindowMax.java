/*
A long array A[] is given to you. There is a sliding window of size w which is moving from the very left of the array to the very right. You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position.

Example :

The array is [1 3 -1 -3 5 3 6 7], and w is 3.

Window position Max
   
[1 3 -1] -3 5 3 6 7 3
1 [3 -1 -3] 5 3 6 7 3
1 3 [-1 -3 5] 3 6 7 5
1 3 -1 [-3 5 3] 6 7 5
1 3 -1 -3 [5 3 6] 7 6
1 3 -1 -3 5 [3 6 7] 7
Input: A long array A[], and a window width w
Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
Requirement: Find a good optimal way to get B[i]

 Note: If w > length of the array, return 1 element with the max of the array. 
*/

 // DEQUE
 public class Solution {
  // DO NOT MODIFY THE LIST
  public ArrayList<Integer> slidingMaximum(final List<Integer> a, int w) {
      int n = a.size();
      ArrayList<Integer> r = new ArrayList<>();
      if(n  == 0 || n < w)
         return r;
      // Keep index
      Deque<Integer> Q = new LinkedList<>();
      // find the first window
      for(int i=0; i<w; ++i){
          while(!Q.isEmpty() && a.get(Q.peekLast()) <= a.get(i))
              Q.removeLast();
            Q.add(i);             
      }
      r.add(a.get(Q.peek()));

      for(int i=w; i<n; ++i){
          // if out of window then remove
            if(!Q.isEmpty() && i - Q.peek() == w)
                Q.remove();
            // smaller number before current number remove
          while(!Q.isEmpty() && a.get(Q.peekLast()) <= a.get(i)){
              Q.removeLast();
          }
     
            Q.add(i);             
          r.add(a.get(Q.peek()));
      }
      return r;
  }
}
