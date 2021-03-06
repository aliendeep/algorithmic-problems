/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the 
first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 
1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 
1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if 
it's an additive number.

Follow up:
How would you handle overflow for very large input integers?
*/
import java.math.*;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        
        // length
        for(int l1=1; l1<=n/2; ++l1){
            if(l1 > 1 && num.charAt(0) == '0')
                return false;
            String a = num.substring(0, l1);
            // length of sum should be atleast the maximum length of l1 and l2
            for(int l2=1; Math.max(l1, l2) <= n-l1-l2; ++l2){
                // > 1 length and starts with 0
                if(l2 > 1 && num.charAt(l1) == '0')
                    break;
                String b = num.substring(l1, l1+l2);
                if(isValid(a, b, l1+l2, num))
                    return true;
            }
        }
        return false;
    }
    
    String add(String a, String b){
        BigInteger b1 = new BigInteger(a);
        BigInteger b2 = new BigInteger(b);
        BigInteger result = b1.add(b2);
        return result.toString();
    }
    
    boolean isValid(String a, String b, int lev, String num){
        if(lev == num.length())
            return true;
        String sum = add(a, b);  
        if(!num.startsWith(sum, lev))
            return false;
        return isValid(b, sum, lev + sum.length(), num); 
    }
}

// Slightly different implementation
class Solution2 {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        // Choose the first number
        // Length of the sum should be at least as long as the first number
        // length of first number = i
        // (string starts from 0)
        for(int i=1; i<=(n-1)/2; i++){
            // If the length of the first string > 1 and starts with 0, then break;
            // Numbers in the additive sequence cannot have leading zeros
            if(num.charAt(0) == '0' && i>=2)
                break;

            // Choose the second number
            // Length of the second number: n - j. Length of the sum should be 
            // n - j >= max(length of first num, length of second num) = (i, j-i)
            // length of second number = j (string starts from i)
            for(int j=i+1; n-j >= i && n-j >= j-i; j++){
                // If the length of the second string > 1 and starts with 0, then break;
                if(num.charAt(i) == '0' && j-i>=2)
                    break;
                String a = num.substring(0, i);    
                String b = num.substring(i, j);
                String sum = num.substring(j);
                
                if(isAdditiveNumberHelper(a, b, sum))
                    return true;
            }
        }
        return false;
    }
    
    boolean isAdditiveNumberHelper(String a, String b, String sum){
        if(sum.length() == 0)
            return true;
        String sumOfAB = add(a, b);
        // sum does not starts with sum of a & b
        if(sum.startsWith(sumOfAB) == false)
            return false;
            
        String rest = sum.substring(sumOfAB.length());
        return isAdditiveNumberHelper(b, sumOfAB, rest);
    }
    
    String add(String a, String b){
        BigInteger b1 = new BigInteger(a);
        BigInteger b2 = new BigInteger(b);
        BigInteger result = b1.add(b2);
        return result.toString();
    }
}

// String Addition
class Solution3 {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        
        // length
        for(int l1=1; l1<=n/2; ++l1){
            if(l1 > 1 && num.charAt(0) == '0')
                return false;
            String a = num.substring(0, l1);
            // length of sum should be atleast the maximum length of l1 and l2
            for(int l2=1; Math.max(l1, l2) <= n-l1-l2; ++l2){
                // > 1 length and starts with 0
                if(l2 > 1 && num.charAt(l1) == '0')
                    break;
                String b = num.substring(l1, l1+l2);
                if(isValid(a, b, l1+l2, num))
                    return true;
            }
        }
        return false;
    }
    
    String addition(String p, String q){
        int n = p.length();
        int m = q.length();
        StringBuilder a = new StringBuilder(p);
        a.reverse();
        StringBuilder b = new StringBuilder(q);
        b.reverse();

        int i = 0, j = 0;
        int x, y, sum, carry = 0;
        StringBuilder r = new StringBuilder();
        while(i < n || j < m || carry != 0){
            x = 0;
            if(i < n){
                x = a.charAt(i) - '0';
                i++;
            }
            y = 0;
            if(j < m){
                y = b.charAt(j) - '0';
                j++;
            }
            
            sum = (x + y + carry) % 10;
            carry = (x + y + carry)/10;
            r.append((char)(sum + '0'));
        }
        r.reverse();
        return r.toString();
    }
    
    boolean isValid(String a, String b, int lev, String num){
        if(lev == num.length())
            return true;
        String sum = addition(a, b);  
        if(!num.startsWith(sum, lev))
            return false;
        return isValid(b, sum, lev + sum.length(), num); 
    }
}

// Iterative
class Solution4 {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        // length
        for(int l1=1; l1<=n/2; ++l1){
            if(l1 > 1 && num.charAt(0) == '0')
                return false;
            // length of sum should be atleast the maximum length of l1 and l2
            for(int l2=1; Math.max(l1, l2)+l1+l2<=n; ++l2){
                // > 1 length and starts with 0
                if(l2 > 1 && num.charAt(l1) == '0')
                    break;
                if(isValid(l1, l2, num))
                    return true;
            }
        }
        return false;
    }
    
    boolean isValid(int i, int j, String num){
        int n = num.length();
        int k = i + j;
        String sum;
        String x = num.substring(0, i);
        String y = num.substring(i, i+j);
        while(k < n){
            sum = addition(x, y);
            if(!num.startsWith(sum, k))
                return false;
            x = y;
            y = sum;
            k += sum.length();
        }
        return true;
    }
    
    String addition(String p, String q){
        int n = p.length();
        int m = q.length();
        StringBuilder a = new StringBuilder(p);
        a.reverse();
        StringBuilder b = new StringBuilder(q);
        b.reverse();

        int i = 0, j = 0;
        int x, y, sum, carry = 0;
        StringBuilder r = new StringBuilder();
        while(i < n || j < m || carry != 0){
            x = 0;
            if(i < n){
                x = a.charAt(i) - '0';
                i++;
            }
            y = 0;
            if(j < m){
                y = b.charAt(j) - '0';
                j++;
            }
            
            sum = (x + y + carry) % 10;
            carry = (x + y + carry)/10;
            r.append((char)(sum + '0'));
        }
        r.reverse();
        return r.toString();
    }
}

