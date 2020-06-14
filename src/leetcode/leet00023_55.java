package leetcode;

public class leet00023_55 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//一般递归
	public static boolean canJump(int[] nums) {
        return canJump(nums, 0);
    }
	
	private static boolean canJump(int[] nums, int posi) {
		if(posi == nums.length - 1) {
			return true;
		}else if(posi > nums.length - 1) {
			return false;
		}else if(nums[posi] == 0) {
			return false;
		}
		boolean flag = false;
		for(int i = 1; i <= nums[posi]; i++) {
			flag = flag || canJump(nums, posi + i);
		}
		return flag;
	}
	
	//普通DP
	public static boolean canJump1(int[] nums) {
		boolean[] arr = new boolean[nums.length];
		arr[arr.length - 1] = true;
		for(int i = arr.length - 2; i >= 0; i--) {
			boolean flag = false;
			for(int j = (i + nums[i] >= arr.length ? arr.length - 1 - i : nums[i]); j >= 0; j--) {
				if(flag) {
					break;
				}
				flag = flag || arr[i + j];
			}
			arr[i] = flag;
		}
		return arr[0];
	}
	
	//优化后的DP，利用了这个问题的特点进行的改进
	//这个问题的每一个位置都代表可以跳的范围，对于倒数第二个位置而言
	//只要可以跳的范围包含最后一个位置就能成功，这个限制就是last位置
	//如果该位置能成功last就会被提前
	//这样每一个位置只需要检查一次就能得出结果
	public boolean canJump3(int[] nums) {
        int last = nums.length-1;
        for (int i = nums.length-2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last <= 0;
    }
	
}
