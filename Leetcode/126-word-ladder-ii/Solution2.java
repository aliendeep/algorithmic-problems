/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

import java.util.*;

public class Solution2 {
    // BFS + DFS
    // BFS to find the shortest distance to all nodes (distnace is not needed actually, only parent is needed)
    // DFS to print the path
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);
        // <node, neighbors> mapping
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        bfs(beginWord, endWord, wordList, neighbors, distance);
        // DFS to print all the paths
        return findPaths(beginWord, endWord, neighbors, distance);
    }

    // With Memoization    
    public List<List<String>> findPaths(String word, String endWord, Map<String, List<String>> neighbors, 
                                        Map<String, Integer> distance){
        List<List<String>> result = new ArrayList();

        if(word.equals(endWord)){
            List<String> p = new ArrayList<>();
            p.add(word);
            result.add(p);
            return result;
        }

        List<String> adjacentNodes = neighbors.get(word);
        if(adjacentNodes != null){
            for(String adjacent : adjacentNodes){
                if(distance.get(adjacent) == distance.get(word) + 1){
                    List<List<String>> next = findPaths(adjacent, endWord, neighbors, distance);
                    for(List<String> t : next){
                        List<String> path = new ArrayList<>();
                        path.add(word);
                        path.addAll(t);
                        result.add(path);
                    }
                }
            }
        }
        return result;
    }
    

    public void printMap(Map<String, List<String>> neighbors){
        for(Map.Entry<String, List<String>> entry : neighbors.entrySet()){
            List<String> n = entry.getValue();
            System.out.print(entry.getKey() + " List: "); 
            for(String t : n){
                System.out.print(t + " ");
            }          
            System.out.println();
        }
    }

    public static void printDist(Map<String, Integer> distance ){
        for(Map.Entry<String, Integer> entry : distance.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void bfs(String beginWord, String endWord, Set<String> wordList, Map<String, List<String>> neighbors,  Map<String, Integer> distance){
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        distance.put(beginWord, 0);
        
        while(!Q.isEmpty()){
            String front = Q.remove();
            if(front.equals(endWord))
                break;    
            // Find all adjacent nodes
            StringBuilder node = new StringBuilder(front);
            int dist = distance.get(front);
            List<String> adjacent =  new ArrayList<>();

            for(int l=0; l<node.length(); ++l){
                // change at most one character
                for(int i=0; i<26; ++i){
                    char c = (char)(i + 'a');
                    // same character
                    if(front.charAt(l) == c)
                        continue;
                    
                    node.setCharAt(l, c);

                    String t = node.toString();

                    //  valid word and not visited yet or end word (doesn't exist in the dictionary)
                    if(wordList.contains(t) && !distance.containsKey(t)){
                        Q.add(t);
                        adjacent.add(t);
                        distance.put(t, dist + 1);
                    }
                    else if(distance.containsKey(t) && distance.get(t) == dist + 1){
                        adjacent.add(t);
                    }
                    // revert
                    node.setCharAt(l, front.charAt(l));
                }
            }
            // directed edge
            neighbors.put(front, adjacent);
        }        
    }

    public static void print(List<String> t){
        for(String n : t){
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void test(String[] d, String start, String end){
        Solution2 s = new Solution2();
        Set<String> dict = new HashSet<>(Arrays.asList(d));
        List<List<String>> r = s.findLadders(start, end, dict);

        for(List<String> t : r)
            print(t);

    }
    public static void main(String[] args){
        String[] d1 = {"hot","dot","dog","lot","log"};
        test(d1, "hit", "cog");
        String[] d2 = {"hot","cog","dog","tot","hog","hop","pot","dot"};
        test(d2, "hot", "dog");
        String[] d3 = {"a","b","c"};
        test(d3, "a", "c");
    }
}