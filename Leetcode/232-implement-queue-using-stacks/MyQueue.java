/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), 
as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

import java.util.*;

class MyQueue {
    Deque<Integer> s1, s2;
    
    public MyQueue(){
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }    
    // Push element x to the back of queue.
    public void push(int x) {
        s1.addFirst(x);
    }
    
    public void transfer(){
        while(!s1.isEmpty()){
            s2.addFirst(s1.removeFirst());
        }
    }
    // Removes the element from queue
    public void pop() {
        if(s2.isEmpty())
            transfer();
        s2.removeFirst();
    }

    // Get the front element.
    public int peek() {
        if(s2.isEmpty())
            transfer();
        int front = s2.peekFirst();
        return front;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s2.isEmpty() && s1.isEmpty();
    }

    public static void main(String[] args){
      MyQueue s = new MyQueue();
      System.out.println(s.empty());      
      s.push(1);
      s.push(2);
      System.out.println(s.peek());
      s.pop();
      System.out.println(s.peek());
      s.pop();
      System.out.println(s.empty());
    }
}
