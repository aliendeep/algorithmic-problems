import java.util.*;

class Main{
  // Doubly linked list node
  static class Node{
    int val;
    public Node prev, next;

    public Node(int v){
        val = v;
        prev = null;
        next = null;
    }
  }

  static class DLL{
    Node dummyHead, dummyTail;
    public DLL(){
        dummyHead = new Node(-1);
        dummyTail = new Node(-1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public void addFirst(Node t) {
        Node next = dummyHead.next;
        dummyHead.next = t;
        t.prev = dummyHead;
        t.next = next;
        next.prev = t;
    }

    public void addLast(Node t) {
      Node prev = dummyTail.prev;
      prev.next = t;
      t.prev = prev;
      t.next = dummyTail;
      dummyTail.prev = t;
    }
 
    public Node removeFirst() {
      Node first = dummyHead.next;
      dummyHead.next = first.next;
      first.next.prev = dummyHead;
      return first;
    }
    
    public void remove(Node t){
        Node next = t.next;
        Node prev = t.prev;
        prev.next = next;
        next.prev = prev;
    }
    
    public void moveFirst(Node t){
        remove(t);
        addFirst(t);
    }
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    String line = in.nextLine();
    String[] w = line.split(" ");

    int p = Integer.parseInt(w[0]);
    int c = Integer.parseInt(w[1]);

    int ci = 1;
    while(p != 0 && c != 0) {
      // Value, DLL
      Map<Integer, Node> map = new HashMap<>();
      DLL list = new DLL();

      System.out.println("Case " + ci + ":");
      ci++;

      for(int x=1; x<=Math.min(c, p); ++x) {
        Node t = new Node(x);
        map.put(x, t);
        list.addLast(t);
      }

      for(int i=0; i<c; ++i) {
        line = in.nextLine();
        w = line.split(" ");

        if(w[0].equals("N")) {
          Node f = list.removeFirst();
          System.out.println(f.val);
          list.addLast(f);
        }
        else {
          int t = Integer.parseInt(w[1]);
          if(map.containsKey(t)) {
            Node n = map.get(t);
            list.moveFirst(n);
          }
          else {
            Node n = new Node(t);
            map.put(t, n);
            list.addFirst(n);
          }
        }
      }

      line = in.nextLine();
      w = line.split(" ");
      p = Integer.parseInt(w[0]);
      c = Integer.parseInt(w[1]);
    }
  }  
}
