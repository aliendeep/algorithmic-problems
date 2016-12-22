/*
Sandy likes palindromes. A palindrome is a word, phrase, number, or other 
sequence of characters which reads the same backward as it does forward. 
For example, madam is a palindrome.

On her  birthday, Sandy's uncle, Richie Rich, offered her an -digit check 
which she refused because the number was not a palindrome. Richie then challenged 
Sandy to make the number palindromic by changing no more than  digits. 
Sandy can only change  digit at a time, and cannot add digits to (or remove 
digits from) the number.

Given  and an -digit number, help Sandy determine the largest possible number 
she can make by changing digits.

Note: Treat the integers as numeric strings. Leading zeros are permitted and 
can't be ignored (So 0011 is not a palindrome, 0110 is a valid palindrome). 
A digit can be modified more than once.

Input Format

The first line contains two space-separated integers,  (the number of digits 
in the number) and  (the maximum number of digits that can be altered), respectively. 
The second line contains an -digit string of numbers that Sandy must attempt to 
make palindromic.

Constraints

Each character  in the number is an integer where .
Output Format

Print a single line with the largest number that can be made by changing no more 
than  digits; if this is not possible, print -1.

Sample Input 0

4 1
3943
Sample Output 0

3993
Sample Input 1

6 3
092282
Sample Output 1

992299
Sample Input 2

4 1
0011
Sample Output 2

-1
*/
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String number = in.next();
        // Check if it's possible to make it palindrome within k digits
        StringBuilder s = new StringBuilder(number);
        int l = s.length();
        int diffCnt = 0;
        for(int i=0, j=l-1; i<j; ++i, j--){
            char a = s.charAt(i);
            char b = s.charAt(j);
            if(a == b)
                continue;
            diffCnt++;
        }
        if(diffCnt > k){
            System.out.println(-1);
            return;
        }
        // Greedy
        int rem = k;
        int remDiffCnt = diffCnt;
        for(int i=0, j=l-1; i<j && rem > 0; ++i, j--){            
            char a = s.charAt(i);
            char b = s.charAt(j);
            if(a == '9' && b == '9')
                continue;
            // different char
            if(a != b){
                // Need to change only different char
                if(rem == remDiffCnt){
                    if(a > b)
                        s.setCharAt(j, a);
                    else
                        s.setCharAt(i, b);
                    rem--;
                }
                else if(rem > remDiffCnt){
                    // extra provisions to change
                    // one of them is 9
                    if(a == '9' || b == '9'){
                        s.setCharAt(i, '9');
                        s.setCharAt(j, '9');
                        rem--;
                    }
                    // change both to 9
                    else if(rem - remDiffCnt >= 1){
                        s.setCharAt(i, '9');
                        s.setCharAt(j, '9');
                        rem -= 2;
                    }                    
                }
                remDiffCnt--;                
            }
            else{
                // Same Character
                // remaining difference count won't change
                if(rem > remDiffCnt){
                    // either one of them is 
                    if(a == '9' || b == '9'){
                        s.setCharAt(i, '9');
                        s.setCharAt(j, '9');
                        rem--;
                    }
                    else if(rem - remDiffCnt >= 2){
                        s.setCharAt(i, '9');
                        s.setCharAt(j, '9');
                        rem -= 2;
                    }
                }
            }
        }
        // change the middle element if odd length
        if(rem > 0 && l % 2 == 1)
            s.setCharAt(l/2, '9');
        
        System.out.println(s.toString());
    }
}
