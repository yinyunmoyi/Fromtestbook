package leetcode;

public class leet00055_130 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//遍历边界，把边界的元素O及其周围元素都变成$
	//然后把剩下的O都变成X
	public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        for(int i = 0; i < board.length; i++){
            go(board, i, 0);
            go(board, i, board[0].length - 1);
        }
        for(int j = 0; j < board[0].length; j++){
            go(board, 0, j);
            go(board, board.length - 1, j);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j  <board[0].length; j++){
                if(board[i][j] == '$'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void go(char[][] board, int x, int y){
        if(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
            if(board[x][y] == '$' || board[x][y] == 'X'){
                return;
            }
            board[x][y] = '$';
            go(board, x + 1, y);
            go(board, x - 1, y);
            go(board, x, y + 1);
            go(board, x, y - 1);
        }
    }
}
