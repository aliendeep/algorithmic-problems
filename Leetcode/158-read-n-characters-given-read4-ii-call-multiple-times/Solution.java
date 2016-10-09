/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/*
Sample Input:
"ab"
[read(1),read(2)]
Sample Output:
["a",""]
*/

import java.util.*;

// Dummy Reader4 class
class Reader4{
  public int read4(char[] buf){
    return 0;
  }
}

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
    * @param buf Destination buffer
    * @param n   Maximum number of characters to read
    * @return    The number of characters read
    */

    char[] buf4;
    Queue<Character> Q;
    int len;

    public Solution(){
        Q = new LinkedList<>();
        buf4 = new char[4];
    }

    public int read(char[] buf, int n) {
        int i = 0;
        while(i < n){
            // Refill Q if needed
            if(Q.isEmpty()){
                // Read next 4 characters
                len = read4(buf4);
                if(len == 0)
                    break;
                for(int j=0; j<len; j++)    
                    Q.add(buf4[j]);
            }
            if(!Q.isEmpty()){
                buf[i++] = Q.remove();
            }
        }
        return i;
    }
}