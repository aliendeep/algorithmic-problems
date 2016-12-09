/*
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if 
h of his/her N papers have at least h citations each, and the other N − h papers 
have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 
papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining 
two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as 
the h-index.

Hint:

- An easy approach is to sort the array first.
- What are the possible values of h-index?
- A faster approach is to use extra space.
*/
public class Solution {
    // O(n)
    public int hIndex(int[] citations) {
        int n = citations.length;
        if(n == 0)      return 0;
        
        int[] cnt = new int[n+1];
        for(int citation : citations){
            cnt[Math.min(citation, n)]++;            
        }
        
        // N papers have at least h citations each
        int k = n;
        int sum = cnt[n];
        while(k > sum){   
            k--;
            sum += cnt[k];    
        }
        return k;
    }
}

class Solution2 {
    // Alternative: O(nlogn)
    public int hIndex(int[] citations) {
        int n = citations.length;
        if(n == 0)
            return 0;
        Arrays.sort(citations);
        int h = 0;
        for(int i=n-1; i>=0 && citations[i] > h; i--)
            h++;

        return h;
    }
}

class Solution3 {
    // Alternative: O(nlogn)
    // Sort (decending) : 6, 5, 3, 1, 0
    // if citations[i] > i, then papers from 0 to i have at least i + 1 citations. Need to find 
    // largest such i
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