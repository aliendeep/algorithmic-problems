/*
Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
*/

public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int[] cnt = new int[n];
        int[] next = new int[n];
        
        // Compute what's going to be the index of the sentence in the next line if the index of first word in the current line is i in the sentence array
        // Also, keep track how many times the current row can accoomodate the whole sentence 
        for(int i=0; i<n; i++){
            int index = i;
            int curLen = 0;
            int c = 0;
            while(curLen + sentence[index].length() <= cols){
                curLen += sentence[index++].length() + 1;
                if(index == n){
                    c++;
                    // reset
                    index = 0;
                }
            }
            next[i] = index;
            System.out.println("Next " + index + " cnt " + c);
            cnt[i] = c;
        }
        
        // Compute the result
        // Start with 0th word        
        int r = 0;
        int index = 0;
        for(int i=0; i<rows; i++){
            r += cnt[index];
            index = next[index];
        }
        return r;
    }
}