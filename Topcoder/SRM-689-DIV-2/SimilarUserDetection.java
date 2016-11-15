/*

Problem Statement
    
Each competitor on Topcoder must have a handle. Sometimes, different people may choose handles that look too similar to each other. For example, consider the handles "TOPCODER" and "T0PCODER". These two handles are different: the second character in the first handle is the letter 'O' (big oh), while the second character in the second handle is the digit '0' (zero).   In this problem, all handles are nonempty strings. Each character in a handle must be a letter ('a'-'z', 'A'-'Z') or a digit ('0'-'9'). We will consider two groups of characters that look too similar to each other. The first group are the characters 'O' (big oh) and '0' (zero) mentioned above. The second group are the characters '1' (one), 'l' (lowercase ell), and 'I' (uppercase i).   Strings S and T are called similar if we can change S into T by repeatedly replacing a character by another character from the same group.   You are given the String[] handles. Return "Similar handles found" if there is at least one pair of similar handles in handles. Otherwise, return "Similar handles not found". Note that the quotes are for clarity only and that the return value is case sensitive.
Definition
    
Class:
SimilarUserDetection
Method:
haveSimilar
Parameters:
String[]
Returns:
String
Method signature:
String haveSimilar(String[] handles)
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
handles will contain between 2 and 50 elements, inclusive.
-
Each element in handles will contain between 1 and 50 characters, inclusive.
-
Each character in handles will be '0'-'9', 'a'-'z' or 'A'-'Z'.
Examples
0)

    
{"top", "coder", "TOPCODER", "TOPC0DER"}
Returns: "Similar handles found"
"TOPCODER" and "TOPC0DER" are similar.
1)

    
{"Topcoder", "topcoder", "t0pcoder", "topcOder"}
Returns: "Similar handles not found"
No two of these handles are similar. Note that case matters: the letter 'o' (lowercase oh) is not in the group with 'O' and '0'.
2)

    
{"same", "same", "same", "different"}
Returns: "Similar handles found"
Any two identical strings are similar.
3)

    
{"0123", "0I23", "0l23", "O123", "OI23", "Ol23"}
Returns: "Similar handles found"
These six handles are all similar to each other.
4)

    
{"i23", "123", "456", "789", "000", "o", "O"}
Returns: "Similar handles not found"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class SimilarUserDetection
{
  
  public String haveSimilar(String[] handles){
    Set<String> set = new HashSet<>();
    for(String str : handles){
      int len = str.length();
      StringBuilder pattern = new StringBuilder();
      for(int i=0; i<str.length(); ++i){
        char c = str.charAt(i);       
        if(c == '0' || c == 'O'){
          pattern.append('0');
        }
        else if(c == '1' || c == 'l' || c == 'I'){
          pattern.append('1');
        }
        else{
          pattern.append(c);
        }         
      }
      if(set.contains(pattern.toString())){
        return "Similar handles found";       
      }
      else{
        set.add(pattern.toString());
      }
    }
    return "Similar handles not found";
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!