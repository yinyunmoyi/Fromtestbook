package test001;

import java.math.BigInteger;
import java.util.Scanner;

public class Unit1 {

	/**
	 * 设计一个程序，要求输入总号码数和抽奖号码数返回中奖概率
	 * 例：有50个数（0-49），你必须从50个数中选择六个数
	 * （1、56、89、87、45、89）作为中奖号码，求中奖概率
	 */
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("输入总号码数：");
		int i = sc.nextInt();
		System.out.println("输入中奖号码数：");
		int j = sc.nextInt();
		
		BigInteger ai = new BigInteger(i+"");
		BigInteger bi = new BigInteger(j+"");
		BigInteger ci = new BigInteger("1");
		BigInteger val = new BigInteger("1");
		
		/*for (int k = 0; k < j; k++) {
			ci = ci.multiply(ai);
			ai = ai.subtract(val);
		}
		for(int k = 0; k <j; k++){
			ci = ci.divide(bi);
			bi = bi.subtract(val);
		}*/
		/*考虑到可以用一个式子和循环表示，这里主要有以下改进：
		1、用valueof方法使得使用--运算符成为可能
		2、考虑到计算组合数总是乘一个除一个，这样
		就可以在一个循环中用乘一处一的方式实现所有运算*/
		for(int p = j, k = 0; k < p; k++){
			ci = ci.multiply(BigInteger.valueOf(i--)).divide(BigInteger.valueOf(j--));
		}
		System.out.print("中奖概率：");
		System.out.print("1/"+ci);
	}

}
