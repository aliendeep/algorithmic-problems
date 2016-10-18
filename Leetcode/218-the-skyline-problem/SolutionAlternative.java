/*
Sample Input:
[[2, 9, 10], [3, 7, 15], [5, 12, 12], [15, 20, 10], [19, 24, 8] ]
Sample Output:
[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
*/
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for(int[] building : buildings){
            // negative of height to distinguish between left and right edge
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1],  building[2]});
        }
        
        Collections.sort(heights, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        });
        
        List<int[]> result = new ArrayList<>();
        
        // Java doesn't have multimap!! this is just poor man's multimap.
        TreeMap<Integer /* height */, Integer /* count */> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1);
        
        int prevHeight = 0;
        for(int[] h : heights){
            int x = h[0];
            int height = h[1];
            boolean left = false;
            if (height < 0) {
                height = -height;
                left = true;
            }

            // if left edge
            if(left){
                if(!heightMap.containsKey(height))
                    heightMap.put(height, 1);
                else    
                    heightMap.put(height, heightMap.get(height) + 1);
            }
            else{  // right
                int cnt = heightMap.get(height);
                if(cnt == 1){
                    heightMap.remove(height);  // 0
                }
                else
                    heightMap.put(height, cnt-1);
            }
            int curHeight = heightMap.firstKey();
            if(prevHeight != curHeight){
                result.add(new int[]{h[0], curHeight});
                prevHeight = curHeight;
            }
        }
        return result;
    }
}