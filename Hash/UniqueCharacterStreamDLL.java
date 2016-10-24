import java.util.*;

class DoublyLinkedList{
    public DoublyLinkedList prev, next;
    char ch;

    public DoublyLinkedList(char ch){
        this.ch = ch;
        this.prev = null;
        this.next = null;
    }
}   

class DLL{
    DoublyLinkedList dummyHead, dummyTail;        
    public DLL(){
        // dummy dummyHead and dummyTail
        dummyHead = new DoublyLinkedList('0');
        dummyTail = new DoublyLinkedList('0');
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    // add at the end
    public void addLast(DoublyLinkedList node){
        DoublyLinkedList prev = dummyTail.prev;
        node.prev = prev;
        prev.next = node;
        node.next = dummyTail;            
        dummyTail.prev = node;
    }

    public DoublyLinkedList peekFirst(){
        if(dummyHead.next == dummyTail)
            return null;
        return dummyHead.next;
    }

    // Remove this particular node
    public void remove(DoublyLinkedList node){
        DoublyLinkedList prev = node.prev;
        DoublyLinkedList next = node.next;
        prev.next = next;
        next.prev = prev;
    }
}

public class UniqueCharacterStreamDLL{
    // Unique Character, DLL
    Map<Character, DoublyLinkedList> uniqueMap;
    Set<Character> duplicateSet;
    DLL list;
    
    public UniqueCharacterStreamDLL(){
        uniqueMap = new HashMap<>();
        duplicateSet = new HashSet<>();
        list = new DLL();
    }

  // Add a new character 
  public void add(char c){    
    if(uniqueMap.containsKey(c)){
        // State change: Move the character from uniqueMap to duplicateSet
        // remove this char from DLL
        DoublyLinkedList node = uniqueMap.get(c);
        list.remove(node);
        uniqueMap.remove(c);
        duplicateSet.add(c);
    }
    // uniqueSet doesn't contain the character, so check whether duplicate set already has the character
    else if(!duplicateSet.contains(c)){
        // Create new Node
        DoublyLinkedList node = new DoublyLinkedList(c);
        // Add it to the list 
        list.addLast(node);
        // Add the character to the unique set
        uniqueMap.put(c, node);
    }
  }

  // Return the first unique character seen so far
  public Character getFirstUnique(){    
    if(uniqueMap.size() == 0)       return null;
    return list.peekFirst().ch;
  }

  public static void test(char[] a){
    UniqueCharacterStreamDLL ob = new UniqueCharacterStreamDLL();
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
