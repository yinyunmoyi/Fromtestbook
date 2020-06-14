package test;

public class test0027 {

	/**
	 * 设计一个大小为32000bit的bit数组，并把第30000位变成1 ￥、(6min)
	 */
	public static void main(String[] args) {
		int[] array = new int[1000];
		int position = 30000/1000;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//设计一个大小为32000bit的bit数组，并把第30000位变成1 ￥
	public static void BuLong(){
		int[] arr = new int[1000];
		int position = 30000/32;
		int pianYi = 30000%32;
		arr[position] = 1 << pianYi;
	}
}
