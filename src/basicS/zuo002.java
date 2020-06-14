package basicS;

import java.util.Stack;

public class zuo002 {

	//给一个整形的栈，现在要把栈中数的顺序调整成从上到下是从大到小的
	//只允许使用栈这种数据结构
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.push(5);
		stack.push(4);
		stack.push(1);
		stack.push(3);
		System.out.println(stack);
		stack = sortStack(stack);
		System.out.println(stack);
	}
	
	//按照另一个辅助栈栈顶和弹出元素之间的关系选择压入或者弹出
	//如果大小顺序不对就将辅助栈弹出至对了为止
	//不能直接将stack的索引指向辅助栈，因为辅助栈一旦回收这个指针就指向空了
	//要直接操作stack才行，指向克隆对象也不行
	public static Stack<Integer> sortStack(Stack<Integer> stack) {
		Stack<Integer> newStack = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int val = stack.pop();
			while(!newStack.isEmpty() && newStack.peek() > val) {
				stack.push(newStack.pop());
			}
			newStack.push(val);
		}
		return newStack;
	}

}
