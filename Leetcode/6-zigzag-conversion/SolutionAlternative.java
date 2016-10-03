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
        if(numRows <= 1)    return s;

        StringBuilder r = new StringBuilder();
        int n = s.length();
        int step = numRows*2-2;
        int step1, step2;

        for(int i=0; i<numRows; i++){
            // First row or last column
            if(i == 0 || i == numRows-1){
                for(int j=i; j<n; j += step)
                    r.append(s.charAt(j));
            }
            else{
                step1 = 2*(numRows - 1 - i);
                step2 = step - step1;
                int j = i;
                boolean flag = true;
                while(j < s.length()){
                    r.append(s.charAt(j));
                    j += (flag ? step1 : step2);
                    flag = !flag;
                }
            }
        }
        return r.toString();        
    }
}