package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class test0009 {

	/**
	 * 用两个队列实现一个栈(10min)
	 * 
	 * 用两个栈实现一个队列(11min)
	 */
	public static void main(String[] args) {
		/*TwoQueueStack stack = new TwoQueueStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());*/
		
		TwoStackQueue queue = new TwoStackQueue();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		queue.offer(6);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}

}

class TwoQueueStack{
	Queue<Integer> queue1;
	Queue<Integer> queue2;
	
	TwoQueueStack(){
		queue1 = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
	}
	
	public void push(int num){
		queue1.offer(num);
	}
	
	public int pop(){
		while(queue1.size() > 1){
			queue2.offer(queue1.poll());
		}
		Queue<Integer> queue = queue1;
		queue1 = queue2;
		queue2 = queue;
		return queue2.poll();
	}
	
	public int peek(){
		while(queue1.size() > 1){
			queue2.offer(queue1.poll());
		}
		Queue<Integer> queue = queue1;
		queue1 = queue2;
		queue2 = queue;
		int num = queue2.peek();
		queue1.offer(queue2.poll());
		return num;
	}
}

class TwoStackQueue{
	
	Stack<Integer> stack1;
	Stack<Integer> stack2;
	
	TwoStackQueue(){
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
	}
	
	public void offer(int num){
		stack1.push(num);
	}
	
	public int poll(){
		if(stack2.isEmpty()){
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
	public int peek(){
		if(stack2.isEmpty()){
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
		}
		return stack2.peek();
	}
}