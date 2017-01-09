/*
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/

// cleaner
public class Solution {
    public void lexicalOrderHelper(int cur, int n, List<Integer> r){
        if(cur > n) return;
        r.add(cur);
        for(int offset = 0; offset <= 9; ++offset){
          int t = cur*10 + offset;
          lexicalOrderHelper(t, n, r);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        for(int i=1; i<=n && i<=9; ++i)
          lexicalOrderHelper(i, n, r);
        return r;
    }
}

class Solution1 {
    // Preorder traversal (root, left, right)
    // Each node has two children left: val*10 and right: val + 1
    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        compute(1, n, r);
        return r;
    }
    
    public void compute(int i, int n, List<Integer> r){
        r.add(i);
        if(i*10 <= n)
            compute(i*10, n, r);
        if(i < n && i % 10 < 9)        
            compute(i+1, n, r);
    }
}

class Solution2 {
    public void recursive(int num, int n, List<Integer> result){
        for(int i=0; i<=9; i++){
            int t = num*10 + i;
            if(t > n)   
                break;
            result.add(t);
            recursive(t, n, result);
        }
    }
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for(int i=1; i<=n && i<=9; i++){
            result.add(i);
            recursive(i, n, result);
        }
        return result;
    }
}

class Solution3 {
    // Alternative: Iterative
    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        int prev = 1;
        r.add(1);
        for(int i=1; i<n; ++i){
            if(prev*10 <= n){
                prev = prev*10;
            }
            else{
                // If prev = 49, then next digit should be 5
                while(prev == n || prev % 10 == 9){
                    prev /= 10;
                }
                prev++;
            }
            r.add(prev);
        }
        return r;
    }
}

// Iterative
class Solution4{
    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        int cur = 1;
        for(int i=0; i<n; ++i){
            r.add(cur);
            if(cur*10 <= n){
                cur = cur*10;
            }
            else{
                if(cur >= n){
                    cur = cur/10;
                }
                cur++;
                while(cur % 10 == 0){
                    cur = cur/10;
                }
            }
        }
        return r;
    }
}
