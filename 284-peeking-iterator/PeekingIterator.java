import java.util.*;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    Integer nextElement = null;
    Iterator<Integer> iterator;        
    public PeekingIterator(Iterator<Integer> iter) {
      // initialize any member here.
      this.iterator = iter;
      nextElement = this.iterator.hasNext() ? this.iterator.next() : null;
    }
    
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;    
    }
    
    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
      Integer r = nextElement;
      if(nextElement == null)
        throw new NoSuchElementException();

      nextElement = this.iterator.hasNext() ? this.iterator.next() : null;    
      return r;
    }
    
    @Override
    public boolean hasNext() {
        return nextElement != null;     
    }

    public static void main(String args[]){
      ArrayList<Integer> a = new ArrayList<>();
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(4);

      Iterator it = a.iterator();
      PeekingIterator peekIt = new PeekingIterator(it);
      System.out.println(peekIt.hasNext());
      System.out.println(peekIt.peek());
      System.out.println(peekIt.peek());
      System.out.println(peekIt.next());
      System.out.println(peekIt.next());
      System.out.println(peekIt.peek());
      System.out.println(peekIt.peek());
      System.out.println(peekIt.next());
      System.out.println(peekIt.hasNext());
      System.out.println(peekIt.peek());
      System.out.println(peekIt.hasNext());
      System.out.println(peekIt.next());
      System.out.println(peekIt.hasNext());
    }
}