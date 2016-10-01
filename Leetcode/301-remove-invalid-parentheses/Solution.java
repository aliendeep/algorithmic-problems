/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

public class Solution {
    // https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
    // DFS
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        removeInvalidParen(s, 0, 0, new char[]{'(', ')'}, result);
        return result;
    }
    public void removeInvalidParen(String s, int start, int removePos, char[] order, List<String> result){
        // Use a counter to keep track of the parenthesis
        int counter = 0;
        for(int i = start; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == order[0])
                counter++;
            else if(c == order[1])
                counter--;
            if(counter >= 0)
                continue;
            // S[0..i] is balanced or in case where order[0] = '(' and order[1] = ')'
            // Number of left parenthesis is <= number of right parenthesis. So, we can go on removing any extra ')'
            for(int j=removePos; j<=i; j++){
                // if it's possible to remove, either starting or the previous character is not the same (to avoid duplicate result)
                if(s.charAt(j) == order[1] && (j == removePos || s.charAt(j-1) != order[1])){
                    String rest = s.substring(0, j) + s.substring(j+1);
                    // remove jth char
                    removeInvalidParen(rest, i, j, order, result);
                }
            }
            // Return all possible results.
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if(order[0] == '(')
            removeInvalidParen(reversed, 0, 0, new char[]{')', '('}, result);
        else
            result.add(reversed);
    }
}