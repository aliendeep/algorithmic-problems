public class Solution {
    // digit root
    public int addDigits(int num) {
        return num - (int)(9*Math.floor((num-1)/9));
    }
}