package leetcode;

public class leet00059_698 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//常规递归方案，将等分的target和每次的初值和位置传入递归中，并用一个布尔数组来记录是否访问过
    //如果分组数k减到了1，就直接返回true，因为剩下的数一定能凑够
    //如果累加值到了target，直接向下递归，累加值清0，
    //当遍历所有可能时先把标记记为true，然后向下递归，然后把标记记为false
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0 || k > nums.length){
            return false;
        }
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % k != 0){
            return false;
        }
        return canPartitionKSubsets(nums, k, 0, 0, sum / k, new boolean[nums.length]);
    }
    
     private boolean canPartitionKSubsets(int[] nums, int k, int posi, int nowVal, int target, boolean[] flag){
        if(k == 1){
            return true;
        }
        if(nowVal == target){
            return canPartitionKSubsets(nums, k - 1, 0, 0, target, flag);
        }
        for(int i = posi; i < nums.length; i++){
            if(flag[i]){
                continue;
            }
            flag[i] = true;
            if(canPartitionKSubsets(nums, k, i + 1, nowVal + nums[i], target, flag)){
                return true;
            }
            flag[i] = false;
        }
        return false;
    }
     
   //加了一个限制条件，如果nowVal大于target，就说明这个分支不可能有满足操作的解了
     //因为题里限制条件就是所有数都是正数
     public boolean canPartitionKSubsets1(int[] nums, int k) {
         if(nums == null || nums.length == 0 || k <= 0 || k > nums.length){
             return false;
         }
         int sum = 0;
         for(int i : nums){
             sum += i;
         }
         if(sum % k != 0){
             return false;
         }
         return canPartitionKSubsets1(nums, k, 0, 0, sum / k, new boolean[nums.length]);
     }
     
      private boolean canPartitionKSubsets1(int[] nums, int k, int posi, int nowVal, int target, boolean[] flag){
         if(k == 1){
             return true;
         }
         if(nowVal > target){
             return false;
         }
         if(nowVal == target){
             return canPartitionKSubsets(nums, k - 1, 0, 0, target, flag);
         }
         for(int i = posi; i < nums.length; i++){
             if(flag[i]){
                 continue;
             }
             flag[i] = true;
             if(canPartitionKSubsets(nums, k, i + 1, nowVal + nums[i], target, flag)){
                 return true;
             }
             flag[i] = false;
         }
         return false;
     }

}
