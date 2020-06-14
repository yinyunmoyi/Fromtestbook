package leetcode;

public class leet00034_189 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//设计反转方法，并传入不同的参数多次调用反转方法
    
    public void rotate(int[] nums, int k) {
       if(nums == null || nums.length == 0 || k == 0){
            return;
        }
        while(k > nums.length){
            k -= nums.length;
        }
        rotate(nums, 0, nums.length - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, nums.length - 1);
    }
    
    private void rotate(int[] arr, int start, int end){
        while(start <= end){
            int num = arr[start];
            arr[start] = arr[end];
            arr[end] = num;
            start++;end--;
        }
    }
}
