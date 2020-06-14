package leetcode;
import java.util.*;

public class leet00041_36 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//初始解法，用常规的实现方法
	//首先遍历每一行，一行设置一个hashset，然后遍历每一列
	//按规律遍历每一个9宫格，每次遍历设置一个hashset
	//来达到去重的效果
	public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < board.length; i++){
            HashSet<Character> set = new HashSet<>();
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.' && set.contains(board[i][j])){
                    return false;
                }
                set.add(board[i][j]);
            }
        }

        for(int j = 0; j < board[0].length; j++){
            HashSet<Character> set = new HashSet<>();
            for(int i = 0; i < board.length; i++){
                if(board[i][j] != '.' && set.contains(board[i][j])){
                    return false;
                }
                set.add(board[i][j]);
            }
        }

        for(int i = 1; i < board.length; i += 3){
            for(int j = 1; j < board[0].length; j += 3){
                if(!isOk(board, i, j)){
                    return false;
                }
            }
        }

        return true;
    }
    
    private boolean isOk(char[][] board, int x, int y){
        HashSet set = new HashSet();
        for(int i = x - 1; i <= x + 1; i++){
            for(int j = y - 1; j <= y + 1; j++){
                System.out.println(board[i][j]);
                if (board[i][j] != '.' && set.contains(board[i][j])){
                    return false;
                }
                set.add(board[i][j]);
            }
        }
        return true;
    }
    
  //改进方案，用数组设置标记的方式来去重
  //将每个位置的计算结果和行或列结合起来，如果遍历到了就置为true
  //检测块是否重复的方式有些特殊，算法的目的是将二维的i和j映射成一维的数字
  //可以将i*一个大数+j的这种方式来设计，为了占用内存的综合考虑将i和j都缩小
  //这样这种映射关系就变成了i / 3 * 3 + j / 3
  //属于同一块的坐标一定会被映射成一个数字
    public boolean isValidSudoku1(char[][] board) {
        boolean[][] cols = new boolean[9][9];
        boolean[][] rows = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                int val = board[i][j] - '1';
                if(cols[val][j] || rows[i][val] || blocks[val][i / 3 * 3 + j / 3]){
                    return false;
                }
                cols[val][j] = true;
                rows[i][val] = true;
                blocks[val][i / 3 * 3 + j / 3] = true;
            }
        }
        return true;
    }
}
