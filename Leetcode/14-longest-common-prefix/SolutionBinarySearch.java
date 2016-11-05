// Write a function to find the longest common prefix string amongst an array of strings.

// Solution (Binary Search)
public class Solution {
    public boolean isSameCharacter(String[] strs, int index){
        int n = strs.length;
        int len = strs[0].length();
        if(len < index)
            return false;
        for(int i=1; i<n; ++i){
            if(strs[i].length() <= index || strs[i].charAt(index) != strs[0].charAt(index))
                return false;
        }    
        return true;
    }
    
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";
        
        int low = 0, high = strs[0].length() - 1;
        while(high - low > 3){
            int mid = (low + high)/2;
            if(!isSameCharacter(strs, mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        for(int i=low; i<=high; ++i){
            if(!isSameCharacter(strs, i))
                return strs[0].substring(0, i);
        }
        return strs[0].substring(0, high+1);
    }
}