package leetcode;

public class leet00027_312 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int maxCoins(int[] nums) {
        //dp[i][j]是把i到j的气球都打掉获得的最大硬币数，k是i到j中最后一个打掉的气球号
		//把整个问题分解成多个i到j的子问题，每个子问题的解决方法就是找到i到j中最后一个打掉的气球k
		//因为是最后一个打掉，所以从i到k和k+1到j这两个部分是先打掉的，这两个部分也是相同的子问题
		//假设这两个子问题已知，那么打掉最后一个气球k时或得的硬币数为：
		//dp[i][k - 1] + newNums[i - 1] * newNums[k] * newNums[j + 1] + dp[k + 1][j]
		//也就是两个子问题加上三个数相乘
		//每一个位置都能这样求出，要构造出数组边界两端的值，故递归数组要比nums大2
	    if (nums == null || nums.length == 0) {
	        return 0;
	    }
	    int n = nums.length;
	    int[] newNums = new int[n + 2];
	    newNums[0] = 1;
	    newNums[n + 1] = 1;
	    for (int i = 0; i < n; i++) {
	        newNums[i + 1] = nums[i];
	    }
	    
	    int[][] dp = new int[n + 2][n + 2];
	    for (int len = 1; len <= n; len++) {
	        for (int i = 1; i <= n - len + 1; i++) {
	            int j = i + len - 1;
	            for (int k = i; k <= j; k++) {
	                dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + newNums[i - 1] * newNums[k] * newNums[j + 1] + dp[k + 1][j]);
	            }
	        }
	    }
	    return dp[1][n];
    }
}
