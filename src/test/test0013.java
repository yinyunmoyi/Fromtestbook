package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0013 {

	/**
	 * 之字形打印一个矩阵，例如
	 * [1, 2, 3, 4]
	 * [5, 6, 7, 8]
	 * [9, 10, 11, 12]
	 * 打印结果为1,2,5,9,6,3,4,7,10,11,8,12  (24min)
	 */
	public static void main(String[] args) {
		int[][] matrix = MyArrays.creatMatrix(3, 4, 0, 100);
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		zhiPrint(matrix);
	}

	public static void zhiPrint(int[][] matrix){
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		boolean flag = false;
		while(x1 < matrix.length && x2 < matrix.length){
			zhiPrint(matrix, x1, y1, x2, y2, flag);
			if(y1 == matrix[0].length - 1){
				x1++;
			}else{
				y1++;
			}
			
			if(x2 == matrix.length - 1){
				y2++;
			}else{
				x2++;
			}
			flag = !flag;
		}
		
	}
	
	private static void zhiPrint(int[][] matrix, int x1, int y1, int x2, int y2, boolean flag){
		if(flag){
			for(int i = x1, j = y1; i <= x2 && j >= y2; i++, j--){
				System.out.println(matrix[i][j]);
			}
		}else{
			for(int i = x2, j = y2; i >= x1 && j <= y1; i--, j++){
				System.out.println(matrix[i][j]);
			}
		}
	}
}
