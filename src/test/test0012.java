package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0012 {

	/**
	 * 将正方形矩阵顺时针旋转(15min)
	 */
	public static void main(String[] args) {
		int[][] martrix = MyArrays.creatMatrix(4, 4, 0, 100);
		for (int[] is : martrix) {
			System.out.println(Arrays.toString(is));
		}
		xuAN(martrix);
		System.out.println("---------------------");
		for (int[] is : martrix) {
			System.out.println(Arrays.toString(is));
		}
	}
	
	public static void xuAN(int[][] matrix){
		int x1 = 0, y1 = 0;
		int x2 = matrix.length - 1, y2 = matrix[0].length - 1;
		int num, i = 0;
		while(x1 <= x2 && y1 <= y2){
			for(i = 0; i < (x2 - x1); i++){
				num = matrix[x1][y1 + i];
				matrix[x1][y1 + i] = matrix[x2 - i][y1];
				matrix[x2 - i][y1] = matrix[x2][y2 - i];
				matrix[x2][y2 - i] = matrix[x1 + i][y2];
				matrix[x1 + i][y2] = num;
			}
			x1++;y1++;x2--;y2--;
		}
	}

}
