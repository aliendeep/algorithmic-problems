/*
Think about Zuma Game. You have a row of balls on the table, colored red(R), 
yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row 
(including the leftmost place and rightmost place). Then, if there is a group of 
3 or more balls in the same color touching, remove these balls. Keep doing this 
until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. 
If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 

Note:
- You may assume that the initial row of balls on the table won’t have any 3 or 
more consecutive balls with the same color.
- The number of balls on the table won't exceed 20, and the string represents 
these balls is called "board" in the input.
- The number of balls in your hand won't exceed 5, and the string represents 
these balls is called "hand" in the input.
- Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
*/
public class Solution {
    public int findMinStep(String board, String hand) {
        if (hand == null || hand.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < hand.length(); i++) {
            char c = hand.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        res.add(Integer.MAX_VALUE);
        backTrack(board, map, res, 0);
        if (res.get(0) == Integer.MAX_VALUE) {
            return -1;
        }
        return res.get(0);
    }
    void backTrack(String board, Map<Character, Integer> map, List<Integer> res, int val) {
        if (board.length() == 0) {
            res.set(0, Math.min(res.get(0), val));
        }
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            if (i < board.length() - 1 && c == board.charAt(i + 1) && map.getOrDefault(c, 0) > 0) {
                int l = i - 1;
                int r = i + 2;
                while (l >=0 && r < board.length() && board.charAt(l) == board.charAt(r) && count(board, l) + count(board, r) >= 3) {
                    l -= count(board, l);
                    r += count(board, r);
                }
                String left = l >= 0 ? board.substring(0, l + 1) : "";
                String right = r < board.length() ? board.substring(r) : "";
                map.put(c, map.getOrDefault(c, 0) - 1);
                backTrack(left + right, map, res, val + 1);
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else if (map.getOrDefault(c, 0) > 1) {
                int l = i - 1;
                int r = i + 1;
                while (l >=0 && r < board.length() && board.charAt(l) == board.charAt(r) && count(board, l) + count(board, r) >= 3) {
                    l -= count(board, l);
                    r += count(board, r);
                }
                String left = l >= 0 ? board.substring(0, l + 1) : "";
                String right = r < board.length() ? board.substring(r) : "";
                map.put(c, map.getOrDefault(c, 0) - 2);
                backTrack(left + right, map, res, val + 2);
                map.put(c, map.getOrDefault(c, 0) + 2);
            }
        }
    }
    int count(String board, int idx) {
        int l = idx - 1;
        int res = 1;
        while (l >= 0 && board.charAt(l) == board.charAt(idx)) {
            l--;
            res++;
        }
        int r = idx + 1;
        while (r < board.length() && board.charAt(r) == board.charAt(idx)) {
            r++;
            res++;
        }
        return res;
    }
}
