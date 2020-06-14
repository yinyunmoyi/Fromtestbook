package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0007 {

	/**
	 * 给定一个数组，求排序之后相邻两数的最大差值，要求时间复杂度O(n)，不能用不基于比较的排序￥、(27min)
	 */
	public static void main(String[] args) {
		//int[] arr = {52, 78, 34, 23, 40, 84, 72, 10, 17};
		int[] arr = MyArrays.creatArray(9, 0, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(maxsplit(arr));
	}
	
	//举例时要取最一般一般的情况，因为边界少减最小值浪费了许多时间
	public static int maxSplit(int[] arr){
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			min = arr[i] < min ? arr[i] : min;
			max = arr[i] > max ? arr[i] : max;
		}
		boolean[] flag = new boolean[arr.length + 1];
		int[] minVal = new int[arr.length + 1];
		int[] maxVal = new int[arr.length + 1];
		int position, sub = 0, num;
		for(int i = 0; i < arr.length; i++){
			position = ((int)((arr[i] - min )/((max - min + 1)/(arr.length + 1))) == arr.length + 1 )?
					arr.length : (int)((arr[i] - min )/((max - min + 1)/(arr.length + 1)));
			if(flag[position]){
				minVal[position] = minVal[position] > arr[i] ? arr[i] : minVal[position];
				maxVal[position] = maxVal[position] < arr[i] ? arr[i] : maxVal[position];
			}else{
				minVal[position] = maxVal[position] = arr[i];
				flag[position] = true;
			}
		}
		
		for(int i = 0; i < flag.length - 1 && flag[i];){
			num = i + 1;
			while(!flag[num]){
				num++;
			}
			sub = (minVal[num] - maxVal[i]) > sub ? (minVal[num] - maxVal[i]) : sub;
			i = num;
		}
		
		return sub;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int maxsplit(int[] arr){
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < arr.length; i++){
			max = arr[i] > max ? arr[i] : max;
			min = arr[i] < min ? arr[i] : min;
		}
		int[] maxValue = new int[arr.length + 1];
		int[] minValue = new int[arr.length + 1];
		boolean[] flag = new boolean[arr.length + 1];
		int position;
		for(int i = 0; i < arr.length; i++){
			position = arr[i] == max ? arr.length : 
				(arr[i] - min) * (arr.length + 1) / (max - min);
			maxValue[position] = flag[position] ? Math.max(maxValue[position], arr[i]) : arr[i];
			minValue[position] = flag[position] ? Math.min(maxValue[position], arr[i]) : arr[i];
			flag[position] = true;
		} 
		int split = Integer.MIN_VALUE;
		int num ;
		for(int i = 0; i < maxValue.length - 1;){
			num = 1;
			while(!flag[i+num]){
				num++;
			}
			split = Math.max(minValue[i+num] - maxValue[i], split);
			i = i + num;
		}
		return split;
	}

}
