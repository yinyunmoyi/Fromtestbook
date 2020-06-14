import java.util.Stack;
class TEST {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    /** initialize your data structure here. */
    public TEST() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int x) {
        if(stack1.isEmpty() || stack1.peek() >= x) {
        	stack1.push(x);
        }
        stack2.push(x);
    }
    
    public void pop() {
        int x = stack2.pop();
        if(!stack1.isEmpty() && x == stack1.peek()){
            stack1.pop();
        }
    }
    
    public int top() {
        return stack2.peek();
    }
    
    public int getMin() {
        return stack1.peek();
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