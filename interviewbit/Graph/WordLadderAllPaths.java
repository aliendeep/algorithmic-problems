/*
Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
If there are multiple such sequence of shortest length, return all of them. Refer to the example for more details.

Example :

Given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
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

public class WordLadderAllPaths{
    // BFS + DFS
    // BFS to find the shortest distance to all nodes 
    // DFS to print the path
    Map<String, List<String>> neighbors;
    // distance from begin word
    Map<String, Integer> distance;
    String endWord;
    // dictionary
    Set<String> wordList;
    int srcToDest;
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dictList) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        wordList = new HashSet<>(dictList);
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
        ArrayList<String> cur = new ArrayList<>();
        cur.add(start);
        
        findPaths(start, cur, result);
        return result;
    }

    public void findPaths(String word, ArrayList<String> cur, ArrayList<ArrayList<String>> result){
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
    public static void print(ArrayList<String> t){
        for(String n : t){
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void test(String[] d, String start, String end){
        ArrayList<String> dict = new ArrayList<>();
        for(String t : d)
            dict.add(t);

        WordLadderAllPaths s = new WordLadderAllPaths();
        ArrayList<ArrayList<String>> r = s.findLadders(start, end, dict);

        for(ArrayList<String> t : r)
            print(t);

    }
    public static void main(String[] args){
        String[] d1 = {"hot","dot","dog","lot","log"};
        //test(d1, "hit", "cog");
        String[] d2 = {"hot","cog","dog","tot","hog","hop","pot","dot"};
        //test(d2, "hot", "dog");
        String[] d3 = {"a","b","c"};
        //test(d3, "a", "c");
        String[] f = {"baba", "abba", "aaba", "bbbb", "abaa", "abab", "aaab", "abba", "abba", "abba", "bbba", "aaab", "abaa", "baba","baaa"};
        // Result: [bbaa baaa baba babb ] [bbaa bbba baba babb ] [bbaa bbba bbbb babb ] 
        test(f, "bbaa", "babb");
    }   
}