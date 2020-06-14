package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0005 {

	/**
	 * 逆序对问题
	 * 一个数组中，如果左边的数比右边的数大就构成一个逆序对
	 * 打印所有逆序对(15min)
	 */
	public static void main(String[] args) {
		int[] arr = {4, 6, 3, 10, 10, 10, 9, 9, 1};
		System.out.println(Arrays.toString(arr));
		reverseD(arr);
	}
	
	public static void reverseD(int[] arr){
		reverseD(arr, 0, arr.length - 1);
	}
	
	private static void reverseD(int[] arr, int start, int end){
		if(start < end){
			int mid = start + (end - start)/2;
			reverseD(arr, start, mid);
			reverseD(arr, mid + 1, end);
			append(arr, start, mid, mid + 1, end);
		}
	}
	
	private static void append(int[] arr, int start1, int end1, int start2, int end2){
		int[] b = new int[end2 - start1 + 1];
		int k = 0, startPosition = start1, n = 0;
		while(start1 <= end1 && start2 <= end2){
			if(arr[start1] > arr[start2]){
				b[k++] = arr[start1++];
				for(n = start2; n <= end2; n++){
					System.out.println(arr[start1 - 1] + " " + arr[n]);
				}
			}else{
				b[k++] = arr[start2++];
			}
		}
		
		while(start1 <= end1){
			b[k++] = arr[start1++];
		}
		while(start2 <= end2){
			b[k++] = arr[start2++];
		}
		for(k = 0; k < b.length; k++){
			arr[startPosition++] = b[k];
		}
	}
	

}
