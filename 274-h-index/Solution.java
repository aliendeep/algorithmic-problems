/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
*/
public class Solution {
    // O(n) Solution    
    public int hIndex(int[] citations) {
        // Let h be the h-index of the author. 
        // h must need to follow the constraint 0<=h<=n, where n is the number of papers
        int n = citations.length;
        int[] cnt = new int[n+1];
        for(int i=0; i<n; i++){
            // if the citation count is greater than no of published paper then it contrinute to the nth item of cnt as h_max = n
            if(citations[i] >= n)
                cnt[n]++;
            else
                cnt[citations[i]]++;
        }
        // A scientist has index h if h of his/her N papers have at least h citations each
        int cntCum = 0;
        for(int i=n; i>=0; i--){
            cntCum += cnt[i];
            if(cntCum >= i)
                return i;
        }
        return 0;
    }
}