/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any 
positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or 
it loops endlessly in a cycle which does not include 1. Those numbers for which 
this process ends in 1 are happy numbers.

Example: 19 is a happy number

1^2 + 9^ 2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
*/

// O(n) Space
public class Solution {
    public List<Integer> split(int n){
        // Split the number into digits
        List<Integer> cur = new ArrayList<>();
        while(n != 0){
            cur.add(n%10);
            n = n/10;
        }
        return cur;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while(n != 1){
            // cycle
            if(set.contains(n))
                return false;
            set.add(n);
            List<Integer> cur = split(n);
            int sum = 0;
            for(int i : cur){
                sum += i*i;
            }
            n = sum;
        }
        return true;
    }
}

class Solution2 {
    public int digitSqrSum(int n){
        int sum = 0;
        while(n != 0){
            int t = n%10;
            sum += t*t;
            n = n/10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while(n != 1){
            // cycle
            if(set.contains(n))
                return false;
            set.add(n);
            n = digitSqrSum(n);   
        }
        return true;
    }
}

// Alternative: O(1) space
class Solution3 {
    public int digitSquareSum(int n){
        int sum = 0;
        while(n != 0){
            int t = n%10;
            sum += t*t;
            n = n/10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do{
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if(fast == 1)
                return true;
        }while(slow != fast);
        
        if(slow == 1)
            return true;
        return false;
    }
}