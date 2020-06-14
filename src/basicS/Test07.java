package basicS;

import java.util.Scanner;

public class Test07 {

	/**
	 * 用递归实现计算x的n次幂
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("输入底数：");
		int n = sc.nextInt();
		System.out.println("输入指数：");
		int m = sc.nextInt();
		int k = pow(n,m);
		System.out.println(n+"的"+m+"次方是"+k);
	}

	private static int pow(int n, int m) {
		
		if(m == 0){
			return 1;
		}
		if(m == 1){
			return n;
		}
		if(m % 2 == 0){
			return pow(n*n,m/2);
		}else{
			return pow(n*n,m/2)*n;
		}
	}

}
