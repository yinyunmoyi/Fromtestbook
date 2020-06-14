package basicS;

import java.util.Arrays;
import java.util.Scanner;

public class Test11 {

	/**
	 * 用厄尔多塞筛计算小于n的所有素数，
	 * 每次找出一个最小的素数 并删除集合中它的倍数
	 * 最后剩下的元素就全是素数
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入一个整数：");
		int n = sc.nextInt();
		anuduo(n);
	}

	//c值指示了最后素数数组的大小
	private static int c = 0;
	private static void anuduo(int n) {
		int[] a = new int[n];
		//初始化数组a
		for(int i = 2;i < a.length;i++){
			a[i] = i;
		}
		//将a数组中非素数都置为空
		for(int i = 2;i < a.length;i++){
			dele(a,i);
		}
		//创建数组b，它是最后的素数数组
		int[] b = new int[c];
		//把a中非空元素都给b
		for(int i = 2,k = 0;i < a.length;i++){
			if(a[i] != 0){
				b[k] = a[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(b));
	}

	private static void dele(int[] a, int i) {
		//如果对应数组中该数是0，说明它已经被清除过了
		//直接跳出循环，否则该值是素数，素数个数加1
		if(a[i] == 0){
			return;
		}else{
			c++;
		}
		//可能整除该素数的值从他的2倍开始
		for(int j = 2; j * i < a.length;j++){
			a[j * i] = 0;
		}
	}

}
