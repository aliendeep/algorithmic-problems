/*
 Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters 
from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom 
note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   

Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/

public class Solution {
    // Time: O(m + n)
    // Space: O(L) where L is the number of distinct characters appearing in the letter
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> freq = new HashMap<>();
        for(int i=0; i<ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            if(!freq.containsKey(c))
                freq.put(c, 1);
            else
                freq.put(c, freq.get(c)+1);
        }
        
        for(char c : magazine.toCharArray()){
            if(freq.containsKey(c)){
                freq.put(c, freq.get(c)-1);
                if(freq.get(c) == 0){
                    freq.remove(c);
                    // All characters are matched
                    if(freq.isEmpty())
                        break;
                }
            }
        }
        return freq.isEmpty();
    }
}

class Solution2 {
    // Time: O(m + n)
    // Space: O(L) where L is the number of distinct characters appearing in the letter
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[256];
        
        for(int i=0; i<ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            freq[c]++;
        }
        
        for(char c : magazine.toCharArray()){
            if(freq[c] > 0){
                freq[c]--;
            }
        }
        
        for(int f : freq)
            if(f != 0)
               return false;
        return true;
    }
}

class Solution3 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[256];
        for(int i=0; i<magazine.length(); i++)
            cnt[magazine.charAt(i)]++;
        
        for(int i=0; i<ransomNote.length(); i++){
            cnt[ransomNote.charAt(i)]--;
            if(cnt[ransomNote.charAt(i)] < 0)
                return false;
        }
        return true;
    }
}
