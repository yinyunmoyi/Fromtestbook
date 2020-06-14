package basicS;

public class Jzoffer84 {

	//一个数组有正有负，求数组子序列和的最大值
	
	//手算一遍就能找到规律，每次累积和都更新最大和，然后如果累积和变成负数就将其归零
	public int FindGreatestSumOfSubArray(int[] array) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            sum += array[i];
            if(sum > maxSum){
                maxSum = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxSum;
    }
}
