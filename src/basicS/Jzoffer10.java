package basicS;

public class Jzoffer10 {

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。
	 * 其中负数用补码表示
	 */
	public static void main(String[] args) {

	}

	//14 ms	9256K	
	//可以简化为方法f，符号位也是可以右移处理的
	public static int NumberOf1(int n) {
        int num = 0;
        for(int i = 1; i <= 31; i++){
            if((n&1) == 1){
                num++;
            }
            n = n >> 1;
        }
        
        if(n >= 0){
            return num;
        }else{
            return num + 1;
        }
    }
	
	//16 ms	9340K
	public static int NumberOf1_a(int n) {
        int num = 0;
        int flag = 1;
        while(flag != 0){
            if((flag&n) != 0){
                num++;
            }
            flag = flag << 1;
        }
        return num;
    }
	
	//14 ms	9356K
	//n = n&(n-1)每次都能让n最后一位1变成0
	//这样有几个1就会循环几次，效率较高

	//如果一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，
	//那么原来处在整数最右边的1就会变为0，原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。
	//其余所有位将不会受到影响。
	public static int NumberOf1_b(int n) {
        int num = 0;
        while(n != 0){
            num++;
            //把最右边的1变0
            n = n&(n-1);
        }
        return num;
    }
	
	//18 ms	9416K
	public int NumberOf1_c(int n) {
        return Integer.toBinaryString(n).replace("0","").length();
    }
	
	//13 ms	9180K
	public int NumberOf1_d(int n) {
        return Integer.bitCount(n);
    }
	
	//17 ms	9388K
	//看不懂
	public int NumberOf1_e(int n) {

        int temp = n;
         temp = (temp & 0x55555555) + ((temp & 0xaaaaaaaa) >>> 1);
         temp = (temp & 0x33333333) + ((temp & 0xcccccccc) >>> 2);
         temp = (temp & 0x0f0f0f0f) + ((temp & 0xf0f0f0f0) >>> 4);
         temp = (temp & 0x00ff00ff) + ((temp & 0xff00ff00) >>> 8);
         temp = (temp & 0x0000ffff) + ((temp & 0xffff0000) >>> 16);
         return temp;
    }
	
	//17 ms	9336K
	public int NumberOf1_f(int n) {

        int num = 0;
        for(int i = 1; i <= 32; i++){
            if((n&1) == 1){
                num++;
            }
            n = n >> 1;
        }
        
        return num;
    }
	
	//16 ms	9212K
	//n = n>>>1 代表无符号右移，无符号右移一个整数与右移相同
	//无符号右移一个负数会把符号位的1一起右移并补零
	public int NumberOf1_g(int n) {

        int num = 0;
        while(n != 0){
            if((n&1) != 0){
                num++;
            }
            n = n>>>1;
        }
        return num;
    }
	
}
