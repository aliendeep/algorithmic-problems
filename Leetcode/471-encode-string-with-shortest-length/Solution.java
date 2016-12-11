/*
Given a non-empty string, encode the string such that its encoded length is the 
shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square 
brackets is being repeated exactly k times.

Note:
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The 
string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If 
there are several solutions, return any of them is fine.
Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input 
string, so we do not encode it.
Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the 
same length = 5, which is the same as "10[a]".
Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to 
"2[abbb]c", so one answer can be "2[2[abbb]c]".
*/

public class Solution {
    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        // init
        for(int len=1; len<=n; ++len){
            for(int start=0; start+len<=n; ++start){
                int end = start + len - 1;
                dp[start][end] = s.substring(start, end+1);
                for(int k=start; k<end; ++k){
                    String str = dp[start][k] + dp[k+1][end];
                    if(str.length() < dp[start][end].length())
                        dp[start][end] = str;
                }
                for(int f=1; f*f<=len; ++f){
                    if(len % f != 0)
                        continue;
                    int k = start;
                    for(k=start; k+f-1<=end; k+=f){
                        if(!dp[start][start+f-1].equals(dp[k][k+f-1])){
                            break;
                        }
                    }  
                    if(k+f-1 > end){
                        String x = Integer.toString(len/f) + "[" + dp[start][start+f-1] + "]";
                        if(x.length() < dp[start][end].length())
                            dp[start][end] = x;
                    }
                    
                    
                    int x = len/f;
                    for(k=start; k+x-1<=end; k+=x){
                        if(!dp[start][start+x-1].equals(dp[k][k+x-1])){
                            break;
                        }
                    }  
                    if(k+x-1 > end){
                        String t = Integer.toString(len/x) + "[" + dp[start][start+x-1] + "]";
                        if(t.length() < dp[start][end].length())
                            dp[start][end] = t;
                    }
                }
            }
        }
        
        return dp[0][n-1];
    }
}
