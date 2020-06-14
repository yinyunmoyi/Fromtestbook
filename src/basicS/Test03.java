package basicS;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test03 {

	/**
	 * 快速排序，从大到小
	 */
	public static void main(String[] args) {
		//1、随机生成一个容量为n的数组，数组的数字大小在0-2000内
		Random r = new Random();
		System.out.println("输入数组大小：");
		Scanner sc =new Scanner(System.in);
		int nextInt = sc.nextInt();
		int[] a = new int[nextInt];
		for(int i = 0;i<a.length;i++){
			a[i] = r.nextInt(2001);
		}
		System.out.println("排序前：");
		System.out.println(Arrays.toString(a));
		//2、实现选择排序：
		fastSort(a);
		System.out.println(Arrays.toString(a));		
	}

	private static void fastSort(int[] a) {
		
	}

}
