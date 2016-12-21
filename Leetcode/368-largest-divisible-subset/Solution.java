/*
Given a set of distinct positive integers, find the largest subset such that every 
pair (Si, Sj) of elements 
in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
*/
// Time: O(n^2)
// Memoization
public class Solution {
    int[] dp;
    int[] parent;
    // dp[i] = Length of the longest subset whose largest element is nums[i]
    // dp[i] = 1 + dp[j] if nums[j] % nums[i] == 0 
    // Otherwise, dp[i] = 1
    public int getLongestPath(int index, int[] nums) {
        if(dp[index] != -1)
            return dp[index];

        dp[index] = 1;
        for(int i=index-1;i>=0; i--){
            if(nums[index] % nums[i] == 0){
                int l = getLongestPath(i, nums) + 1;
                if(l > dp[index]){
                    dp[index] = l;
                    parent[index] = i;
                }
            }
        }
        return dp[index];
    }

    // We can extend a subset if:
    // If the new element is larger than all other elements in the subset, then we can extend the subset if the new element is divisible by the largest element of the subset 
    // If the new element is smaller than all other elements in the subset, then if new element can divide the smallest of the subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        // Sort the array
        Arrays.sort(nums);
        
        dp = new int[n];
        Arrays.fill(dp, -1);

        parent = new int[n];
        int endIndex = -1;
        int maxLength = 0;
        // Add smaller element at the front
        for(int i=n-1; i>=0; i--){
            int len = getLongestPath(i, nums);
            if(maxLength < len){
                maxLength = len;
                endIndex = i;
            }
        }
        
        // maxIndex points to the first element of the largest subset
        List<Integer> r = new ArrayList<>();
        int len = dp[endIndex];
        for(int i=0; i<len; i++){
            r.add(nums[endIndex]);
            endIndex = parent[endIndex];
        }
        Collections.reverse(r);
        return r;
    }
}

// Time: O(n^2)
class Solution2 {
    // We can extend a subset if:
    // If the new element is larger than all other elements in the subset, then we can extend the subset if the new element is divisible by the largest element of the subset 
    // If the new element is smaller than all other elements in the subset, then if new element can divide the smallest of the subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        // Sort the array
        Arrays.sort(nums);
        
        // dp[i] = Length of the longest subset whose largest element is nums[i]
        // dp[i] = 1 + dp[j] if nums[j] % nums[i] == 0 
        // Otherwise, dp[i] = 1
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int[] successor = new int[n];
        int maxIndex = 0;
        // Add smaller element at the front
        for(int i=n-1; i>=0; i--){
            for(int j=i+1; j<n; j++){
                // nums[j] > nums[i]
                if(nums[j] % nums[i] == 0 && dp[i] < 1 + dp[j]){
                    dp[i] = 1 + dp[j];
                    successor[i] = j;
                    if(dp[maxIndex] < dp[i]){
                        maxIndex = i;
                    }
                }
            }
        }
        
        // maxIndex points to the first element of the largest subset
        List<Integer> r = new ArrayList<>();
        int len = dp[maxIndex];
        for(int i=0; i<len; i++){
            r.add(nums[maxIndex]);
            maxIndex = successor[maxIndex];
        }
        return r;
    }
}


// Memoization
// Alternative
class Solution3 {
    int[] dp;
    int[] parent;
    public int getLongestPath(int index, int[] nums) {
        if(dp[index] != -1)
            return dp[index];

        dp[index] = 1;
        for(int i=index+1;i<nums.length; i++){
            if(nums[i] % nums[index] == 0){
                int l = getLongestPath(i, nums) + 1;
                if(l > dp[index]){
                    dp[index] = l;
                    parent[index] = i;
                }
            }
        }
        return dp[index];
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return Collections.EMPTY_LIST;
            
        // Sort the array
        Arrays.sort(nums);
        
        dp = new int[n];
        Arrays.fill(dp, -1);

        parent = new int[n];
        int startIndex = -1;
        int maxLength = 0;
        for(int i=0; i<n; i++){
            int len = getLongestPath(i, nums);
            if(maxLength < len){
                maxLength = len;
                startIndex = i;
            }
        }
        
        // maxIndex points to the first element of the largest subset
        List<Integer> r = new ArrayList<>();
        int len = dp[startIndex];
        for(int i=0; i<len; i++){
            r.add(nums[startIndex]);
            startIndex = parent[startIndex];
        }
        return r;
    }
}

// Longest path problem O(n^2)
class Solution4 {
    public int longestPath(int node, Map<Integer, List<Integer>> graph, Map<Integer, Integer> dp, 
                                                                        Map<Integer, Integer> parent){
        if(dp.containsKey(node))
            return dp.get(node);

        List<Integer> adj = graph.get(node);
        int maxLen = 1;
        int par = node;
        for(int neigh : adj){
            int nLen = longestPath(neigh, graph, dp, parent) + 1;
            if(nLen > maxLen){
                maxLen = nLen;
                par = neigh;
            }
        }
        //System.out.println(node + " maxLen " + maxLen + " par " + par);
        dp.put(node, maxLen);
        parent.put(node, par);
        return maxLen;
    }
    
    public void getPath(int node, Map<Integer, Integer> parent, List<Integer> path){
        //System.out.println(node + " ");
        path.add(node);
        if(node == parent.get(node))
            return;
        getPath(parent.get(node), parent, path);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return path;
        Arrays.sort(nums);
        // distinct integer
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int num : nums){
            graph.put(num, new ArrayList<>());
        }
        for(int i=0; i<n-1; ++i){
            for(int j=i+1; j<n; ++j){
                if(nums[i] == 0 || nums[j] == 0)
                    continue;
                if(nums[j] % nums[i] == 0){
                    // directed
                    graph.get(nums[i]).add(nums[j]);
                }
            }
        }
        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        for(int num : nums)
            parent.put(num, num);
            
        int maxLen = 0;
        int end = -1;
        for(int node : nums){
            int len = longestPath(node, graph, dp, parent);
            if(maxLen < len){
                maxLen = len;
                end = node;
            }
        }
        getPath(end, parent, path);
        return path;
    }
}

// Longest path problem O(n^2) Solution
class Solution5 {
    public int longestPath(int nodeIndex, int[] nums, int[] dp, int[] parent){
        int n = nums.length;

        if(dp[nodeIndex] != -1)
            return dp[nodeIndex];

        int maxLen = 1;
        int par = nodeIndex;
        // Already sorted
        for(int i=nodeIndex + 1; i<n; ++i){
            if(nums[i] % nums[nodeIndex] != 0)
                continue;

            int nLen = longestPath(i, nums, dp, parent) + 1;
            if(nLen > maxLen){
                maxLen = nLen;
                par = i;
            }
        }
        dp[nodeIndex] = maxLen;
        parent[nodeIndex] = par;
        return maxLen;
    }
    
    public void getPath(int nodeIndex, int[] nums, int[] parent, List<Integer> path){
        path.add(nums[nodeIndex]);
        if(nodeIndex == parent[nodeIndex])
            return;
        getPath(parent[nodeIndex], nums, parent, path);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return path;
        Arrays.sort(nums);
        
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        int[] parent = new int[n];
        for(int i=0; i<n; ++i)
            parent[i] = i;

        int maxLen = 0;
        int endIndex = -1;
        for(int i=0; i<n; ++i){
            int len = longestPath(i, nums, dp, parent);
            if(maxLen < len){
                maxLen = len;
                endIndex = i;
            }
        }
        getPath(endIndex, nums, parent, path);
        return path;
    }
}
