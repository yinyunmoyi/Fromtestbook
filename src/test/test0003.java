package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0003 {

	/**
	 * 给定一个数组arr和一个数num，把小于等于num的数放在数组左边 大于num的数放在数组右边(8min)(6min)
	 * 
	 * 荷兰国旗问题： 给定一个数组arr和一个数num，把小于num的数放在数组左边 
	 * 等于num的数放在数组中间，大于num的数放在数组右边(14min)(4min)
	 * 
	 * 经过拆分数组改进的快排算法￥￥
	 * 
	 * 经过国旗问题改进的快排算法(24min)(12min)
	 * 
	 *  
	 */
	public static void main(String[] args) {
		//int[] arr = MyArrays.creatArray(9, 0, 100);
		int[] arr = {38, 84, 33, 87, 54, 40, 68, 56, 24};
		System.out.println(Arrays.toString(arr));
		quickSort1_guoqi(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void split(int[] arr, int key){
		int j = -1;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] < key){
				swap(arr, i, j + 1);
				j++;
			}
		}
	}
	
	private static void swap(int[] arr, int start, int end){
		int i = arr[start];
		arr[start] = arr[end];
		arr[end] = i;
	}
	
	public static void splitMid(int[] arr, int key){
		int j = -1, i = 0, m = arr.length;
		while(i < m){
			if(arr[i] > key){
				swap(arr, i, m - 1);
				m--;
			}else if(arr[i] < key){
				swap(arr, i, j + 1);
				j++;
				i++;
			}else{
				i++;
			}
		}
	}
	
	public static void quickSort1(int[] arr){
		quickSort1(arr, 0, arr.length - 1);
	}
	
	private static void quickSort1(int[] arr, int start, int end){
		if(start < end){
			int key = split1(arr, start, end);
			quickSort1(arr, start, key - 1);
			quickSort1(arr, key + 1, end);
		}
	}
	
	private static int split1(int[] arr, int start , int end){
		int key = arr[start];
		int j = start - 1;
		for(int i = start; i <= end; i++){
			//这里必须是《=， 一旦改为《就会出错，需要修正的范围就会扩大，即使修改了quickSort1(arr, key, end);
			//也会导致无限递归
			if(arr[i] <= key){
				swap(arr, i, j + 1);
				j++;
			}
		}
		//这里必须要互换，因为假如不换那么临界值不为key，这个位置就定错了，接下来的运算也是错误的
		//所以要互换保证位置正确，而互换就说明key作为第一个值没动过，这就要求上面的必须为《=
		swap(arr, start, j < start ? start : j);
		return j < start ? start : j;
	}
	
	public static void quickSort2(int[] arr){
		quickSort2(arr, 0, arr.length - 1);
	}
	
	private static void quickSort2(int[] arr, int start, int end){
		System.out.println(start + " " + end);
		if(start < end){
			
			int[] flag = split2(arr, start, end);
			quickSort2(arr, start, flag[0]);
			quickSort2(arr, flag[1], end);
		}
	}
	
	private static int[] split2(int[] arr, int start, int end){
		int key = arr[start];
		int j = start - 1;
		int endPosition = end;
		for(int i = start; i <= end; ){
			if(arr[i] < key){
				swap(arr, i, j+1);
				j++;
				i++;
			}else if(arr[i] > key){
				swap(arr, i, end);
				end--;
			}else{
				i++;
			}
		}
		
		return new int[]{j < start ? start : j, end < endPosition ? end + 1: end};
	}
	
	public static void split1_a(int[] arr, int key){
		int small = -1;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] <= key){
				swap(arr, i, small+1);
				small++;
			}
		}
	}
	
	public static void guoqi(int[] arr, int key){
		int small = -1, i = 0;
		int big = arr.length;
		while(i < big){
			if(arr[i] > key){
				swap(arr, big - 1, i);
				big--;
			}else if(arr[i] < key){
				swap(arr, i, small + 1);
				small++;
				i++;
			}else{
				i++;
			}
		}
	}
	
	public static void quickSort1_split(int[] arr){
		quickSort1_split(arr, 0, arr.length - 1);
	}
	
	private static void quickSort1_split(int[] arr, int start, int end){
		if(start < end){
			int key = fenSplit(arr, start, end);
			quickSort1_split(arr, start, key - 1);
			quickSort1_split(arr, key + 1, end);
		}
	}
	
	private static int fenSplit(int[] arr, int start, int end){
		
		int key = arr[start];
		int small = start - 1;
		for(int i = start; i <= end; i++){
			//这里必须是《=，因为按照该算法要把key调到小于区的末尾，也就是默认key在小于区，最后返回时一次排序的结果
			//多个key可能出现在数组的任何位置，这显然是错误的
			if(arr[i] <= key){
				swap(arr, i, small+1);
				small++;
			}
		}
		swap(arr, start, small < start ? start : small);
		System.out.println(Arrays.toString(arr));
		//一旦把上面设置为《=，这里就不用设置三元运算符了，因为small必大于等于start不会有越界情况
		return small;
	}
	
	public static void quickSort1_guoqi(int[] arr){
		quickSort1_guoqi(arr, 0, arr.length - 1);
	}
	
	private static void quickSort1_guoqi(int[] arr, int start, int end){
		if(start < end){
			int[] key = guoqiSplit(arr, start, end);
			quickSort1_guoqi(arr, start, key[0] - 1);
			quickSort1_guoqi(arr, key[1] + 1, end);
		}
	}
	
	private static int[] guoqiSplit(int[] arr, int start, int end){
		int key = arr[start], small = start - 1, big = end + 1, i = start;
		while(i < big){
			if(arr[i] > key){
				swap(arr, i, big - 1);
				big--;
			}else if(arr[i] < key){
				swap(arr, i, small + 1);
				small++;
				i++;
			}else{
				i++;
			}
		}
		return new int[]{small + 1, big - 1};
	}
}
