package test;

public class test1005 {

	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
	 * 
	 * 矩阵求法没有实现！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！1
	 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	 * 
	 * (6min)
	 */
	public static void main(String[] args) {

	}
	
	public static int FeiBo(int num){
		if(num == 0){
			return 0;
		}else if(num == 1 || num == 2){
			return 1;
		}
		int begin = 1;
		int end = 2;
		int value;
		num = num - 2;
		while(num > 0){
			value = begin + end;
			begin = end;
			end = value;
			num--;
		}
		return end;
	}
	
	

}
