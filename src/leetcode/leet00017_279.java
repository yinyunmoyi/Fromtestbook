package leetcode;

import java.util.Arrays;

public class leet00017_279 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 常规DP方法，建立一个数组，数组的每个位置都是该位置对应的结果
	// 计算n = 65时，它可以是dp[64] + 1,也可以是dp[61] + 1....
	// 这样一个值其实是依赖之前的所有值
	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= n; ++i) {
			int min = Integer.MAX_VALUE;
			int j = 1;
			while (i - j * j >= 0) {
				min = Math.min(min, dp[i - j * j] + 1);
				++j;
			}
			dp[i] = min;
		}
		return dp[n];
	}
	
	//基于一种数学方法：Legendre's three-square theorem
    public int numSquares1(int n) {
        if (is_sqrt(n))
			return 1;
		while (n % 4 == 0) {
			n /= 4;
		}
		if (n % 8 == 7)
			return 4;
		for (int i = 0; i * i < n; i++) {
			if (is_sqrt(n - i * i))
				return 2;
		}
		return 3;
    }
    
    public boolean is_sqrt(int n) {
		int m = (int) Math.sqrt(n);
		if (m * m == n)
			return true;
		else
			return false;
	}
}
