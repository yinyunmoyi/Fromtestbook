package leetcode;

public class leet00047_334 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//三层循环，最慢的解法，每次定位第一个数a，找比a大的数b，然再往后找比b大的c
    //如果能找到就返回true
    public boolean increasingTriplet(int[] nums) {
        int a, b;
        for(int i = 0; i < nums.length - 2; i++){
            a = nums[i];
            for(int j = i + 1; j < nums.length - 1; j++){
                b = nums[j];
                if(b > a){
                    for(int k = j + 1; k < nums.length; k++){
                        if(nums[k] > b){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
  //改进的算法，把复杂度降低到了ON方，这是由第一种算法改编而成
    //第一种算法有一些计算冗余，如果在遍历到j时就知道后面有数比j大的话
    //就能避免一层循环，所以用一个布尔数组来记录每一个位置后面有没有
    //数比该位置大，首先填充该数组
    //从后向前遍历记录最大值，如果该位置的数大于最大值就说明后面没有比他大的
    //得到数组之后，借助该数组就能使循环加速
    public boolean increasingTriplet1(int[] nums) {
        boolean[] flag = new boolean[nums.length];
        int max = Integer.MIN_VALUE;
        for(int j = nums.length - 1; j >= 0; j--){
            if(nums[j] < max){
                flag[j] = true;
            }else{
                max = nums[j];
            }
        }

        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                if(nums[j] > nums[i] && flag[j]){
                    return true;
                }
            }
        }
        return false;
    }
    
  //一种dp算法，i位置意味该位置前有多少个连续递增数（包括自己）
    //双层小范围循环就能计算每一个dpi
    //如果某个超过2就返回true
    public boolean increasingTriplet2(int[] nums) {
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if(dp[i] >= 3){
                return true;
            }
        }
        return false;
    }
    
  //最优算法，无论是时间复杂度和空间复杂度都降低到了最大
    //想象一个三层门，如果第一个元素进来了比第一道门小就会取代第一个数
    //如果它比第一道门大比第二道门小就会取代第二个数
    //如果它连续大于两个数那么就一定形成了一个顺序的3连升
    public boolean increasingTriplet3(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= first){
                first = nums[i];
            }else if(nums[i] <= second){
                second = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }
    
  //一个ON的算法，建立两个数组，一个是i位置代表0到i最小的数
    //另一个i位置代表末尾到i最大的数
    //如果某个数满足介于i位置这两个数之间就说明组成递增
    public boolean increasingTriplet4(int[] nums) {
        int[] big = new int[nums.length];
        int[] small = new int[nums.length];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            small[i] = min;
        }
        int max = Integer.MIN_VALUE;
        for(int i = nums.length - 1; i >= 0; i--){
            max = Math.max(max, nums[i]);
            big[i] = max;
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > small[i] && nums[i] < big[i]){
                return true;
            }
        }
        return false;
    }
    
    
}
