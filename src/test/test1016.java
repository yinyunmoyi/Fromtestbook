package test;

import java.util.Stack;

public class test1016 {

	//定义栈的数据结构，请在该类型中实现一个能够得到栈中
	//所含最小元素的min函数（时间复杂度应为O（1））
	
	//(11min)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myStack st = new myStack();
		st.push(4);
		st.push(2);
		st.push(3);
		st.push(6);
		System.out.println(st.getMin());
	}

}

class myStack{
	Stack<Integer> stack;
	Stack<Integer> minStack;
	myStack(){
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	public void push(int num){
		stack.push(num);
		if((!minStack.isEmpty() && minStack.peek() > num) || minStack.isEmpty()) {
			minStack.push(num);
		}
	}
	
	public int pop() {
		if(stack.isEmpty()) {
			throw new RuntimeException();
		}
		int pop = stack.pop();
		if(pop == minStack.peek()) {
			minStack.pop();
		}
		return pop;
	}
	
	public int getMin() {
		if(stack.isEmpty()) {
			throw new RuntimeException();
		}
		return minStack.peek();
	}
}
