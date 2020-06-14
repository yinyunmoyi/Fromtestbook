package test;

public class test1010 {

	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。
	 * 求base的exponent次方
	 * 
	 * (2min)￥
	 */
	public static void main(String[] args) {

	}
	
	public static double getRes(double base, int exponent){
		double res = 1;
		int num = Math.abs(exponent);
		while(num > 0){
			if((num & 1) != 0){
				res *= base;
			}
			base = base * base;
			num >>= 2;
		}
		return exponent > 0 ? res : 1/res;
	}

}
