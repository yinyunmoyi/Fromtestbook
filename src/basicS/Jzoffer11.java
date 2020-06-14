package basicS;

public class Jzoffer11 {

	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。
	 * 求base的exponent次方
	 */
	public static void main(String[] args) {

	}

	//66 ms	10492K
	//快速求幂算法
	public double Power(double base, int exponent) {
        double sum = 1;
        int num = Math.abs(exponent);
        while(num > 0){
            if((num&1) != 0){
                sum = sum * base;
            }
            base = base * base;
            num = num >> 1;
        }
        
        return exponent > 0? sum : 1/sum;
    }
	
	//44 ms	10664K	
	//把指数n拆成n/2 * n/2  或  n-1/2  *  n-1/2 * 1
	//用递归去实现，这样算法复杂度也能降到logn
	public double Power_a(double base, int exponent) {
        if(exponent == 0){
            return 1;
        }else if(exponent == 1){
            return base;
        }
        int n = exponent;
        if(exponent < 0){
            n = -exponent;
        }
        double result = Power(base, n >> 1);
        result = result * result;
        if((exponent&1) != 0){
            result = result * base;
        }
        if(exponent < 0){
            result = 1/result;
        }
        
        return result;
    }
	
	
	
}
