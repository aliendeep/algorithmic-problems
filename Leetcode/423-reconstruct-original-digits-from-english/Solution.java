/*
Given a non-empty string containing an out-of-order English representation of 
digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That 
means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
*/
// Cleaner 
// Try to count unique letter 
public class Solution {
    public String originalDigits(String s) {
        int n = s.length();
        int[] cnt = new int[10];
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            if(c == 'z')    cnt[0]++;
            if(c == 'o')    cnt[1]++; // 0, 1, 2, 4
            if(c == 'w')    cnt[2]++;
            if(c == 'h')    cnt[3]++; // 3-8
            if(c == 'u')    cnt[4]++;
            if(c == 'f')    cnt[5]++; // 4-5
            if(c == 'x')    cnt[6]++; 
            if(c == 's')    cnt[7]++; // 6 - 7
            if(c == 'g')    cnt[8]++;
            if(c == 'i')    cnt[9]++; // 9, 5, 6, 8
        }
        cnt[1] -= (cnt[2] + cnt[0] + cnt[4]);
        cnt[3] -= cnt[8];
        cnt[5] -= cnt[4];
        cnt[7] -= cnt[6];
        cnt[9] = cnt[9] - (cnt[5] + cnt[6] + cnt[8]);
        StringBuilder r = new StringBuilder();
        for(int i=0; i<10; ++i){
            while(cnt[i] > 0){
                r.append(i);
                cnt[i]--;
            }
        }
        return r.toString();
    }
}

class Solution2 {
    String[] map = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    Map<Character, Integer> uniqueMap;
    
    public void decrement(int[] cnt, int index){
        String s = map[index];
        for(int i=0; i<s.length(); i++){
            cnt[s.charAt(i) - 'a']--;
        }    
    }
    
    public void update(String unique, int[] cnt, StringBuilder r){
        for(int i=0; i<unique.length(); i++){
            Character c = unique.charAt(i);
            int index = uniqueMap.get(c);
            while(cnt[c - 'a'] > 0){
                decrement(cnt, index);
                r.append(Integer.toString(index));
            }
        }
    }  
    
    public String originalDigits(String s) {
        StringBuilder r = new StringBuilder();
        if(s.length() == 0)
            return r.toString();
            
        int[] cnt = new int[26];
        for(int i=0; i<s.length(); i++){
            cnt[s.charAt(i) - 'a']++;
        }
        
        uniqueMap = new HashMap<>();
        uniqueMap.put('z', 0);
        uniqueMap.put('w', 2);
        uniqueMap.put('u', 4);
        uniqueMap.put('x', 6);
        uniqueMap.put('g', 8);
        // 2 Length unique
        uniqueMap.put('o', 1);
        uniqueMap.put('h', 3);
        uniqueMap.put('f', 5);
        uniqueMap.put('s', 7);

        String unique = "zwuxg";
        update(unique, cnt, r);

        String unique2 = "ohfs";
        update(unique2, cnt, r);
        
        // Add nine if necessary
        int cntNine = 0;
        for(int i=0; i<26; i++){
            cntNine += cnt[i];
        }
        
        if(cntNine > 0){
            char[] repeat = new char[cntNine/4];
            Arrays.fill(repeat, '9');
            r.append(new String(repeat));
        }
        
        char[] x = r.toString().toCharArray();
        Arrays.sort(x);
        return new String(x);
    }
}
