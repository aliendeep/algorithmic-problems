/*
Given a non-empty string str and an integer k, rearrange the string such that 
the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to 
rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

import java.util.*;
// O(nlogn) if all characters are different
// O(log(alphabet)) if not all characters are distinct
public class Solution {
    class Entry {
        int freq;
        char c;
        public Entry(int freq, char c) {
            this.freq = freq;
            this.c = c;
        }
    }
    
    public String rearrangeString(String str, int k) {
        int n = str.length();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i <n; ++i) {
            char c = str.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        TreeSet<Entry> active = new TreeSet<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry a, Entry b) {
                if(a.freq != b.freq)
                    return Integer.compare(b.freq, a.freq);
                return Integer.compare(a.c, b.c);
            }
        });
        
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            active.add(new Entry(entry.getValue(), entry.getKey()));
        }

        if(k == 0) 
            k = 1;
            
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i){
            if(i >= k){
                // re-activate k-old character.
                char reChar = sb.charAt(i-k);
                int freq = freqMap.get(reChar);
                if(freq > 0){
                    active.add(new Entry(freq, reChar));
                }
            }
            Entry e = active.pollFirst();
            if(e == null)   return "";
            sb.append(e.c);
            freqMap.put(e.c, e.freq - 1);
        }
        return sb.toString();
    }
}

// Greedy
// Try to place the  
// Keep count and next valid position
class Solution2 {
    public int getNextValidIndex(int index, int[] cnt, int[] validPosition){
        int maxCount = 0;
        int nextIndex = -1;
        for(int i=0; i<26; i++){
            // Index should be >= minimum viable valid index
            if(cnt[i] > 0 && validPosition[i] <= index && maxCount < cnt[i]){
                 maxCount = cnt[i];
                 nextIndex = i;
            }
        }
        return nextIndex;
    }
    
    // Greedy, Find the valid character with highest occurrence
    public String rearrangeString(String str, int k) {
        int n = str.length();
        if(n == 0)          return "";
        // All input strings are given in lowercase letters
        int[] cnt = new int[26];
        for(int i=0; i<n; ++i){
            cnt[str.charAt(i) - 'a']++;
        }
        
        // next valid position of char i + 'a'
        int[] validPosition = new int[26];
        StringBuilder result = new StringBuilder();
        for(int i=0; i<n; i++){
            int nextValidCharIndex = getNextValidIndex(i, cnt, validPosition);
            if(nextValidCharIndex == -1)     return "";
            // Decrease Count
            cnt[nextValidCharIndex]--;
            // next valid position
            // At least distance k from each other.
            validPosition[nextValidCharIndex] = i+k;
            result.append((char)(nextValidCharIndex + 'a'));
        }
        return result.toString();
    }
}

// Max Heap
// O(nlg(26))

public class Solution{
    class Pair{
        char c;
        int cnt;
        public Pair(char c1, int v){
            c = c1;
            cnt =  v;
        }
    }
    public String rearrangeString(String str, int k){
        int n = str.length();
        if(n == 0)          return "";
        // All input strings are given in lowercase letters
        int[] cnt = new int[26];
        for(int i=0; i<n; ++i){
            cnt[str.charAt(i) - 'a']++;
        }
        
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair> (new Comparator<Pair>(){
            @Override
            public int compare(Pair a, Pair b){
                return Integer.compare(b.cnt, a.cnt);
            }
        });

        for(int i=0; i<26; ++i){
            if(cnt[i] == 0)
                continue;
            maxHeap.add(new Pair((char)(i+'a'), cnt[i]));
        }        

        StringBuilder result = new StringBuilder();
        int remainingCharacter = n;
        while(!maxHeap.isEmpty()){
            int size = Math.min(k, remainingCharacter);
            // Add the elements of the set after k length
            Map<Character, Integer> wait = new HashMap<>();
            for(int i=0; i<size; ++i){
                if(maxHeap.isEmpty())
                    return "";
                Pair p = maxHeap.poll();
                result.append(p.c);
                int newCnt = p.cnt - 1;
                if(newCnt > 0)
                    wait.put(p.c, newCnt);
                remainingCharacter--;
            }
            // add all the elements of the wait queue in the maxHeap
            for(Map.Entry<Character, Integer> entry : wait.entrySet()){
                maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }
        return result.toString();
    }   
    public static void main(String[] args){
        Solution ob = new Solution();
        System.out.println(ob.rearrangeString("aaadbbcc", 2));
        System.out.println(ob.rearrangeString("aaabc", 3));
        System.out.println(ob.rearrangeString("aabbcc", 3));
    }    
}

public class Solution {
    class Entry {
        int freq;
        char c;
        public Entry(int freq, char c) {
            this.freq = freq;
            this.c = c;
        }
    }
    
    public String rearrangeString(String str, int k) {
        int n = str.length();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i <n; ++i) {
            char c = str.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        TreeSet<Entry> active = new TreeSet<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry a, Entry b) {
                if(a.freq != b.freq)
                    return Integer.compare(b.freq, a.freq);
                return Integer.compare(a.c, b.c);
            }
        });
        
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            active.add(new Entry(entry.getValue(), entry.getKey()));
        }

        if(k == 0) 
            k = 1;
            
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i){
            if(i >= k){
                // re-activate k-old character.
                char reChar = sb.charAt(i-k);
                int freq = freqMap.get(reChar);
                if(freq > 0){
                    active.add(new Entry(freq, reChar));
                }
            }
            Entry e = active.pollFirst();
            if(e == null)   return "";
            sb.append(e.c);
            freqMap.put(e.c, e.freq - 1);
        }
        return sb.toString();
    }
}
