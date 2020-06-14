package basicS;

import java.util.Stack;

public class zuo003 {

	//汉诺塔问题，输入n，从上往下号码分别是1-n
	//要求不能直接从一端移动到另一端，只能先移动到中间再移动
	
	//用递归和栈两种方法去做
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hanNuo(3);
	}

	//递归，把n个数从一端移动到另一端，拆解成n-1个数的移动和最后一个数的移动
	public static void hanNuo(int num) {
		hanNuo(num, "left", "right", "mid");
	}
	
	public static void hanNuo(int num, String start, String end, String mid) {
		if(num == 1) {
			System.out.println("take " + num + " from " + start + " to " + mid);
			System.out.println("take " + num + " from " + mid + " to " + end);
		}else {
			hanNuo(num - 1, start, end, mid);
			System.out.println("take " + num + " from " + start + " to " + mid);
			hanNuo(num - 1, end, start, mid);
			System.out.println("take " + num + " from " + mid + " to " + end);
			hanNuo(num - 1, start, end, mid);
		}
	}
	
	//用栈来做
	//用三个栈来模拟三个位置，栈之间元素移动时要按照几个原则
	//刚从一个栈移动过去的元素不能移动回来，因为工作不能重复
	//刚从一个栈a移动到栈b一定不能再从栈a移动到栈b，因为那会出现大元素放置到小元素上方的现象
	//移动的元素一定要满足移动后小的元素在大的元素下方
	//有这几个条件限制，每一步操作都是唯一确定的
	//直至左和中两个栈为空循环结束
	public static void hanNuo1(int num) {
		Stack<Integer> leftStack = new Stack<Integer>();
		while(num > 0) {
			leftStack.push(num--);
		}
		Stack<Integer> midStack = new Stack<Integer>();
		Stack<Integer> rightStack = new Stack<Integer>();
		System.out.println("take " + 1 + " from " + "left" + " to " + "mid");
		midStack.push(leftStack.pop());
		Stack<Integer> nowStack = midStack;
		Stack<Integer> lastStack = leftStack;
		while(!(leftStack.isEmpty() && midStack.isEmpty())) {
			if(nowStack == midStack) {
				if(lastStack == leftStack) {
					if((!midStack.isEmpty()) && (rightStack.isEmpty() || rightStack.peek() > midStack.peek())) {
						rightStack.push(midStack.pop());
						System.out.println("take " + rightStack.peek() + " from " + "mid" + " to " + "right");
						nowStack = rightStack;
						lastStack = midStack;
					}else {
						midStack.push(rightStack.pop());
						System.out.println("take " + midStack.peek() + " from " + "right" + " to " + "mid");
						lastStack = rightStack;
					}
				}else {
					if((!midStack.isEmpty()) && (leftStack.isEmpty() || leftStack.peek() > midStack.peek())) {
						leftStack.push(midStack.pop());
						System.out.println("take " + leftStack.peek() + " from " + "mid" + " to " + "left");
						nowStack = leftStack;
						lastStack = midStack;
					}else {
						midStack.push(leftStack.pop());
						System.out.println("take " + midStack.peek() + " from " + "left" + " to " + "mid");
						lastStack = leftStack;
					}
				}
			}else if(nowStack == leftStack) {
				if((!rightStack.isEmpty()) && (midStack.isEmpty() || midStack.peek() > rightStack.peek())) {
					midStack.push(rightStack.pop());
					System.out.println("take " + midStack.peek() + " from " + "right" + " to " + "mid");
					nowStack = midStack;
					lastStack = rightStack;
				}else {
					rightStack.push(midStack.pop());
					System.out.println("take " + rightStack.peek() + " from " + "mid" + " to " + "right");
					nowStack = rightStack;
					lastStack = midStack;
				}
			}else {
				if((!leftStack.isEmpty()) && (midStack.isEmpty() || midStack.peek() > leftStack.peek())) {
					midStack.push(leftStack.pop());
					System.out.println("take " + midStack.peek() + " from " + "left" + " to " + "mid");
					nowStack = midStack;
					lastStack = leftStack;
				}else {
					leftStack.push(midStack.pop());
					System.out.println("take " + leftStack.peek() + " from " + "mid" + " to " + "left");
					nowStack = leftStack;
					lastStack = midStack;
				}
			}
		}
	}
}
