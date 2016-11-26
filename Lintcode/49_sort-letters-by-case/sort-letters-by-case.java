/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/sort-letters-by-case
@Language: Java
@Datetime: 16-11-08 05:40
*/

public class Solution {
    /** 
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        //write your code here
        int n = chars.length;
        int[] lcnt = new int[26];
        for(int i=0; i<n; ++i){
            if(chars[i] >= 'a' && chars[i] <= 'z')    
                lcnt[chars[i] - 'a']++;
        }
        int[] ucnt = new int[26];
        for(int i=0; i<n; ++i){
            if(chars[i] >= 'A' && chars[i] <= 'Z')    
                ucnt[chars[i] - 'A']++;
        }
        
        int index = 0;
        for(int i=0; i<26; ++i){
            while(lcnt[i] > 0){
                chars[index++] = (char)(i + 'a');
                lcnt[i]--;
            }
        }

        for(int i=0; i<26; ++i){
            while(ucnt[i] > 0){
                chars[index++] = (char)(i + 'A');
                ucnt[i]--;
            }
        }

    }
}
