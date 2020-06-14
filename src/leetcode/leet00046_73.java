package leetcode;

public class leet00046_73 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//这道题的难点在于如果读到0就处理该行该列时
    //该行该列没有遍历到的部分如果有0，就直接被覆盖了
    //原来本来应该那个0引起的行和列就没有处理被漏掉
    //初始解法，用两个数组记录0位置的行和列
    //遍历一遍矩阵，填充这两个数组，然后集中处理这两个数组指示的行和列
    public void setZeroes(int[][] matrix) {
        boolean[] cols = new boolean[matrix[0].length];
        boolean[] rows = new boolean[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    cols[j] = true;
                    rows[i] = true;
                }
            }
        }

        for(int i = 0; i < cols.length; i++){
            if(cols[i]){
                for(int k = 0; k < matrix.length; k++){
                    matrix[k][i] = 0;
                }
            }
        }

        for(int i = 0; i < rows.length; i++){
            if(rows[i]){
                for(int k = 0; k < matrix[0].length; k++){
                    matrix[i][k] = 0;
                }
            }
        }
    }
    
  //这是一个额外空间复杂度O1的算法
    //首先先确定第一行第一列有没有0，创建两个boolean值分别记录有无
    //然后搜索其他行列，如果ij位置是0，就把0j和i0设置为0
    //这样第一行和第一列就成为了指示位，第一行和第一列的情况又由两个布尔值去记录
    //这样所有信息都记录完毕了，开始依次恢复即可
    public void setZeroes1(int[][] matrix) {
        boolean rowFlag = false, colFlag = false;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][0] == 0){
                colFlag = true;
            }
        }
        for(int j = 0; j < matrix[0].length; j++){
            if(matrix[0][j] == 0){
                rowFlag = true;
            }
        }
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int j = 1; j < matrix[0].length; j++){
            if(matrix[0][j] == 0){
                for(int i = 0; i < matrix.length; i++){
                    matrix[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < matrix.length; i++){
            if(matrix[i][0] == 0){
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        if(colFlag){
            for(int i = 0; i < matrix.length; i++){
                matrix[i][0] = 0;
            }
        }

        if(rowFlag){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
    }
}
