/*
You are playing the following Nim Game with your friend: There is a heap of 
stones on the table, each time one of you take turns to remove 1 to 3 stones. 
The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a 
function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game:
no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones 
such that you will always be the winner?
*/
public class Solution {
    public boolean canWinNim(int n) {
        // Simulation
        // 1, 2, 3, 4, 5, 6, 7, 8
        // W  W  W  L  W  W  W  L 
        return n%4 == 0 ? false : true;        
    }
}

// Approach 2: Gives stack overflow for large number (1348820612)
class Solution2 {
    static int[] move = {1, 2, 3};
    // True - winning state
    // false - losing state
    Map<Integer, Boolean> dp;
    public boolean canWin(int n){
        if(n == 0)
            return false;

        if(dp.containsKey(n))
            return dp.get(n);
        
        for(int i=0; i<3; ++i){
            if(n >= move[i]){
                int next = n - move[i];
                if(!canWin(next)){
                    dp.put(n, true);
                    return true;
                }
            }
        }
        dp.put(n, false);
        return false;
        
    }
    
    public boolean canWinNim(int n) {
        dp = new HashMap<>();
        if(n <= 1000){
            return canWin(n);
        }
        else{
            return n%4 == 0 ? false : true;
        }
    }
}
