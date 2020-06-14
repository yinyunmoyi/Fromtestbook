package leetcode;

public class leet00019_300 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int lengthOfLIS(int[] nums) {
        //初始方法，用动态规划去做，假设n个数的最大递增长度已知
        //求n+1的情况。n+1的情况一定是前n个最大序列的某个组合而成的
        //要和第n+1个数组成最大序列的条件是，该序列的末尾数字必须小于nums[n+1]
        //否则无法组成递增序列，在能组成递增序列的可能中找出那个最大的即可
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int len = 1;
        dp[0] = 1;
        for(int i = 1; i < dp.length; i++) {
        	int maxVal = 0;
        	for(int j = 0; j < i; j++) {
        		if(nums[j] < nums[i]) {
        			maxVal = Math.max(maxVal, dp[j]);
        		}
        	}
        	dp[i] = maxVal + 1;
        	len = Math.max(len, dp[i]);
        }
        return len;
    }
	
	//改进方案，时间复杂度降低到了Olognn
    //这次的dp数组不再是该位置的结果，而是最长递增序列的最后一个数字
    //在到达一个新的位置时，它有可能和前面的序列组成递增序列
    //但是并不是所有序列都会构成新序列，只有那些长度为x（x为可能出现的长度）中序列的末尾值最小的才有可能被更新
    //比如1,3,5,2,8,4中可能更新的序列有：
    //1
    //1,2
    //1,3,4
    //1,3,5,8
    //如果有新的数字6出现，那么它会引发最后一行更新为1,3,5,6
    //实际上就是在末尾数字中寻找比该数字大的数然后更新，如果比最后一个数还大就说明有新长度产生
    //因为末尾数字序列是递增的，所以可以用二分法加速这个过程，从而把时间复杂度降到Olognn
    public int lengthOfLIS1(int[] nums) {
        if(nums == null || nums.length == 0) {
			return 0;
		}
		int[] tail = new int[nums.length];
		int len = 0;
		tail[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			if(tail[len] < nums[i]) {
				tail[++len] = nums[i];
			}else if(tail[0] > nums[i]) {
				tail[0] = nums[i];
			}else {
				tail[search(tail, len, nums[i])] = nums[i];
			}
		}
		return len + 1;
    }
    
    public int search(int[] tail, int len, int num) {
		int start = 0;
		int end = len;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(tail[mid] > num) {
				end = mid - 1;
			}else if(tail[mid] < num) {
				start = mid + 1;
			}else {
				return mid;
			}
		}
		return start;
	}
}
