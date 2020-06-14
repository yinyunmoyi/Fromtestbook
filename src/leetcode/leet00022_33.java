package leetcode;

public class leet00022_33 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	//这道题是一道典型的二分法解决问题
		//大概思路就是先找到最小的元素，然后确定target到底在哪一边，最后用二分法解决
		//但是实现过程是很容易出问题的
		//二分法解决问题主要是要确定三个方面的控制：
		//第一、while后的条件表达式到底带不带=
		//第二、while内部if里面的更新条件：end = mid - 1;
		//第三、最后返回什么
		
		//首先是寻找最小位置的二分法
		//根据mid和end位置或start位置的相对大小就能得到更新方法
		//更新时start = mid + 1;、end = mid;
		//这个end的更新是特别的，因为有可能遇见边界情况
		//这样设置就不会漏掉结果，也正因为如此所以while的更新是不带等号的
		//最后返回时一定是start=end时，故返回end或start哪一个都行
		
		//其次是根据target和最小位置确定新start和end
		//start和end如果用nums[0]的比较，会出错，此时要换用其他方法,就是用最后一个值比较
		//有一个特殊情况就是相等的情况：target <= nums[nums.length - 1]
		//不要费时间犹豫和分析，直接拎出来特殊处理
		
		//总结就是：定三个限制信息、一个方法不行马上换另一个、特殊情况直接单独处理
		public static int search(int[] nums, int target) {
			if (nums == null || nums.length == 0) {
				return -1;
			}
			int minValIndex = getMin(nums);
			int start = (target <= nums[nums.length - 1] ? minValIndex : 0);
			int end = (target > nums[nums.length - 1] ? minValIndex: nums.length - 1);
			while(start <= end && end >= 0 && start < nums.length) {
				int mid = start + (end - start) / 2;
				if(nums[mid] > target) {
					end = mid - 1;
				}else if(nums[mid] < target) {
					start = mid + 1;
				}else {
					return mid;
				}
			}
			return -1;
		}

		private static int getMin(int[] nums) {
			int start = 0, end = nums.length - 1;
			while (start < end) {
				int mid = start + (end - start) / 2;
				if (nums[end] < nums[mid]) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}
			return start;
		}
}
