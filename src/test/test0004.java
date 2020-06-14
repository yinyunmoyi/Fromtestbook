package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0004 {

	/**
	 * 求一个数组的小和，每一个数左边比当前小的数累加起来
	 * 所有数的和即为数组的小和￥、(17min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(5, 0, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(printsmallSum1_a(arr));
		System.out.println(Arrays.toString(arr));
	}

	public static int smallSum(int[] arr){
		return smallSum(arr, 0, arr.length - 1);
	}
	
	private static int smallSum(int[] arr, int start, int end){
		if(start < end){
			int mid = start + (end - start)/2;
			return smallSum(arr, start , mid) + smallSum(arr, mid + 1, end)
					+ mergeSum(arr, start , mid, mid + 1, end);
		}
		
		return 0;
	}
	
	private static int mergeSum(int[] arr, int start1, int end1, int start2, int end2){
		int sum = 0, k = 0, i = start1;
		int[] b = new int[end2 - start1 + 1];
		while(start1 <= end1 && start2 <= end2){
			if(arr[start1] >= arr[start2]){
				b[k++] = arr[start2++];
			}else{
				b[k++] = arr[start1++];
				sum += ((end2 - start2 + 1) * arr[start1 - 1]);
			}
		}
		
		while(start1 <= end1){
			b[k++] = arr[start1++];
		}
		while(start2 <= end2){
			b[k++] = arr[start2++];
		}
		for(k = 0; k < b.length; k++){
			arr[i++] = b[k];
		}
		return sum;
	}
	
	public static int printsmallSum1_a(int[] arr){
		return printsmallSum1_a(arr, 0, arr.length - 1);
	}
	
	private static int printsmallSum1_a(int[] arr, int start, int end){
		if(start < end){
			int mid = start + (end - start)/2;
			return printsmallSum1_a(arr, start, mid) + printsmallSum1_a(arr, mid + 1, end)
					+ mergesmallsum(arr, start, mid, mid + 1, end);
		}
		return 0;
	}
	
	private static int mergesmallsum(int[] arr, int start1, int end1, int start2, int end2){
		int[] b = new int[end2 - start1 + 1];
		int startpoision = start1, k = 0 , sum = 0;
		while(start1 <= end1 && start2 <= end2){
			if(arr[start1] < arr[start2]){
				sum += arr[start1] * (end2 - start2 + 1);
				b[k++] = arr[start1++];
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
		for(int m = 0; m < b.length; m++){
			arr[startpoision++] = b[m];
		}
		return sum;
	}
}
