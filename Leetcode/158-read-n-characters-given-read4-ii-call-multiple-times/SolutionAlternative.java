/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
    * @param buf Destination buffer
    * @param n   Maximum number of characters to read
    * @return    The number of characters read
    */
    char[] buf4;
    // n4 : available number of characters
    static int i4 = 0, n4 = 0;
    
    public Solution(){
        buf4 = new char[4];
        i4 = 0;
        n4 = 0;
    }
    public int read(char[] buf, int n) {
        int i = 0;
        while(i < n){
            if(i4 == 0 || i4 >= n4){
                i4 = 0;
                n4 = read4(buf4);
                if(n4 == 0)
                    break;
            }
            // copy only i4 < n4
            buf[i++] = buf4[i4++];
        }
        return i;
    }
}