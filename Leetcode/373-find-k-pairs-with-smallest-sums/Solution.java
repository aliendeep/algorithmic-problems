/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

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
    class Pair{
        int first, second;
        Pair(int f, int s){
            this.first = f;
            this.second = s;
        }
    };
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> r = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;
        if(nums1 == null || n == 0 || nums2 == null || m == 0)
            return r;
    
        Comparator<Pair> comp = new Comparator<Pair>(){
            @Override
            public int compare(Pair a, Pair b){
                return nums1[a.first] + nums2[a.second] - (nums1[b.first] + nums2[b.second]);
            }
        };

        PriorityQueue<Pair> Q = new PriorityQueue<Pair>(k, comp);
        boolean[][] visited = new boolean[n][m];
        // Index of the number taken from the first array, Index of the number taken from the second array
        Q.add(new Pair(0, 0));
        visited[0][0] = true;
        // Either increment the index of the first array or increment the index of the second array
        int[][] move = new int[][]{{0, 1}, {1, 0}};

        while(!Q.isEmpty() && k > 0){
            Pair p = Q.poll();
            r.add(new int[]{nums1[p.first], nums2[p.second]});
            // For the two moves
            for(int i=0; i<2; i++){
                int x = p.first + move[i][0];
                int y = p.second + move[i][1];
                if(x < n && y < m && !visited[x][y]){
                    Q.add(new Pair(x, y));
                    visited[x][y] = true;
                }
            }            
            k--;
        } 
        return r;
    }
}