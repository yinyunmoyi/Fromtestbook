package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0001 {

	/**
	 * 实现简单排序(5min)
	 * 冒泡排序(5min)、
	 * 优化冒泡排序(5min)、
	 * 选择排序(7min)￥、(5min)
	 * 插入排序(4min) ￥、(5min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(9, 0, 100);
		System.out.println(Arrays.toString(arr));
		insertSort_a(arr);
		System.out.println(Arrays.toString(arr));
		
	}
	
	public static void selectSort_a(int[] arr){
		int min;
		for(int i = 0; i < arr.length; i++){
			min = i;
			for(int j = i; j < arr.length; j++){
				if(arr[min] > arr[j]){
					min = j;
				}
			}
			swap(arr, i, min);
		}
	}
	
	public static void insertSort_a(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = i; j > 0 && arr[j] < arr[j - 1]; j--){
					swap(arr, j, j-1);
			}
		}
	}
	
	public static void easySort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = i; j < arr.length; j++){
				if(arr[j] < arr[i]){
					swap(arr, i, j);
				}
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j){
		int num = arr[i];
		arr[i] = arr[j];
		arr[j] = num;
	}
	
	public static void bubbleSort(int[] arr){
		for(int i = arr.length - 1; i >= 0; i--){
			for(int j = arr.length - 1; j > 0; j--){
				if(arr[j] < arr[j - 1]){
					swap(arr, j - 1, j);
				}
			}
		}
	}
	
	public static void exBubbleSort(int[] arr){
		boolean flag = true;
		for(int i = arr.length - 1; i >= 0 && flag; i--){
			flag = false;
			for(int j = arr.length - 1; j > 0; j--){
				if(arr[j] < arr[j - 1]){
					swap(arr, j - 1, j);
					flag = true;
				}
			}
		}
	}
	
	//可以省去最小值的记录变量，直接把变量放入数组下标中
	public static void selectSort(int[] arr){
		int minPosition, j;
		for(int i = 0; i < arr.length; i++){
			minPosition = i;
			for(j = i; j < arr.length; j++){
				if(arr[j] < arr[minPosition]){
					minPosition = j;
				}
			}
			swap(arr, minPosition, i);
		}
	}
	
	//将比较条件移动到for的判断中，因为一旦不满足条件之后的循环都没有意义了
	public static void insertSort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = i;j > 0 && arr[j] < arr[j - 1] ; j--){
					swap(arr, j , j-1);
			}
		}
	}
	
	

}
