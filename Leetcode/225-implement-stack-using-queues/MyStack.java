/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
*/


/*
Algorithm
  1) One by one dequeue everything except the last element from q1 and enqueue to q2.
  2) Dequeue the last item of q1, the dequeued item is result, store it.
  3) Swap the names of q1 and q2
  4) Return the item stored in step 2.
  Swapping of names is done to avoid one more movement of all elements from q2 to q1.
*/

import java.util.*;

public class MyStack {
    Deque<Integer> Q1, Q2;
    public MyStack(){
        Q1 = new LinkedList<>();
        Q2 = new LinkedList<>();
    }
    // Push element x onto stack.
    public void push(int x) {
        Q1.add(x);
    }
    
    public void swapQueues(){
        Deque<Integer> t = Q1;
        Q1 = Q2;
        Q2 = t;
    }
    // Removes the element on top of the stack.
    public void pop() {
      // transfer everything from q1 to q2 and return the last element
        while(Q1.size() > 1){
          Q2.add(Q1.remove());
        }
        Q1.remove();
        swapQueues();
    }

    // Get the top element.
    public int top() {
        int top = -1;
        while(!Q1.isEmpty()){
          top = Q1.remove();
          Q2.add(top);
        }
        swapQueues();
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return Q1.isEmpty() && Q2.isEmpty();
    }
    public static void main(String[] args){
      MyStack s = new MyStack();
      System.out.println(s.empty());      
      s.push(1);
      s.push(2);
      System.out.println(s.top());
      s.pop();
      System.out.println(s.top());
      s.pop();
      System.out.println(s.empty());
    }
}