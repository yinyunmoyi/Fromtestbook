package basicS;

import java.math.BigInteger;

public class Jzoffer08 {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
	 */
	public static void main(String[] args) {
		int i = JumpFloor(9);
		System.out.println(i);
		//int j = JumpFloor_a(9);
		//System.out.println(j);
	}

	//30 ms	9868K
	//用直接算法计算，发现6级台阶实际上是C60、C51、C42、C33的和
	//当台阶数很大时为避免溢出使用BigInteger类
	public static int JumpFloor(int target) {
        int num = 0, ceil = 0, floor = target;
        
        while(ceil <= floor){
            num += count(floor--, ceil++);
        }
        return num;
    }
    
    private static int count(int floor, int ceil){
        if(ceil == 0){
            return 1;
        }
        if(ceil * 2 > floor){
            return count(floor, floor - ceil);
        }
        BigInteger num = BigInteger.valueOf(1); 
        BigInteger div1 = BigInteger.valueOf(1); 
        BigInteger div2 = BigInteger.valueOf(1);
        BigInteger floor_big = BigInteger.valueOf(floor);
        BigInteger ceil_big = BigInteger.valueOf(ceil);
        int flag = ceil;
        for(int i = 1; i <= ceil; i++){
            div1 = div1.multiply(floor_big);
            floor_big = floor_big.subtract(num);
        }
        
        for(int j = 1; j <= flag - 1; j++){
            div2 = div2.multiply(ceil_big);
            ceil_big = ceil_big.subtract(num);
        }
        return div1.divide(div2).intValue();
        
    }
    
    
    //14 ms	9204K
    //找规律发现，n为1时结果为1，n为2时结果为2，n为3时结果为3，n为4时结果为5.。。。
    //这是一个斐波那契数列
    public int JumpFloor_a(int target) {
        if(target == 1|| target == 2){
            return target;
        }
        int num1 = 1;
        int num2 = 2;
        int sum = 0;
        for(int i = 3; i <= target; i++){
            sum = num1 + num2;
            num1 = num2;
            num2 = sum;
        }
        return num2;
    }
}
