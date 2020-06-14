package leetcode;

public class leet00045_162 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//初始算法，把第一个位置和最后一个位置在代码里特殊处理
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        for(int i = 0; i < nums.length; i++){
            if(i == 0 && nums[i] > nums[i + 1]){
                return i;
            }else if(i == nums.length - 1 && nums[i] > nums[i - 1]){
                return i;
            }else if(i > 0 && i < nums.length - 1 && nums[i] > nums[i - 1] && nums[i] > nums[i + 1]){
                return i;
            }
        }
        return -1;
    }
    
  //用system类的静态方法arraycopy来完成数组的扩充，然后把头尾值设置最小值
    //这样就不用在代码里特殊处理了
    public int findPeakElement1(int[] nums) {
        if (nums.length == 1) return 0;
        int[] newNums = new int[nums.length + 2];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[0] = Integer.MIN_VALUE;
        newNums[newNums.length - 1] = Integer.MIN_VALUE;
        for (int i = 1; i < newNums.length - 1; ++i) {
            if (newNums[i] > newNums[i - 1] && newNums[i] > newNums[i + 1]) return i - 1;
        }
        return -1;
    }
    
  //二分法，将复杂度降到logarithmic complexity.
    //如果本数比右数大，就让搜索范围变化到start到本数
    //否则变化到右数到end
    public int findPeakElement2(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start < end){
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[mid + 1]){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }
}
