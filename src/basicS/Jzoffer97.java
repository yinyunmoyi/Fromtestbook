package basicS;


public class Jzoffer97 {

	//请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
	//每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，
	//则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 
	//这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
	//因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
	
	//分散搜索的思路
	//这道题难点在于如何在搜索的时候发现不匹配再往回查找同时做到不走重复路
	//应该利用一个Boolean数组保证不走重复路，在多次尝试中一次尝试失败利用递归的特性往回设置Boolean数组的值
	//这样就能再次标记为从未来过
	
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        boolean[] flag = new boolean[matrix.length];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(isTrue(matrix, rows, cols, i, j, 0, str, flag)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isTrue(char[] matrix, int rows, int cols, int i, int j, int posi,
                         char[] str, boolean[] flag){
        int k = i * cols + j;
        if(posi >= str.length){
            return true;
        }
        if(k >= matrix.length || k < 0 || i >= rows || i < 0 || 
           j >= cols || j < 0 || str[posi] != matrix[k] || flag[k]){
            return false;
        }
         flag[k] = true;
        if(isTrue(matrix, rows, cols, i + 1, j, posi + 1, str, flag) ||
          isTrue(matrix, rows, cols, i, j + 1, posi + 1, str, flag) ||
            isTrue(matrix, rows, cols, i - 1, j, posi + 1, str, flag) ||
            isTrue(matrix, rows, cols, i, j - 1, posi + 1, str, flag)){
            return true;
        }
        flag[k] = false;
        return false;
    }
}
