/*
Given a list of unique words. Find all pairs of distinct indices (i, j) in the 
given list, so that the concatenation of the two words, i.e. words[i] + words[j] 
is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
*/
// Time: O(m * n^2)
// m = number of words in the list
// n = length of the word
import java.util.*;

public class Solution {
    public boolean isPalindrome(String word){
        for(int i=0, j=word.length()-1; i<j; i++, j--)
            if(word.charAt(i) != word.charAt(j))
                return false;
        return true;
    }
    // Hash Table
    // Distinct indices
    public List<List<Integer>> palindromePairs(String[] words) {
        // Set to avoid duplicates
        Set<List<Integer>> resultSet = new HashSet<>();        
        // Put all words in the dictionary
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++)
            map.put(words[i], i);
            
        for(int i=0; i<words.length; i++){
            // Check if part of the word is a palindrome 
            // sssll
            // <= to handle empty input cases: example: ["t", ""];
            for(int j=0; j<=words[i].length(); j++){
                // Example: part 1 = ss
                // Part 2: sll
                String part1 = words[i].substring(0, j);
                String part2 = words[i].substring(j);
                System.out.println(part1 + " "  + part2);
                if(isPalindrome(part1)){
                    String revPart2 = new StringBuilder(part2).reverse().toString();
                    // lls
                    if(map.containsKey(revPart2) && map.get(revPart2) != i){
                        List<Integer> r = new ArrayList<>();
                        // As the result is lls + sssll
                        r.add(map.get(revPart2));
                        r.add(i);
                        System.out.println(revPart2 + words[i] + " Adding: " + r.get(0) + " " + r.get(1));
                        resultSet.add(r);
                    }
                }
                if(isPalindrome(part2)){
                    String revPart1 = new StringBuilder(part1).reverse().toString();
                    if(map.containsKey(revPart1) && map.get(revPart1) != i){
                        List<Integer> r = new ArrayList<>();
                        r.add(i);
                        r.add(map.get(revPart1));
                        System.out.println("#" + words[i] + revPart1 + " Adding: " + r.get(0) + " " + r.get(1));
                        resultSet.add(r);
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    public static void runTest(String[] words){
        Solution s = new Solution();
        List<List<Integer>> r = s.palindromePairs(words);
        for(List<Integer> list : r){
            System.out.print("[" + list.get(0) + " " + list.get(1) + "] ");
        }
        System.out.println();

    }
    public static void main(String[] args){
        String[] words = {"bat", "tab", "cat"};
        //runTest(words);
        String[] words2 = {"abcd", "dcba", "lls", "s", "sssll"};
        runTest(words2);
    }
}
