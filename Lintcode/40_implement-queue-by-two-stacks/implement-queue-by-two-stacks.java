/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/implement-queue-by-two-stacks
@Language: Java
@Datetime: 16-11-26 01:38
*/

class Queue {
    Deque<Integer> s1, s2;
    
    public Queue(){
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }    
    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }
    
    public void transfer(){
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
    }
    // Removes the element from queue front.
    public int pop() {
        if(s2.isEmpty())
            transfer();
        return s2.pop();
    }

    // Get the front element.
    public int top() {
        if(s2.isEmpty())
            transfer();
        int front = s2.peekFirst();
        return front;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s2.isEmpty() && s1.isEmpty();
    }
}