/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
*/

public class Solution {
    int rectangleArea(int x1, int y1, int x2, int y2){
        return (x2-x1)*(y2-y1); 
    }
    
    boolean isIntersect(int A, int B, int C, int D, int E, int F, int G, int H){
        return A <= G && C >= E && B <= H && D >= F;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = rectangleArea(A, B, C, D);
        int area2 = rectangleArea(E, F, G, H);
        if(!isIntersect(A, B, C, D, E, F, G, H))
            return area1 + area2;

        int ix1 = Math.max(A, E);
        int iy1 = Math.max(B, F);
        int ix2 = Math.min(C, G);
        int iy2 = Math.min(D, H);
        int commonArea = rectangleArea(ix1, iy1, ix2, iy2);
        return area1 + area2 - commonArea;
    }
}