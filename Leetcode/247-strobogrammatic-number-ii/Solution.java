/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees 
(looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
Input: 
3
4
Output:
[101, 111, 181, 609, 619, 689, 808, 818, 888, 906, 916, 986]
[1001, 1111, 1691, 1881, 1961, 6009, 6119, 6699, 6889, 6969, 8008, 8118, 8698, 
8888, 8968, 9006, 9116, 9696, 9886, 9966]
*/
import java.util.*;

// Add Prefix & suffix to the result of n-2
class Solution0 {
    List<String> helper(int n, int m) {
        if(n == 0)  return new ArrayList<>(Arrays.asList(""));
        if(n == 1)  return new ArrayList<>(Arrays.asList("0", "1", "8"));
        List<String> l = helper(n-2, m);
        List<String> r = new ArrayList<>();
        for(String s : l){
          if(n != m)
              r.add("0" + s + "0");
          r.add("1" + s + "1");
          r.add("6" + s + "9");
          r.add("8" + s + "8");
          r.add("9" + s + "6");
        }
        return r;
    }

    public List<String> findStrobogrammatic(int n){
        return helper(n, n);
    }    
}

// StringBuilder
class Solution1 {
    // 1 Length strobogrammatic strings
    List<String> len1; 

    public Solution1(){
        len1 = new ArrayList<>();
        len1.add("0");
        len1.add("1");
        len1.add("8");
    }

    public String addPrefixSuffix(char start, String mid, char end){
        StringBuilder t = new StringBuilder();
        t.append(start);
        t.append(mid);
        t.append(end);
        return t.toString();
    }
    
    public List<String> findStrobogrammaticHelper(int n, int lev) {
        if(n == 0){
            List<String> r = new ArrayList<>();
            r.add("");
            return r;
        }
        if(n == 1)            
            return new ArrayList<>(len1);

        List<String> sub = findStrobogrammaticHelper(n-2, lev);
        List<String> result = new ArrayList<>();
        for(String str : sub){
            // Skip adding 0 at the front
            // Only the first call will have lev == n 
            if(n != lev){
                result.add(addPrefixSuffix('0', str, '0'));
            }
            result.add(addPrefixSuffix('1', str, '1'));
            result.add(addPrefixSuffix('6', str, '9'));
            result.add(addPrefixSuffix('8', str, '8'));
            result.add(addPrefixSuffix('9', str, '6'));
        }
        return result;    
    }
    public List<String> findStrobogrammatic(int n) {
        List<String>  r = findStrobogrammaticHelper(n, n);
        Collections.sort(r);
        return r;
    }
}

// Iterative Solution
class Solution2 {
    public String addPrefixSuffix(char start, String mid, char end){
        StringBuilder t = new StringBuilder();
        t.append(start);
        t.append(mid);
        t.append(end);
        return t.toString();
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> prev, cur;
        if(n % 2 == 0)
            prev = new ArrayList<>(Arrays.asList(""));
        else
            prev = new ArrayList<>(Arrays.asList("0", "1", "8"));          
        
        if(n < 2)
            return prev;

        for(; n>1; n-=2){
            cur = new ArrayList<>();
            for(String str : prev){
                if(n > 3)
                    cur.add(addPrefixSuffix('0', str, '0'));
                
                cur.add(addPrefixSuffix('1', str, '1'));
                cur.add(addPrefixSuffix('6', str, '9'));
                cur.add(addPrefixSuffix('8', str, '8'));
                cur.add(addPrefixSuffix('9', str, '6'));
            }
            prev = new ArrayList<>(cur);
        }     
        Collections.sort(prev);
        return prev;
    }
}

// Recursive
class Solution3 {
    public final static char[][] map = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    List<String> result; 
    public List<String> findStrobogrammatic(int n) {
        char[] s = new char[n];
        result = new ArrayList<>();
        findStrobogrammaticHelper(s, 0, n-1);
        Collections.sort(result);
        return result;
    }

    public void findStrobogrammaticHelper(char[] cur, int l, int r){
        // found n length string
        if(l > r){
            result.add(new String(cur));
            return;
        }
        for(int i=0; i<map.length; ++i){
            char[] pair = map[i];
            // can't start with 0
            if(l == 0 && i == 0)
                continue;

            // 1 length 
            // need to be the same
            if(l == r && pair[0] != pair[1])
                continue;

            cur[l] = pair[0];
            cur[r] = pair[1];
            findStrobogrammaticHelper(cur, l+1, r-1);                       
        }
    }
    public static void main(String[] args){
        Solution ob = new Solution();
        System.out.println(ob.findStrobogrammatic(4));
    }      
}
