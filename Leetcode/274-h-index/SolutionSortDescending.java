public class Solution {
    // Alternative: O(nlogn)
    // Sort (decending) : 6, 5, 3, 1, 0
    // if citations[i] > i, then papers from 0 to i have at least i + 1 citations. Need to find largest such i
    public int hIndex(int[] c) {
        int n = c.length;
        if(n == 0)      return 0;
        // Sort in descending order
        Integer[] citations =  new Integer[n];
        for(int i=0; i<n; ++i){
            citations[i] = c[i];
        }
        Arrays.sort(citations, Collections.reverseOrder());
        int i = 0;
        while(i < n && citations[i] > i){
            ++i;
        }
        
        // i+1 after the while loop
        return i;
    }
}