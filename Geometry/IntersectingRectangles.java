/*
You should implement a function that takes as argument an array of NN non-degenerate 
rectangles in the cartesian plane. Return the number of pairs of rectangles that 
strictly intersect each other (their intersection has a strictly positive area).
*/
// https://csacademy.com/contest/interview-archive/#task/intersecting-rectangles
import java.util.*;

class Solution{
    static class Rectangle {
        int x1, y1, x2, y2;
        
        Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    

    static class Rect{
        int x1, y1, x2, y2;
        int id;
        
        Rect(int x1, int y1, int x2, int y2, int id) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.id = id;
        }
        @Override
        public boolean equals(Object ob){
            if(ob instanceof Rect){
                Rect r = (Rect)ob;
                if(r.x1 == this.x1 && r.x2 == this.x2 &&
                   r.y1 == this.y1 && r.y2 == this.y2 &&
                   r.id == this.id)
                    return true;
            }
            return false;
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(x1, x2, y1, y2, id);
        }
    }
    static class Event{
        int time;
        Rect rect;
        public Event(int t, Rect r){
            time = t;
            rect = r;
        }
    }
    
    static boolean intersectSegmentsStrict(int l1, int r1, int l2, int r2) {
        return l1 < r2 && l2 < r1;
    }
    
    static boolean intersect(Rect a, Rect b){
        // if(a.x1 <= b.x2 && a.x2 >= b.x1 && a.y1 <= b.y2 && a.y2 >= b.y1)
        //     return true;
        // return false;
        
        // overlap
        // return Math.max(a.x1, b.x1) < Math.min(a.x2, b.x2) &&
        //       Math.max(a.y1, b.y1) < Math.min(a.y2, b.y2);
        
        //return a.x1 < b.x2 && a.x2 > b.x1 && a.y1 < b.y2 && a.y2 > b.y1;
        
        return intersectSegmentsStrict(a.x1, a.x2, b.x1, b.x2) &&
               intersectSegmentsStrict(a.y1, a.y2, b.y1, b.y2);
    }

    static int intersectCount(Rect a, Set<Rect> active){
        int cnt = 0;
        for(Rect b : active){
            if(intersect(a, b)){
                cnt++;
            }
        }   
        return cnt;
    }

    static int intersectingRectangles(Rectangle[] rectangles) {
        int n = rectangles.length;
        Rect[] r = new Rect[n];
        for(int i=0; i<n; ++i){
            r[i] = new Rect(rectangles[i].x1, rectangles[i].y1, 
                            rectangles[i].x2, rectangles[i].y2, i);
        }

        // Sort by x coordinates
        Event[] events = new Event[2*n];
        int index = 0;
        for(Rect rect : r){
            events[index++] = new Event(rect.x1, rect);
            events[index++] = new Event(rect.x2, rect);
        }
        Arrays.sort(events, new Comparator<Event>(){
            @Override
            public int compare(Event a, Event b){
                return Integer.compare(a.time, b.time);
            }
        });
        
        Set<Rect> active = new HashSet<Rect>();
        int cnt = 0;
        for (Event e : events) {
            Rect rect = e.rect;
            // right edge
            if(e.time == rect.x2){
                active.remove(rect);
            }
            else{
                cnt += intersectCount(rect, active);
                active.add(rect);
            }
        }
        return cnt;
    }  

    // Line sweep using priority queue
    static int intersectingRectangles2(Rectangle[] rectangles) {
        int n = rectangles.length;
        Rect[] r = new Rect[n];
        for(int i=0; i<n; ++i){
            r[i] = new Rect(rectangles[i].x1, rectangles[i].y1, 
                            rectangles[i].x2, rectangles[i].y2, i);
        }
        // Bruteforce solution
        // int ans = 0;
        // for (int i = 0; i < n; ++i) {
        //     for (int j = i+1; j < n; ++j) {
        //         if (intersect(r[i], r[j])) ++ans;
        //     }
        // }
        // return ans;

        // Sort by x coordinates
        PriorityQueue<Event> Q = new PriorityQueue<Event>(n, new Comparator<Event>(){
            @Override
            public int compare(Event a, Event b){
                return Integer.compare(a.time, b.time);
            }
        });    
        for(Rect rect : r){
            Q.add(new Event(rect.x1, rect));
            Q.add(new Event(rect.x2, rect));
        }
        
        Set<Rect> active = new HashSet<Rect>();
        int cnt = 0;
        while(!Q.isEmpty()){
            Event event = Q.poll();
            Rect rect = event.rect;
            // right edge
            if(event.time == rect.x2){
                active.remove(rect);
            }
            else{
                cnt += intersectCount(rect, active);
                active.add(rect);
            }
        }
        return cnt;
    }      
}
