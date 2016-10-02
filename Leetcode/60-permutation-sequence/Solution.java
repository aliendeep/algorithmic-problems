/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/
public class Solution {
    int[] f;
    // Given n will be between 1 and 9 inclusive.
    public Solution(){
        // calculate factorial
        f = new int[10];
        f[0] = 1;
        for(int i=1; i<10; i++)
            f[i] = f[i-1]*i;
    }

    public String getPermutation(int n, int k) {
        // Create the list of the number
        List<Integer> a = new ArrayList<>();
        for(int i=1; i<=n; i++){
            a.add(i);
        }        
        
        k--;
        StringBuilder r = new StringBuilder();
        for(int i=n-1; i>=0; i--){
            // block size = (k-1)/(n-1)!
            int index = k/f[i];
            r.append(a.get(index));
            // remove that number
            a.remove(index);
            k -= index*f[i];
        }
        return r.toString();
    }
}   