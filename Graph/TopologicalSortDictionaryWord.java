/*
Given a sorted dictionary of an alien language, find order of characters
Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.

Examples:

Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
Output: Order of characters is 'b', 'd', 'a', 'c'
Note that words are sorted and in the given language "baa" 
comes before "abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.

Input:  words[] = {"caa", "aaa", "aab"}
Output: Order of characters is 'c', 'a', 'b'
http://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
*/

import java.util.*;

class TopologicalSortDictionaryWord{
  public enum COL {
      WHITE, GRAY, BLACK
  }
  List<Character> order;

  public List<Character> findOrder(String[] words){
    // Construct the graph
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> color = new HashMap();
    char startNode = '0';
    // Add all nodes to the graph
    for(String word : words){
      for(int i=0; i<word.length(); i++){
        char c = word.charAt(i);
        if(!graph.containsKey(c)){
          if(startNode == '0'){
            startNode = c;
          }
          graph.put(c, new ArrayList<>());
          color.put(c, 0);
        }
      }
    }
    // number of nodes
    int n = graph.size();

    // Check all adjacent pairs of string
    int i = 0;
    while(i < words.length-1){
        String x = words[i];
        String y = words[i+1];
        int p = 0, q = 0;
        // Find the first mismatched character
        while(p < x.length() && q < y.length()){
            if(x.charAt(p) != y.charAt(q)){
              // Add an edge from pth character to qth character
              List<Character> adj = graph.get(x.charAt(p));
              adj.add(y.charAt(q));
              graph.put(x.charAt(p), adj);
              break;
            }
            p++;
            q++;
        }
        i++;
    }


    order = new ArrayList<>();
    // index of the starting node is 0
    topologicalSort(startNode, color, graph);
    
    // cycle exists
    if(order.size() < n){
      return Collections.EMPTY_LIST;
    } 

    List<Character> result = new ArrayList<>();
    // reverse order
    for(i=n-1; i>=0; i--){
      result.add(order.get(i));
    }
    return result;
  }

  // Topological sort
  // Return false if there is a cycle in the graph, in that case finding topological ordering is not possible
  public void topologicalSort(Character node, Map<Character, Integer> color, Map<Character, List<Character>> graph){
    color.put(node, 1);
    // Adjacency of node
    List<Character> adj = graph.get(node);
    for(int i=0; i<adj.size(); i++){
      char neighbor = adj.get(i);
      // cycle exists
      if(color.get(neighbor) == 1){
        return;
      }
      else if(color.get(neighbor) == 0){
        topologicalSort(neighbor, color, graph);
      }
    }
    order.add(node);
    color.put(node, 2);
  }
  public static void main(String[] args){
    TopologicalSortDictionaryWord s = new TopologicalSortDictionaryWord();
    //String[] words = {"caa", "aaa", "aab"};
    String[] words = {"baa", "abcd", "abca", "cab", "cad"};
    List<Character> order = s.findOrder(words);
    for(Character c : order){
      System.out.print(c + " ");
    }

  }
}