/*
You are playing the following Flip Game with your friend: Given a string that 
contains only these two characters: + and -, you and your friend take turns to 
flip two consecutive "++" into "--". The game ends when a person can no longer 
make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a 
win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/

import java.util.*;
// Time complexity: Double factorial
// (n - 1) x (n - 3) x (n - 5) x ...
public class Solution {
    public boolean bktk(char[] s){
        for(int i=0; i<s.length-1; i++){
            if(s[i] == '+' && s[i+1] == '+'){
                s[i] = '-';
                s[i+1] = '-';
                boolean win = !bktk(s);
                s[i] = '+';
                s[i+1] = '+';
                if(win)
                    return true;
            } 
        }
        return false;
    }

    public boolean canWin(String s) {
        return bktk(s.toCharArray());
    }
}

// Backtracking
class Solution2 {
    public boolean canWin(String s) {
        int n = s.length();
        if(n < 2)
            return false;
        for(int i=0; i<n-1; ++i){
            // find ith index starts with ++ 
            if(s.startsWith("++", i)){
                String nextState = s.substring(0, i) + "--" + s.substring(i+2);
                // if the second opponent can't win starting from next state
                if(!canWin(nextState)){
                    return true;
                } 
            }
        }
        return false;
    }
}


// Memoization (Exponential)
class Solution3 {
    public boolean canWin(String s, Map<String, Boolean> map) {
        int n = s.length();
        if(n < 2)
            return false;
        if(map.containsKey(s))     
            return map.get(s);
        for(int i=0; i<n-1; ++i){
            // find ith index starts with ++ 
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                String nextState = s.substring(0, i) + "--" + s.substring(i+2);
                // if the second opponent can't win starting from next state
                if(!canWin(nextState, map)){
                    map.put(s, true);
                    return true;
                } 
            }
        }
        map.put(s, false);
        return false;
    }
    public boolean canWin(String s) {
        int n = s.length();
        if(n < 2)
            return false;
        // String to state (win or lose)
        Map<String, Boolean> map = new HashMap<>();
        return canWin(s, map);
    }    
}