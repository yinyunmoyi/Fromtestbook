package basicS;

import java.util.ArrayList;

public class Jzoffer18 {

	/**
	 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
	 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
	 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
	 */
	public static void main(String[] args) {

	}

	//38 ms	9368K
	//初始算法，有很多缺陷
	public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int num = matrix.length * matrix[0].length;
        for(int i = 0, j = 0, k = matrix.length - 1, m = matrix[0].length - 1;
          ;k-- , m--){
           while(j <= m){
               if(list.size() == num){
                   return list;
               }
               list.add(matrix[i][j++]);
           }
            j--;
            i++;
           while(i <= k){
               if(list.size() == num){
                   return list;
               }
               list.add(matrix[i++][j]);
           }
            i--;
            j--;
            while(j >= matrix.length - k - 1){
                if(list.size() == num){
                   return list;
               }
                list.add(matrix[i][j--]);
            }
            j++;
            i--;
            while(i >= matrix[0].length - m){
                if(list.size() == num){
                   return list;
               }
                list.add(matrix[i--][j]);
            }
            i++;
            j++;
            if(list.size() == num){
                   return list;
               }
       }
    }
	
	//28 ms	9200K
	//优化版本，这种方法用了一个circle作为圈数，这样所有关于行与列的限制值都可以用circle表示了，避免变量混乱
	//第二，circle作为圈数确定了循环次数，不再需要计数来确定了
	//第三，每次小循环之间不再更新角标，而是下一个循环从初始值直接确定为对的答案
	//第四，前两个小循环不加附加条件，后两个加上附加条件，即行数或列数如果不变那么就不进入循环
	//这在控制循环方面做了很大优化，直接避免了许多重复的错误情况
	public ArrayList<Integer> printMatrix_a(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int row = matrix.length;
        int collor = matrix[0].length;
        int circle = ((row < collor ? row:collor) - 1)/2 + 1;
        int j = 0, k = 0;
        for(int i = 0; i < circle; i++){
            for(j = i; j < collor - i; j++){
                list.add(matrix[i][j]);
            }
            for(k = i + 1; k < row - i; k++){
                list.add(matrix[k][collor - i - 1]);
            }
            for(j = collor - i - 2; j >= i && i != row - i - 1; j--){
                list.add(matrix[row - i - 1][j]);
            }
            for(k = row - i - 2; k > i && i != collor - i - 1; k--){
                list.add(matrix[k][i]);
            }
        }
        return list;
    }
}
