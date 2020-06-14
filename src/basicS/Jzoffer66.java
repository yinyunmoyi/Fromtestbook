package basicS;

public class Jzoffer66 {

	//写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
	
	//本质就是利用二进制加法，sum是两个数异或的结果，是本位加的结果（不考虑进位）
	//jin是考虑进位的修正，只有两个数在同为是1时才会结果为1，结果左移一位准备进位
	//然后进位修正和本位结果继续相加，直至没有进位了才停止
	public int Add(int num1,int num2) {
        int sum;
        int jin;
        do{
            sum = (num1^num2);
            jin = (num1&num2) << 1;
            num1 = sum;
            num2 = jin;
        }while(num2 != 0);
        return num1;
    }
}
