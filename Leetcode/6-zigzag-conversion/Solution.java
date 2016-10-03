/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
    public String convert(String s, int numRows) {
        // init
        StringBuilder[] sb = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++)
            sb[i] = new StringBuilder();
        
        int i = 0;
        int n = s.length();
        while(i < n){
            for(int index=0; index<numRows && i < n; index++, i++)
                sb[index].append(s.charAt(i));
    
            for(int index=numRows-2; index > 0 && i < n; index--, i++)
                sb[index].append(s.charAt(i));
        }
        
        for(int index=1; index<numRows; index++){
            sb[0].append(sb[index]);
        }
        return sb[0].toString();
    }
}