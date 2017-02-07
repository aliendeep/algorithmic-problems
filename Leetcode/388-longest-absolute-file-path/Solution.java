/*
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 
contains a file file1.ext and an empty second-level sub-directory subsubdir1. 
subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to 
a file within our file system. For example, in the second example above, the 
longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 
32 (not including the double quotes).

Given a string representing the file system in the above format, return the 
length of the longest absolute path to file in the abstracted file system. 
If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another 
path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/

public class Solution {
    public int lengthLongestPath(String input) {
        String[] tokens = input.split("\n");
        int[] len = new int[tokens.length+1];
        int maxLen = 0;
        for(String token : tokens){
            // Number of \t represents the level
            // Find the number of \t in the token
            int lev = token.lastIndexOf("\t") + 1;
            // Length of the previous part of the path + length of the current token - number of \t (as \t is not part of the result) + 1 for the /
            len[lev + 1] = len[lev] + token.length() - lev + 1;
            // if the token is the name of the file (check if it contains .)
            if(token.contains("."))
                // len[lev + 1] - 1 because number of / = # of tokens - 1
                maxLen = Math.max(maxLen, len[lev + 1] - 1);
        }
        return maxLen;
    }
}

// Stack
class Solution {
    public int lengthLongestPath(String input) {
        // length
        Deque<Integer> stk = new LinkedList<>();
        stk.push(0);
        int maxLen = 0;
        for(String token : input.split("\n")){
            // count no of tabs
            int lev = token.lastIndexOf("\t") + 1;
            while(lev + 1 < stk.size()){
                stk.pop();
            }
            // remove tabs and add /
            int length = stk.peekFirst() + token.length() - lev + 1; 
            stk.push(length);
            if(token.contains(".")){
                // remove the last /
                maxLen = Math.max(maxLen, length - 1);
            }
        }
        return maxLen;
    }
}
