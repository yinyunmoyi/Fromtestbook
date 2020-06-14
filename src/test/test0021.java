 package test;

public class test0021 {

	/**
	 * 输入一个参数N，给出纸条从下边到上边连续对折N次从上到下所有折痕的方向，
	 * 例如N=1，down，N=2，down down up(17min)(5min)
	 */
	public static void main(String[] args) {
		//printZhe(4);
		printZ(4);
	}

	public static void printZhe(int num){
		printZhe(num, true);
	}
	
	public static void printZhe(int level, boolean flag){
		if(level > 0){
			printZhe(level - 1, true);
			System.out.print((flag ? "down" : "up") + " ");
			printZhe(level - 1, false);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void printZ(int num){
		printZ(1, false, num);
	}
	
	private static void printZ(int num, boolean flag, int size){
		if(num > size){
			return;
		}
		printZ(num + 1, false, size);
		System.out.print((flag ? "up" : "down") + " ");
		printZ(num + 1, true, size);
	}
}
