package test;

public class test1006 {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
	 * 
	 * (7min)
	 */
	public static void main(String[] args) {

	}

	public static int getNum(int times){
		if(times <= 0){
			return 0;
		}else if(times == 1){
			return 1;
		}else if(times == 2){
			return 2;
		}
		int begin = 1, end = 2, res = 3;
		for(int i = 2; i < times; i++){
			res = begin + end;
			begin = end;
			end = res;
		}
		return end;
	}
}
