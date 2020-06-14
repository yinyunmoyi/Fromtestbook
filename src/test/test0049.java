package test;

import java.util.Stack;

public class test0049 {

	/**
	 * 求最大直方图面积
	 * 假设有一个数组4,3,2,5,1，数组中没有重复值，这个数组每一个数代表直方图的高度
	 *                  |
	 *            |     |
	 *            | |   |
	 *            | | | |
	 *            | | | | | 
	 * 求这个直方图中最大的矩形面积，这里是8,要求时间复杂度ON
	 * 
	 * (21min)
	 */
	public static void main(String[] args) {
		int area = getMaxArea(new int[]{4,3,2,5,1});
		System.out.println(area);
	}
	
	public static int getMaxArea(int[] arr){
		Stack<Integer> stack = new Stack<Integer>();
		int area = 0;
		for(int i = 0; i < arr.length; i++){
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
				int pos = stack.pop();
				int leftSmall = !stack.isEmpty() ? stack.peek() : -1;
				int rightSmall = i;
				area = Math.max((rightSmall - leftSmall - 1) * arr[pos], area);
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()){
			int pos = stack.pop();
			int leftSmall = !stack.isEmpty() ? stack.peek() : -1;
			int rightSmall = arr.length;
			area = Math.max((rightSmall - leftSmall - 1) * arr[pos], area);
		}
		return area;
	}

}
