package test;

import java.util.Stack;

public class test1017 {

	//输入两个整数序列，第一个序列表示栈的压入顺序，
	// * 请判断第二个序列是否可能为该栈的弹出顺序。
	// * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
	// * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
	// * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
	
	//(23min)
	//(26min)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(test1017.isOk(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
	}
	public static boolean isOk(int[] arr1, int[] arr2) {
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0, j = 0;
		stack.push(arr1[i++]);
		while((!stack.isEmpty() && stack.peek() == arr2[j]) || i < arr1.length) {
			while(i < arr1.length && !stack.isEmpty() && stack.peek() != arr2[j]) {
				stack.push(arr1[i++]);
			}
			if(stack.peek() == arr2[j]) {
				stack.pop();
				j++;
			}
		}
		return stack.isEmpty();
	}
	
	//顺序逆序扫描了两次压入数组，如果和弹出数组相同就num加一，如果最后弹出序列能扫描完就说明满足
	//如果顺序不对一定扫描不完弹出序列
	public static boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || pushA.length == 0 
           || popA == null || popA.length == 0 || pushA.length != popA.length){
            return false;
        }
        int num = 0;
        int k = 0;
        for(int i = 0; i < pushA.length; i++){
            if(pushA[i] == popA[k]){
                k++;num++;
            }
        }
        for(int i = pushA.length - 1; i >= 0; i--){
            if(k < pushA.length && pushA[i] == popA[k]){
                k++;num++;
            }
        }
        return num == pushA.length;
    }

}
