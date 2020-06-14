package leetcode;

public class leet00058_309 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//首先推出递推式buy[i] = Math.max(buy[i - 1], sell[i - 2] - price);
    //和sell[i] = Math.max(sell[i - 1], buy[i - 1] + price);
    //两者分别意为第i天之前（不包括第i天以buy为最后动作的收益最大值）和第i天之前以sell为
    //最后动作的收益最大值
    //进行计算之前，首先令buy[1] = -prices[0];
    //如果不赋予buy初始化，那么buy一直是0，将无法计算
    //开始遍历最后返回sell的最后一个值即可
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }

        int length = prices.length;
        // buy[i]: max profit if the first "i" days end with a "buy" day
        int[] buy = new int[length + 1];
        // buy[i]: max profit if the first "i" days end with a "sell" day
        int[] sell = new int[length + 1];

        buy[1] = -prices[0];

        for (int i = 2; i <= length; i++) {
            int price = prices[i - 1];
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - price);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + price);
        }

        // sell[length] >= buy[length]
        return sell[length];
    }

}
