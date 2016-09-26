/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/
public class Solution {
    // Greedy
    // Pick out the tallest persons from the current subarray and sort them
    // Index = k value
    // and repeat;
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
           @Override
           public int compare(int[] a, int[] b){
               // if height is the same, decide based on k
               return a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]);  
           }
        });
        List<int[]> result = new LinkedList<>();
        for(int[] a : people){
            //  Adds the item at the end of the list.
            if(a[1] > result.size())
                result.add(a);
            // Add the item at the position of k
            else
                result.add(a[1], a);
        }
        return result.toArray(new int[people.length][people.length]);
    }
}