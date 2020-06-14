package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test1011 {

	/**
	 * 输入一个整数数组，来调整该数组中数字的顺序，
	 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
	 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 * 
	 * 省时间版(3min)
	 * 省空间版(7min)
	 * 
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(8, 0, 100);
		System.out.println(Arrays.toString(arr));
		splitOdd_1(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void splitOdd(int[] arr){
		int[] b = new int[arr.length];
		int k = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] % 2 == 1){
				b[k++] = arr[i];
			}
		}
		
		for(int i = 0; i < arr.length; i++){
			if(arr[i] % 2 == 0){
				b[k++] = arr[i];
			}
		}
		
		for(int i = 0; i < arr.length ; i++){
			arr[i] = b[i];
		}
	}
	
	public static void splitOdd_1(int[] arr){
		for(int i = 1; i < arr.length; i++){
			if(arr[i] % 2 == 1){
				for(int j = i; j >= 1 && arr[j - 1] % 2 == 0; j--){
					swap(arr, j, j-1);
				}
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j){
		int num = arr[i];
		arr[i] = arr[j];
		arr[j] = num;
	}
 
}
