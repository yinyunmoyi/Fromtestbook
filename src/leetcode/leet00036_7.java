package leetcode;

public class leet00036_7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	 //这道题的难点有两个
    //一个是负数除正数的余数还是负数
    //还有就是越界是难以判断的，这里用(newNum * 10 + s - s) / 10 != newNum这个等式来判断是否越界
    //首先右式newNum是不可能越界的，它是上一个循环计算出来的新值
    //左式的newNum * 10 + s是可能越界的值，然后将它拆开算回去 ，看和不越界的值比较是否相等
    //这样就能确定是否越界
    public int reverse(int x) {
        int num = x;
        int newNum = 0;
        while(num != 0){
            int s = num % 10;
            num /= 10;
            if((newNum * 10 + s - s) / 10 != newNum){
                return 0;
            }
            newNum = newNum * 10 + s;

        }
        return newNum;
    }
}
