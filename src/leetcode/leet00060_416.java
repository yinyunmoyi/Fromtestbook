package leetcode;

public class leet00060_416 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//初始递归方法
	public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        return helper(nums.length - 1, nums, 0, sum / 2);
    }
    
    private boolean helper(int idx, int[] nums, int sum, int target) {
        if (idx < 0 || sum > target || nums[idx] > target) {
            return false;
        }
        if (sum == target) {
            return true;
        }
        return helper(idx - 1, nums, sum + nums[idx], target) 
            || helper(idx - 1, nums, sum, target);
    }
    
    //初始dp
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
        	sum += nums[i];
        }
        if(sum % 2 != 0) {
        	return false;
        }
        boolean[][] arr = new boolean[nums.length + 1][sum / 2 + 1];
        arr[nums.length][sum / 2] = true;
        for(int i = arr.length - 2; i >= 0; i--) {
        	for(int j = 0; j < arr[0].length; j++) {
        		arr[i][j] = (arr[i + 1][j] || 
        				(j + nums[i] < arr[0].length ? arr[i + 1][j + nums[i]] : false));
        	}
        }
        return arr[0][0];
    }
    
    //改进后dp
    
  //关于dp的思考，这就是一个典型的将空间复杂度压缩到一半的例子
    //虽然整个的递归过程没有发生变化，但是因为依赖位置的特殊性导致选择特殊的顺序就可以做到
    //只用一个一维数组就模拟数据的全过程
    public static boolean canPartition2(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = volumn; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
        }
        return dp[volumn];
    }
}
