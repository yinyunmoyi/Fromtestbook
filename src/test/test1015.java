package test;

public class test1015 {

	/**
	 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
	 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
	 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
	 * 
	 * (17min)
	 */
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{5,6,7},{9,10,11},{13,14,15}};
		printMatrix(matrix);
	}
	
	public static void printMatrix(int[][] matrix) {
		int startRow = 0;
		int endRow = matrix.length - 1;
		int startCol = 0;
		int endCol = matrix[0].length - 1;
		while(startRow <= endRow && startCol <= endCol) {
			print(matrix, startRow, endRow, startCol, endCol);
			startRow++;endRow--;startCol++;endCol--;
		}
	}
	
	public static void print(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
		if(startRow == endRow && startCol == endCol) {
			System.out.print(matrix[startRow][startCol] + " ");
		}else if(startRow == endRow) {
			for(int i = startCol; i <= endCol; i++) {
				System.out.print(matrix[startRow][i] + " ");
			}
		}else if(startCol == endCol) {
			for(int i = startRow; i <= endRow; i++) {
				System.out.print(matrix[i][startCol] + " ");
			}
		}else {
			for(int i = startCol; i <= endCol; i++) {
				System.out.print(matrix[startRow][i] + " ");
			}
			for(int i = startRow + 1; i <= endRow; i++) {
				System.out.print(matrix[i][endCol] + " ");
			}
			for(int i = endCol - 1; i >= startCol; i--) {
				System.out.print(matrix[endRow][i] + " ");
			}
			for(int i = endRow - 1; i >= (startRow + 1); i--) {
				System.out.print(matrix[i][startCol] + " ");
			}
		}
	}

	
}
