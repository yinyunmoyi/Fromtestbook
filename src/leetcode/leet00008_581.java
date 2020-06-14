package leetcode;

import java.util.Arrays;

public class leet00008_581 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//again
	
	
	//Given an integer array, you need to find one continuous subarray that 
	//if you only sort this subarray in ascending order, 
	//then the whole array will be sorted in ascending order, too.

	//You need to find the shortest such subarray and output its length.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//把数组先排序，然后从前向后遍历，记录下最后一个与之前位置数不相同的点
	//从后先前遍历，记录下最靠前的位置数不相同的点
	//然后相减即可，但是由于需要先排序，所以Onlogn
	public static int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] arr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[i] = nums[i];
        }
        Arrays.sort(arr);
        int start = 0;
        int end = nums.length - 1;
        while(start < nums.length && arr[start] == nums[start]){
            start++;
        }
        while(end >= 0 && arr[end] == nums[end]){
            end--;
        }
        return start == nums.length ? 0 : end - start + 1;
    }
	
	//这种解法把时间复杂度降到了ON
	//首先找到递增区间的右边界，和末尾递增区间的左边界
	//但是最后结果不一定是这两个位置的差，比如数组4 11 13 14 5 6
	//虽然递增的右边界14和递减的左边界5差2个位置，但是最后结果却是5,
	//两个位置隐含的一个限制就是后面的最小值要比left要小，前面的最大值要比right要大
	//加上这个限制修正两个位置即可
	public int findUnsortedSubarray1(int[] nums) {
        int left = 0, right = nums.length - 1;

		while (left < nums.length - 1 && nums[left] <= nums[left+1])
			left++;
			
		while (right > 0 && nums[right] >= nums[right-1])
			right--;
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = left; i <= right; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		
		while (left >= 0 && nums[left] > min)
			left--;
		
		while (right < nums.length && nums[right] < max)
			right++;
		
		int result = (right - left - 1);
				
		return result < 0 ? 0 : result;
    }
}
