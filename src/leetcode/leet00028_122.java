package leetcode;

public class leet00028_122 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	//买卖股票问题一，只允许至多交易一次
		/*public static int maxProfit1(int[] prices) {
	        int min = Integer.MAX_VALUE;
	        int res = 0;
	        for(int i = 0; i < prices.length; i++) {
	        	min = Math.min(min, prices[i]);
	        	res = Math.max(prices[i] - min, res);
	        }
	        return res;
	    }*/
		
		//只要今天股价比昨天低就买再抛，即使出现3，4，6这样的序列其实是3买6卖收益最高
		//采用时刻买卖的策略，3买，4卖了再买，6卖，相当于3卖6卖，这样一定取得最大收益
	    public int maxProfit(int[] prices) {
	        int sum = 0;
	        for(int i = 1; i < prices.length; i++){
	            if(prices[i]>prices[i-1]) sum += prices[i] - prices[i-1];
	        }
	        return sum;
	    }
}
