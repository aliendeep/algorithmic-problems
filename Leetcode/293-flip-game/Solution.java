/*
You are playing the following Flip Game with your friend: Given a string that contains 
only these two characters: + and -, you and your friend take turns to flip two 
consecutive "++" into "--". The game ends when a person can no longer make a move 
and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
*/
// O(n) 
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> r = new ArrayList<>();
        char[] a = s.toCharArray();
        for(int i=0; i<a.length-1; i++){
            if(a[i] == a[i+1] && a[i] == '+'){
                a[i] = '-';
                a[i+1] = '-';
                r.add(new String(a));
                //undo
                a[i] = '+';
                a[i+1] = '+';
            }
        }
        return r;
    }
}

// Find index of ++ and move ahead
class Solution2 {
    public List<String> generatePossibleNextMoves(String s) {
        int n = s.length();
        List<String> result = new ArrayList<>();
        if(n == 0)
            return result;

        int i = 0;
        StringBuilder cur = new StringBuilder(s);
        while(i < n){
            int index = s.indexOf("++", i);            
            if(index == -1)     break;
            
            cur.setCharAt(index, '-');
            cur.setCharAt(index + 1, '-');

            result.add(cur.toString());

            cur.setCharAt(index, '+');
            cur.setCharAt(index + 1, '+');
            
            // update index
            i = index + 1;
        }
        return result;
    }
}