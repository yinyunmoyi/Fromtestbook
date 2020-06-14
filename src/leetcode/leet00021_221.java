package leetcode;

public class leet00021_221 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//用DP的思想去解决问题，把一个位置ij看成是以该位置为右下角点的正方形边长
    //一个位置的边长值依赖它左，左上和上三个位置的值，确定了依赖关系之后就开始DP
    //按照行的顺序DP即可
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int[][] arr = new int[matrix.length][matrix[0].length];
        int max = 0;
		for(int i = 0; i < matrix.length; i++) {
        	for(int j = 0; j < matrix[0].length; j++) {
        		if(matrix[i][j] != '0' && (i - 1) >= 0 && (j - 1) >= 0) {
        			arr[i][j] = Math.min(arr[i - 1][j - 1], Math.min(arr[i - 1][j], arr[i][j - 1])) + 1;
        		}else if(matrix[i][j] != '0') {
        			arr[i][j] = 1;
        		}
        		max = Math.max(max, arr[i][j]);
        	}
        }
		return max * max;
    }
}
