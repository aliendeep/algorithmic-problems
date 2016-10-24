import java.util.*;

public class UniqueCharacterStream{
  Set<Character> uniqueSet;
  Set<Character> duplicateSet;
  LinkedList<Character> window;

  public UniqueCharacterStream(){
    uniqueSet = new HashSet<>();
    duplicateSet = new HashSet<>();
    window = new LinkedList<>();
  }

  // Add a new character 
  public void add(char c){    
    if(uniqueSet.contains(c)){
      // State change: Move the character from uniqueSet to duplicateSet
      uniqueSet.remove(c);
      duplicateSet.add(c);
    }
    // uniqueSet doesn't contain the character, so check whether duplicate set already has the character
    else if(!duplicateSet.contains(c)){
      // Add the character to the unique set
      uniqueSet.add(c);
      // Append the character at the end
      window.addLast(c);
    }

    // Adjust window
    // Amortized O(1)
    // Number of elements in the window = At most n elements = at most n pop and n calls to the getFirstUnique function
    // Therefore, Amortized O(1)
    while(duplicateSet.contains(window.peekFirst())){
      window.removeFirst();  
    }
  }

  // Return the first unique character seen so far
  public Character getFirstUnique(){    
    if(window.isEmpty())
        return null;
    return window.peekFirst();
  }

  public static void test(char[] a){
    UniqueCharacterStream ob = new UniqueCharacterStream();
    for(int i=0; i<a.length; i++){
      ob.add(a[i]);
      System.out.print(ob.getFirstUnique() + " ");
    }
    System.out.println();
  }
  public static void main(String[] args){
    char[] a = {'a', 'b', 'b', 'c', 'a'};
    test(a);
    char[] b = {'d', 'b', 'd', 'a', 'b', 'c'};
    test(b);
  }
}
