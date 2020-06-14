package leetcode;

import java.util.Arrays;

public class leet00013_494 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//常规解法
	//从普通的递归关系改遍而来
	public static int findTargetSumWays1(int[] nums, int S) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
        if(S > sum || S < -1 * sum){
            return 0;
        }
		int[][] matrix = new int[sum * 2 + 1][nums.length + 1];
		matrix[S + sum][nums.length] = 1;
		for(int j = nums.length - 1; j >= 0; j--) {
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][j] = i - nums[j] >= 0 && i + nums[j] < matrix.length ?
						matrix[i - nums[j]][j + 1] + matrix[i + nums[j]][j + 1] : 0;
			}
		}
		return matrix[sum][0];
	}
	
	//优化解法，把常规解法从二维的矩阵dp转换成了一个数组，空间复杂度降低了一个档次
	//之所以可以降低是因为把整个问题转换成了一个更简单的问题
	
	//如果P和N分别是nums数组的两个集合，问题可以描述成，找一对P、N，存在多少使下式成立：
	// sum(P) - sum(N) = target
	//两边处理一下得下式：
	// sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
	// 2 * sum(P) = target + sum(nums)
	//这样问题就转换为了有多少个nums的子集P使其满足：2 * sum(P) = target + sum(nums)
	//这个问题再用递归去解决即可
	public static int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public static int subsetSum(int[] nums, int s) {
    	int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--) {
            	dp[i] += dp[i - n]; 
            }
        return dp[s];
    }

}
