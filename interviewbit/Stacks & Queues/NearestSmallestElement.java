/*
Given an array, find the nearest smaller element G[i] for every element A[i] in 
the array such that the element has an index smaller than i.

More formally,

G[i] for an element A[i] = an element A[j] such that 
    j is maximum possible AND 
    j < i AND
    A[j] < A[i]
Elements for which no smaller element exist, consider next smaller element as -1.

Example:

Input : A : [4, 5, 2, 10]
Return : [-1, 4, -1, 2]

Example 2:

Input : A : [3, 2, 1]
Return : [-1, -1, -1]
*/

public class Solution {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> a) {
        int n = a.size();
        ArrayList<Integer> r = new ArrayList<>();
        Deque<Integer> stk = new LinkedList<>();
        for(int i = 0; i<n; ++i){
            int num = a.get(i);
            // All elements greater or equal than num that occurred before num can be discarded
            while(!stk.isEmpty() && stk.peekFirst() >= num){
                stk.pop();
            }
            if(stk.isEmpty())
                r.add(-1);
            else
                r.add(stk.peekFirst());
            stk.push(num);
        }
        return r;
    }
}


// Naive
class Solution2 {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> a) {
        ArrayList<Integer> r = new ArrayList<>();
        int n = a.size();
        int[] stk = new int[n]; 
        Arrays.fill(stk, Integer.MAX_VALUE);
        for(int i = 0; i<n; ++i){
            int num = a.get(i);
            int min = Integer.MAX_VALUE;
            for(int j=i-1; j>=0; --j){
                if(stk[j] < num){
                    min = stk[j];
                    break;
                }
            }
            if(min == Integer.MAX_VALUE){
                r.add(-1);
            }
            else
                r.add(min);
            stk[i] = num;
        }
        return r;
    }
}
