/*

Problem Statement
    
For the purposes of this problem, a DNA sequence is a string consisting of the letters A, C, G, and T.  You have been hired by researchers at TopBiologist to help them with some programming tasks. The researchers have found a sequence of uppercase letters, which is given to you in the String sequence. They have asked you to write a program which find the length of the longest DNA sequence that is a substring of sequence.  A substring can be obtained from sequence by deleting some (possibly zero) characters from the beginning or end. For example, suppose sequence is the string "TOPBOATER". Then "TOP", "PBOA", "T", and "AT" are some examples of substrings of sequence. Of these examples, "T" and "AT" are DNA sequences. The answer to the problem would be 2 because "AT" is the longest such sequence and its length is 2.  Please find and return the length of the longest DNA sequence that is a substring of sequence.
Definition
    
Class:
DNASequence
Method:
longestDNASequence
Parameters:
String
Returns:
int
Method signature:
int longestDNASequence(String sequence)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
sequence will contain between 1 and 50 characters, inclusive.
-
Each character of sequence will be an upper-case English letter ('A'-'Z').
Examples
0)

    
"TOPBOATER"
Returns: 2
This is the example from the problem statement.
1)

    
"SUSHI"
Returns: 0
None of the substrings of "SUSHI" are DNA sequences, so the answer is 0.
2)

    
"GATTACA"
Returns: 7

3)

    
"GOODLUCK"
Returns: 1

4)

    
"VVZWKCSIQEGANULDLZESHUYHUQGRKUMFCGTATGOHMLKBIRCA"
Returns: 6

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class DNASequence
{
  public int longestDNASequence(String sequence)
  {
    int n = sequence.length();
    int run = 0, max = 0;
    for(int i=0; i<n; ++i){
      char c = sequence.charAt(i);
      if(c != 'A' && c != 'C' && c != 'G' && c != 'T'){
        max = Math.max(max, run);
        run = 0;
      }   
      else{
        run++;
      }
    } 
    max = Math.max(max, run);
    return max;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!