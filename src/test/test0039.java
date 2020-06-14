package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0039 {

	/**
	 * 给一个二维数组，每个数都是正数，要求从左上角走到右下角，每次只能向右或者向下移动
	 * 求移动路径的最小和(递归版和动态规划版)
	 * 
	 * (40min)(30min)
	 * (11min)(22min)
	 */
	public static void main(String[] args) {
		int[][] matrix = MyArrays.creatMatrix(3, 4, 0, 5);
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("-----------------");
		System.out.println(getMinSum_2(matrix));
	}

	public static int getMinPath(int[][] matrix){
		return getMinPath(matrix, 0, 0, 0);
	}
	
	private static int getMinPath(int[][] matrix, int pos1, int pos2, int num){
		if(pos1 >= matrix.length || pos2 >= matrix[0].length){
			return Integer.MAX_VALUE;
		}
		num += matrix[pos1][pos2];
		if(pos1 == matrix.length - 1 && pos2 == matrix[0].length - 1){
			return num;
		}else{
			int num1 = getMinPath(matrix, pos1, pos2 + 1, num);
			int num2 = getMinPath(matrix, pos1 + 1, pos2, num);
			return Math.min(num1, num2);
		}
	}
	
	
	public static int getMinPathIn(int[][] matrix){
		int line1 = matrix.length - 1;
		int line2 = matrix[0].length - 1;
		int[][] mat = new int[line1 + 1][line2 + 1];
		mat[line1][line2] = matrix[line1][line2];
		for(int i = line1 - 1; i >= 0; i--){
			mat[i][line2] = mat[i + 1][line2] + matrix[i][line2];
		}
		for(int i = line2 - 1; i >= 0; i--){
			mat[line1][i] = mat[line1][i + 1] + matrix[line1][i];
		}
		int times = Math.min(line1 + 1, line2 + 1) - 1;
		for(int i = 1; i <= times; i++){
			mat[line1 - i][line2 - i] = Math.min(mat[line1 - i + 1][line2 - i], mat[line1 - i][line2 - i + 1])
					+ matrix[line1 - i][line2 - i];
			for(int j = line1 - i - 1; j >= 0; j--){
				mat[j][line2 - i] = Math.min(mat[j + 1][line2 - i], mat[j][line2 - i + 1]) 
						+ matrix[j][line2 - i];
			}
			for(int j = line2 - i - 1; j >= 0; j--){
				mat[line1 - i][j] = Math.min(mat[line1 - i][j + 1], mat[line1 - i + 1][j])
						+ matrix[line1 - i][j];
			}
		}
		return mat[0][0];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//给一个二维数组，每个数都是正数，要求从左上角走到右下角，每次只能向右或者向下移动
	// * 求移动路径的最小和(递归版和动态规划版)
	public static int getMinSum_1(int[][] matrix){
		return getMinSum_1(0, 0, 0, matrix);
	}
	
	private static int getMinSum_1(int line1, int line2, int nowSum, int[][] matrix){
		if(line1 == matrix.length - 1 && line2 == matrix[0].length - 1){
			return nowSum + matrix[line1][line2];
		}else if(line1 == matrix.length - 1){
			return getMinSum_1(line1, line2 + 1, nowSum + matrix[line1][line2], matrix);
		}else if(line2 == matrix[0].length - 1){
			return getMinSum_1(line1 + 1, line2, nowSum + matrix[line1][line2], matrix);
		}else if(line1 < matrix.length && line2 < matrix[0].length){
			return Math.min(getMinSum_1(line1 + 1, line2, nowSum + matrix[line1][line2], matrix), 
			getMinSum_1(line1, line2 + 1, nowSum + matrix[line1][line2], matrix));
		}else{
			return 0;
		}
	}
	
	public static int getMinSum_2(int[][] matrix){
		int[][] flag = new int[matrix.length][matrix[0].length];
		flag[matrix.length - 1][matrix[0].length - 1] = matrix[matrix.length - 1][matrix[0].length - 1];
		for(int j = matrix[0].length - 2; j >= 0; j--){
			flag[matrix.length - 1][j] = flag[matrix.length - 1][j + 1] +
					matrix[matrix.length - 1][j];
		}
		for(int i = matrix.length - 2; i >= 0; i--){
			flag[i][matrix[0].length - 1] = flag[i + 1][matrix[0].length - 1] + 
					matrix[i][matrix[0].length - 1];
		}
		int size = Math.min(matrix.length, matrix[0].length) - 1;
		for(int i = 1; i <= size; i++){
			fullFlag(matrix, flag, matrix.length - 1 - i, matrix[0].length - 1 - i);
			for(int j = matrix[0].length - 2 - i; j >= 0; j--){
				fullFlag(matrix ,flag, matrix.length - 1 - i, j);
			}
			for(int j = matrix.length - 2 - i; j >= 0; j--){
				fullFlag(matrix, flag, j, matrix[0].length - 1 - i);
			}
		}
		for(int[] arr : flag){
			System.out.println(Arrays.toString(arr));
		}
		return flag[0][0];
	}
	
	private static void fullFlag(int[][] matrix, int[][] flag, int i, int j){
		if(i >= 0 && j >= 0){
			flag[i][j] = Math.min(flag[i][j + 1], flag[i + 1][j]) + matrix[i][j];
		}
	}
	
	
	
	
}
