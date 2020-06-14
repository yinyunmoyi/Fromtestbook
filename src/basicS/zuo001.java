package basicS;

import java.util.Stack;

public class zuo001 {

	//将一个栈逆序过来，要求用递归和操作栈的方法来实现
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);
		stack.add(5);
		System.out.println(stack);
		reverseStack(stack);
		System.out.println(stack);
	}

	//解题的关键在于利用栈的特性，先调用递归的方法后退出
	//如果直接将弹出的数字保存一下继续递归，结束之后再压回栈中
	//最后结果和初始栈的顺序一样
	//所以必须封装一个方法可以弹出栈底的元素并返回该元素
	public static void reverseStack(Stack<Integer> stack){
		if(stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastEle(stack);
		reverseStack(stack);
		stack.push(i);
	}
	
	//这个方法用于弹出栈底的元素并返回该元素
	//可以采用首先弹出元素，然后向下递归，最后将元素再装回栈中的顺序设计
	//这样压回的顺序还是原来的顺序
	//只是最后如果栈为空就直接返回弹出的元素，然后向上一直传递
	//由于最后是返回语句，所以要把元素在返回语句之前再装回去就可以了
	public static int getAndRemoveLastEle(Stack<Integer> stack) {
		int num = stack.pop();
		if(stack.isEmpty()) {
			return num;
		}else {
			int res = getAndRemoveLastEle(stack);
			stack.push(num);
			return res;
		}
	}
}
