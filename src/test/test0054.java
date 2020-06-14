package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import basicS.MyArrays;

public class test0054 {

	/**
	 * 给定一个数组和一个数aim，求和为aim的最长子数组长度,数组有正有负，要求时间复杂度为ON
	 * 
	 * (11min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(8, -5, 5);
		System.out.println(Arrays.toString(arr));
		System.out.println(getLength(arr, 0));
	}
	
	public static int getLength(int[] arr, int aim){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0, maxLength = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			if(!map.containsKey(sum)){
				map.put(sum, i);
			}
			if(map.containsKey(sum - aim)){
				maxLength = Math.max(maxLength, i - map.get(sum - aim));
			}
		}
		return maxLength;
	}

}
