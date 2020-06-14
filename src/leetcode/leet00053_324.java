package leetcode;
import java.util.*;

public class leet00053_324 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//这个算法是Onlogn的时间复杂度
    //首先将数组排序，排序后取到中位数的位置
    //开始填充数组，依次从前半部分的末尾和后半部分的末尾取数即可
    //不能前后两个指针运动，因为如果中间存在一些相等的值那么最后就会
    //不满足条件
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] arr = new int[nums.length];
        int mid = (nums.length - 1)/2, end = nums.length - 1, k = 0;
        for(int i = 0; i < nums.length; i++){
            arr[k++] = ((i&1) == 0) ? nums[mid--] : nums[end--];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = arr[i];
        }
    }
    
  //一种ON的解法，首先基于BFPRT来计算中位数的大小
    //然后开始进行排序，把大的放前面，小的放后面
    //排序的过程中，把相关的映射都替换成newIndex方法
    //该方法可以转换映射，把原来0 1 2 3 4 5 6映射到0-1 1-3 2-4 3-0 4-2 5-4 6-6
    //这样就可以保证大小完全均匀铺开，不会出现中位数相同的数相邻的情况
    //这个映射的构造要基于绘图，而size|1 代表比size稍大的一个奇数
    public void wiggleSort1(int[] nums) {
        int median = getMid(nums);

        int start = 0, k = 0, end = nums.length - 1;

        while (k <= end) {
            if (nums[newIndex(k,nums.length)] > median) {
                swap(nums, newIndex(start++,nums.length), newIndex(k++,nums.length));
            }
            else if (nums[newIndex(k,nums.length)] < median) {
                swap(nums, newIndex(end--,nums.length), newIndex(k,nums.length));
            }
            else {
                k++;
            }
        }
    }
    
     private void swap(int[] arr, int start, int end){
        int num = arr[start];
        arr[start] = arr[end];
        arr[end] = num;
    }
    
    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
    
    private int getMid(int[] nums){
        int[] arr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[i] = nums[i];
        }
        Arrays.sort(arr);
        return arr[(arr.length)/2];
    }
}
