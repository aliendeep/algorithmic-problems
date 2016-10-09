/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

import java.util.*;

// Dummy Reader4 class
class Reader4{
  public int read4(char[] buf){
    return 0;
  }
}
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        for(int start=0; start<n; start += 4){
            char[] t = new char[4];
            int l = read4(t);
            // public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)            
            System.arraycopy(t, 0, buf, start, Math.min(l, n - start));
            if(l < 4)
                return Math.min(start + l, n);
        }
        return n;
    }
}