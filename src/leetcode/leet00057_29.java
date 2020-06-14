package leetcode;

public class leet00057_29 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//用位运算和加减法代替除法
    //如果循环加减代替除法会直接超时
    //位运算的原理就是加倍除数，直到2倍的除数已经超过被除数
    //这个时候把被除数替换成两者相减，继续这个过程
    //直至被除数小于除数为止，在这个过程中记录加倍的次数并累加
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        int res = 0;
        int f1 = dividend > 0 ? 1 : -1;
        int f2 = divisor > 0 ? 1 : -1;
        long k1 = Math.abs((long)dividend);
        long k2 = Math.abs((long)divisor);
        while(k1 >= k2){
            int times = 1;
            long n = k2;
            while(k1 >= (n << 1)){
                n <<= 1;
                times <<= 1;
            }
            res += times;
            k1 -= n;
        }
        return res * f1 * f2;
    }
}
