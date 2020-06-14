package test002;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class unit2 {

	/**
	 * 设计一个程序，输入总号码数和抽取号码数，使其随机产生一定数量号码
	 * 例：总号码数为50个（1-50），随机抽取5个数：24/34/21/2/7
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入总号码数：");
		int n = sc.nextInt();
		System.out.println("输入抽取号码数：");
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = i+1;
		}
		
		Random r = new Random();
		int m;
		int[] b = new int[k];
		for (int i = 0; i < k; i++) {
			m = r.nextInt(n-i) ;
			b[i] = a[m];
			a[m] = a[n - 1- i];
		}
		Arrays.sort(b);
		for (int i : b) {
			System.out.print(i+" ");
		}
		
	}

}
