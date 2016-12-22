/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one 
element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]
*/
public class Solution {
    // min heap
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> r = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;
        if(n == 0 || m == 0 || k == 0)
            return r;
        
        PriorityQueue<int[]> minHeap = new PriorityQueue(k, new Comparator<int[]>(){
            @Override
            public int compare(int[] x, int[] y){
               return Integer.compare(nums1[x[0]]+nums2[x[1]], nums1[y[0]]+nums2[y[1]]);
            }
        });

        boolean[][] visited = new boolean[n][m];
        // Index of the number taken from the first array, Index of the number taken from the second array
        minHeap.add(new int[]{0, 0});
        visited[0][0] = true;
        // Either increment the index of the first array or increment the index of the second array
        int[][] move = new int[][]{{0, 1}, {1, 0}};

        while(!minHeap.isEmpty() && k > 0){
            int[] p = minHeap.poll();
            r.add(new int[]{nums1[p[0]], nums2[p[1]]});
            // For the two moves
            for(int i=0; i<2; i++){
                int x = p[0] + move[i][0];
                int y = p[1] + move[i][1];
                if(x < n && y < m){
                    if(!visited[x][y]){
                        minHeap.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }            
            k--;
        } 
        return r;
    }
}

// Bruteforce: maxHeap
class Solution2 {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue(k, new Comparator<int[]>(){
           @Override
           public int compare(int[] x, int[] y){
               return Integer.compare(y[0]+y[1], x[0]+x[1]);
           }
        });
        
        for(int i=0; i<nums1.length; ++i){
            for(int j=0; j<nums2.length; ++j){
                int[] t = new int[2];
                t[0] = nums1[i];
                t[1] = nums2[j];
                maxHeap.add(t);
            }
        }
        // remove extra k elements
        while(maxHeap.size() > k){
            maxHeap.poll();
        }
        List<int[]> result = new ArrayList<>(maxHeap);
        return result;
    }
}
