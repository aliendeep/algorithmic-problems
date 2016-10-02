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

/*
000
001
011
010
110
111
101
100

We can see that the last two digits of 4 codes at the bottom is just the descending sequence of the first 4 codes. The first 4 codes are 0, 1, 3, 2. So, we can easily get the last 4 codes: 2 + 4, 3 + 4, 1 + 4, 0 + 4, which is 6, 7, 5, 4. We can keep doing this until we reach n digits.
*/
// Time: O(2^n)

public class Solution {
    // Wikipedia
    public List<Integer> grayCode(int n) {
        List<Integer> r = new ArrayList<>();

        int total = (1<<n);
        for(int i=0; i<total; i++)
            r.add(i ^ (i >> 1));

        return r;
    }
}