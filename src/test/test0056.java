package test;

import java.util.HashMap;
import java.util.Map;

public class test0056 {

	/**
	 * 数组异或和划分问题
	 * 定义数组异或和就是数组中所有的数异或起来的结果
	 * 给定一个数组你可以将其划分成多个子数组，要求划分的子数组中异或和为0的子数组最多
	 * 求这种状况下异或和为0的子数组个数，要求时间复杂度为ON
	 * 
	 * ￥
	 */
	public static void main(String[] args) {

	}

	public static int getNum(int[] arr){
		int sum = 0;
		int pre;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int[] dp = new int[arr.length];
		for(int i = 0; i <arr.length; i++){
			sum ^= arr[i];
			if(map.containsKey(sum)){
				pre = map.get(sum);
				dp[i] = (pre == -1 ? 1 : dp[pre] + 1);
			}
			if(i >= 1){
				dp[i] = Math.max(dp[i], dp[i - 1]);
			}
			map.put(sum, i);
		}
		return dp[arr.length - 1];
	}
	
}
