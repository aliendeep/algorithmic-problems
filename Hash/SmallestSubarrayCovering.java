import java.util.*;

# TODO
class Subarray {
  public Integer start;
  public Integer end;

  public Subarray(Integer start, Integer end) {
    this.start = start;
    this.end = end;
  }
}

public class SmallestSubarrayCovering{
  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords){
    int start = -1;
    Set<String> active = new HashSet<String>(keywords);
    // contains the index of 
    List<Integer> current = new ArrayList<>();
    int i = 0;
    // Compute the first set
    for(i=0; i<paragraph.size(); ++i){
      String word = paragraph.get(i);
      if(active.contains(word)){
        if(start == -1){
          start = i;
        }
        current.add(i);        
        active.remove(word);
        if(active.size() == 0)
          break;
      }
    }
    Subarray result = new Subarray(start, current.get(current.size()-1));
    // Find the first covering set
    int minLength = i - start + 1;    
    int listIndex = 0;
    while(i < paragraph.size()){
      String word = paragraph.get(i);
      if(word.equals(current.get(listIndex))){
          // advance the start
          listIndex++;
          start = current.get(listIndex); 
          if(i - start + 1 < minLength){
            minLength = Math.min(minLength, i - start + 1);
            result = new Subarray(start, current.get(current.size()-1));    
          }
          current.add(i);        
      }
      ++i;
    }
    return result;
  }
}