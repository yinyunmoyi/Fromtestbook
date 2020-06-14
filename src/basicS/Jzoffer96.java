package basicS;

public class Jzoffer96 {

	//地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
	//但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
	//但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
	
	//分散搜索的思想，需要一个函数判断到底可不可进
	public int movingCount(int threshold, int rows, int cols)
    {
        boolean[][] flag = new boolean[rows][cols];
        return isTrue(threshold, 0, 0, rows, cols, flag);
    }
    
    private int isTrue(int k, int i, int j, int rows, int cols, boolean[][] flag){
        if(i < 0 || i >= rows || j < 0 || j >= cols || flag[i][j]){
            return 0;
        }
        
        if(count(i)+ count(j) > k){
            return 0;
        }
        flag[i][j] = true;
        return 1 + isTrue(k, i + 1, j, rows, cols, flag) 
            + isTrue(k, i - 1, j, rows, cols, flag)
            + isTrue(k, i, j + 1, rows, cols, flag)
            + isTrue(k, i, j - 1, rows, cols, flag);
    }
    
    private int count(int i){
        int k = 0;
        while(i > 0){
            k += (i % 10);
            i = i/10;
        }
        return k;
    }
}
