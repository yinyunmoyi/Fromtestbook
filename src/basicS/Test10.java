package basicS;

import java.util.Scanner;

public class Test10 {

	/**
	 * 判断一个数是否为素数
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("输入一个整数：");
		int n = sc.nextInt();
		boolean m = isprime(n);
		String k = m==true?"是":"不是";
		System.out.println("它"+k+"素数");
	}

	private static boolean isprime(int n) {
		
		if(n == 2){
			return true;
		}
		
		boolean m = true;
		for(int k = 2;k <= Math.sqrt(n); k++){
			//如果能被k整除说明不是素数立即跳出循环
			if(n % k == 0){
				m = false;
				break;
			}
		}
		return m;
	}

}
