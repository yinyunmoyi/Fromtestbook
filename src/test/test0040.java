package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0040 {

	/**
	 * 给一个数组，还有一个int数aim，可以任意选择数组中的数字（每个数字最多只能选一次），
	 * 尝试累加到aim，如果累加成功返回true，不成功返回false(递归版和动态规划版)
	 * 
	 * (6min)(38min)
	 */
	public static void main(String[] args) {
		//int[] arr = {1, 5, 3, 2, 1};
		int[] arr = MyArrays.creatArray(5, -5, 5);
		System.out.println(Arrays.toString(arr));
		System.out.println(isSumIn(arr, 5));
	}

	public static boolean isSum(int[] arr, int aim){
		return isSum(arr, 0, 0, aim);
	}
	
	private static boolean isSum(int[] arr, int level, int sum, int aim){
		if(sum == aim){
			return true;
		}
		if(level < arr.length){
			return isSum(arr, level + 1, sum, aim) || isSum(arr, level + 1, sum + arr[level], aim);
		}else{
			return false;
		}
	}
	
	public static boolean isSumIn(int[] arr, int aim){
		int max = 0;
		for(int i = 0; i < arr.length; i++){
			max += Math.abs(arr[i]);
		}
		System.out.println(max);
		if(aim > max){
			return false;
		}
		boolean[][] matrix = new boolean[max + 1][arr.length + 1];
		matrix[aim][arr.length - 1] = true;
		for(int i = aim; i >= 1; i--){
			for(int j = arr.length - 1; j >= 1; j--){
				if(matrix[i][j]){
					matrix[i][j - 1] = true;
					if(i - arr[j] >= 0){
						matrix[i - arr[j]][j - 1] = true;
					}
				}
			}
		}
		for(boolean[] is : matrix){
			System.out.println(Arrays.toString(is));
		}
		return matrix[0][0];
	}
}
