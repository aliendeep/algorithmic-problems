/*
This is a follow up of Shortest Word Distance. The only difference is now you are 
given the list of words and your method will be called repeatedly many times with 
different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a 
method that takes two words word1 and word2 and return the shortest distance 
between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

import java.util.*;

public class WordDistance {
    // Key, Index where the key string appeared in the list
    Map<String, List<Integer>> map;
    
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for(int i=0; i<words.length; i++){
            String x = words[i];
            if(!map.containsKey(x)){
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(x, l);
            }
            else{
                map.get(x).add(i);
            }
        }
    }
    public int shortest(String word1, String word2) {
        List<Integer> first = map.get(word1);
        List<Integer> second = map.get(word2);
        return findMinDiffSortedArrays(first, second);   
    }
    
    //  Find the minimum difference between two sorted lists
    // O(m + n)
    public int findMinDiffSortedArrays(List<Integer> a, List<Integer> b){
        int i = 0;
        int j = 0;
        int minDiff = Integer.MAX_VALUE;
        while(i < a.size() && j < b.size()){
            int x = a.get(i);
            int y = b.get(j);
            minDiff = Math.min(minDiff, Math.abs(x - y));
            if(x < y)   i++;
            else        j++;
        }
        return minDiff;
    }
    public static void main(String[] args){
      String[] words = {"practice", "makes", "perfect", "coding", "makes"};
      WordDistance wordDistance = new WordDistance(words);
      System.out.println(wordDistance.shortest("coding", "practice"));
      System.out.println(wordDistance.shortest("makes", "coding"));      
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");