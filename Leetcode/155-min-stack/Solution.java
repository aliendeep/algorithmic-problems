/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

public class MinStack {
    Deque<Integer> s1, s2;
    /** initialize your data structure here. */
    public MinStack() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }
    
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty() || s2.peekFirst() >= x)
            s2.push(x);
    }
    
    public void pop() {
        int x = s1.removeFirst();
        if(s2.peekFirst() == x)
            s2.removeFirst();
    }
    
    public int top() {
        return s1.peekFirst();
    }
    
    public int getMin() {
        return s2.peekFirst();
    }
}

public class MinStack {
    class Info{
        int x;
        int min;
        public Info(int a, int m){
            x = a;
            min = m;
        }
    }
    Deque<Info> stk;
    
    /** initialize your data structure here. */
    public MinStack() {
        stk = new LinkedList<>();
    }
        
    public void push(int x) {
        int min = stk.isEmpty() ? x : Math.min(stk.peekFirst().min, x);
        stk.push(new Info(x, min));
    }
    
    public void pop() {
        stk.pop();
    }
    
    public int top() {
        return stk.peek().x;
    }
    
    public int getMin() {
        return stk.peek().min;
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
