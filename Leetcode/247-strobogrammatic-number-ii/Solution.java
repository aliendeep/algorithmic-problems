/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

public class Solution {
    // 1 Length strobogrammatic strings
    List<String> len1; 

    public Solution(){
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
        System.out.print(t);
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
        return findStrobogrammaticHelper(n, n);
    }
}