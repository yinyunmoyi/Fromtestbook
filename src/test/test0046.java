package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class test0046 {

	/**
	 * 生成窗口最大值数组
	 * 有一个整型数组arr和大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
	 * 对于数组：[4,3,5,4,3,3,6,7]，窗口大小为3，那么首次出现时窗口是：4,3,5
	 * 然后是3,5,4，直到最后窗口中是3,6,7
	 * 写一个方法，入参为数组arr和w，求一个大小为n-w+1的数组，这个数组代表每个位置下窗口中的最大元素
	 * 此时应该返回[5,5,5,4,6,7]
	 * 
	 * (18min)
	 * (11min)
	 */
	public static void main(String[] args) {
		int[] res = getWin2_2(new int[]{4,3,5,4,3,3,6,7}, 3);
		System.out.println(Arrays.toString(res));
	}

	public static int[] getWindow(int[] arr, int w){
		if(arr == null || arr.length == 0 || w > arr.length){
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		int k = 0;
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < arr.length; i++){
			if(i >= w){
				if(list.peekFirst() == i - 3){
					list.pollFirst();
				}
			}
			while(!list.isEmpty() && arr[list.peekLast()] < arr[i]){
				list.pollLast();
			}
			list.addLast(i);
			if(i >= w - 1){
				res[k++] = arr[list.peekFirst()];
			}
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//生成窗口最大值数组
	// * 有一个整型数组arr和大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
	// * 对于数组：[4,3,5,4,3,3,6,7]，窗口大小为3，那么首次出现时窗口是：4,3,5
	// * 然后是3,5,4，直到最后窗口中是3,6,7
	// * 写一个方法，入参为数组arr和w，求一个大小为n-w+1的数组，这个数组代表每个位置下窗口中的最大元素
	// * 此时应该返回[5,5,5,4,6,7]
	public static int[] getWin2_2(int[] arr, int w){
		if(arr == null || arr.length == 0 || w > arr.length){
			return null;
		}
		int[] win = new int[arr.length - w + 1];
		int k = 0;
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < arr.length; i++){
			if(!list.isEmpty() && (i - w) >= 0 && list.peekFirst() == (i - w)){
				list.pollFirst();
			}
			while(!list.isEmpty() && arr[list.peekLast()] < arr[i]){
				list.pollLast();
			}
			list.addLast(i);
			if((i + 1) >= w){
				win[k++] = arr[list.peekFirst()];
			}
		}
		return win;
	}
}
