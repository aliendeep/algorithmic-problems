/*
Given N axis-aligned rectangles where N > 0, determine if they all together form 
an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. 
For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left 
point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
*/

/*
Line Sweep Algorithm
https://www.topcoder.com/community/data-science/data-science-tutorials/line-sweep-algorithms/
- We have the idea of an event: an X coordinate at which something interesting happens. 
- Need to keep an active set

Bottom-left point 
r[0]: x0 (Event starts) 
r[1]: y0

Top-right point
r[2]: x1 (Event ends) - Remove from the active set
r[3]: y1
https://discuss.leetcode.com/topic/55944/o-n-log-n-sweep-line-solution
*/
public class Solution {
    class Event{
        int time;
        int[] rect;
        public Event(int t, int[] r){
            time  = t;
            rect = r;
        }
    }
    
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        // Sort by x coordinates
        PriorityQueue<Event> Q = new PriorityQueue<Event>(n, new Comparator<Event>(){
            @Override
            public int compare(Event a, Event b){
                if(a.time != b.time)
                    return Integer.compare(a.time, b.time);
                // Same time
                return Integer.compare(a.rect[0], b.rect[0]);
            }
        });    
        int yMin = Integer.MAX_VALUE; 
        int yMax = Integer.MIN_VALUE;
        for(int[] r : rectangles){
            // insert x coordinates
            Q.add(new Event(r[0], r));
            Q.add(new Event(r[2], r));
            yMin = Math.min(yMin, r[1]);
            yMax = Math.max(yMax, r[3]);
        }
        
        // Keep an active set
        TreeSet<int[]> active = new TreeSet<int[]>(new Comparator<int[]>(){
            @Override
            // if intersects, return 0
            public int compare(int[] a, int[] b){
                // No overlap
                // upper right y of a <= lower left y of b
                if(a[3] <= b[1])    return -1;
                // lower left y of a >= upper left y of b
                if(a[1] >= b[3])    return  1;
                // overlaps
                return 0;
            }
        });
        
        int yRange = yMax - yMin;
        int verticalRangeOfCurrentLine = 0;
        while(!Q.isEmpty()){
            int time = Q.peek().time;
            // Same time
            while(!Q.isEmpty() && Q.peek().time == time){
                Event t = Q.poll();
                int[] rect = t.rect;
                // end x
                if(time == rect[2]){
                    // remove this rectangle from the active set
                    active.remove(rect);
                    verticalRangeOfCurrentLine -= (rect[3] - rect[1]);
                }
                // Enter the rectangle into the set
                else{
                    // Already exists
                    if(!active.add(rect))
                        return false;
                    // Add
                    verticalRangeOfCurrentLine += (rect[3] - rect[1]);
                }
            }
            // items left but y range is invalid
            if(!Q.isEmpty() && verticalRangeOfCurrentLine != yRange)
                return false;
        }
        return true;
    }
}

// Line Sweep O(nlogn) Solution
class Solution {
    class Event{
        int time;
        Rectangle rect; 
        public Event(int t, Rectangle r){
            time  = t;
            rect = r;
        }
    }
    class Rectangle{
        int x1, y1, x2, y2;
        public Rectangle(int x1, int y1, int x2, int y2){
            this.x1 = x1;      
            this.y1 = y1;      
            this.x2 = x2;      
            this.y2 = y2;      
        }
    }
    
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        Event[] events = new Event[2*n];
        
        int ymin = Integer.MAX_VALUE, ymax = Integer.MIN_VALUE;
        int i = 0;
        for(int[] rect : rectangles){
            events[i++] = new Event(rect[0], new Rectangle(rect[0], rect[1], rect[2], rect[3]));
            events[i++] = new Event(rect[2], new Rectangle(rect[0], rect[1], rect[2], rect[3]));
            ymin = Math.min(ymin, rect[1]);
            ymax = Math.max(ymax, rect[3]);
        }
        
        Arrays.sort(events, new Comparator<Event>(){
            @Override
            public int compare(Event a, Event b){
                if(a.time != b.time)
                    return Integer.compare(a.time, b.time);
                // Same time
                return Integer.compare(a.rect.x1, b.rect.x1);
            } 
        });   

        TreeSet<Rectangle> active = new TreeSet<Rectangle>(new Comparator<Rectangle>(){
            @Override
            // if intersects, return 0
            public int compare(Rectangle a, Rectangle b){
                // No overlap
                // upper right y of a <= lower left y of b
                if(a.y2 <= b.y1)    return -1;
                // lower left y of a >= upper left y of b
                if(a.y1 >= b.y2)    return  1;
                // overlaps
                return 0;
            }
        });

        int yRange = ymax - ymin;
        int curYRange = 0;
        i = 0;
        while(i < events.length){
            Event prev = events[i];
            while(i < events.length && prev.time == events[i].time){
                Event e = events[i];
                // left edge
                if(e.time == e.rect.x1){
                    if(!active.add(e.rect)){
                        return false;
                    }
                    curYRange += (e.rect.y2 - e.rect.y1); 
                }
                else{
                    active.remove(e.rect);
                    curYRange -= (e.rect.y2 - e.rect.y1); 
                }
                ++i;
            }
            if(i<events.length && curYRange != yRange){
                return false;
            }
        }
        return curYRange == 0;
    }
}

// O(n) Solution (Point counting)
class Solution2 {
    // Observations:
    // Large rectangle area sum should be equal to the sum of the smaller rectangles
    // Count of all points should be even
    // Count of the corner points of the large rectangle should be one
    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles.length == 0)
            return true;
        // Corner points (x1, y1), (x2, y2)
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0;
        Set<String> set = new HashSet<>();
        // Find the corner points
        for(int[] rect : rectangles){
            x1 = Math.min(x1, rect[0]);            
            y1 = Math.min(y1, rect[1]);            
            x2 = Math.max(x2, rect[2]);            
            y2 = Math.max(y2, rect[3]);            
            
            area += (rect[2] - rect[0])*(rect[3] - rect[1]);
            String[] cornerPoints = new String[4];
            cornerPoints[0]  = rect[0] + "#" + rect[1]; 
            cornerPoints[1] = rect[0] + "#" + rect[3]; 
            cornerPoints[2] = rect[2] + "#" + rect[1]; 
            cornerPoints[3] = rect[2] + "#" + rect[3]; 
            // Count of all points should be even
            for(String point : cornerPoints){
                if(set.contains(point))
                    set.remove(point);
                else
                    set.add(point);
            }
        }
        String[] cornerPoints = new String[4];
        cornerPoints[0] = x1 + "#" + y1; 
        cornerPoints[1] = x1 + "#" + y2; 
        cornerPoints[2] = x2 + "#" + y1; 
        cornerPoints[3] = x2 + "#" + y2; 
        
        if(set.size() != 4) 
            return false;
        // Count of the corner points of the large rectangle should be one
        for(String point : cornerPoints){
            if(!set.contains(point))
                return false;
        }
        
        // Large rectangle area sum should be equal to the sum of the smaller rectangles
        return (x2 - x1) * (y2 - y1) == area;
    }
}
