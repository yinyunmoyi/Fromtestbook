package basicS;

import java.util.Scanner;

public class Test12 {

	/**
	 * 不用递归快速求幂
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("请输入底数：");
		double n = sc.nextDouble();
		System.out.println("请输入指数：");
		int m = sc.nextInt();
		double k = pow(n, m);
		System.out.println(n + "的" + m + "次方是：" + k);
	}

	private static double pow(double n, int m) {

		//如果指数是0或1直接输出结果
		if (m == 0) {
			return 0;
		}
		if (m == 1) {
			return n;
		}
		//创建一个数组u，里面存入快速求幂过程中的中间数
		//以备拿出来用
		double[] u = new double[m+1];
		//将1索引设置为n
		u[1] = n;
		int c = 1;
		double k = 1;
		int p = 0;
		//开始快速求幂阶段，当到达一半时停止
		while (m >= 2 * c) {
			//相乘之后再替换相乘，快速积累
			k = n * n;
			n = k;
			//c代表现在求幂的次数
			c = 2 * c;
			//在这个过程中的中间数存入数组
			u[c] = k;
		}
		//把此时积累完成的c记录一下
		p = c;
		//进入缓慢求幂阶段，此时快速积累已经完成
		//剩下要做的就是把值从数组中拿出
		//相乘至得到想要的次数
		for (int a = p / 2; m > c; a = a / 2) {
			k = k * u[a];
			c = c + a;

			/*
			 * if(m - c > p/2){ k = k * u[p/2]; c = c + p/2; } if(m - c > p/4){
			 * k = k * u[p/4]; c = c + p/4; }
			 */

		}
		return k;
	}

}
