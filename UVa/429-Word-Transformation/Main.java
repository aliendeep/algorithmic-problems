import java.util.*;

class Main{
  static int minimumTransformation(String start, String end, Set<String> dict) {
    Queue<String> Q = new LinkedList<>();
    Q.add(start);
    
    int minTransformation = 0;
    Set<String> visited = new HashSet<>();

    while(!Q.isEmpty()){
        int size = Q.size();
        for(int f=0; f<size; ++f){
            String cur = Q.remove();

            if(cur.equals(end))
                return minTransformation;

            for(int pos=0; pos<cur.length(); pos++){
                for(int i=0; i<26; i++){

                    // same character
                    if(cur.charAt(pos) == i + 'a')
                        continue;
                    
                    // Change one character
                    StringBuilder str = new StringBuilder(cur);
                    str.setCharAt(pos, (char)(i + 'a'));
                    String newWord = str.toString();

                    if(dict.contains(newWord) && !visited.contains(newWord)){
                        Q.add(newWord);
                        // remove it from the dictionary
                        visited.add(newWord);
                    }
                }                
            }
        }
      minTransformation++;
    }
    return minTransformation; 
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = Integer.parseInt(in.nextLine());
    while(t-- > 0) {
      String line;
      // dictionary
      Set<String> dict = new HashSet<>();
      while(in.hasNext()) {
        line = in.nextLine();
        if(line.equals("*"))
          break;
        dict.add(line);
      }

      String input;
      while(in.hasNext()) {
        line = in.nextLine();
        if(line.length() == 0)
          break;

        String[] words = line.split(" ");
        System.out.println(line + " " + minimumTransformation(words[0], words[1], dict));
      }
      if(t > 0)
        System.out.println();
    }
  }  
}
