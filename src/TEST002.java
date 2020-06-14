import java.util.Arrays;
import java.util.LinkedList;
//import java.util.Stack;

public class TEST002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(numTrees(4));
		// System.out.println(findTargetSumWays1(new int[] {1,1,1,1,1},3));
		//System.out.println(decodeString1("a10[bc4[n]]"));
		//System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
		System.out.println(maxProfit1(new int[] {1,2,3,0,2}));
	}

	public static int maxProfit(int[] prices) {
        return maxProfit(prices, 0, 0, true);
    }
	
	public static int maxProfit(int[] arr, int posi, int nowVal, boolean flag) {
		if(posi >= arr.length) {
			return flag ? nowVal : Integer.MIN_VALUE;
		}
		if(flag) {
			return Math.max(maxProfit(arr, posi + 1, nowVal - arr[posi], false), 
					maxProfit(arr, posi + 1, nowVal, true));
		}else {
			return Math.max(maxProfit(arr, posi + 2, nowVal + arr[posi], true),
					maxProfit(arr, posi + 1, nowVal, false));
		}
	}
	
	public static int maxProfit1(int[] prices) {
		int sum = 0;
		for(int i = 0; i < prices.length; i++) {
			sum += prices[i];
		}
		int[][] TrueMatrix = new int[2 * sum + 1][prices.length + 2];
		int[][] FalseMatrix = new int[2 * sum + 1][prices.length + 2];
		for(int i = 0; i < TrueMatrix.length; i++) {
			TrueMatrix[i][TrueMatrix[0].length - 1] = i - sum;
			TrueMatrix[i][TrueMatrix[0].length - 2] = i - sum;
			FalseMatrix[i][TrueMatrix[0].length - 1] = Integer.MIN_VALUE;
			FalseMatrix[i][TrueMatrix[0].length - 2] = Integer.MIN_VALUE;
		}
		for(int j = TrueMatrix[0].length - 3; j >= 0; j--) {
			for(int i = 0; i < TrueMatrix.length; i++) {
				TrueMatrix[i][j] = i - prices[j] >= 0 && i - prices[j] < FalseMatrix.length ?
						Math.max(TrueMatrix[i][j + 1], FalseMatrix[i - prices[j]][j + 1]) : 0;
				FalseMatrix[i][j] = i + prices[j] >= 0 && i + prices[j] < FalseMatrix.length ?
						Math.max(FalseMatrix[i][j + 1], TrueMatrix[i + prices[j]][j + 2]) : 0;
			}
		}
		return TrueMatrix[sum][0];
	}
	
	
	/*
	 * public static int maxProfit2(int[] prices) { int[] sell = new
	 * int[prices.length + 1]; int[] buy = new int[prices.length + 1]; buy[1] = -1 *
	 * prices[0]; for(int i = 1; i < ) }
	 */
	

}
