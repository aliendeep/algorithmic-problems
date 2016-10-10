/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/

public class Solution {
    public int minArea(char[][] image, int x, int y) {
        int r = image.length;
        int c = image[0].length;
        int rightX = 0, rightY = 0;
        int leftX = r, leftY = c;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(image[i][j] == '1'){
                    leftX = Math.min(leftX, i);
                    leftY = Math.min(leftY, j);
                    rightX = Math.max(rightX, i);
                    rightY = Math.max(rightY, j);
                }
            }
        }
        return (rightX - leftX + 1)*(rightY - leftY + 1);
    }
}
