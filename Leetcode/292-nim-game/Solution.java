public class Solution {
    public boolean canWinNim(int n) {
        // Simulation
        // 1, 2, 3, 4, 5, 6, 7, 8
        // W  W  W  L  W  W  W  L 
        return n%4 == 0 ? false : true;        
    }
}