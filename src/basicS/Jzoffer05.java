package basicS;

import java.util.LinkedList;
import java.util.Stack;

public class Jzoffer05 {

	/**
	 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
	 */
	public static void main(String[] args) {

	}

	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    
    
    //16 ms	9496K	
    //每次入队列或出队列时，都把已有栈的元素倒出到另一个栈中
    //入队列时倒出完成后再加入元素
    //出队列时倒出完成后再弹出元素
    public void push(int node) {
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }
    
    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
    
    //16 ms	9392K
    //每次入队列时将元素压入stack1中
    //每次出队列时检查stack2是否为空，如果不为空则将1中的元素倒入2中
    //最后将2中的元素弹出即可
    public void push_a(int node) {
        stack1.push(node);
    }
    
    public int pop_a() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
