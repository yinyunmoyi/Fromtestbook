package leetcode;

public class leet00052_50 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//这道题不能用寻常的快速幂算法来解决
    //常规算法都会超时，只能用递归方法
    //递归时的终止条件就是n为0，如果n不为0
    //先递归求得myPow(x, n / 2)，然后如果能整除就返回half * half
    //否则根据n的正负乘x或1/x
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        double half = myPow(x, n / 2);
        if (n % 2 == 0) return half * half;
        if (n > 0) return half * half * x;
        return half * half / x;
    }
    /*
    
    这种方法也可以计算，但是还是超时了，如果是负数一开始就把底换成1/x
    自己写时尝试了半天，后来发现循环中的两段代码互换位置即可解决
    public static double myPow(double x, int n) {
        x = n > 0 ? x : 1/x;
        n = Math.abs(n);
        double sum = 1;
        while(n != 0){
            if((n&1) == 1){
                sum *= x;
            }
            x = x * x;
            n >>= 1;
        }
        return sum;
    }
    */
}
