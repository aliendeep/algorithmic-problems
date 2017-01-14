/*
Given two words (beginWord and endWord), and a dictionary's word list, find all 
shortest transformation sequence(s) from beginWord to endWord, such that:

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

// Cleaner Solution
public class Solution{
    // BFS + DFS
    // BFS to find the shortest distance to all nodes and construct the adjacency list
    // DFS to print the path using the adjacency list
    Map<String, List<String>> neighbors;
    // distance from begin word
    Map<String, Integer> distance;
    String endWord;
    // dictionary
    Set<String> wordList;
    int srcToDest;
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        this.wordList = dict;
        wordList.add(end);
        this.endWord = end;
        // <node, neighbors> mapping
        neighbors = new HashMap<>();
        distance = new HashMap<>();
        srcToDest = 0;
        bfs(start);
        
        // no path
        if(!distance.containsKey(end))
            return result;
            
        srcToDest = distance.get(end);
        // DFS to print all the paths
        List<String> cur = new ArrayList<>();
        cur.add(start);
        
        findPaths(start, cur, result);
        return result;
    }

    public void findPaths(String word, List<String> cur, List<List<String>> result){
        if(cur.size() > srcToDest + 1)
            return;
        if(word.equals(endWord)){
            result.add(new ArrayList<>(cur));
            return;
        }

        int dist = distance.get(word);
        List<String> adj =  neighbors.get(word);
        if(adj != null){
            for(String n : adj){
                if(distance.get(n) == dist + 1){
                    cur.add(n);
                    findPaths(n, cur, result);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

    public void bfs(String beginWord){
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        distance.put(beginWord, 0);
        
        while(!Q.isEmpty()){
            String front = Q.remove();
            if(front.equals(endWord)){
                break;    
            }
            StringBuilder node = new StringBuilder(front);
            int dist = distance.get(front);
            // Find all adjacent nodes
            List<String> adjacent =  new ArrayList<>();
            for(int position=0; position<node.length(); ++position){
                // change at most one character
                for(int i=0; i<26; ++i){
                    char c = (char)(i + 'a');
                    // same character
                    if(front.charAt(position) == c)
                        continue;
                    
                    node.setCharAt(position, c);

                    String t = node.toString();

                    if(wordList.contains(t)){              
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

// Old Solution
class Solution2 {
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
        Map<String, List<List<String>>> paths = new HashMap<>();
        return findPaths(beginWord, endWord, neighbors, paths, distance);
    }

    // With Memoization    
    public List<List<String>> findPaths(String word, String endWord, Map<String, List<String>> neighbors, 
                                        Map<String, List<List<String>>> paths, Map<String, Integer> distance){
        List<List<String>> result = new ArrayList();

        if(word.equals(endWord)){
            List<String> p = new ArrayList<>();
            p.add(word);
            result.add(p);
            return result;
        }

        if(paths.containsKey(word)){
            return paths.get(word);
        }

        List<String> adjacentNodes = neighbors.get(word);
        if(adjacentNodes != null){
            for(String adjacent : adjacentNodes){
                if(distance.get(adjacent) == distance.get(word) + 1){
                    //System.out.println(word + " " + adjacent);
                    List<List<String>> next = findPaths(adjacent, endWord, neighbors, paths, distance);
                    for(List<String> t : next){
                        List<String> path = new ArrayList<>();
                        path.add(word);
                        path.addAll(t);
                        result.add(path);
                    }
                }
            }
        }
        paths.put(word, result);
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
        Solution s = new Solution();
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
