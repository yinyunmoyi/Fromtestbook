package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import basicS.MyArrays;

public class test0055 {

	/**
	 * 给定一个数组，求奇数根偶数个数相同的最长子数组(同一个数组有0,1,2三种值，求1和2的个数相同的最长子数组)
	 * 
	 * (10min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(8, -5, 5);
		System.out.println(Arrays.toString(arr));
		System.out.println(getNumOdd(arr));
	}

	public static int getNumOdd(int[] arr){
		int[] newArr = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			if(arr[i] % 2 == 0){
				newArr[i] = -1;
			}else{
				newArr[i] = 1;
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0, maxLen = Integer.MIN_VALUE;
		for(int i = 0; i < newArr.length; i++){
			sum += newArr[i];
			if(!map.containsKey(sum)){
				map.put(sum, i);
			}
			if(map.containsKey(sum)){
				maxLen = Math.max(maxLen, i - map.get(sum));
			}
		}
		
		return maxLen;
	}
}
