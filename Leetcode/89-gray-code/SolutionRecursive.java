/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
*/

public class Solution {
    public List<Integer> grayCode(int n) {
        if(n == 0)
            return Arrays.asList(0);
        if(n == 1)
            return Arrays.asList(0, 1);
        
        // Begins with 0
        List<Integer> grayCodeBitMnus = grayCode(n-1);
        
        int leadingBitOne = (1 << (n-1));
        List<Integer> reflection = new ArrayList<>();
        for(int i=grayCodeBitMnus.size() - 1; i>=0; i--){
            reflection.add(leadingBitOne | grayCodeBitMnus.get(i));
        }
        
        List<Integer> result = new ArrayList<>(grayCodeBitMnus);
        result.addAll(reflection);
        return result;
    }
}