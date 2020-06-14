package leetcode;

public class leet00025_152 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//初始DP版本
	public int maxProduct(int[] nums) {
        int[] arr = new int[nums.length];
		int product = 1;
		for(int i = 0; i < nums.length; i++) {
			product *= nums[i];
			arr[i] = product;
			product = (product == 0 ? 1 : product);
		}
		int res = Integer.MIN_VALUE;
		for(int i = arr.length - 1; i >= 0; i--) {
			res = Math.max(res, arr[i]);
			if(arr[i] != 0) {
				for(int j = i - 1; j >= 0 && arr[j] != 0; j--) {
					res = Math.max(res, arr[i] / arr[j]);
				}
			}
		}
		return res;
    }
	
	//改进版本，前后遍历了两遍数组，将累计值取最大值，遇见0就直接跳过，乘积重新开始计数
		//之所以需要前后两遍相乘主要是要避免失去一些结果
		//比如1，2，3，-1，8，9如果从前向后乘那么8和9的乘积不会出现在结果中，只能后向前再遍历一遍
		//如果是1，2，3，-1，8，9，-1，那么久不会有这种问题，因为因为两个负一的出现使整个序列的值乘积都为正
		//也就是说当有一个或奇数个-1时才有可能漏掉情况
		 public static int maxProduct1(int[] nums) {
		       int cur = 1, max = nums[0];
		    for (int i = 0; i < nums.length; i++) {
		        cur *= nums[i];
		        if (cur > max) {
		            max = cur;
		        }
		        if (nums[i] == 0) {
		            cur = 1;
		        }
		    }
		    cur = 1;
		    for (int i= nums.length-1; i >= 0; i--) {
		        cur *= nums[i];
		        if (cur > max) {
		            max = cur;
		        }
		        if (nums[i] == 0) {
		            cur = 1;
		        }
		    }
		    return max;
		        
		    }
}
