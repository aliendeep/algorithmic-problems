/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/building-outline
@Language: Java
@Datetime: 16-11-25 02:39
*/

import java.util.TreeMap;

public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    class Info{
        int pos;
        int height;
        public Info(int c, int h){
            pos =  c;
            height = h;
        }
    }
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<Info> heightInfos = new ArrayList<>();
        for(int[] building : buildings){
            int h = building[2];
            // negative of height to distinguish between left and right edge
            heightInfos.add(new Info(building[0], -h));    
            heightInfos.add(new Info(building[1],  h));    
        }
        Collections.sort(heightInfos, new Comparator<Info>(){
            @Override
            public int compare(Info a, Info b){
                if(a.pos == b.pos)
                    return Integer.compare(a.height, b.height);
                return Integer.compare(a.pos, b.pos);
            }            
        });
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        // height, count
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        // height 0
        map.put(0, 1);
        int prevHeight = 0;
        for(Info p : heightInfos){
            boolean left = false;
            if(p.height < 0){
                p.height = -p.height;
                left = true;
            }
            if(left){
                if(map.containsKey(p.height))
                    map.put(p.height, map.get(p.height)+1);
                else
                    map.put(p.height, 1);
            }
            // right edge
            else{
                int cnt = map.get(p.height) - 1;
                if(cnt == 0)
                    map.remove(p.height);
                else
                    map.put(p.height, cnt);
            }
            // get the largest height
            int curHeight = map.firstKey();
            if(prevHeight != curHeight){
                ArrayList<Integer> t = new ArrayList<>();
                t.add(p.pos);
                t.add(curHeight);
                result.add(t);
                prevHeight = curHeight;
            }
        }
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        for(int i=0; i<result.size()-1; ++i){
            ArrayList<Integer> p = result.get(i);
            if(p.get(1) == 0)   
                continue;
            
            ArrayList<Integer> t = new ArrayList<>();
            t.add(p.get(0));
            t.add(result.get(i+1).get(0));
            t.add(p.get(1));
            r.add(t);
        }
        return r;
    }
}