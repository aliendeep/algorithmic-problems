// https://www.hackerrank.com/challenges/bear-and-steady-gene
// Minimum Window Substring
import java.io.*;
import java.util.*;

public class Solution {  
    public static int getId(char c){
        if(c == 'A')    return 0;
        if(c == 'C')    return 1;
        if(c == 'G')    return 2;
        if(c == 'T')    return 3;
        return -1;
    }
    public static char getChar(int i){
        if(i == 0)  return 'A';
        if(i == 1)  return 'C';
        if(i == 2)  return 'G';
        if(i == 3)  return 'T';
        return '0';
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        // desired count
        int d = n/4;
        int[] originalCnt = new int[4];
        for(int i=0; i<n; ++i){
            originalCnt[getId(s.charAt(i))]++;
        }        
        Set<Character> set = new HashSet<>();
        // Generate the map set that contains extraneous character
        int[] target = new int[4];
        for(int i=0; i<4; ++i){
            int extra = (originalCnt[i] - d);
            if(extra > 0){
                set.add(getChar(i));
                target[i] = extra;
            }
        }
        if(set.size() == 0){
            System.out.println(0);
            return;
        }
        // Find the smallest window that contains all the characters in the map 
        int[] cur = new int[4];
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        for(int end = 0; end < n; ++end){
            char c = s.charAt(end);
            int index = getId(c);
            cur[index]++;
            if(cur[index] >= target[index]){
                if(set.contains(c)){
                    set.remove(c);
                }
            }
            // this window covers all the required characters
            if(set.size() == 0){
                // more than required character exists
                while(start < end && cur[getId(s.charAt(start))] > target[getId(s.charAt(start))]){
                    cur[getId(s.charAt(start))]--;                   
                    start++;
                }
                if(minLen > (end + 1 - start)){
                    minLen = (end + 1) - start;
                }
            }
        }
        System.out.println(minLen);
    }
}
