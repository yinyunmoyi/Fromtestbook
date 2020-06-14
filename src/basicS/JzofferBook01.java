package basicS;

public class JzofferBook01 {

	/**
	 * 用一条语句判断一个整数是不是2的整数次方
	 * 
	 * 输入两个整数m和n，求需要改变m二进制中的多少位能变成n
	 */
	public static void main(String[] args) {
		//System.out.println(isUnOdd(4));
	}
	
	//因为一个数按位与一个数减一会将最右边的1变成0，如果它是2的整数次方，那么一定只有一个1，这样的操作一定能把它变成0
	public static boolean isUnOdd(int num){
		return (num&(num - 1)) == 0;
	}

	public static int getNum(int m, int n){
		int k = (m^n);
		int num = 0;
		while(k != 0){
			num++;
			k = (k&(k - 1));
		}
		return num;
	}
}
