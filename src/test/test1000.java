package test;

public class test1000 {

	/**
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
	 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
	 * 判断数组中是否含有该整数。(4min)
	 */
	public static void main(String[] args) {

	}
	
	public static boolean haveNum(int[][] matrix, int num){
		int i = matrix.length - 1, j = 0;
		while(i >= 0 && j < matrix[0].length){
			if(matrix[i][j] < num){
				j++;
			}else if(matrix[i][j] > num){
				i--;
			}else{
				return true;
			}
		}
		return false;
	}

}
