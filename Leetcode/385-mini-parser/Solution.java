/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// Recursive
public class Solution {
    public static int index = 0;
    public NestedInteger deserialize(String s) {
        // reset index
        index = 0;
        NestedInteger result = new NestedInteger();
        if(s.length() == 0)
            return result;
        if(s.charAt(0) != '[')
            return intToNestedInteger(s);

        // Skip [
        index++;
        return recurNestedInt(s);
    }
    public NestedInteger intToNestedInteger(String s){
        int sign = 1;
        if(s.charAt(index) == '-'){
            sign = -1;
            index++;
        }
        
        int num = 0;
        while(index < s.length()){
            char c = s.charAt(index);
            if(Character.isDigit(c))
                num = num*10 + c - '0';
            else
                break;
            index++;
        }
        return new NestedInteger(sign*num);
    }
    
    public NestedInteger recurNestedInt(String s){
        NestedInteger result = new NestedInteger();
        while(index < s.length() && s.charAt(index) != ']'){
            char c = s.charAt(index);
            // negative number
            if(c == '['){
                index++;
                result.add(recurNestedInt(s));
            }
            else if(c == '-' || Character.isDigit(c)){
                result.add(intToNestedInteger(s));
            }
            else{
                // ignore ,
                index++;
            }
        }
        // skip the ]
        index++;
        return result;
    }    
}