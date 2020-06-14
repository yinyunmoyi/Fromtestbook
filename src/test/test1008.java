package test;

public class test1008 {

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。
	 * 其中负数用补码表示
	 * 
	 * ￥
	 */
	public static void main(String[] args) {

	}

	public static int numOfOne(int num){
		int times = 0;
		for(int i = 0; i < 32; i++){
			if((num & 1) == 1){
				times++;
			}
			num >>>= 2;
		}
		return times;
	}
}
