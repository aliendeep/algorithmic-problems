import java.util.*;

class Main{
  public static String convert(String x) {
    StringBuilder r = new StringBuilder();
    for(char c : x.toCharArray()) {
      if(Character.isLetter(c)) {
        r.append(Character.toLowerCase(c));
      }      
      else if(c == '-') {
        r.append(c);
      }      
    }
    return r.toString();
  }

  public static String convertLine(String x) {
    StringBuilder r = new StringBuilder();
    for(char c : x.toCharArray()) {
      if(Character.isLetter(c)) {
        r.append(Character.toLowerCase(c));
      }      
      else if(c == '-') {
        r.append(c);
      }      
      else
        r.append(" ");
    }
    return r.toString();
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    TreeSet<String> set = new TreeSet<>();

    StringBuilder run = new StringBuilder();
    while(in.hasNext()) {
      String line = in.nextLine();
      if(line.length() == 0) {
        if(run.length() > 0) {
          set.add(convert(run.toString()));
          run.setLength(0);
        }
        continue;
      }

      line = convertLine(line);
      String[] words = line.split(" ");
      int n = words.length;
      if(n == 0)  continue;
      // first word
      // single word
      if(n == 1) {
        // last char is hyphen
        if(words[0].length() > 1 && words[0].charAt(words[0].length()-1) == '-')
          run.append(words[0].substring(0, words[n-1].length()-1));
        else {
          run.append(words[0]);
          set.add(convert(run.toString()));
          // reset run
          run.setLength(0);
        }
      } 
      // number of words > 1
      else {
          run.append(words[0]);
          set.add(convert(run.toString()));
          run.setLength(0);
      }       

      for(int i=1; i<n-1; ++i) {
        set.add(convert(words[i]));
      }

      // last word
      if(n > 1) {
        // new word
        if(words[n-1].charAt(words[n-1].length()-1) == '-') {
          run.setLength(0);
          // remove the hyphen
          run.append(words[n-1].substring(0, words[n-1].length()-1));          
        }
        else {
          set.add(convert(words[n-1]));
          run.setLength(0);
        }
      }
    } 
    // last word
    if(run.length() > 0) {
      String w = run.toString();
      set.add(convert(w));
    }

    for(String sorted : set) {
      if(sorted.length() == 0)  continue;
      System.out.println(sorted);
    }   
  }  
}
