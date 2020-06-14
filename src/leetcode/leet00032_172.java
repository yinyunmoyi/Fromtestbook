package leetcode;

public class leet00032_172 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//只需要寻找2的因子数和5的因子数即可，因为每隔2必有一个2的因子
    //所以5的因子要少于2的因子，所以关键在于求5的因子
    //每隔5就有一个5的因子，但是这样的数可能有多个5因子
    //每隔25/125/625也有5的因子，这是一个连乘的过程
    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            int tmp = n / 5;
            count += tmp;
            n = tmp;
        }
        return count;
    }
}
