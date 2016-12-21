/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into 
another if and only if both the width and height of one envelope is greater than 
the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you 
can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
*/

public class Solution {
    // 2D LIS
    // find the leftmost index where the element should be inserted
    // len of the array (exclusive)    
    int binarySearch(int[] a, int len, int target){
        int l = 0, h = len - 1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(a[mid] == target){
                h = mid;
            }
            else if(a[mid] < target){
                l = mid + 1;
            }
            // a[mid] > target
            else{
                h = mid - 1;
            }
        }
        for(int i=l; i<=h; ++i){
            if(a[i] >= target)
                return i;
        }
        return h+1;
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(envelopes == null || n == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        // Sort by width
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    // decreasing height
                    return Integer.compare(b[1], a[1]);
                }
                // otherwise
                return Integer.compare(a[0], b[0]);
            }
        });
        int[] minHeightForLength = new int[n];
        int len = 0;
        for(int[] envelop : envelopes){
            // height
            int index = binarySearch(minHeightForLength, len, envelop[1]);
            minHeightForLength[index] = envelop[1];
            if(index == len)
                len++;
        }            
        return len;
    }
}

class Solution2 {
    // Sort the array. Width increasing and height decreasing if width are same 
    // ([8, 7] cannot contains [8, 6], so we need to put [8, 7] before [8, 6] )
    // LCS on height
    // O(nlogn)
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(envelopes == null || n == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    // decreasing height
                    return Integer.compare(b[1], a[1]);
                }
                // otherwise
                return Integer.compare(a[0], b[0]);
            }
        });
        
        // The array contains the min value of the last element of i-length increasing subsequence
        int[] minHeightForLength = new int[n];
        int len = 0;
        for(int[] envelop : envelopes){
            // Returns index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1). 
            int index = Arrays.binarySearch(minHeightForLength, 0, len, envelop[1]);
            if(index < 0)
                index = -(index+1);
            minHeightForLength[index] = envelop[1];
            // If the insertion point of the current element is equal to the longest length found so far, then we can extend the subsequence.
            if(index == len)
                len++;
        }
        return len;
    }
}

// Time Complexity: O(n^2)
class Solution3 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(envelopes == null || n == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        // Sort by width
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    // decreasing height
                    return Integer.compare(b[1], a[1]);
                }
                // otherwise
                return Integer.compare(a[0], b[0]);
            }
        });
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<i; ++j){
                if(envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}

// Alternative: O(n^2)
// Sort by width (decreasing order)
class Solution4 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(envelopes == null || n == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        // Sort by width (decreasing order)
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return Integer.compare(a[1], b[1]);
                }
                // otherwise
                return Integer.compare(b[0], a[0]);
            }
        });
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<i; ++j){
                // smaller envelope fits into larger envelope
                if(envelopes[j][1] > envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}

// Alternative: O(n^2)
class Solution5 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(envelopes == null || n == 0 || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        // Sort by width (decreasing order)
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return Integer.compare(b[0], a[0]);
            }
        });
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<i; ++j){
                // smaller envelope fits into larger envelope
                if(envelopes[j][1] > envelopes[i][1] && envelopes[j][0] > envelopes[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}
