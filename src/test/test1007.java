package test;

public class test1007 {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
	 * 
	 * ￥
	 */
	public static void main(String[] args) {

	}
	
	public static int getTimes(int num){
		int times = num - 1;
		int res = 1;
		int base = 2;
		while(times > 0){
			if((times & 1) != 0){
				res *= base;
			}
			base = base * base;
			times >>= 1;
		}
		return res;
	}

}
