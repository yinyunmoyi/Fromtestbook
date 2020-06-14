package leetcode;

public class leet00005_198 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//again
	
	
	
	
	//You are a professional robber planning to rob houses along a street. 
	//Each house has a certain amount of money stashed, the only constraint stopping 
	//you from robbing each of them is that adjacent houses have security system connected 
	//and it will automatically contact the police if two adjacent houses were broken into on the same night.


	//Given a list of non-negative integers representing the amount of money of each house, 
	//determine the maximum amount of money you can rob tonight without alerting the police.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public static int rob1(int[] nums) {
	    	int[] arr = new int[nums.length + 2];
	    	for(int i = nums.length - 1; i >= 0; i--) {
	    		arr[i] = Math.max(nums[i] + arr[i + 2], arr[i + 1]);
	    	}
	    	return arr[0];
	    }
}
