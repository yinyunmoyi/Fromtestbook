package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0006 {

	/**
	 * 桶排序（24min）、(11min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(9, 0, 100);
		System.out.println(Arrays.toString(arr));
		tongSort(arr);
		System.out.println(Arrays.toString(arr));
		
	}

	//不要随便把表达式放在for上，一次false就足以结束循环
	public static void bucketSort(int[] arr){
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] > max){
				max = arr[i];
			}
			if(arr[i] < min){
				min = arr[i];
			}
		}
		int[] b = new int[max - min + 1];
		for(int i = 0; i < arr.length; i++){
			b[arr[i] - min]++;
		}
		int k = 0;
		for(int i = 0; i < b.length; ){
			if(b[i] > 0){
				arr[k++] = i + min;
				b[i]--;
			}
			if(b[i] == 0){
				i++;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void tongSort(int[] arr){
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			min = (arr[i] < min ? arr[i] : min);
			max = (arr[i] > max ? arr[i] : max);
		}
		int[] b = new int[max - min + 1];
		for(int i = 0; i < arr.length; i++){
			b[arr[i] - min]++;
		}
		int k = 0;
		for(int i = 0; i < arr.length; i++){
			while(b[k] == 0){
				k++;
			}
			arr[i] = k + min;
			b[k]--;
		}
	}
}
