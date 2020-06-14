package leetcode;

public class leet00033_69 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//这道题就是用迭代法解方程
    //首先先把方程转换成等于0的形式，然后让左式等于fx
    //然后用x = x - fx/fx导来迭代即可
    public int mySqrt(int x) {
        double init = 1;
        while(true){
            double num = init - (init * init - x)/(2 * init);
            if((init * init <= x && (init + 1) * (init + 1) > x) || num == init){
                break;
            }
            init = num;
        }
        return (int)init;
    }
}
