package test;

import java.util.Arrays;
import java.util.Stack;

public class test0048 {

	/**
	 * 单调栈
	 * 求一个数组中各位置中左边离该位置最近的比它大的数和右边离该位置最近的比它大的数
	 * 用两个integer数组表示出来，如果没有就置位null,要求时间复杂度On，假设数组中没有重复元素
	 * 
	 * (13min)
	 */
	public static void main(String[] args) {
		Integer[][] res = getNearFlag(new int[]{1,3,4,2,6,5,9,8,7});
		for(Integer[] arr : res){
			System.out.println(Arrays.toString(arr));
		}
	}

	public static Integer[][] getNearFlag(int[] arr){
		Stack<Integer> stack = new Stack<Integer>();
		Integer[][] res = new Integer[2][arr.length];
		int i = 0;
		while(i < arr.length){
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]){
				res[1][stack.pop()] = arr[i];
			}
			if(stack.isEmpty()){
				res[0][i] = null;
			}else{
				res[0][i] = arr[stack.peek()];
			}
			stack.push(i);
			i++;
		}
		
		while(!stack.isEmpty()){
			res[1][stack.pop()] = null;
		}
		return res;
	}
}
