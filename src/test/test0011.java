package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0011 {

	/**
	 * 回字打印矩阵(18min)
	 */
	public static void main(String[] args) {
		int[][] matrix = MyArrays.creatMatrix(4, 3, 0, 100);
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		huiPrint(matrix);
	}

	public static void huiPrint(int[][] matrix){
		int x1 = 0, y1 = 0;
		int x2 = matrix.length - 1, y2 = matrix[0].length - 1;
		int i;
		while(x1 <= x2 && y1 <= y2){
			if(x1 < x2 && y1 < y2){
				for(i = y1; i <= y2; i++){
					System.out.println(matrix[x1][i]);
				}
				for(i = x1 + 1; i <= x2; i++){
					System.out.println(matrix[i][y2]);
				}
				for(i = y2 - 1; i >= y1; i--){
					System.out.println(matrix[x2][i]);
				}
				for(i = x2 - 1; i >= x1 + 1; i--){
					System.out.println(matrix[i][y1]);
				}
			}else if(x1 == x2){
				for(i = y1; i <= y2; i++){
					System.out.println(matrix[x1][i]);
				}
			}else if(y1 == y2){
				for(i = x1; i <= x2; i++){
					System.out.println(matrix[i][y1]);
				}
			}
			x1++;y1++;x2--;y2--;
		}
	}
}
