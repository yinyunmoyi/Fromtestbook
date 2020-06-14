package test;

public class test0050 {

	/**
	 * 求最大子矩阵的大小
	 * 给一个矩阵只有0和1，求这个矩阵中全是1的最大子矩阵的面积
	 * 
	 * 
	 * ￥
	 */
	public static void main(String[] args) {

	}

	public static int getAreaInMatrix(int[][] matrix){
		int[][] b = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				b[i][j] = matrix[i][j];
			}
		}
		
		int sum = test0049.getMaxArea(b[0]);
		for(int i = 1; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i - 1][j] == 0){
					b[i][j] = 0;
				}else{
					b[i][j] = matrix[i - 1][j] + 1;
				}
			}
			sum = Math.max(sum, test0049.getMaxArea(b[i]));
		}
		return 0;
	}
}
