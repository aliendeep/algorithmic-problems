/* 
A strobogrammatic number is a number that looks the same when rotated 180 degrees 
(looked at upside down).

Write a function to determine if a number is strobogrammatic. 
The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

public class Solution {
    public boolean isStrobogrammatic(String num) {
        int n = num.length();
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        for(int i=0, j=n-1; i<=j; i++, j--){
            char x = num.charAt(i);
            char y = num.charAt(j);
            if(!map.containsKey(y))
                return false;
            if(x != map.get(y))
                return false;
        }
        return true;
    }
}
