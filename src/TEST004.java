import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.List;

public class TEST004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int num = search(new int[] {0, 1,2},1);
		//System.out.println(num);
		//System.out.println(canJump1(new int[] {2,3,1,1,4}));
		char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		System.out.println(exist(board, "ABCB"));
	}
	
	public static boolean exist(char[][] board, String word) {
        boolean flag = false;
		for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		if(word.charAt(0) == board[i][j]) {
        			boolean[][] sign = new boolean[board.length][board[0].length];
        			flag = flag || exist(board, sign, i, j, word, 0);
        		}
        	}
        }
		return flag;
    }
	
	private static boolean exist(char[][] board, boolean[][] sign, int i, int j, String word, int posi) {
		if(posi == word.length()) {
			return true;
		}else if(i >= 0 && j >= 0 && i < board.length && j < board[0].length 
				&& !sign[i][j] && word.charAt(posi) == board[i][j]) {
			sign[i][j] = true;
			if(exist(board, sign, i + 1, j, word, posi + 1) || 
					exist(board, sign, i, j + 1, word, posi + 1) ||
					exist(board, sign, i - 1, j, word, posi + 1) ||
					exist(board, sign, i, j - 1, word, posi + 1)) {
				return true;
			}
			sign[i][j] = false;
			return false;
		}else {
			return false;
		}
	}

	

}
