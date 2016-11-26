/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/number-of-airplanes-in-the-sky
@Language: Java
@Datetime: 16-11-25 03:06
*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    class Info{
        int pos;
        int isEnd;
        public Info(int p, int e){
            pos = p;
            isEnd = e;
        }
    }
    public int countOfAirplanes(List<Interval> airplanes) { 
        List<Info> a = new ArrayList<>();
        for(Interval in : airplanes){
            a.add(new Info(in.start, 1)); 
            // end - 0
            a.add(new Info(in.end, 0));    
        }    
        
        Collections.sort(a, new Comparator<Info>(){
            @Override
            public int compare(Info x, Info y){
                if(x.pos == y.pos){
                    return Integer.compare(x.isEnd, y.isEnd);
                }
                return Integer.compare(x.pos, y.pos);
            }
        });
        
        int max = 0;
        int cnt = 0;
        for(Info t : a){
            if(t.isEnd == 1)
                cnt++;
            else
                cnt--;
            max = Math.max(max, cnt);
        }
        return max;
    }
}