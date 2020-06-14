package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0029 {

	/**
	 *  岛问题
	 * 一个矩阵只有0和1两种值，如果称上下左右连成一片的1为一个岛，求岛的个数(12min)
	 */
	public static void main(String[] args) {
		int[][] matrix = MyArrays.creatMatrix(5, 5, 0, 1);
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println(sumDao(matrix));
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
	}

	public static int sumDao(int[][] matrix){
		int sum = 0;
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i][j] == 1){
					sum++;
					infect(matrix, i, j);
				}
			}
		}
		return sum;
	}
	
	private static void infect(int[][] matrix, int i, int j){
		if( i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length && matrix[i][j] == 1){
			matrix[i][j] = 2;
			infect(matrix, i + 1, j);
			infect(matrix, i, j + 1);
			infect(matrix, i - 1, j);
			infect(matrix, i, j - 1);
		}
	}
}
