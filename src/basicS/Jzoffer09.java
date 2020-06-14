package basicS;

import java.lang.Math;

public class Jzoffer09 {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
	 */
	public static void main(String[] args) {

	}

	
	//由数学归纳法推导得出其实就是计算2的n-1次方
	//14 ms	9232K
	//调用本来内置的方法
	public int JumpFloorII(int target) {
        return (int)Math.pow(2, target - 1);
    }
	
	//22 ms	9260K
	//快速幂算法
	public int JumpFloorII_a(int target) {
        int sum = 1;
        int mid = 2;
        int n = target - 1;
        while(n > 0){
            if((n&1) != 0){
                sum *= mid;
            }
            mid = mid * mid;
            n = n>>1;
        }
        return sum;
    }
	
	//16 ms	9292K
	//用简单递归完成
	public int JumpFloorII_b(int target) {
        if(target == 1){
            return 1;
        }else if(target == 2){
            return 2;
        }
        
        return 2 * JumpFloorII(target - 1);
    }
	
	//16 ms	9424K
	//直接用位运算去做，因为移位本身就是在做2的次方运算
	public int JumpFloorII_c(int target) {
        return 1<<(--target);
    }
	
}
