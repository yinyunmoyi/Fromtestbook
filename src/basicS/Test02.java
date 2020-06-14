package basicS;

import java.util.Scanner;

public class Test02 {

	/**
	 * 有这样一个矩形，要求把该矩形分成均匀的正方形且该正方形的边长最大，
	 * 输出正方形的边长和需要多少个正方形填满这个矩形
	 */
	public static void main(String[] args) {
		System.out.println("输入矩形的长和宽：");
		Scanner sc =new Scanner(System.in);
		System.out.println("输入长：");
		int a = sc.nextInt();
		System.out.println("输入宽：");
		int b = sc.nextInt();
		int c = side(a,b);
		System.out.println("正方形的边长："+ c);
		System.out.println("正方形的个数："+(a*b/c/c));
	}

	private static int side(int a, int b) {
		
		if(a == b){
			return a;
		}else{
			if(a>b){
				return side(a-b,b);
			}else{
				return side(b-a,a);
			}
		
		}
	}

}
