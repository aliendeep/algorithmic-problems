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
// Bruteforce
public class Solution {
    int minCnt;
    int countLeft(String board, int index){
        char c = board.charAt(index);
        int cnt = 0;
        while(index >= 0 && board.charAt(index) == c){
            index--;
            cnt++;
        }
        return cnt;
    }
    
    int countRight(String board, int index){
        int n = board.length(); 
        int cnt = 0;
        char c = board.charAt(index);
        while(index < n && board.charAt(index) == c){
            index++;
            cnt++;
        }
        return cnt;
    }
    
    public void bktk(String board, int[] cnt, int steps){
        int n = board.length(); 
        if(n == 0){
            minCnt = Math.min(minCnt, steps);
            return;
        }
        for(int i=0; i<n; ++i){
            // if remaining count is > 1
            char c = board.charAt(i);
            // You may assume that the initial row of balls on the table won’t have any 3 or 
            // more consecutive balls with the same color.
            // remaining count > 0
            if(cnt[c-'A'] > 0){
                // add one ball
                if(i < n-1 && c == board.charAt(i+1)){
                    // Count which other same colored balls become adjacent and get removed if we add this c colored ball
                    int left = i - 1;
                    int right = i + 2;
                    while(left>=0 && right<n && board.charAt(left) == board.charAt(right)){
                        int c1 = countLeft(board, left);
                        int c2 = countRight(board, right);
                        if(c1 + c2 < 3)
                            break;
                        left -= c1;
                        right += c2;
                    }
                    StringBuilder rest = new StringBuilder();
                    if(left >= 0)
                        rest.append(board.substring(0, left+1));
                    if(right < n)
                        rest.append(board.substring(right));
                    cnt[c - 'A']--;
                    bktk(rest.toString(), cnt, steps + 1);
                    cnt[c - 'A']++;
                }
                // add two balls
                else if(cnt[c-'A'] >= 2){
                    int left = i - 1;
                    int right = i + 1;
                    while(left>=0 && right<n && board.charAt(left) == board.charAt(right)){
                        int c1 = countLeft(board, left);
                        int c2 = countRight(board, right);
                        if(c1 + c2 < 3)
                            break;
                        left -= c1;
                        right += c2;
                    }
                    StringBuilder rest = new StringBuilder();
                    if(left >= 0)
                        rest.append(board.substring(0, left+1));
                    if(right < n)
                        rest.append(board.substring(right));
                    cnt[c - 'A'] -= 2;
                    bktk(rest.toString(), cnt, steps + 2);
                    cnt[c - 'A'] += 2;
                }
            }
        }
    }
    public int findMinStep(String board, String hand) {
        int m = board.length();
        int[] cnt = new int[26];
        int[] total = new int[26];
        for(int i=0; i<hand.length(); ++i){
            int c = hand.charAt(i) - 'A';
            cnt[c]++;
            total[c]++;
        }
        for(int i=0; i<board.length(); ++i){
            int c = board.charAt(i) - 'A';
            total[c]++;
        }
        for(int i=0; i<board.length(); ++i){
            int c = board.charAt(i) - 'A';
            if(total[c] < 3)
                return -1;
        }
        minCnt = Integer.MAX_VALUE;
        bktk(board, cnt, 0);
        return minCnt == Integer.MAX_VALUE ? -1 : minCnt;  
    }
}
