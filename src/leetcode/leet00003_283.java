package leetcode;

public class leet00003_283 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//Given an array nums, write a function to move all 0's 
	//to the end of it while maintaining the relative order of the non-zero elements.
	
	//为了保持相对顺序用了另一个数组来装非0元素，然后再拷贝回原来的数组
	public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        int[] arr = new int[nums.length];
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                arr[k++] = nums[i];
            }
        }
        for(int i = 0; i < arr.length; i++){
            nums[i] = arr[i];
        }
    }
	
	//改良后，根本无需申请新数组，因为有效的数字不会被覆盖
	public void moveZeroes1(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        int k = 0;
        for(int i = 0; i < nums.length; i++){
           if(nums[i] != 0){
                nums[k++] = nums[i];
            }
        }
        while(k < nums.length){
            nums[k++] = 0;
        }
    }
}
