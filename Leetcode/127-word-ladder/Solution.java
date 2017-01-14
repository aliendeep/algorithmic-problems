/*
Given two words (beginWord and endWord), and a dictionary's word list, find the 
length of shortest transformation sequence from beginWord to endWord, 
such that:

1. Only one letter can be changed at a time
2. Each intermediate word must exist in the word list

For example,
Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

import java.util.*;

public class Solution {
    // BFS
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        
        int resultCnt = 0;
        int levCnt = 1;
        while(!q.isEmpty()){
            String t = q.remove();
            levCnt--;
            for(int pos=0; pos<t.length(); pos++){
                for(int i=0; i<26; i++){
                    // same character
                    if(t.charAt(pos) == i + 'a')
                        continue;
                        
                    // Change one character
                    StringBuilder str = new StringBuilder(t);
                    str.setCharAt(pos, (char)(i + 'a'));
                    String newWord = str.toString();
                    // Reached the end word (+2 for start and end word)
                    if(newWord.compareTo(endWord) == 0)
                        return resultCnt + 2;
                        
                    if(wordList.contains(newWord)){
                        q.add(newWord);
                        // remove it from the dictionary
                        wordList.remove(newWord);
                    }
                }                
            }
            
            if(levCnt == 0){
                levCnt = q.size();
                resultCnt++;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
      Solution s = new Solution();
      Set<String> wordList = new HashSet<>();
      wordList.add("hot");
      wordList.add("dot");
      wordList.add("dog");
      wordList.add("lot");
      wordList.add("log");
      System.out.println(s.ladderLength("hit", "cog", wordList));
    } 
}

//Adding memory
class Solution3{
    // BFS + DFS
    // BFS to find the shortest distance to all nodes 
    // DFS to print the path
    Map<String, Set<String>> neighbors;
    // distance from begin word
    Map<String, Integer> distance;
    String endWord;
    // dictionary
    Set<String> dict;
    int srcToDest;
    // Start to word -> all possible paths mapping
    Map<String, List<List<String>>> paths;

    public List<List<String>> findLadders(String start, String end, Set<String> d) {
        this.dict = d;
        dict.add(end);
        this.endWord = end;
        // <node, neighbors> mapping
        neighbors = new HashMap<>();
        distance = new HashMap<>();
        bfs(start);
        
        // no path
        if(!distance.containsKey(end))
            return new ArrayList<>();
            
        // Print all the paths
        paths = new HashMap<>();
        return findPaths(start);
    }
    
    public List<List<String>> findPaths(String word){
        List<List<String>> r = new ArrayList<>();
        // reached the end word
        if(word.equals(endWord)){
            List<String> x = new ArrayList<>();
            x.add(word);
            r.add(x);
            return r;
        }

        if(paths.containsKey(word))
            return paths.get(word);
        
        int dist = distance.get(word);
        
        Set<String> adj = neighbors.get(word);
        if(adj != null){
            for(String neighbor : adj){
                if(distance.get(neighbor) == dist + 1){
                    List<List<String>> rest = findPaths(neighbor);
                    for(List<String> t : rest){
                        List<String> x = new ArrayList<>();
                        x.add(word);
                        x.addAll(t);
                        r.add(x);
                    }
                }
            }
        }
        paths.put(word, r);
        return r;
    }

    public void bfs(String start){
        Queue<String> Q = new LinkedList<>();
        Q.add(start);
        distance.put(start, 0);
        
        while(!Q.isEmpty()){
            String front = Q.remove();
            if(front.equals(endWord)){
                break;    
            }
            StringBuilder node = new StringBuilder(front);
            int dist = distance.get(front);
            // Compute all adjacent nodes
            Set<String> adjacent = new HashSet<>();
            for(int position=0; position<node.length(); ++position){
                // change at most one character
                for(int i=0; i<26; ++i){
                    char c = (char)(i + 'a');
                    // same character
                    if(front.charAt(position) == c)
                        continue;
                    
                    node.setCharAt(position, c);

                    String t = node.toString();
                    if(dict.contains(t)){              
                        adjacent.add(t);
                        //  valid word and not visited yet 
                        if(!distance.containsKey(t)){
                            Q.add(t);
                            distance.put(t, dist + 1);
                        }
                    }
                    // revert
                    node.setCharAt(position, front.charAt(position));
                }
            }
            // directed edge
            neighbors.put(front, adjacent);
        }       
    }  
}
