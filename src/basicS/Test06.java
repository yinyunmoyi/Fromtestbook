package basicS;

import java.util.Scanner;

public class Test06 {

	/**
	 * 计算两个数的最大公因数，用欧几里得算法
	 * a、b两数（a>b）取余得c，然后对b和c做同样的操作直至余数为0，此时b即所求
	 */
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入两个整数：");
		int m = sc.nextInt();
		int n = sc.nextInt();
		int k = maxsam(m,n);
		System.out.println("最大公约数为："+ k);
	}

	private static int maxsam(int m, int n) {
		//即使m<n，也不会有问题，因为第一次循环它们的余数为m,这样它们
		//就会交换位置
		while(n != 0){
			int k = m % n;
			m = n;
			n = k;
		}
		return m;
	}

}
