/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

You must change exactly one character in every transformation
Each intermediate word must exist in the dictionary
Example :

Given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note that we account for the length of the transformation path instead of the number of transformation itself.

 Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class WordLadderLength{
    // BFS
  public int ladderLength(String start, String end, ArrayList<String> dictV) {
      if(start.equals(end))
          return 1;

        Set<String> dict = new HashSet<>(dictV);
      
        Queue<String> Q = new LinkedList<>();
        Q.add(start);
        
        int levCnt = Q.size();
        int shortestLen = 1;
        while(!Q.isEmpty()){
            String front = Q.remove();
            levCnt--;
            for(int pos=0; pos<front.length(); pos++){
                for(int i=0; i<26; i++){
                    // same character
                    if(front.charAt(pos) == (char)(i + 'a'))
                        continue;
                        
                    // Change one character
                    StringBuilder str = new StringBuilder(front);
                    str.setCharAt(pos, (char)(i + 'a'));
                    String newWord = str.toString();
                    // Reached the end word (+1 end word)
                    if(newWord.compareTo(end) == 0)
                        return shortestLen + 1;
                        
                    if(dict.contains(newWord)){
                        Q.add(newWord);
                        // remove it from the dictionary
                        // No need to use separate visited list
                        dict.remove(newWord);
                    }
                }                
            }
            
            // traversed a whole level
            if(levCnt == 0){
                levCnt = Q.size();
                shortestLen++;
            }
        }
        return 0;
  }
}
