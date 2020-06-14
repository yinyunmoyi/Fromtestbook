package leetcode;

public class leet00002_1013 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Given an array A of integers, return true if and only if we can 
	//partition the array into three non-empty parts with equal sums.

	//Formally, we can partition the array if we can find indexes i+1 < 
	//j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] 
	//== A[j] + A[j-1] + ... + A[A.length - 1])
	
	
	
	
	//题意要求分成三个和相同的数组，在和确定之后只要找到首位两个和为sum/3的数组即可
	//注意数组的边界问题即可
	public boolean canThreePartsEqualSum(int[] A) {
        int totalSum = 0;
        int leftSum = 0;
        int rightSum = 0;
        int start = 0;
        for (int a : A) totalSum += a;
        while (start < A.length) {
            leftSum += A[start];
            totalSum -= A[start];
            if (leftSum * 2 == totalSum) break;
            start++;
        }
        if (totalSum == 0) return false;
        for (int i=A.length-1;i>start;i--) {
            totalSum -= A[i];
            rightSum += A[i];
            if (totalSum == rightSum && rightSum == leftSum) {
                return true;
            }
      
        }
        return false;
    }
}
